package com.mineupserver.plugin.vipinho.commands.vipadm;

import java.util.HashMap;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import com.mineupserver.plugin.vipinho.Metodos;
import com.mineupserver.plugin.vipinho.Vipinho;
import com.mineupserver.plugin.vipinho.utilidades.Db;
import com.mineupserver.plugin.vipinho.utilidades.VerificarConfigs;

public class Area {
	public Area(CommandSender sender, Command cmd, String label, String[] args, Vipinho plugin){
		HashMap<String, String> cmdhelp = Db.cmd_help;
		if (args.length > 1){
			if(Db.cmd("vipadm_area_criar", args[1])){//começa "create"
				if(Metodos.sePlayerRemetente(sender)){
					if (args.length > 2){
						FileConfiguration area_config = Metodos.PegarConfig("dados/areas.yml");
						ConfigurationSection areas = Metodos.PegarConfSessao("areas", area_config);
						Set<String> nome_areas = areas.getKeys(false);
						for(String nome_area : nome_areas){
							if(args[2].equalsIgnoreCase(nome_area)){
								sender.sendMessage(Metodos.Traducao("erro_area_existente", "/vipadm "+cmdhelp.get("vipadm_area")+" "+cmdhelp.get("vipadm_area_recriar")+" <"+Metodos.Traducao("nome")+">"));
								return;
							}
						}
						Player player = (Player) sender;
						//acesar o hash
						double x1 = 0;
						double y1 = 0;
						double z1 = 0;
						String pos1 = null;
						if(Db.pos_pri.containsKey(player.getName())){
							if(Db.pos_pri.get(player.getName()) != null){
								x1 = Db.pos_pri.get(player.getName()).getX();
								y1 = Db.pos_pri.get(player.getName()).getY();
								z1 = Db.pos_pri.get(player.getName()).getZ();
								pos1 = "ok";
							}
						} else{sender.sendMessage(Metodos.Traducao("indefinida_pos1"));}
						double x2 = 0;
						double y2 = 0;
						double z2 = 0;
						String pos2 = null;
						if(Db.pos_seg.containsKey(player.getName())){
							if(Db.pos_seg.get(player.getName()) != null){
								x2 = Db.pos_seg.get(player.getName()).getX();
								y2 = Db.pos_seg.get(player.getName()).getY();
								z2 = Db.pos_seg.get(player.getName()).getZ();
								pos2 = "ok";
							}
						} else{sender.sendMessage(Metodos.Traducao("indefinida_pos2"));}
						//
						if(pos1 == "ok"){
							if(pos2 == "ok"){
								areas.set(args[2]+".mundo", player.getWorld().getName());
								areas.set(args[2]+".pos1", x1+" "+y1+" "+z1);
								areas.set(args[2]+".pos2", x2+" "+y2+" "+z2);
								Metodos.salvarConfig("dados/areas.yml", area_config);
								Db.recarregarAreas();
								Db.atualizarAreas();
								sender.sendMessage(Metodos.Traducao("sucesso_add_area", args[2]));
							}
						}
					}
					else{
						sender.sendMessage(Metodos.Traducao("erro_syntax_cmd", "/vipadm "+cmdhelp.get("vipadm_area")+" "+cmdhelp.get("vipadm_area_criar")+" <"+Metodos.Traducao("nome")+">"));
					}
				}
				else{
					sender.sendMessage(Metodos.Traducao("alerta_remetente_errado"));
				}
			}//termina "create"
			
			
			else if(Db.cmd("vipadm_area_recriar", args[1])){//começa "recreate"
				if(Metodos.sePlayerRemetente(sender)){
					if (args.length > 2){
						FileConfiguration area_config = Metodos.PegarConfig("dados/areas.yml");
						ConfigurationSection areas = Metodos.PegarConfSessao("areas", area_config);
						Set<String> nome_areas = areas.getKeys(false);
						String confere = null;
						for(String nome_area : nome_areas){
							if(args[2].equalsIgnoreCase(nome_area)){
								confere = "ok";
							}
						}
						if(confere != "ok"){
							sender.sendMessage(Metodos.Traducao("erro_rearea_inesistente", "/vipadm "+cmdhelp.get("vipadm_area")+" "+cmdhelp.get("vipadm_area_criar")+" <"+Metodos.Traducao("nome")+">"));
							return;
						}
						Player player = (Player) sender;
						//acesar o hash
						double x1 = 0;
						double y1 = 0;
						double z1 = 0;
						String pos1 = null;
						if(Db.pos_pri.containsKey(player.getName())){
							if(Db.pos_pri.get(player.getName()) != null){
								x1 = Db.pos_pri.get(player.getName()).getX();
								y1 = Db.pos_pri.get(player.getName()).getY();
								z1 = Db.pos_pri.get(player.getName()).getZ();
								pos1 = "ok";
							}
						} else{sender.sendMessage(Metodos.Traducao("indefinida_pos1"));}
						double x2 = 0;
						double y2 = 0;
						double z2 = 0;
						String pos2 = null;
						if(Db.pos_seg.containsKey(player.getName())){
							if(Db.pos_seg.get(player.getName()) != null){
								x2 = Db.pos_seg.get(player.getName()).getX();
								y2 = Db.pos_seg.get(player.getName()).getY();
								z2 = Db.pos_seg.get(player.getName()).getZ();
								pos2 = "ok";
							}
						} else{sender.sendMessage(Metodos.Traducao("indefinida_pos2"));}
						//
						if(pos1 == "ok"){
							if(pos2 == "ok"){
								areas.set(args[2]+".mundo", player.getWorld().getName());
								areas.set(args[2]+".pos1", x1+" "+y1+" "+z1);
								areas.set(args[2]+".pos2", x2+" "+y2+" "+z2);
								Metodos.salvarConfig("dados/areas.yml", area_config);
								Db.recarregarAreas();
								Db.atualizarAreas();
								sender.sendMessage(Metodos.Traducao("sucesso_readd_area", args[2]));
							}
						}
					}
					else{
						sender.sendMessage(Metodos.Traducao("erro_syntax_cmd", "/vipadm "+cmdhelp.get("vipadm_area")+" "+cmdhelp.get("vipadm_area_recriar")+" <"+Metodos.Traducao("nome")+">"));
					}
				}
				else{
					sender.sendMessage(Metodos.Traducao("alerta_remetente_errado"));
				}
			}//termina "recreate"
			
			
			else if(Db.cmd("vipadm_area_remover", args[1])){//começa "remove"
				if (args.length > 2){
					FileConfiguration config = Metodos.PegarConfig("dados/areas.yml");
					if(Metodos.PegarConfSessao("areas", config).get(args[2]) != null){
						config.getConfigurationSection("areas").set(args[2], null);
						Metodos.salvarConfig("dados/areas.yml", config);
						Db.recarregarAreas();
						Db.atualizarAreas();
						sender.sendMessage(Metodos.Traducao("sucesso_del_area", args[2]));
						new VerificarConfigs();
					}
					else{
						sender.sendMessage(Metodos.Traducao("erro_area_inesistente"));
						return;
					}
				}
				else{
					sender.sendMessage(Metodos.Traducao("erro_syntax_cmd", "/vipadm "+cmdhelp.get("vipadm_area")+" "+cmdhelp.get("vipadm_area_remover")+" <"+Metodos.Traducao("nome")+">"));
				}
			}//termina "remove"
			
			
			else if(Db.cmd("vipadm_area_lista", args[1])){//começa "list"
				sender.sendMessage("-----"+Metodos.Traducao("lista_areas")+"-----");
				for(String nome_area : Metodos.PegarConfig("dados/areas.yml").getConfigurationSection("areas").getKeys(false)){
					sender.sendMessage("§6"+nome_area);
				}
				sender.sendMessage("--------------------");
			}//termina "list"
			
			
			else if(Db.cmd("vipadm_area_tp", args[1])){//começa "tp"
				if(Metodos.sePlayerRemetente(sender)){
					if (args.length > 2){
						if(Db.mundo.containsKey(args[2])){
							String mundo = Db.mundo.get(args[2]);
							double[] min = Db.min.get(args[2]);
							double[] max = Db.max.get(args[2]);
							double minX = min[0]; double minY = min[1]; double minZ = min[2];
							double maxX = max[0]; double maxY = max[1]; double maxZ = max[2];
							double X = minX+((maxX-minX)/2); double Y = minY; double Z = minZ+((maxZ-minZ)/2);
							Player player = (Player) sender;
							double contY = minY;
							while(contY <= maxY){
								Location local1 = new Location(plugin.getServer().getWorld(mundo), X, contY, Z);
								Location local2 = new Location(plugin.getServer().getWorld(mundo), X, contY+1, Z);
								String bloco_tipo1 = local1.getBlock().getType().name();
								String bloco_tipo2 = local2.getBlock().getType().name();
								if(bloco_tipo1.equalsIgnoreCase("air") && bloco_tipo2.equalsIgnoreCase("air")){
									player.teleport(local1);
									return;
								}
								contY++;
							}
							Location local = new Location(plugin.getServer().getWorld(mundo), X, Y+2, Z);
							player.teleport(local);
							return;
						}
						else{
							sender.sendMessage(Metodos.Traducao("erro_area_tp_inexistente"));
							return;
						}
					} else {
						sender.sendMessage(Metodos.Traducao("erro_syntax_cmd", "/vipadm "+cmdhelp.get("vipadm_area")+" "+cmdhelp.get("vipadm_area_tp")+" <"+Metodos.Traducao("nome")+">"));
						return;
					}
				} else{
					sender.sendMessage(Metodos.Traducao("alerta_remetente_errado"));
					return;
				}
			}//termina "tp"
			else{
				sender.sendMessage(Metodos.Traducao("erro_argumento", "\n"
						+ "/vipadm "+cmdhelp.get("vipadm_area")+" "+cmdhelp.get("vipadm_area_criar")+" <"+Metodos.Traducao("nome")+">"+"\n"
						+ "/vipadm "+cmdhelp.get("vipadm_area")+" "+cmdhelp.get("vipadm_area_recriar")+" <"+Metodos.Traducao("nome")+">"+"\n"
						+ "/vipadm "+cmdhelp.get("vipadm_area")+" "+cmdhelp.get("vipadm_area_remover")+" <"+Metodos.Traducao("nome")+">"+"\n"
						+ "/vipadm "+cmdhelp.get("vipadm_area")+" "+cmdhelp.get("vipadm_area_lista")+"\n"
						+ "/vipadm "+cmdhelp.get("vipadm_area")+" "+cmdhelp.get("vipadm_area_tp")+" <"+Metodos.Traducao("nome")+">"
						));
			}
			
		}
		else if(args.length < 2){
			String[] area_ajuda = {
					"/vipadm "+cmdhelp.get("vipadm_area")+" "+cmdhelp.get("vipadm_area_criar")+" <"+Metodos.Traducao("nome")+">",
					"/vipadm "+cmdhelp.get("vipadm_area")+" "+cmdhelp.get("vipadm_area_recriar")+" <"+Metodos.Traducao("nome")+">",
					"/vipadm "+cmdhelp.get("vipadm_area")+" "+cmdhelp.get("vipadm_area_remover")+" <"+Metodos.Traducao("nome")+">",
					"/vipadm "+cmdhelp.get("vipadm_area")+" "+cmdhelp.get("vipadm_area_lista"),
					"/vipadm "+cmdhelp.get("vipadm_area")+" "+cmdhelp.get("vipadm_area_tp")+" <"+Metodos.Traducao("nome")+">"};
			String ajuda = Metodos.Traducao("area_ajuda", area_ajuda);
			sender.sendMessage(ajuda);
		}
		else{
			sender.sendMessage(Metodos.Traducao("erro_desconhecido", "/vipadm "+cmdhelp.get("vipadm_area")));
		}
	}
}
