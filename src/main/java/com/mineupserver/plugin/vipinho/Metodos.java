package com.mineupserver.plugin.vipinho;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.permissions.PermissionAttachmentInfo;

import com.mineupserver.plugin.vipinho.utilidades.Db;


public class Metodos {

	private static Vipinho plugin = Vipinho.referencia;

//////////////////////////////

	public static HashMap<String, String> areaPegarMundo(){
		HashMap<String,String> retorno = new HashMap<String, String>();
		FileConfiguration config_areas = Db.pegarAreas();
		ConfigurationSection sessao_areas = Metodos.PegarConfSessao("areas", config_areas);
		Set<String> nome_areas = sessao_areas.getKeys(false);
		for(String nome_area : nome_areas){
			ConfigurationSection sessao_area = Metodos.PegarConfSessao(nome_area, sessao_areas);
			retorno.put(nome_area, sessao_area.getString("mundo"));
		}		
		return retorno;
	}

//////////////////////////////

	public static HashMap<String, double[]> areaPegarPos1(){
		HashMap<String, double[]> retorno = new HashMap<String, double[]>();
		FileConfiguration config_areas = Db.pegarAreas();
		ConfigurationSection sessao_areas = Metodos.PegarConfSessao("areas", config_areas);
		Set<String> nome_areas = sessao_areas.getKeys(false);
		for(String nome_area : nome_areas){
			ConfigurationSection sessao_area = Metodos.PegarConfSessao(nome_area, sessao_areas);
			String[] cord = sessao_area.getString("pos1").split(" ");
			double[] cords = {Double.parseDouble(cord[0]), Double.parseDouble(cord[1]), Double.parseDouble(cord[2])};
			retorno.put(nome_area, cords);
		}		
		return retorno;
	}

//////////////////////////////

	public static HashMap<String, double[]> areaPegarPos2(){
		HashMap<String, double[]> retorno = new HashMap<String, double[]>();
		FileConfiguration config_areas = Db.pegarAreas();
		ConfigurationSection sessao_areas = Metodos.PegarConfSessao("areas", config_areas);
		Set<String> nome_areas = sessao_areas.getKeys(false);
		for(String nome_area : nome_areas){
			ConfigurationSection sessao_area = Metodos.PegarConfSessao(nome_area, sessao_areas);
			String[] cord = sessao_area.getString("pos2").split(" ");
			double[] cords = {Double.parseDouble(cord[0]), Double.parseDouble(cord[1]), Double.parseDouble(cord[2])};
			retorno.put(nome_area, cords);
		}		
		return retorno;
	}

//////////////////////////////

	public static int StringParaInt(String string){
		int retorno = 0;
		try {
			retorno = Integer.parseInt (string);
		}
		catch (NumberFormatException e) {
			plugin.getLogger().info(Traducao("erro_num")+"\u001B[0m");
			e.printStackTrace();
		}
		return retorno;
	}

//////////////////////////////

	public static String msgNoVip(){
		FileConfiguration config = plugin.getConfig();
		String msg = config.getString("message-no-vip");
		String retorno;
		if(!msg.isEmpty()){
			retorno = msg;
		} else {
			retorno = Metodos.Traducao("mensagem_no_vip");
		}
		return retorno;
	}

//////////////////////////////

	public static String msgPermVipadm(){
		FileConfiguration config = plugin.getConfig();
		String msg = config.getString("message-no-vip");
		String retorno = Metodos.Traducao("mensagem_perm_vipadm");
		if(!msg.isEmpty()){
			retorno = msg;
		}
		return retorno;
	}

//////////////////////////////

	public static boolean permVerifique(CommandSender sender, String perm){
		boolean retorno = false;
		for(PermissionAttachmentInfo permi : sender.getEffectivePermissions()){
			if(permi.getPermission().equalsIgnoreCase(perm)){
				retorno = permi.getValue();
			}
			if(permi.getPermission().equalsIgnoreCase("Vipinho.*")){
				if(permi.getValue()){
					retorno = true;
				}
			}
			if(permi.getPermission().equalsIgnoreCase("*")){
				if(permi.getValue()){
					retorno = true;
				}
			}
		}
		return retorno;
	}

//////////////////////////////
	/**Retorna a lista de grupos desse Player
	 * ou retorno null se nao pertence a nenhum grupo
	 * @param player_nome
	 * @return null, ListaDeGrupos
	 */
	public static List<String> vipVerifique(String player_nome){
		ConfigurationSection grupos = Metodos.PegarConfSessao("grupos", Db.pegarGrupos());
		Set<String> nome_grupos = grupos.getKeys(false);
		List<String> retorno = null;
		for(String nome_grupo : nome_grupos){
			List<String> lista = grupos.getStringList(nome_grupo+".players");
			for(String player : lista){
				if(player_nome.equalsIgnoreCase(player)){
					if(retorno == null){
						retorno = new ArrayList<String>();
					}
					retorno.add(nome_grupo);
				}
			}
		}
		return retorno;
	}

//////////////////////////////

	public static List<String> gruposVerifique(String player_nome){
		ConfigurationSection grupos = Metodos.PegarConfSessao("grupos", Db.pegarGrupos());
		Set<String> nome_grupos = grupos.getKeys(false);
		List<String> retorno = new ArrayList<String>();
		for(String nome_grupo : nome_grupos){
			ConfigurationSection grupo = Metodos.PegarConfSessao(nome_grupo, grupos);
			List<String> lista = grupo.getStringList("players");
			for(String player : lista){
				if(player_nome.equalsIgnoreCase(player)){
					retorno.add(nome_grupo);
				}
			}
		}
		return retorno;
	}

//////////////////////////////

	public static HashMap<String, List<String>> areasVerifique(){
		HashMap<String, List<String>> retorno = new HashMap<String, List<String>>();
		ConfigurationSection grupos = Metodos.PegarConfSessao("grupos", Db.pegarGrupos());
		Set<String> nome_grupos = grupos.getKeys(false);
		for(String nome_grupo : nome_grupos){
			ConfigurationSection grupo = Metodos.PegarConfSessao(nome_grupo, grupos);
			List<String> lista_players = grupo.getStringList("players");
			for(String player : lista_players){
				List<String> areas = new ArrayList<String>();
				if(retorno.get(player) != null){
					areas.addAll(retorno.get(player));
				}
				areas.addAll(grupo.getStringList("areas"));
				retorno.put(player, areas);
			}
		}
		return retorno;
	}

//////////////////////////////

	public static List<String> areasVerifiqueTeste(String player_name){
		List<String> retorno = new ArrayList<String>();
		ConfigurationSection grupos = Metodos.PegarConfSessao("grupos", Db.pegarGrupos());
		Set<String> nome_grupos = grupos.getKeys(false);
		for(String nome_grupo : nome_grupos){
			ConfigurationSection grupo = Metodos.PegarConfSessao(nome_grupo, grupos);
			List<String> lista_players = grupo.getStringList("players");
			for(String player : lista_players){
				if(player.equalsIgnoreCase(player_name)){
					retorno.addAll(grupo.getStringList("areas"));
				}
			}
		}
		return retorno;
	}

//////////////////////////////

	public static boolean slotsVerifique(PlayerInventory inventario){
		int cont = 36;
		ItemStack[] slots = inventario.getContents();
		for(ItemStack slot : slots){
			if(slot != null){
				cont --;
			}
		}
		if(cont < 1){
			return false;
		}
		else{
			return true;
		}
	}

//////////////////////////////

	public static Properties pegarProperties(String name){
		Properties retorno = new Properties();
		InputStream stream = plugin.getResource(name);
		if(stream == null){return null;}
		try {
			retorno.load(stream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return retorno;
	}

//////////////////////////////

	public static String Traducao(String key, String[] replaces){
		String[] partes = replaces;
		String retorno = null;
		Properties idioma = Db.traducao;
		if(idioma.containsKey(key)){
			retorno = idioma.getProperty(key);
		}
		else{
			plugin.getLogger().info(Traducao("erro_idioma", key)+"\u001B[0m");
			return "Server error in translation";
		}
		for(int cont = 1; cont <= partes.length; cont++){
			retorno = retorno.replace("[["+cont+"]]", partes[cont-1]);
		}
		return retorno;
	}

//////////////////////////////

	public static String Traducao(String key, String replace){
		String[] partes = {replace};
		String retorno = null;
		Properties idioma = Db.traducao;
		if(idioma.containsKey(key)){
			retorno = idioma.getProperty(key);
		}
		else if(key.equalsIgnoreCase("erro_idioma")){
			plugin.getLogger().info("\u001B[36m!!The plugin could not find the translation of this translation error!!"
					+ "(\u001B[31mO plugin falhou ao tentar acessar a traducao de:\u001B[33m "+replace+"\u001B[36m)\u001B[0m");
			return "Server error in translation";
		}
		else{
			plugin.getLogger().info(Traducao("erro_idioma", key)+"\u001B[0m");
			return "Server error in translation";
		}
		for(int cont = 1; cont <= partes.length; cont++){
			retorno = retorno.replace("[["+cont+"]]", partes[cont-1]);
		}
		return retorno;
	}

//////////////////////////////

	public static String Traducao(String key){
		String retorno = null;
		Properties idioma = Db.traducao;
		if(idioma.containsKey(key)){
			retorno = idioma.getProperty(key);
		}
		else{
			plugin.getLogger().info(Traducao("erro_idioma", key)+"\u001B[0m");
			return "Server error in translation";
		}
		return retorno;
	}

//////////////////////////////

	public static Properties pegarIdioma(){
		String language = plugin.getConfig().getString("language");
		Properties idioma = Metodos.pegarProperties("com/mineupserver/resources/languages/"+language+".properties");
		return idioma;
	}

//////////////////////////////

	public static boolean sePlayerRemetente(CommandSender sender){
		if (sender instanceof Player) {
			return true;
		} 
		else{
			return false;
		}
	}

//////////////////////////////

	public static void addDropItem(ItemStack item, Player player){
		PlayerInventory inventario = player.getInventory();
		boolean teste = slotsVerifique(inventario);
		if(teste == true){
			inventario.addItem(item);
		}
		else{
			int cont = 0;
			int qnt_sobra = 0;
			while(cont < 36){
				ItemStack inv_item = inventario.getContents()[cont];
				if(inv_item.getType() == item.getType()){
					int qnt = inv_item.getMaxStackSize() - inv_item.getAmount();
					if(qnt > 0){
						qnt_sobra += qnt;
					}
				}
				cont ++;
			}
			int qnt_drop = item.getAmount() - qnt_sobra;
			inventario.addItem(item);
			if(qnt_drop > 0){
				ItemStack item_drop = item;
				item_drop.setAmount(qnt_drop);
				Location local = player.getLocation();
				player.getWorld().dropItemNaturally(local, item_drop);
			}
		}
	}

//////////////////////////////

    public static void salvarTeste(String string){
    	FileConfiguration yaml = new YamlConfiguration();
		File file = new File(plugin.getDataFolder()+"/"+"ArquivoDeSaida.yml");
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		yaml.set("Saida", string);
		try {
			yaml.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
//////////////////////////////

    public static void salvarKits(){
		File file = new File(plugin.getDataFolder()+"/"+"kits.yml");
		if(!file.exists()){
			plugin.saveResource("kits.yml", false);
		}
    }
    
//////////////////////////////

	public static FileConfiguration PegarConfig(String Nome){
		FileConfiguration yaml = new YamlConfiguration();
		File file = new File(plugin.getDataFolder()+"/"+Nome);
		if(!file.exists()){
			File dados = new File(plugin.getDataFolder()+"/dados");
			if(!dados.exists()){
				dados.mkdir();
			}
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			yaml.load(file);
		} catch (FileNotFoundException e) {
			plugin.getLogger().info(""+e);
		} catch (IOException e) {
			plugin.getLogger().info(""+e);
		} catch (InvalidConfigurationException e) {
			plugin.getLogger().info(""+e);
		}
		return yaml;
	}

//////////////////////////////

	public static ConfigurationSection PegarConfSessao(String sessao, ConfigurationSection yaml){
		if(yaml.get(sessao) != null){
			ConfigurationSection retorno = yaml.getConfigurationSection(sessao);
			return retorno;
		} else{
			yaml.createSection(sessao);
			ConfigurationSection retorno = yaml.getConfigurationSection(sessao);
			return retorno;
		}
	}

//////////////////////////////

	public static void enviaTeste(String teste){
		plugin.getServer().getPlayer("Manike").sendMessage(teste);
	}
	public static void enviaTeste(String teste, String player){
		plugin.getServer().getPlayer(player).sendMessage(teste);
	}

//////////////////////////////
	
	public static void salvarConfig(String Nome, FileConfiguration Yaml){
		File file = new File(plugin.getDataFolder()+"/"+Nome);
		try {
			Yaml.save(file);
		} catch (IOException e) {
			plugin.getLogger().info(""+e);
		}
	}

//////////////////////////////

	public static void copiarPadroes(String Nome, boolean subkey){
		
		FileConfiguration yaml_jar = new YamlConfiguration();
		InputStream stream = plugin.getResource(Nome);
		
		try {
			yaml_jar.load(stream);
		} catch (IOException e) {
			plugin.getLogger().info(""+e);
		} catch (InvalidConfigurationException e) {
			plugin.getLogger().info(""+e);
		}
		
		FileConfiguration yaml_pasta = PegarConfig(Nome);
		Set<String> keys = yaml_jar.getKeys(true);
		
		for (String key : keys){
			if(yaml_pasta.get(key) == null){
			yaml_pasta.set(key, yaml_jar.get(key));
			}
		}
		salvarConfig(Nome, yaml_pasta);
	}

	public static String pegarMsgBuger() {
		String teste = plugin.getConfig().getString("msg-bug-area");
		return teste;		
	}

	public static int obterIdGrupo() {
		//FileConfiguration grupos = Db.pegarGrupos();
		//PegarConfSessao("", yaml)
		
		return 3;
	}
}