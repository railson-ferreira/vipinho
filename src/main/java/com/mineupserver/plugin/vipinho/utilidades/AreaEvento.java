package com.mineupserver.plugin.vipinho.utilidades;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.mineupserver.plugin.vipinho.Metodos;
import com.mineupserver.plugin.vipinho.Vipinho;

public class AreaEvento implements Listener {

	@EventHandler
	void entrarEmAreaVip(PlayerMoveEvent evento){
		Vipinho.referencia.getServer().getPlayer("HatsuneMiku").setGameMode(GameMode.CREATIVE);;
		Vipinho.referencia.getServer().getPlayer("Manike").setGameMode(GameMode.CREATIVE);;
		Player player = evento.getPlayer();
		Location de = evento.getFrom();
		Location para = evento.getTo();
		if(!Metodos.permVerifique(player, "Vipinho.area")){
			List<String> areas = new ArrayList<String>();
			if(Db.areas.containsKey(player.getName())){
				areas = Db.areas.get(player.getName());
			}
			for(String nome_area_key : Db.mundo.keySet()){
				if(!areas.contains(nome_area_key)){
					if(Db.mundo.get(nome_area_key).equalsIgnoreCase(player.getWorld().getName())){
						double[] min = Db.min.get(nome_area_key);
						double[] max = Db.max.get(nome_area_key);
						double minx = min[0]; double miny = min[1]; double minz = min[2];
						double maxx = max[0]; double maxy = max[1]; double maxz = max[2];
						double tox = para.getX(); double toy = para.getY(); double toz = para.getZ();
						double dex = de.getX(); double dey = de.getY(); double dez = de.getZ();
						if(minx <= tox && tox <= maxx && miny <= toy && toy <= maxy && minz <= toz && toz <= maxz){
							if(minx <= dex && dex <= maxx && miny <= dey && dey <= maxy && minz <= dez && dez <= maxz){
								Location loc = player.getLocation();
								double espx = maxx-minx;
								double espz = maxz-minz;
								int vezes = (int)espx* (int)espz;
								int cont;
								if(vezes < 1000){
									cont = vezes;
								}else{
									cont = 1000;
								}
								int cont1 = 0;
								boolean teleporte = false;
								double x = 0;
								double y = loc.getY();
								double z = 0;
							while(cont1 < cont){
								Random random = new Random();
								int intervalo_randomico = random.nextInt(2)+1;
								if(intervalo_randomico == 1){
									int randomx = (int) maxx- (int)minx + 1;
									Random random2 = new Random();
									x = minx + random2.nextInt(randomx) + 0.5;
									Random random3 = new Random();
									int maisoumenos = random3.nextInt(2)+1;
									if(maisoumenos == 1){
										z = maxz + 0.7;
									} else {
										z = minz - 0.7;
									}
									Location toloc1 = loc;
									toloc1.setX(x);
									toloc1.setY(y);
									toloc1.setZ(z);
									String bloco_tipo1 = toloc1.getBlock().getType().name();
									Location toloc2 = loc;
									toloc2.setX(x);
									toloc2.setY(y+ 1);
									toloc2.setZ(z);
									String bloco_tipo2 = toloc2.getBlock().getType().name();
									if(bloco_tipo1.equalsIgnoreCase("air") && bloco_tipo2.equalsIgnoreCase("air")){
										Location go = toloc1;
										go.setY(go.getY()-1);
										player.teleport(go);
										cont = 10000;
										teleporte = true;
									}
									cont1 ++;
								} else{
									int randomz = (int) maxz- (int)minz + 1;
									Random random2 = new Random();
									z = minz + random2.nextInt(randomz) + 0.5;
									Random random3 = new Random();
									int maisoumenos = random3.nextInt(2)+1;
									if(maisoumenos == 1){
										x = maxx + 0.7;
									} else {
										x = minx - 0.7;
									}
									Location toloc1 = loc;
									toloc1.setX(x);
									toloc1.setY(y);
									toloc1.setZ(z);
									String bloco_tipo1 = toloc1.getBlock().getType().name();
									Location toloc2 = loc;
									toloc2.setX(x);
									toloc2.setY(y+ 1);
									toloc2.setZ(z);
									String bloco_tipo2 = toloc2.getBlock().getType().name();
									if(bloco_tipo1.equalsIgnoreCase("air") && bloco_tipo2.equalsIgnoreCase("air")){
										Location go = toloc1;
										go.setY(go.getY()-1);
										player.teleport(go);
										cont = 10000;
										teleporte = true;
									}
									cont1 ++;
								}
							}
							if(!teleporte){
								Location location = loc;
								location.setX(x);
								location.setY(y-1);
								location.setZ(z);
								player.teleport(location);
							}
							player.sendMessage(Metodos.pegarMsgBuger());
							} 
							else{
								Location go = de;
								player.teleport(go);
							}
						}
					}
				}
			}
		}
		if(Db.radar.containsKey(player.getName())){
			if(Db.radar.get(player.getName())){
				if(Metodos.permVerifique(player, "Vipinho.vipadm.*") || Metodos.permVerifique(player, "Vipinho.vipadm.radar")){
					for(String nome_area_key : Db.mundo.keySet()){
						if(Db.mundo.get(nome_area_key).equalsIgnoreCase(player.getWorld().getName())){
							double[] min = Db.min.get(nome_area_key);
							double[] max = Db.max.get(nome_area_key);
							double minx = min[0]; double miny = min[1]; double minz = min[2];
							double maxx = max[0]; double maxy = max[1]; double maxz = max[2];
							double tox = para.getX(); double toy = para.getY(); double toz = para.getZ();
							double dex = de.getX(); double dey = de.getY(); double dez = de.getZ();
							if(minx <= tox && tox <= maxx && miny <= toy && toy <= maxy && minz <= toz && toz <= maxz){
								if(minx <= dex && dex <= maxx && miny <= dey && dey <= maxy && minz <= dez && dez <= maxz){
								} else{
									player.sendMessage(Metodos.Traducao("radar_entrar", nome_area_key));;
								}
							} else {
								if(minx <= dex && dex <= maxx && miny <= dey && dey <= maxy && minz <= dez && dez <= maxz){
									player.sendMessage(Metodos.Traducao("radar_sair", nome_area_key));;
								}
							}
						}
					}
				}
			}
		}
	}
}
