package com.mineupserver.plugin.vipinho.commands;

import java.util.HashMap;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.mineupserver.plugin.vipinho.Metodos;
import com.mineupserver.plugin.vipinho.Vipinho;
import com.mineupserver.plugin.vipinho.commands.vip.Kit;
import com.mineupserver.plugin.vipinho.commands.vip.Tp;
import com.mineupserver.plugin.vipinho.utilidades.Db;
public class Vip implements CommandExecutor  {
	Vipinho plugin;
	public Vip(Vipinho plugin) {
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		HashMap<String, String> cmdhelp = Db.cmd_help;
		String ajuda = Metodos.Traducao("ajuda_vip")+"\n"
				+ Metodos.Traducao("ajuda_vip_ajuda", "/vip "+cmdhelp.get("vip_ajuda"))+"\n"
				+ Metodos.Traducao("ajuda_vip_kit", "/vip "+cmdhelp.get("vip_kit"))+"\n"
				+ Metodos.Traducao("ajuda_vip_tp", "/vip "+cmdhelp.get("vip_teleporte")+" ["+Metodos.Traducao("nome")+"]");
		if(Metodos.permVerifique(sender, "Vipinho.vip") || Metodos.permVerifique(sender, "Vipinho.vip.*")){
		if (sender instanceof Player) {
			Player player = (Player) sender;
			List<String> grupos = Metodos.vipVerifique(player.getName());
		  if(grupos != null){
			if(args.length < 1){
				player.sendMessage(ajuda);
	        	return true;
	        }
			else if (args.length > 0){//Comandos do "Vip"
				if (Db.cmd("vip_ajuda", args[0])){
					if(Metodos.permVerifique(sender, "Vipinho.vip.*") || Metodos.permVerifique(sender, "Vipinho.vip.help")){
						player.sendMessage(ajuda);return true;
					} else {sender.sendMessage(Metodos.Traducao("mensagem_perm"));return true;}
				}
				if (Db.cmd("vip_kit", args[0])){
					if(Metodos.permVerifique(sender, "Vipinho.vip.*") || Metodos.permVerifique(sender, "Vipinho.vip.kit")){
						new Kit(cmd, label, args, plugin, player);return true;
					} else {sender.sendMessage(Metodos.Traducao("mensagem_perm"));return true;}
				}//Fim do "kit"
				if(Db.cmd("vip_teleporte", args[0])){
					if(Metodos.permVerifique(sender, "Vipinho.vip.*") || Metodos.permVerifique(sender, "Vipinho.vip.tp")){
						new Tp(cmd, label, args, plugin, player);return true;
					} else {sender.sendMessage(Metodos.Traducao("mensagem_perm"));return true;}
				}
				player.sendMessage(Metodos.Traducao("erro_cmd", "/vip "+cmdhelp.get("vip_ajuda")));return true;
				
			}//Fim do Comandos do "Vip"
			
			else{//erro desconhecido
				player.sendMessage(Metodos.Traducao("erro_desconhecido", "/vip "+cmdhelp.get("vip_ajuda")));
				return true;
			}
		  } else {sender.sendMessage(Db.mensagem_no_vip);return true;}
		}
		else {//usando comando sem ser player
			sender.sendMessage(Metodos.Traducao("alerta_remetente_errado"));
			return true;
		}
		} else{
			sender.sendMessage(Metodos.Traducao("mensagem_perm"));
			return true;
		}
	}
}
