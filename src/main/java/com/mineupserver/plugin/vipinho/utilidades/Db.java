package com.mineupserver.plugin.vipinho.utilidades;
import java.io.IOException;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.mineupserver.plugin.vipinho.Metodos;
import com.mineupserver.plugin.vipinho.Vipinho;

public class Db {
	static Vipinho plugin = Vipinho.referencia;
	//HashMaps
		public static HashMap<String, Location> pos_pri = new HashMap<String,Location>();//1
		public static HashMap<String, String> pos_pri_span = new HashMap<String,String>();//1
		///////////////////////////////////////////////////////////////////////
		public static HashMap<String, Location> pos_seg = new HashMap<String,Location>();//2
		public static HashMap<String, String> pos_seg_span = new HashMap<String,String>();//2
	/////////
	//Sell//
		public static HashMap<String, String> sel_grupo = new HashMap<String,String>();

		public static Properties traducao;
		
	////////
		public static String mensagem_no_vip;

		public static boolean machadoAtivo;

		public static HashMap<String, Long> delay_kit = new HashMap<String, Long>();

		public static HashMap<String, List<String>> cmds = new HashMap<String, List<String>>();
		public static HashMap<String, String> cmd_help = new HashMap<String, String>();
		
		
	//areas//
		public static HashMap<String, Boolean> radar = new HashMap<String,Boolean>();
		public static HashMap<String, List<String>> areas = new HashMap<String, List<String>>();
		
		public static HashMap<String, String> mundo = new HashMap<String,String>();
		public static HashMap<String, double[]> min = new HashMap<String,double[]>();
		public static HashMap<String, double[]> max = new HashMap<String,double[]>();
		
	////////
	//Configs//
		public static FileConfiguration config_grupos = null;
		public static void recarregarGrupos(){config_grupos = Metodos.PegarConfig("grupos.yml");}
		
		public static FileConfiguration config_kits = null;
		public static void recarregarKits(){config_kits = Metodos.PegarConfig("kits.yml");}
		
		public static FileConfiguration config_areas = null;
		public static void recarregarAreas(){config_areas = Metodos.PegarConfig("dados/areas.yml");}
		
		public static FileConfiguration config_teleportes = null;
		public static void recarregarTeleportes(){config_teleportes = Metodos.PegarConfig("dados/teleportes.yml");}
	///////////
		
/////////Recarregar/////////
		public static void recarregar(){
			cmds.clear();
			cmd_help.clear();
			plugin.reloadConfig();
			atualizaTraducao();
			atualizaTraducaoCmd();
			recarregarGrupos();
			recarregarKits();
			recarregarAreas();
			recarregarTeleportes();
			atualizarAreas();
			AddCmdsPadroes.AddComandosPadroes();
			AddCmdsPadroes.colocarTraducaoNaAjuda();
			mensagem_no_vip = Metodos.msgNoVip();
			machadoAtivo = plugin.getConfig().getBoolean("axe");
		}
		
////Metodos////
		public static void atualizaTraducao(){
			String language = plugin.getConfig().getString("language");
			Properties idioma = Metodos.pegarProperties("com/mineupserver/plugin/vipinho/resources/languages/"+language+".properties");
			if(idioma == null){
				plugin.getLogger().info("\u001B[31mThe translation cited as \u001B[33m"+plugin.getConfig().getString("language")+"\u001B[31m probably does not exist. for this reason the translation went to \u001B[33mPortuguese-BR\u001B[31m\u001B[0m");
				Properties idioma_br = Metodos.pegarProperties("com/mineupserver/plugin/vipinho/resources/languages/br.properties");
				if(idioma_br == null){
					traducao = new Properties();					
				} else {
					traducao = idioma_br;
				}
			} else {
				traducao = idioma;
			}
		}
		public static void atualizaTraducaoCmd(){
			String endereco = plugin.getClass().getResource("/plugin.yml").getPath();
			endereco = endereco.replace("!/plugin.yml", "");
			endereco = endereco.replace("file:/", "");
			endereco = endereco.replace("%20", " ");
			ZipFile zip = null;
			try {
				zip = new ZipFile(endereco);
			} catch (IOException e) {
				e.printStackTrace();
			}
			Enumeration<? extends ZipEntry> entries = zip.entries();
			while(entries.hasMoreElements()){
				ZipEntry zipEntry = (ZipEntry) entries.nextElement();  
				String name = zipEntry.getName();
				if(name.contains("com/mineupserver/plugin/vipinho/resources/languages/commands/")){
					Properties cmds_file = new Properties();
					try {
						cmds_file.load(plugin.getResource(name));
					} catch (IOException e) {
						e.printStackTrace();
					}
					Set<Object> cmds_keys = cmds_file.keySet();
					for(Object cmd_nome : cmds_keys){
						String comando = cmds_file.getProperty(cmd_nome+"");
						if(cmds.containsKey(cmd_nome)){
							List<String> cmds_traduzidos = cmds.get(cmd_nome);
							if(!cmds_traduzidos.contains(comando)){
								cmds_traduzidos.add(comando);
								cmds.put(cmd_nome+"", cmds_traduzidos);
							}
						}
					}
					String nome_traducao = name+"";
					nome_traducao = nome_traducao.replace("com/mineupserver/plugin/vipinho/resources/languages/commands/", "");
					nome_traducao = nome_traducao.replace("_cmds.properties", "");
					if(nome_traducao.equalsIgnoreCase(plugin.getConfig().getString("language"))){
						for(Object cmd_nome : cmds_keys){
							Db.cmd_help.put(cmd_nome+"", cmds_file.getProperty(cmd_nome+""));
						}
					}
				}
            }
		}
		
		public static boolean cmd(String nome, String cmd_vefique){
			List<String> lista_cmds = Db.cmds.get(nome);
			for(String cmd : lista_cmds){
				if(cmd.equalsIgnoreCase(cmd_vefique)){
					return true;
				}
			}
			return false;
		}

		public static void atualizarAreas(){
			HashMap<String, double[]> pos1 = new HashMap<String,double[]>();
			HashMap<String, double[]> pos2 = new HashMap<String,double[]>();
			mundo.clear();
			min.clear();
			max.clear();
			mundo.putAll(Metodos.areaPegarMundo());
			areas.putAll(Metodos.areasVerifique());
			pos1.putAll(Metodos.areaPegarPos1());
			pos2.putAll(Metodos.areaPegarPos2());
			for(String key_nome_area : mundo.keySet()){
				double[] pos1_loc = pos1.get(key_nome_area);
				double[] pos2_loc = pos2.get(key_nome_area);
				double x1 = (int) pos1_loc[0]; double y1 = (int) pos1_loc[1]; double z1 = (int) pos1_loc[2];
				double x2 = (int) pos2_loc[0]; double y2 = (int) pos2_loc[1]; double z2 = (int) pos2_loc[2];
				double minx; double miny; double minz;
				double maxx; double maxy; double maxz;
				if(x1 < x2){minx = x1; maxx = x2+0.999999999;}else{minx = x2; maxx = x1+0.999999999;}
				if(y1 < y2){miny = y1; maxy = y2+0.999999999;}  else{miny = y2; maxy = y1+0.999999999;}
				if(z1 < z2){minz = z1; maxz = z2+0.999999999;}else{minz = z2; maxz = z1+0.999999999;}
				double[] min_loc = {minx, miny, minz};
				double[] max_loc = {maxx, maxy, maxz};
				min.put(key_nome_area, min_loc);
				max.put(key_nome_area, max_loc);
			}
			
		}
		
		public static void limparMachado(){
			pos_pri.clear();
			pos_pri_span.clear();
			pos_seg.clear();
			pos_seg_span.clear();
		}
		
		public static FileConfiguration pegarGrupos(){
			if(config_grupos == null){
				config_grupos = Metodos.PegarConfig("grupos.yml");
				return config_grupos;
			} else {
				return config_grupos;
			}
		}
		public static FileConfiguration pegarKits(){
			if(config_kits == null){
				config_kits = Metodos.PegarConfig("kits.yml");
				return config_kits;
			} else {
				return config_kits;
			}
		}
		public static FileConfiguration pegarAreas(){
			if(config_areas == null){
				config_areas = Metodos.PegarConfig("dados/areas.yml");
				return config_areas;
			} else {
				return config_areas;
			}
		}
		public static FileConfiguration pegarTeleportes(){
			if(config_teleportes == null){
				config_teleportes= Metodos.PegarConfig("dados/teleportes.yml");
				return config_teleportes;
			} else {
				return config_teleportes;
			}
		}
		public static void carregarDelayKit(){
			FileConfiguration config_delay = Metodos.PegarConfig("dados/delay_kits.delay");
			Set<String> keys = config_delay.getKeys(false);
			for(String key : keys){
				String valor = config_delay.getString(key);
				long delay = 0;
				try {
					delay = Long.parseLong(valor);
				}
				catch (NumberFormatException e) {
					plugin.getLogger().info(Metodos.Traducao("erro_kit_delay")+"\u001B[0m");
					e.printStackTrace();
					plugin.getLogger().info(Metodos.Traducao("erro_kit_delay")+"\u001B[0m");
				}
				Db.delay_kit.put(key, delay);
			}
		}
		public static void salvarDelayKit(){
			ConfigurationSection grupos = Metodos.PegarConfSessao("grupos", Db.pegarGrupos());
			
	        Calendar cal = Calendar.getInstance();
	        long agora = cal.getTimeInMillis()/1000;
			Set<String> keys = Db.delay_kit.keySet();
			FileConfiguration delays = new YamlConfiguration();
			for(String key : keys){
				long[] delay = null;
				for(String nome_grupo : Metodos.gruposVerifique(key)){
					long kit_delay = grupos.getLong(nome_grupo+".kit_delay");
					long prioridade = grupos.getLong(nome_grupo+".prioridade");
					if(delay == null){
						long[] delay1 = {kit_delay, prioridade};
						delay = delay1;
					} else {
						if(delay[1] < prioridade){
							long[] delay1 = {kit_delay, prioridade};
							delay = delay1;
						}
					}
				}
				long espera = agora - Db.delay_kit.get(key);
				if(delay[0] > espera){
					delays.set(key, Db.delay_kit.get(key));
				}
			}
			Metodos.salvarConfig("dados/delay_kits.delay", delays);
		}
}
