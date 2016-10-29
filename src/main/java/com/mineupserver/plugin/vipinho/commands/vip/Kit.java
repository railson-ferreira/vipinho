package com.mineupserver.plugin.vipinho.commands.vip;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentWrapper;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import com.mineupserver.plugin.vipinho.Metodos;
import com.mineupserver.plugin.vipinho.Vipinho;
import com.mineupserver.plugin.vipinho.utilidades.Db;

public class Kit{
	public Kit(Command cmd, String label, String[] args, Vipinho plugin, Player player){
		HashMap<String, String> cmdhelp = Db.cmd_help;
		boolean sucesso = false;
		//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//
		//Obter Kits
		FileConfiguration config_kits = Metodos.PegarConfig("kits.yml");
		ConfigurationSection ListaKits = Metodos.PegarConfSessao("kits", config_kits);
		//==//==//==//==//==//==//==//==//==//==//==//==//==//==//==//

		List<String> kits = new ArrayList<String>();
		ConfigurationSection grupos = Metodos.PegarConfSessao("grupos", Db.pegarGrupos());
		
		Calendar cal = Calendar.getInstance();
		long agora = cal.getTimeInMillis()/1000;
		long[] delay = null;
		for(String nome_grupo : Metodos.gruposVerifique(player.getName())){
			List<String> kits_grupo = grupos.getStringList(nome_grupo+".kits");
			long kit_delay = grupos.getLong(nome_grupo+".kit_delay");
			long prioridade = (long) grupos.getInt(nome_grupo+".prioridade");
			for(String kit_grupo : kits_grupo){
				if(ListaKits.contains(kit_grupo)){
					kits.add(kit_grupo);
				}
			}
			if(delay == null){
				long[] delay1 = {kit_delay, prioridade};
				delay = delay1;
			} else {
				if(delay[1] < prioridade){
					long[] delay1 = {kit_delay, prioridade};
					delay = delay1;
				} else {Metodos.Traducao("erro_desconhecido", "/vip "+cmdhelp.get("vip_ajuda"));}
			}
		}
		if(Db.delay_kit.containsKey(player.getName())){
			long antes = Db.delay_kit.get(player.getName());
			long espera = agora - antes;
			if(delay[0] > espera){
				long tempo = delay[0] - espera;
				long dia = 0;
				long hor = 0;
				long min = 0;
				long seg = 0;
				if(tempo > 59){
					min = (int) tempo/60;
					seg = (int) tempo%60;
					if(min > 59){
						hor = min/60;
						min = min%60;
						if(hor > 23){
							dia = hor/24;
							hor = hor%24;
						}
					}
				} else {
					seg = (int) tempo;
				}
				String dias = "";
				NumberFormat formatter = new DecimalFormat("00"); 
				String horas = formatter.format(hor);  
				String minutos = formatter.format(min);  
				String segundos = formatter.format(seg);
				if(dia > 0){
					dias = dia+" "+Metodos.Traducao("dias_e")+" ";
				}
				player.sendMessage(Metodos.Traducao("kit_delay", dias+horas+":"+minutos+":"+segundos));
				return;
			} else {
				Db.delay_kit.put(player.getName(), agora);
			}
		} else {
			Db.delay_kit.put(player.getName(), agora);
		}
		for(String kitkey : kits){
			//Obter Itens
			List<String> itens = ListaKits.getStringList(kitkey);
			for(String item : itens){
				String[] argumentos = item.split(" ");
				String[] encantos = argumentos;
				int tamanho = argumentos.length;
				int todoslocais = tamanho - 1;
				int local = 0;
				
				PlayerInventory inventario = player.getInventory();
				
				int item_id = 0;
				int quantidade = 1;
				String localidade = "0";
				
				if(local <= todoslocais){item_id = Metodos.StringParaInt(argumentos[local]);}
				local ++;
				if(local <= todoslocais){quantidade = Metodos.StringParaInt(argumentos[local]);}
				local ++;
				if(local <= todoslocais){localidade = argumentos[local];}
				local ++;
				
				@SuppressWarnings("deprecation")
				ItemStack itempack = new ItemStack(item_id, quantidade);
				//Encantar
				if(local <= todoslocais){
					String[] encanto = argumentos[local].split(":");
					int encanto_id = Metodos.StringParaInt(encanto[0]);
					int encanto_nv = 1;
					Enchantment encantamento = new EnchantmentWrapper(encanto_id);
					if(encanto.length > 1){
						encanto_nv = Metodos.StringParaInt(encanto[1]);
					}
					else{
						encanto_nv = encantamento.getMaxLevel();
					}
					itempack.addUnsafeEnchantment(encantamento, encanto_nv);
					
					encantos[0] = "";encantos[1] = "";encantos[2] = "";encantos[3] = "";
					for(String outro_encanto : encantos){
						if(outro_encanto != ""){
							String[] encantamento_outro = outro_encanto.split(":");
							int encanto_id_outro = Metodos.StringParaInt(encantamento_outro[0]);
							int encanto_nv_outro = 1;	
							Enchantment outro_encantamento = new EnchantmentWrapper(encanto_id_outro);
							if(encantamento_outro.length > 1){
								encanto_nv_outro = Metodos.StringParaInt(encantamento_outro[1]);	
							}
							else{
								encanto_nv_outro = outro_encantamento.getMaxLevel();
							}
							itempack.addUnsafeEnchantment(outro_encantamento, encanto_nv_outro);		
						}
					}
				}//fim do Encantar
				
				//dar o item
				if(localidade.equalsIgnoreCase("0")){
					Metodos.addDropItem(itempack, player);	
				} else if(localidade.equalsIgnoreCase("HAND")){
					ItemStack mao = inventario.getItemInHand();
					inventario.setItemInHand(itempack);
					if(mao !=null){
						Metodos.addDropItem(mao, player);
					}
				} else if(localidade.equalsIgnoreCase("HELMET")){
					ItemStack capacete = inventario.getHelmet();
					inventario.setHelmet(itempack);
					if(capacete !=null){
						Metodos.addDropItem(capacete, player);
					}
				} else if(localidade.equalsIgnoreCase("CHESTPLATE")){
					ItemStack peitoral = inventario.getChestplate();
					inventario.setChestplate(itempack);
					if(peitoral !=null){
						Metodos.addDropItem(peitoral, player);
					}
				} else if(localidade.equalsIgnoreCase("LEGGINGS")){
					ItemStack calcas = inventario.getLeggings();
					inventario.setLeggings(itempack);
					if(calcas !=null){
						Metodos.addDropItem(calcas, player);
					}
				} else if(localidade.equalsIgnoreCase("BOOTS")){
					ItemStack botas = inventario.getBoots();
					inventario.setBoots(itempack);
					if(botas !=null){
						Metodos.addDropItem(botas, player);
					}
				} else{
					int loc = Metodos.StringParaInt(localidade) - 1;
					if(loc < 36){
						ItemStack[] o_item = inventario.getContents();
						inventario.setItem(loc, itempack);
						if(o_item[loc] !=null){
							Metodos.addDropItem(o_item[loc], player);
						}
					}
					else{
						Metodos.addDropItem(itempack, player);
						plugin.getLogger().info(Metodos.Traducao("erro_inv_local")+"\u001B[0m");
					}
				}
				sucesso = true;
			}//fim dos itens
		}//fim dos kits	
		player.sendMessage(Metodos.Traducao("kit_sucesso"));
		if(!sucesso){player.sendMessage(Metodos.Traducao("kit_indisponivel"));}
	}
}
