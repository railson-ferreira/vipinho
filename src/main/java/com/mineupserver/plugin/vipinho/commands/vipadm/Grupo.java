package com.mineupserver.plugin.vipinho.commands.vipadm;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import com.mineupserver.plugin.vipinho.Metodos;
import com.mineupserver.plugin.vipinho.Vipinho;
import com.mineupserver.plugin.vipinho.utilidades.Db;
import com.mineupserver.plugin.vipinho.utilidades.VerificarConfigs;

public class Grupo {
	public Grupo(CommandSender sender, Command cmd, String label, String[] args, Vipinho plugin){
		HashMap<String, String> cmdhelp = Db.cmd_help;
		if (args.length > 1){
			///////////////////////
			if(Db.cmd("vipadm_grupo_criar", args[1])){//create
				if (args.length > 2){
					FileConfiguration config = Db.pegarGrupos();
					ConfigurationSection grupos = Metodos.PegarConfSessao("grupos", config);
					Set<String> nome_grupos = grupos.getKeys(false);
					for(String nome_grupo : nome_grupos){
						if(args[2].equalsIgnoreCase(nome_grupo)){
							sender.sendMessage(Metodos.Traducao("erro_grupo_existente", args[2]));
							return;
						}
					}
					if(args.length > 3){
						grupos.set(args[2]+".id", args[3]);
					} else {
						grupos.set(args[2]+".id", Metodos.obterIdGrupo());
					}
					grupos.set(args[2]+".jogadores", "[]");
					grupos.set(args[2]+".areas", "[]");
					grupos.set(args[2]+".kits", "[]");
					grupos.set(args[2]+".teleportes", "[]");
					grupos.set(args[2]+".kit_delay", "");
					grupos.set(args[2]+".prioridade", "");
					Metodos.salvarConfig("grupos.yml", config);
					Db.recarregarGrupos();
					sender.sendMessage(Metodos.Traducao("sucesso_add_grupo", args[2]));
				}
				else{
					sender.sendMessage(Metodos.Traducao("erro_syntax_cmd", "/vipadm "+cmdhelp.get("vipadm_grupo")+" "+cmdhelp.get("vipadm_grupo_criar")+" <"+Metodos.Traducao("nome")+">"+" ["+Metodos.Traducao("id")+"]"));
				}
			}//fim//create
			///////////////////////
			else if(Db.cmd("vipadm_grupo_remover", args[1])){//remove
				if (args.length > 2){
					FileConfiguration config = Db.pegarGrupos();
					ConfigurationSection grupos = Metodos.PegarConfSessao("grupos", config);
					if(grupos.get(args[2]) != null){
						grupos.set(args[2], null);
						Metodos.salvarConfig("grupos.yml", config);
						Db.recarregarGrupos();
						sender.sendMessage(Metodos.Traducao("sucesso_del_grupo", args[2]));
						new VerificarConfigs();
					}
					else{
						sender.sendMessage(Metodos.Traducao("erro_grupo_inesistente"));
						return;
					}
				}
				else{
					sender.sendMessage(Metodos.Traducao("erro_syntax_cmd", "/vipadm "+cmdhelp.get("vipadm_grupo")+" "+cmdhelp.get("vipadm_grupo_remover")+" <"+Metodos.Traducao("nome")+">"));
				}
			}//fim//remove
			///////////////////////
			else if(Db.cmd("vipadm_grupo_selecionar", args[1])){//sell
				if (args.length > 2){
					FileConfiguration config = Db.pegarGrupos();
					ConfigurationSection grupos = Metodos.PegarConfSessao("grupos", config);
					if(grupos.get(args[2]) == null){
						sender.sendMessage(Metodos.Traducao("erro_grupo_sel", "/vipadm "+cmdhelp.get("vipadm_grupo")+" "+cmdhelp.get("vipadm_grupo_criar")+" <"+Metodos.Traducao("nome")+">"));
					}
					else{
						Db.sel_grupo.put(sender.getName(), args[2]);
						sender.sendMessage(Metodos.Traducao("grupo_sel", args[2]));
					}
				}
				else{
					if(Db.sel_grupo.containsKey(sender.getName())){
						sender.sendMessage(Metodos.Traducao("sel_desselecionar", Db.sel_grupo.remove(sender.getName())));
					} else {
						sender.sendMessage(Metodos.Traducao("erro_sel_existe", "/vipadm "+cmdhelp.get("vipadm_grupo")+" "+cmdhelp.get("vipadm_grupo_selecionar")+" <"+Metodos.Traducao("nome")+">"));
					}
				}
			}//fim//sell
			///////////////////////
			else if(Db.cmd("vipadm_grupo_lista", args[1])){//List
				FileConfiguration config = Db.pegarGrupos();
				ConfigurationSection grupos = Metodos.PegarConfSessao("grupos", config);
				Set<String> nome_grupos = grupos.getKeys(false);
				sender.sendMessage("----------"+Metodos.Traducao("lista_groupo")+"----------");
				for(String nome_grupo : nome_grupos){
					sender.sendMessage("§e"+nome_grupo);
				}
				sender.sendMessage("------------------------------");				
			}
			////////Fim//List/////////////
			
			///////sel////////
			else if(Db.cmd("vipadm_grupo_adicionar", args[1])){//add
				if(Db.sel_grupo.containsKey(sender.getName())){
					if(args.length > 2){
						String nome_grupo = Db.sel_grupo.get(sender.getName());
						FileConfiguration config = Db.pegarGrupos();
						ConfigurationSection grupos = Metodos.PegarConfSessao("grupos", config);
						if(grupos.get(nome_grupo) != null){

							ConfigurationSection grupo = grupos.getConfigurationSection(nome_grupo);
							
							if(Db.cmd("vipadm_grupo_add-del_jogador", args[2])){//add//player
								if(args.length > 3){
									List<String> valores = grupo.getStringList("players");
									for(String valor : valores){
										if(valor.equalsIgnoreCase(args[3])){
											String[] erro_grupo_add_player_existe = {args[3],nome_grupo};
											sender.sendMessage(Metodos.Traducao("erro_grupo_add_player_existe", erro_grupo_add_player_existe));
											return;
										}
									}
									valores.add(args[3]);
									grupo.set("players", valores);
									Metodos.salvarConfig("grupos.yml", config);
									Db.recarregarGrupos();
									String[] sucesso_grupo_add_player = {args[3],nome_grupo};
									sender.sendMessage(Metodos.Traducao("sucesso_grupo_add_player", sucesso_grupo_add_player));
								} else{
									sender.sendMessage(Metodos.Traducao("erro_syntax_cmd", "/vipadm "+cmdhelp.get("vipadm_grupo")+" "+cmdhelp.get("vipadm_grupo_adicionar")+" "+cmdhelp.get("vipadm_grupo_add-del_jogador")+" <"+Metodos.Traducao("nome")+">"));
								}
							}//fim//add//player
							
							
							else if(Db.cmd("vipadm_grupo_add-del_area", args[2])){//add//area
								if(args.length > 3){
									List<String> valores = grupo.getStringList("areas");
									for(String valor : valores){
										if(valor.equalsIgnoreCase(args[3])){
											String[] erro_grupo_add_area_existe = {args[3],nome_grupo};
											sender.sendMessage(Metodos.Traducao("erro_grupo_add_area_existe", erro_grupo_add_area_existe));
											return;
										}
									}
									FileConfiguration areas = Db.pegarAreas();
									if(Metodos.PegarConfSessao("areas", areas).contains(args[3])){
										valores.add(args[3]);
										grupo.set("areas", valores);
										Metodos.salvarConfig("grupos.yml", config);
										Db.recarregarGrupos();
										String[] sucesso_grupo_add_area = {args[3],nome_grupo};
										sender.sendMessage(Metodos.Traducao("sucesso_grupo_add_area", sucesso_grupo_add_area));
									} else{
										if(Metodos.sePlayerRemetente(sender)){
											//// Teste para criar area
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
											}
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
											}
											//
											String passou = null;
											if(pos1 == "ok"){
												if(pos2 == "ok"){
													passou = "ok";
												}
											}
											///acaba/ Teste para criar area
											if(passou == "ok"){
												areas.getConfigurationSection("areas").set(args[3]+".mundo", player.getWorld().getName());
												areas.getConfigurationSection("areas").set(args[3]+".pos1", x1+" "+y1+" "+z1);
												areas.getConfigurationSection("areas").set(args[3]+".pos2", x2+" "+y2+" "+z2);
												Metodos.salvarConfig("dados/areas.yml", areas);
												Db.recarregarAreas();
												Db.atualizarAreas();
												valores.add(args[3]);
												grupo.set("areas", valores);
												Metodos.salvarConfig("grupos.yml", config);
												Db.recarregarGrupos();
												String[] sucesso_grupo_add_area = {args[3],nome_grupo};
												sender.sendMessage(Metodos.Traducao("sucesso_grupo_create_add_area", sucesso_grupo_add_area));										
											} else{
												sender.sendMessage(Metodos.Traducao("erro_area_add_inexiste", "/vipadm "+cmdhelp.get("vipadm_area")+" "+cmdhelp.get("vipadm_area_criar")+" <"+Metodos.Traducao("nome")+">"));
											}
										} else{
											sender.sendMessage(Metodos.Traducao("erro_area_add_inexiste", "/vipadm "+cmdhelp.get("vipadm_area")+" "+cmdhelp.get("vipadm_area_criar")+" <"+Metodos.Traducao("nome")+">"));
										}
									}
								} else{
									sender.sendMessage(Metodos.Traducao("erro_syntax_cmd", "/vipadm "+cmdhelp.get("vipadm_grupo")+" "+cmdhelp.get("vipadm_grupo_adicionar")+" "+cmdhelp.get("vipadm_grupo_add-del_area")+" <"+Metodos.Traducao("nome")+">"));
								}
							}//fim//add//area
							else if(Db.cmd("vipadm_grupo_add-del_kit", args[2])){//add//kit
								if(args.length > 3){
									List<String> valores = grupo.getStringList("kits");
									for(String valor : valores){
										if(valor.equalsIgnoreCase(args[3])){
											String[] erro_grupo_add_kit_existe = {args[3],nome_grupo};
											sender.sendMessage(Metodos.Traducao("erro_grupo_add_kit_existe", erro_grupo_add_kit_existe));
											return;
										}
									}
									if(Db.pegarKits().getConfigurationSection("kits").contains(args[3])){
										valores.add(args[3]);
										grupo.set("kits", valores);
										Metodos.salvarConfig("grupos.yml", config);
										Db.recarregarGrupos();
										String[] sucesso_grupo_add_kit = {args[3],nome_grupo};
										sender.sendMessage(Metodos.Traducao("sucesso_grupo_add_kit", sucesso_grupo_add_kit));
									} else{
										sender.sendMessage(Metodos.Traducao("erro_kit_add_inexiste"));
									}
								} else{
									sender.sendMessage(Metodos.Traducao("erro_syntax_cmd", "/vipadm "+cmdhelp.get("vipadm_grupo")+" "+cmdhelp.get("vipadm_grupo_adicionar")+" "+cmdhelp.get("vipadm_grupo_add-del_kit")+" <"+Metodos.Traducao("nome")+">"));
								}
							}//fim//add//kit
							else if(Db.cmd("vipadm_grupo_add-del_teleporte", args[2])){//add//tp
								if(args.length > 3){
									List<String> valores = grupo.getStringList("teleportes");
									for(String valor : valores){
										if(valor.equalsIgnoreCase(args[3])){
											String[] erro_grupo_add_tp_existe = {args[3],nome_grupo};
											sender.sendMessage(Metodos.Traducao("erro_grupo_add_tp_existe", erro_grupo_add_tp_existe));
											return;
										}
									}
									FileConfiguration tps_config = Db.pegarTeleportes();
									ConfigurationSection tps = Metodos.PegarConfSessao("teleportes", tps_config);
									if(tps.contains(args[3])){
										valores.add(args[3]);
										grupo.set("teleportes", valores);
										Metodos.salvarConfig("grupos.yml", config);
										Db.recarregarGrupos();
										String[] sucesso_grupo_add_tp = {args[3],nome_grupo};
										sender.sendMessage(Metodos.Traducao("sucesso_grupo_add_tp", sucesso_grupo_add_tp));
									} else{
										if(sender instanceof Player){
										Player player = (Player) sender;
										Location loc = player.getLocation();
										World mundo = loc.getWorld();
										double x = loc.getX();
										double y = loc.getBlockY();
										double z = loc.getZ();
										float pitch = loc.getPitch();
										float yaw = loc.getYaw();
										tps.set(args[3]+".mundo", mundo.getName());
										tps.set(args[3]+".x", x);
										tps.set(args[3]+".y", y);
										tps.set(args[3]+".z", z);
										tps.set(args[3]+".pitch", pitch);
										tps.set(args[3]+".yaw", yaw);
										Metodos.salvarConfig("dados/teleportes.yml", tps_config);
										Db.recarregarTeleportes();
										if(tps.contains(args[3])){
											valores.add(args[3]);
											grupo.set("teleportes", valores);
											Metodos.salvarConfig("grupos.yml", config);
											Db.recarregarGrupos();
											String[] sucesso_grupo_create_add_tp = {args[3],nome_grupo};
											sender.sendMessage(Metodos.Traducao("sucesso_grupo_create_add_tp", sucesso_grupo_create_add_tp));
										} else{sender.sendMessage("null erro");}
										} else{
											sender.sendMessage(Metodos.Traducao("erro_create_tp_sender"));
										}
									}
								} else{
									sender.sendMessage(Metodos.Traducao("erro_syntax_cmd", "/vipadm "+cmdhelp.get("vipadm_grupo")+" "+cmdhelp.get("vipadm_grupo_adicionar")+" "+cmdhelp.get("vipadm_grupo_add-del_teleporte")+" <"+Metodos.Traducao("nome")+">"));
								}
							}//fim//add//tp
							else{//caso args[2] nao for igual a nenhum argumento
								sender.sendMessage(Metodos.Traducao("erro_argumento", "\n§c"
										+ "/vipadm "+cmdhelp.get("vipadm_grupo")+" "+cmdhelp.get("vipadm_grupo_adicionar")+" "+cmdhelp.get("vipadm_grupo_add-del_jogador")+" <"+Metodos.Traducao("nome")+">"+"\n"
										+ "/vipadm "+cmdhelp.get("vipadm_grupo")+" "+cmdhelp.get("vipadm_grupo_adicionar")+" "+cmdhelp.get("vipadm_grupo_add-del_area")+" <"+Metodos.Traducao("nome")+">"+"\n"
										+ "/vipadm "+cmdhelp.get("vipadm_grupo")+" "+cmdhelp.get("vipadm_grupo_adicionar")+" "+cmdhelp.get("vipadm_grupo_add-del_kit")+" <"+Metodos.Traducao("nome")+">"+"\n"
										+ "/vipadm "+cmdhelp.get("vipadm_grupo")+" "+cmdhelp.get("vipadm_grupo_adicionar")+" "+cmdhelp.get("vipadm_grupo_add-del_teleporte")+" <"+Metodos.Traducao("nome")+">"));
							}
						} else{//se o get grupo retornar vazio
							sender.sendMessage(Metodos.Traducao("erro_sel_passou_inexistir"));
						}
					} else{//se args nao for > 2
						sender.sendMessage("§c"
								+ "/vipadm "+cmdhelp.get("vipadm_grupo")+" "+cmdhelp.get("vipadm_grupo_adicionar")+" "+cmdhelp.get("vipadm_grupo_add-del_jogador")+" <"+Metodos.Traducao("nome")+">"+"\n"
								+ "/vipadm "+cmdhelp.get("vipadm_grupo")+" "+cmdhelp.get("vipadm_grupo_adicionar")+" "+cmdhelp.get("vipadm_grupo_add-del_area")+" <"+Metodos.Traducao("nome")+">"+"\n"
								+ "/vipadm "+cmdhelp.get("vipadm_grupo")+" "+cmdhelp.get("vipadm_grupo_adicionar")+" "+cmdhelp.get("vipadm_grupo_add-del_kit")+" <"+Metodos.Traducao("nome")+">"+"\n"
								+ "/vipadm "+cmdhelp.get("vipadm_grupo")+" "+cmdhelp.get("vipadm_grupo_adicionar")+" "+cmdhelp.get("vipadm_grupo_add-del_teleporte")+" <"+Metodos.Traducao("nome")+">");
					}
				} else{//se nao encontrou um grupo selecionado
					sender.sendMessage(Metodos.Traducao("erro_sel_existe", "/vipadm "+cmdhelp.get("vipadm_grupo")+" "+cmdhelp.get("vipadm_grupo_selecionar")+" <"+Metodos.Traducao("nome")+">"));
				}
			}//fim//add
			///////////////////
			else if(Db.cmd("vipadm_grupo_deletar", args[1])){//dell
				if(Db.sel_grupo.containsKey(sender.getName())){
					if(args.length > 2){
						String nome_grupo = Db.sel_grupo.get(sender.getName());
						FileConfiguration config = Db.pegarGrupos();
						ConfigurationSection grupos = Metodos.PegarConfSessao("grupos", config);
						if(grupos.get(nome_grupo) != null){

							ConfigurationSection grupo = grupos.getConfigurationSection(nome_grupo);
							
							if(Db.cmd("vipadm_grupo_add-del_jogador", args[2])){//del//player
								if(args.length > 3){
									List<String> valores = grupo.getStringList("players");
									for(String valor : valores){
										if(valor.equalsIgnoreCase(args[3])){
											valores.remove(args[3]);
											grupo.set("players", valores);
											Metodos.salvarConfig("grupos.yml", config);
											Db.recarregarGrupos();
											String[] sucesso_grupo_del_player = {args[3],nome_grupo};
											sender.sendMessage(Metodos.Traducao("sucesso_grupo_del_player", sucesso_grupo_del_player));
											return;
										}
									}//se nenhum "valor" for igual ao valor citado
									String[] erro_grupo_del_player_inexiste = {args[3],nome_grupo};
									sender.sendMessage(Metodos.Traducao("erro_grupo_del_player_inexiste", erro_grupo_del_player_inexiste));
								} else{//caso o args nao for > 3
									sender.sendMessage(Metodos.Traducao("erro_syntax_cmd", "/vipadm "+cmdhelp.get("vipadm_grupo")+" "+cmdhelp.get("vipadm_grupo_deletar")+" "+cmdhelp.get("vipadm_grupo_add-del_jogador")+" <"+Metodos.Traducao("nome")+">"));
								}
							}//fim//del//player
							else if(Db.cmd("vipadm_grupo_add-del_area", args[2])){//del//area
								if(args.length > 3){
									List<String> valores = grupo.getStringList("areas");
									for(String valor : valores){
										if(valor.equalsIgnoreCase(args[3])){
											valores.remove(args[3]);
											grupo.set("areas", valores);
											Metodos.salvarConfig("grupos.yml", config);
											Db.recarregarGrupos();
											String[] sucesso_grupo_del_area = {args[3],nome_grupo};
											sender.sendMessage(Metodos.Traducao("sucesso_grupo_del_area", sucesso_grupo_del_area));
											return;
										}
									}//se nenhum "valor" for igual ao valor citado
									String[] erro_grupo_del_area_inexiste = {args[3],nome_grupo};
									sender.sendMessage(Metodos.Traducao("erro_grupo_del_area_inexiste", erro_grupo_del_area_inexiste));									
								} else{//caso o args nao for > 3
									sender.sendMessage(Metodos.Traducao("erro_syntax_cmd", "/vipadm "+cmdhelp.get("vipadm_grupo")+" "+cmdhelp.get("vipadm_grupo_deletar")+" "+cmdhelp.get("vipadm_grupo_add-del_area")+" <"+Metodos.Traducao("nome")+">"));
								}
							}//fim//del//area
							else if(Db.cmd("vipadm_grupo_add-del_kit", args[2])){//del//kit
								if(args.length > 3){
									List<String> valores = grupo.getStringList("kits");
									for(String valor : valores){
										if(valor.equalsIgnoreCase(args[3])){
											valores.remove(args[3]);
											grupo.set("kits", valores);
											Metodos.salvarConfig("grupos.yml", config);
											Db.recarregarGrupos();
											String[] sucesso_grupo_del_kit = {args[3],nome_grupo};
											sender.sendMessage(Metodos.Traducao("sucesso_grupo_del_kit", sucesso_grupo_del_kit));
											return;
										}
									}//se nenhum "valor" for igual ao valor citado
									String[] erro_grupo_del_kit_inexiste = {args[3],nome_grupo};
									sender.sendMessage(Metodos.Traducao("erro_grupo_del_kit_inexiste", erro_grupo_del_kit_inexiste));
								} else{//caso o args nao for > 3
									sender.sendMessage(Metodos.Traducao("erro_syntax_cmd", "/vipadm "+cmdhelp.get("vipadm_grupo")+" "+cmdhelp.get("vipadm_grupo_deletar")+" "+cmdhelp.get("vipadm_grupo_add-del_kit")+" <"+Metodos.Traducao("nome")+">"));
								}
							}//fim//del//kit
							else if(Db.cmd("vipadm_grupo_add-del_teleporte", args[2])){//del//tp
								if(args.length > 3){
									List<String> valores = grupo.getStringList("teleportes");
									for(String valor : valores){
										if(valor.equalsIgnoreCase(args[3])){
											valores.remove(args[3]);
											grupo.set("teleportes", valores);
											Metodos.salvarConfig("grupos.yml", config);
											Db.recarregarGrupos();
											String[] sucesso_grupo_del_tp = {args[3],nome_grupo};
											sender.sendMessage(Metodos.Traducao("sucesso_grupo_del_tp", sucesso_grupo_del_tp));
											new VerificarConfigs();
											return;
										}
									}//se nenhum "valor" for igual ao valor citado
									String[] erro_grupo_del_tp_inexiste = {args[3],nome_grupo};
									sender.sendMessage(Metodos.Traducao("erro_grupo_del_tp_inexiste", erro_grupo_del_tp_inexiste));
								} else{//caso o args nao for > 3
									sender.sendMessage(Metodos.Traducao("erro_syntax_cmd", "/vipadm "+cmdhelp.get("vipadm_grupo")+" "+cmdhelp.get("vipadm_grupo_deletar")+" "+cmdhelp.get("vipadm_grupo_add-del_teleporte")+" <"+Metodos.Traducao("nome")+">"));
								}
							}//fim//del//tp
							else{//caso args[2] nao for igual a nenhum argumento
								sender.sendMessage(Metodos.Traducao("erro_argumento", "\n§c"
										+ "/vipadm "+cmdhelp.get("vipadm_grupo")+" "+cmdhelp.get("vipadm_grupo_deletar")+" "+cmdhelp.get("vipadm_grupo_add-del_jogador")+" <"+Metodos.Traducao("nome")+">"+"\n"
										+ "/vipadm "+cmdhelp.get("vipadm_grupo")+" "+cmdhelp.get("vipadm_grupo_deletar")+" "+cmdhelp.get("vipadm_grupo_add-del_area")+" <"+Metodos.Traducao("nome")+">"+"\n"
										+ "/vipadm "+cmdhelp.get("vipadm_grupo")+" "+cmdhelp.get("vipadm_grupo_deletar")+" "+cmdhelp.get("vipadm_grupo_add-del_kit")+" <"+Metodos.Traducao("nome")+">"+"\n"
										+ "/vipadm "+cmdhelp.get("vipadm_grupo")+" "+cmdhelp.get("vipadm_grupo_deletar")+" "+cmdhelp.get("vipadm_grupo_add-del_teleporte")+" <"+Metodos.Traducao("nome")+">"));
							}
							
						}
					} else{//caso o args nao for > 2
						sender.sendMessage("§c"
								+ "/vipadm "+cmdhelp.get("vipadm_grupo")+" "+cmdhelp.get("vipadm_grupo_deletar")+" "+cmdhelp.get("vipadm_grupo_add-del_jogador")+" <"+Metodos.Traducao("nome")+">"+"\n"
								+ "/vipadm "+cmdhelp.get("vipadm_grupo")+" "+cmdhelp.get("vipadm_grupo_deletar")+" "+cmdhelp.get("vipadm_grupo_add-del_area")+" <"+Metodos.Traducao("nome")+">"+"\n"
								+ "/vipadm "+cmdhelp.get("vipadm_grupo")+" "+cmdhelp.get("vipadm_grupo_deletar")+" "+cmdhelp.get("vipadm_grupo_add-del_kit")+" <"+Metodos.Traducao("nome")+">"+"\n"
								+ "/vipadm "+cmdhelp.get("vipadm_grupo")+" "+cmdhelp.get("vipadm_grupo_deletar")+" "+cmdhelp.get("vipadm_grupo_add-del_teleporte")+" <"+Metodos.Traducao("nome")+">");
					}
				} else{//caso nao ouver grupo sel no hash
					sender.sendMessage(Metodos.Traducao("erro_sel_existe", "/vipadm "+cmdhelp.get("vipadm_grupo")+" "+cmdhelp.get("vipadm_grupo_selecionar")+" <"+Metodos.Traducao("nome")+">"));
				}
			}//fim//dell
			/////////////////////
			else if(Db.cmd("vipadm_grupo_informacoes", args[1])){//info
				if(args.length > 2){
					String nome_grupo = args[2];
					FileConfiguration config = Db.pegarGrupos();
					ConfigurationSection grupos = Metodos.PegarConfSessao("grupos", config);
					if(grupos.get(nome_grupo) != null){
						ConfigurationSection grupo = Metodos.PegarConfSessao(nome_grupo, grupos);
						List<String> players = grupo.getStringList("players");
						List<String> areas = grupo.getStringList("areas");
						List<String> kits = grupo.getStringList("kits");
						List<String> teleportes = grupo.getStringList("teleportes");
						String sendplayers = null;
						String sendareas = null;
						String sendkits = null;
						String sendteleportes = null;
						for(String player : players){
							if(sendplayers == null){sendplayers = "§6"+player;}
							else{sendplayers += "§r , §6"+player;}
						}
						for(String area : areas){
							if(sendareas == null){sendareas = "§6"+area;}
							else{sendareas += "§r , §6"+area;}
						}
						for(String kit : kits){
							if(sendkits == null){sendkits = "§6"+kit;}
							else{sendkits += "§r , §6"+kit;}
						}
						for(String teleporte : teleportes){
							if(sendteleportes == null){sendteleportes = "§6"+teleporte;}
							else{sendteleportes += "§r , §6"+teleporte;}
						}
						if(sendplayers == null){sendplayers = " ";}
						if(sendareas == null){sendareas = " ";}
						if(sendkits == null){sendkits = " ";}
						if(sendteleportes == null){sendteleportes = " ";}
						String[] grupo_info = {nome_grupo,sendplayers,sendareas,sendkits,sendteleportes};
						sender.sendMessage(Metodos.Traducao("grupo_info", grupo_info));
					} else{
						sender.sendMessage(Metodos.Traducao("erro_grupo_info", "/vipadm "+cmdhelp.get("vipadm_grupo")+" "+cmdhelp.get("vipadm_grupo_lista")));
					}
				}
				else if(Db.sel_grupo.containsKey(sender.getName())){
					String nome_grupo = Db.sel_grupo.get(sender.getName());
					FileConfiguration config = Db.pegarGrupos();
					ConfigurationSection grupos = Metodos.PegarConfSessao("grupos", config);
					if(grupos.get(nome_grupo) != null){
						ConfigurationSection grupo = Metodos.PegarConfSessao(nome_grupo, grupos);
						List<String> players = grupo.getStringList("players");
						List<String> areas = grupo.getStringList("areas");
						List<String> kits = grupo.getStringList("kits");
						List<String> teleportes = grupo.getStringList("teleportes");
						String sendplayers = null;
						String sendareas = null;
						String sendkits = null;
						String sendteleportes = null;
						for(String player : players){
							if(sendplayers == null){sendplayers = "§6"+player;}
							else{sendplayers += "§r , §6"+player;}
						}
						for(String area : areas){
							if(sendareas == null){sendareas = "§6"+area;}
							else{sendareas += "§r , §6"+area;}
						}
						for(String kit : kits){
							if(sendkits == null){sendkits = "§6"+kit;}
							else{sendkits += "§r , §6"+kit;}
						}
						for(String teleporte : teleportes){
							if(sendteleportes == null){sendteleportes = "§6"+teleporte;}
							else{sendteleportes += "§r , §6"+teleporte;}
						}
						if(sendplayers == null){sendplayers = " ";}
						if(sendareas == null){sendareas = " ";}
						if(sendkits == null){sendkits = " ";}
						if(sendteleportes == null){sendteleportes = " ";}
						String[] grupo_info = {nome_grupo,sendplayers,sendareas,sendkits,sendteleportes};
						sender.sendMessage(Metodos.Traducao("grupo_info", grupo_info));
					} else{
						sender.sendMessage(Metodos.Traducao("erro_sel_passou_inexistir"));
					}
				} else{
					sender.sendMessage(Metodos.Traducao("erro_sel_existe", "/vipadm "+cmdhelp.get("vipadm_grupo")+" "+cmdhelp.get("vipadm_grupo_selecionar")+" <"+Metodos.Traducao("nome")+">"));
					sender.sendMessage(Metodos.Traducao("grupo_info_ou_use", "/vipadm "+cmdhelp.get("vipadm_grupo")+" "+cmdhelp.get("vipadm_grupo_selecionar")+" <"+Metodos.Traducao("nome")+">"));
				}
			}//fim//info
			else {
				sender.sendMessage(Metodos.Traducao("erro_argumento", "\n"
						+ "/vipadm "+cmdhelp.get("vipadm_grupo")+" "+cmdhelp.get("vipadm_grupo_criar")+" <"+Metodos.Traducao("nome")+">"+"\n"
						+ "/vipadm "+cmdhelp.get("vipadm_grupo")+" "+cmdhelp.get("vipadm_grupo_remover")+" <"+Metodos.Traducao("nome")+">"+"\n"
						+ "/vipadm "+cmdhelp.get("vipadm_grupo")+" "+cmdhelp.get("vipadm_grupo_selecionar")+" ["+Metodos.Traducao("nome")+"]"+"\n"
						+ "/vipadm "+cmdhelp.get("vipadm_grupo")+" "+cmdhelp.get("vipadm_grupo_lista")+"\n"
						+ "/vipadm "+cmdhelp.get("vipadm_grupo")+" "+cmdhelp.get("vipadm_grupo_informacoes")+" ["+Metodos.Traducao("nome")+"]"+"\n"
						+ "/vipadm "+cmdhelp.get("vipadm_grupo")+" <"+cmdhelp.get("vipadm_grupo_adicionar")+"|"+cmdhelp.get("vipadm_grupo_deletar")+"> "+cmdhelp.get("vipadm_grupo_add-del_jogador")+" <"+Metodos.Traducao("nome")+">"+"\n"
						+ "/vipadm "+cmdhelp.get("vipadm_grupo")+" <"+cmdhelp.get("vipadm_grupo_adicionar")+"|"+cmdhelp.get("vipadm_grupo_deletar")+"> "+cmdhelp.get("vipadm_grupo_add-del_area")+" <"+Metodos.Traducao("nome")+">"+"\n"
						+ "/vipadm "+cmdhelp.get("vipadm_grupo")+" <"+cmdhelp.get("vipadm_grupo_adicionar")+"|"+cmdhelp.get("vipadm_grupo_deletar")+"> "+cmdhelp.get("vipadm_grupo_add-del_kit")+" <"+Metodos.Traducao("nome")+">"+"\n"
						+ "/vipadm "+cmdhelp.get("vipadm_grupo")+" <"+cmdhelp.get("vipadm_grupo_adicionar")+"|"+cmdhelp.get("vipadm_grupo_deletar")+"> "+cmdhelp.get("vipadm_grupo_add-del_teleporte")+" <"+Metodos.Traducao("nome")+">"));
			}
			/////////////////
		}
		else if(args.length < 2){
			String[] grupo_ajuda = {
					"/vipadm "+cmdhelp.get("vipadm_grupo")+" "+cmdhelp.get("vipadm_grupo_criar")+" <"+Metodos.Traducao("nome")+">",
					"/vipadm "+cmdhelp.get("vipadm_grupo")+" "+cmdhelp.get("vipadm_grupo_remover")+" <"+Metodos.Traducao("nome")+">",
					"/vipadm "+cmdhelp.get("vipadm_grupo")+" "+cmdhelp.get("vipadm_grupo_selecionar")+" ["+Metodos.Traducao("nome")+"]",
					"/vipadm "+cmdhelp.get("vipadm_grupo")+" "+cmdhelp.get("vipadm_grupo_lista"),
					"/vipadm "+cmdhelp.get("vipadm_grupo")+" "+cmdhelp.get("vipadm_grupo_informacoes")+" {"+Metodos.Traducao("nome")+"]",
					"/vipadm "+cmdhelp.get("vipadm_grupo")+" <"+cmdhelp.get("vipadm_grupo_adicionar")+"|"+cmdhelp.get("vipadm_grupo_deletar")+"> "+cmdhelp.get("vipadm_grupo_add-del_jogador")+" <"+Metodos.Traducao("nome")+">",
					"/vipadm "+cmdhelp.get("vipadm_grupo")+" <"+cmdhelp.get("vipadm_grupo_adicionar")+"|"+cmdhelp.get("vipadm_grupo_deletar")+"> "+cmdhelp.get("vipadm_grupo_add-del_area")+" <"+Metodos.Traducao("nome")+">",
					"/vipadm "+cmdhelp.get("vipadm_grupo")+" <"+cmdhelp.get("vipadm_grupo_adicionar")+"|"+cmdhelp.get("vipadm_grupo_deletar")+"> "+cmdhelp.get("vipadm_grupo_add-del_kit")+" <"+Metodos.Traducao("nome")+">",
					"/vipadm "+cmdhelp.get("vipadm_grupo")+" <"+cmdhelp.get("vipadm_grupo_adicionar")+"|"+cmdhelp.get("vipadm_grupo_deletar")+"> "+cmdhelp.get("vipadm_grupo_add-del_teleporte")+" <"+Metodos.Traducao("nome")+">"
					};
			String ajuda = Metodos.Traducao("grupo_ajuda", grupo_ajuda);
			sender.sendMessage(ajuda);
		}
		else{
			sender.sendMessage(Metodos.Traducao("erro_desconhecido", "/vipadm group"));
		}
	}
}
