package com.mineupserver.plugin.vipinho.utilidades.sql;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;

import com.mineupserver.plugin.vipinho.Metodos;
import com.mineupserver.plugin.vipinho.Vipinho;

public class MySQL{
	private static Vipinho plugin = Vipinho.referencia;
	private static boolean iniciado = false;
	private static Timer timer = new Timer ();
	
	private static String url;
	private static String user ;
	private static String password;
	
	private static int mode;
	static Connection conexao;

	static long auto_update_time;
	static long ultimo_update = 0;
	static File file_last_db_update = null;
	static String nome_tabela;
	static String[] colunas_tabela = {"id", "nick", "vip_id", "validity", "data_activation", "data_validity", "status"};

	public static void inicializar(){
		if(iniciado){
			return;
		} 
		FileConfiguration config = plugin.getConfig();
		ConfigurationSection mysqlConf = Metodos.PegarConfSessao("mysql", config);
		mysqlConf.contains("enabled");
		if(mysqlConf.isBoolean("enabled")){
			if(mysqlConf.getBoolean("enabled")){
				if(mysqlConf.contains("host") && mysqlConf.contains("port") && mysqlConf.contains("user") && mysqlConf.contains("password") && mysqlConf.contains("database")){
					String host = mysqlConf.getString("host");
					String port = mysqlConf.getString("port");
					String database = mysqlConf.getString("database");
					user = mysqlConf.getString("user");
					password = mysqlConf.getString("password");
					if(mysqlConf.contains("mode")){
						mode = mysqlConf.getInt("mode");
					} else {
						mode = 0;						
					}
					String comando = "?zeroDateTimeBehavior=convertToNull&autoReconnect=true&characterEncoding=UTF-8&characterSetResults=UTF-8";
					url = "jdbc:mysql://" + host +":"+port + "/" + database+comando;
					iniciado = true;
					conexao = AbrirConexaoMySQL(url, user, password);
					String last_db_update = null;
					if(mysqlConf.contains("auto_update_time")){
						if(mysqlConf.isInt("auto_update_time") || mysqlConf.isLong("auto_update_time") || mysqlConf.isDouble("auto_update_time") ){
							auto_update_time = mysqlConf.getLong("auto_update_time");
						}else{
							auto_update_time = 0;
						}
					}else{
						auto_update_time = 0;
					}
					if(mysqlConf.contains("file-last_db_update")){
						last_db_update = mysqlConf.getString("last_db_update");
					}
					if(last_db_update != null && !last_db_update.isEmpty()){
						file_last_db_update = new File(plugin.getDataFolder()+"/"+last_db_update);
					}
					boolean ok = false;
					if(mysqlConf.contains("table.name") && mysqlConf.contains("table.columns")){
						nome_tabela = mysqlConf.getString("table.name");
						if(mode == 1){
							if(		mysqlConf.contains("table.columns.id") && mysqlConf.contains("table.columns.nick") && mysqlConf.contains("table.columns.vip_id") &&
									mysqlConf.contains("table.columns.validity") && mysqlConf.contains("table.columns.data_activation") && mysqlConf.contains("table.columns.data_validity")
									){
								colunas_tabela[0] = mysqlConf.getString("table.columns.id");
								colunas_tabela[1] = mysqlConf.getString("table.columns.nick");
								colunas_tabela[2] = mysqlConf.getString("table.columns.vip_id");
								colunas_tabela[3] = mysqlConf.getString("table.columns.validity");
								colunas_tabela[4] = mysqlConf.getString("table.columns.data_activation");
								colunas_tabela[5] = mysqlConf.getString("table.columns.data_validity");
								ok = true;
							}
						}
						if(mode == 2){
							if(		mysqlConf.contains("table.columns.id") && mysqlConf.contains("table.columns.nick") && mysqlConf.contains("table.columns.vip_id") &&
									mysqlConf.contains("table.columns.validity") && mysqlConf.contains("table.columns.data_activation") && mysqlConf.contains("table.columns.data_validity") &&
									mysqlConf.contains("table.columns.status")
									){
								colunas_tabela[0] = mysqlConf.getString("table.columns.id");
								colunas_tabela[1] = mysqlConf.getString("table.columns.nick");
								colunas_tabela[2] = mysqlConf.getString("table.columns.vip_id");
								colunas_tabela[3] = mysqlConf.getString("table.columns.validity");
								colunas_tabela[4] = mysqlConf.getString("table.columns.data_activation");
								colunas_tabela[5] = mysqlConf.getString("table.columns.data_validity");
								colunas_tabela[6] = mysqlConf.getString("table.columns.status");
								ok = true;
							}
						} else if (mysqlConf.contains("table.columns.id") && mysqlConf.contains("table.columns.nick") && mysqlConf.contains("table.columns.vip_id")){
							colunas_tabela[0] = mysqlConf.getString("table.columns.id");
							colunas_tabela[1] = mysqlConf.getString("table.columns.nick");
							colunas_tabela[2] = mysqlConf.getString("table.columns.vip_id");
							ok = true;
							mode = 0;
						}
					}
					if(ok){
						//TT   TimerTask
						TimerTask TT = new TimerTask() {
							
							@Override
							public void run() {
								boolean ok = false;
								long agora = new Date().getTime()/1000;
								if(auto_update_time > 0 && agora - ultimo_update >= auto_update_time){
										ok = true;
								}else if(file_last_db_update != null) {
									if(file_last_db_update.lastModified()/1000 >= agora){
										ok = true;
									}
								}
								if(ok){
									Connection sql = PegarConexao();
									if(sql != null){
										try {
											PreparedStatement prepare = sql.prepareStatement("SELECT * FROM "+nome_tabela);
											prepare.executeQuery();
											ResultSet resultado = prepare.getResultSet();
											PlayerVip.Limpar();
											while(resultado.next()){
												String id = resultado.getString(colunas_tabela[0]);
												String Nick = resultado.getString(colunas_tabela[1]);
												int VIP_id = resultado.getInt(colunas_tabela[2]);
												long DATA_ativacao = 0;
												long DATA_validade = 0;
												if(mode == 1 || mode == 2){
													Timestamp DATA_ativacao_time = resultado.getTimestamp(colunas_tabela[4]);
													if(DATA_ativacao_time != null){
														DATA_ativacao = DATA_ativacao_time.getTime();
													} 
													Timestamp DATA_validade_time = resultado.getTimestamp(colunas_tabela[5]);
													if(DATA_validade_time != null){
														DATA_validade = DATA_validade_time.getTime();
													} 
												}
													
												if(mode == 1){
													new PlayerVip(id, Nick, VIP_id, DATA_ativacao, DATA_validade);
												}
												if(mode == 2){
													if(resultado.getString("status").equalsIgnoreCase("pago")){
														new PlayerVip(id, Nick, VIP_id, DATA_ativacao, DATA_validade);	
													}
												}else {
													new PlayerVip(id, Nick, VIP_id, DATA_ativacao, DATA_validade);			
												}
											}
										} catch (SQLException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
								}
								
							}
						};
						//TT   TimerTask
						timer.schedule(TT, 0, 1000);
					}
					
					
				}
				
			}			
		}
		
	}
	public static void Finalizar(){
		timer.cancel();
	}
	private static Connection AbrirConexaoMySQL(String url, String user, String password){
		Connection connection = null; 
		plugin.getLogger().info("Conectando com o Banco de Dados.");
		try {
			connection = DriverManager.getConnection(url, user, password);
			if (connection != null) {
				plugin.getLogger().info("Conectado com sucesso!");
			} else {
				plugin.getLogger().info("Não foi possivel realizar conexão");
			}
            return connection;
		}catch (SQLException e) {//Não conseguindo se conectar ao banco
			plugin.getLogger().info("Nao foi possivel conectar ao Banco de Dados.");
			return null;
		}
		
	}
	private static boolean Status(){
		if(conexao != null){
			return true;
		} else {
			return false;
		}
	}
	private static Connection AbrirConexao(){
		plugin.getLogger().info("Abrindo conexao com o Banco de Dados.");
		if(!Status()){
			return AbrirConexaoMySQL(url, user, password);
		} else {
			plugin.getLogger().info("Ja tem uma conexao aberta.");
			return null;
		}
	}
	private static boolean FecharConexao() {
		plugin.getLogger().info("Fechando conexao com o Banco de Dados.");
		if(Status()){
			try {
				conexao.close();
				return true;
			} catch (SQLException e) {
				return false;
			}
		} else {
			return false;
		}
	}
	private static Connection ReiniciarConexao() {
		plugin.getLogger().info("Reiniciando conexao com o Banco de Dados.");
		FecharConexao();
		return AbrirConexao();
	}
	private static Connection PegarConexao(){
		if(Status()){
			return conexao;
		} else{
			plugin.getLogger().info("Nao tem nenhuma conexao aberta.");
			return null;
		}
	}

}
