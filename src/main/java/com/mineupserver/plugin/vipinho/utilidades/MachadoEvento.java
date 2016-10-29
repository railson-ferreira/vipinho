package com.mineupserver.plugin.vipinho.utilidades;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.mineupserver.plugin.vipinho.Metodos;

public class MachadoEvento implements Listener 
{

	
	@SuppressWarnings("deprecation")
	Material machado = new ItemStack(271).getType();
	@EventHandler
	void machado_esq(PlayerInteractEvent evento){
		if(Db.machadoAtivo){
		Player player = evento.getPlayer();
		if(Metodos.permVerifique(player, "Vipinho.axe")){
		if(player.getItemInHand().getType() == machado){
			
			evento.getAction();
			Action click = Action.LEFT_CLICK_BLOCK;
			String name = player.getName();
			
			if(evento.getAction() == click){
				//Começa a Ação
				
				Location loc_bloco = evento.getClickedBlock().getLocation();
				
				if(!Db.pos_pri.containsKey(name)){
					Db.pos_pri.put(name, loc_bloco);
					player.sendMessage(Metodos.Traducao("sel_pri"));
					Db.pos_pri_span.put(name, "");
				}
				else{
					if(Db.pos_pri.get(name).getX() == loc_bloco.getX()){
						if(Db.pos_pri.get(name).getY() == loc_bloco.getY()){
							if(Db.pos_pri.get(name).getZ() == loc_bloco.getZ()){
								if(Db.pos_pri_span.containsKey(name)){
									if(Db.pos_pri_span.get(name) == "ok"){return;}
								}
								player.sendMessage(Metodos.Traducao("alert_sel_pri_span"));
								Db.pos_pri_span.put(name, "ok");
							}
							else{
								Db.pos_pri.put(name, loc_bloco);
								player.sendMessage(Metodos.Traducao("sel_pri"));
								Db.pos_pri_span.put(name, "");
							}
						}
						else{
							Db.pos_pri.put(name, loc_bloco);
							player.sendMessage(Metodos.Traducao("sel_pri"));
							Db.pos_pri_span.put(name, "");
						}
					}
					else{
						Db.pos_pri.put(name, loc_bloco);
						player.sendMessage(Metodos.Traducao("sel_pri"));
						Db.pos_pri_span.put(name, "");
					}
				}//acaba a ação
			}
		}
		return;
	}
	}
	}
	@EventHandler
	void machado_dir(PlayerInteractEvent evento){
		if(Db.machadoAtivo){
		Player player = evento.getPlayer();
		if(Metodos.permVerifique(player, "Vipinho.axe")){
		if(player.getItemInHand().getType() == machado){
			
			evento.getAction();			
			Action click = Action.RIGHT_CLICK_BLOCK;
			String name = player.getName();
			
			if(evento.getAction() == click){
				//Começa a Ação
				
				Location loc_bloco = evento.getClickedBlock().getLocation();
				
				if(!Db.pos_seg.containsKey(name)){
					Db.pos_seg.put(name, loc_bloco);
					player.sendMessage(Metodos.Traducao("sel_seg"));
					Db.pos_seg_span.put(name, "");
				}
				else{
					if(Db.pos_seg.get(name).getX() == loc_bloco.getX()){
						if(Db.pos_seg.get(name).getY() == loc_bloco.getY()){
							if(Db.pos_seg.get(name).getZ() == loc_bloco.getZ()){
								if(Db.pos_seg_span.containsKey(name)){
									if(Db.pos_seg_span.get(name) == "ok"){return;}
								}
								player.sendMessage(Metodos.Traducao("alert_sel_seg_span"));
								Db.pos_seg_span.put(name, "ok");
							}
							else{
								Db.pos_seg.put(name, loc_bloco);
								player.sendMessage(Metodos.Traducao("sel_seg"));
								Db.pos_seg_span.put(name, "");
							}
						}
						else{
							Db.pos_seg.put(name, loc_bloco);
							player.sendMessage(Metodos.Traducao("sel_seg"));
							Db.pos_seg_span.put(name, "");
						}
					}
					else{
						Db.pos_seg.put(name, loc_bloco);
						player.sendMessage(Metodos.Traducao("sel_seg"));
						Db.pos_seg_span.put(name, "");
					}
				}//acaba a ação
			}
		}
		return;
	}
	}
	}
	@EventHandler
	void bloq_quebrar(BlockBreakEvent evento){
		if(Db.machadoAtivo){
		Player player = evento.getPlayer();
		if(Metodos.permVerifique(player, "Vipinho.axe")){
		if(evento.getPlayer().getItemInHand().getType() == machado){
			evento.setCancelled(true);
		}
		return;
	}
	}
	}
}
