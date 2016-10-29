package com.mineupserver.plugin.vipinho.commands;

import java.util.HashMap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.mineupserver.plugin.vipinho.Metodos;
import com.mineupserver.plugin.vipinho.Vipinho;
import com.mineupserver.plugin.vipinho.commands.vipadm.Area;
import com.mineupserver.plugin.vipinho.commands.vipadm.Grupo;
import com.mineupserver.plugin.vipinho.commands.vipadm.Jogador;
import com.mineupserver.plugin.vipinho.utilidades.Db;
import com.mineupserver.plugin.vipinho.utilidades.VerificarConfigs;

public class Vipadm implements CommandExecutor  {
	Vipinho plugin;
	public Vipadm(Vipinho plugin) {
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		HashMap<String, String> cmdhelp = Db.cmd_help;
		String ajuda = Metodos.Traducao("ajuda_vipadm")+"\n"
				+ Metodos.Traducao("ajuda_vipadm_ajuda", "/vipadm "+cmdhelp.get("vipadm_ajuda"))+"\n"
				+ Metodos.Traducao("ajuda_vipadm_grupo", "/vipadm "+cmdhelp.get("vipadm_grupo"))+"\n"
				+ Metodos.Traducao("ajuda_vipadm_area", "/vipadm "+cmdhelp.get("vipadm_area"))+"\n"
				+ Metodos.Traducao("ajuda_vipadm_ajustar", "/vipadm "+cmdhelp.get("vipadm_ajustar"))+"\n"
				+ Metodos.Traducao("ajuda_vipadm_machado", "/vipadm "+cmdhelp.get("vipadm_machado"))+"\n"
				+ Metodos.Traducao("ajuda_vipadm_recarregar", "/vipadm "+cmdhelp.get("vipadm_recarregar"));
		if(Metodos.permVerifique(sender, "Vipinho.vipadm")){
			if (args.length > 0){
				if(Db.cmd("vipadm_ajuda", args[0])){
					if(Metodos.permVerifique(sender, "Vipinho.vipadm.*") || Metodos.permVerifique(sender, "Vipinho.vipadm.help")){
						sender.sendMessage(ajuda); return true;
					} else {
						sender.sendMessage(Metodos.Traducao("mensagem_perm"));return true;
					}
				}
				
				if(Db.cmd("vipadm_grupo", args[0])){
					if(Metodos.permVerifique(sender, "Vipinho.vipadm.*") || Metodos.permVerifique(sender, "Vipinho.vipadm.group")){
						new Grupo(sender, cmd, label, args, plugin); return true;
					} else {
						sender.sendMessage(Metodos.Traducao("mensagem_perm"));return true;
					}
				}
				
				if(Db.cmd("vipadm_area", args[0])){
					if(Metodos.permVerifique(sender, "Vipinho.vipadm.*") || Metodos.permVerifique(sender, "Vipinho.vipadm.area")){
						new Area(sender, cmd, label, args, plugin); return true;
					} else {
						sender.sendMessage(Metodos.Traducao("mensagem_perm"));return true;
					}
				}
				
				if(Db.cmd("vipadm_jogador", args[0])){
					if(Metodos.permVerifique(sender, "Vipinho.vipadm.*") || Metodos.permVerifique(sender, "Vipinho.vipadm.player")){
						new Jogador(sender, cmd, label, args, plugin); return true;
					} else {
						sender.sendMessage(Metodos.Traducao("mensagem_perm"));return true;
					}
				}
				if(Db.cmd("vipadm_ajustar", args[0])){
					if(Metodos.permVerifique(sender, "Vipinho.vipadm.*") || Metodos.permVerifique(sender, "Vipinho.vipadm.ajuste")){
					new VerificarConfigs();
					sender.sendMessage(Metodos.Traducao("ajuste"));
					return true;
					} else {
						sender.sendMessage(Metodos.Traducao("mensagem_perm"));return true;
					}
				}
				if(Db.cmd("vipadm_machado", args[0])){
					if(Metodos.permVerifique(sender, "Vipinho.vipadm.*") || Metodos.permVerifique(sender, "Vipinho.vipadm.machado")){
					if(Db.machadoAtivo){
						Db.machadoAtivo = false;
						Db.limparMachado();
						sender.sendMessage(Metodos.Traducao("machado_desativado"));
					} else{
						Db.machadoAtivo = true;
						sender.sendMessage(Metodos.Traducao("machado_ativado"));
					}
					return true;
					} else {
						sender.sendMessage(Metodos.Traducao("mensagem_perm"));return true;
					}
				}
				if(Db.cmd("vipadm_radar", args[0])){
					if(Metodos.sePlayerRemetente(sender)){
						if(Metodos.permVerifique(sender, "Vipinho.vipadm.*") || Metodos.permVerifique(sender, "Vipinho.vipadm.radar")){
							if(Db.radar.containsKey(sender.getName())){
								if(Db.radar.get(sender.getName())){
									Db.radar.put(sender.getName(), false);
									sender.sendMessage(Metodos.Traducao("radar_desativado"));
									return true;
								} else{
									Db.radar.put(sender.getName(), true);
									sender.sendMessage(Metodos.Traducao("radar_ativado"));
									return true;
								}
							} else {
								Db.radar.put(sender.getName(), true);
								sender.sendMessage(Metodos.Traducao("radar_ativado"));
								return true;
							}
						} else {
							sender.sendMessage(Metodos.Traducao("mensagem_perm"));return true;
						}
					} else {
						sender.sendMessage(Metodos.Traducao("alerta_remetente_errado"));
						return true;
					}
				}
				if(Db.cmd("vipadm_recarregar", args[0])){
					if(Metodos.permVerifique(sender, "Vipinho.vipadm.*") || Metodos.permVerifique(sender, "Vipinho.vipadm.machado")){
						Db.recarregar();
						sender.sendMessage(Metodos.Traducao("recarregar"));
						return true;
					} else {
						sender.sendMessage(Metodos.Traducao("mensagem_perm"));return true;
					}
				}
				sender.sendMessage(Metodos.Traducao("erro_cmd", "/vipadm "+cmdhelp.get("vipadm_ajuda")));
				return true;
			}
			else if(args.length < 1){
				sender.sendMessage(ajuda);
				return true;
			}
			else{
				sender.sendMessage(Metodos.Traducao("erro_desconhecido", "/vipadm "+cmdhelp.get("vipadm_ajuda")));
				return true;
			}
		} else {
		sender.sendMessage(Metodos.Traducao("mensagem_perm"));return true;
		}
	}
}
