package com.mineupserver.plugin.vipinho.commands.vip;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import com.mineupserver.plugin.vipinho.Metodos;
import com.mineupserver.plugin.vipinho.Vipinho;
import com.mineupserver.plugin.vipinho.utilidades.Db;

public class Tp {

	public Tp(Command cmd, String label, String[] args, Vipinho plugin, Player player) {
		HashMap<String, String> cmdhelp = Db.cmd_help;
		List<String> teleportes_grupo = new ArrayList<String>();
		ConfigurationSection grupos = Metodos.PegarConfSessao("grupos", Metodos.PegarConfig("grupos.yml"));
		for(String grupo : Metodos.gruposVerifique(player.getName())){
			teleportes_grupo.addAll(grupos.getStringList(grupo+".teleportes"));
		}
		if (args.length > 1){
			ConfigurationSection config_tps = Metodos.PegarConfSessao("teleportes", Metodos.PegarConfig("dados/teleportes.yml"));
			Set<String> name_teleportes = config_tps.getKeys(false);
			if(teleportes_grupo.contains(args[1]) && name_teleportes.contains(args[1])){
				String mundo_string = config_tps.getString(args[1]+".mundo");
				String x_string = config_tps.getString(args[1]+".x");
				String y_string = config_tps.getString(args[1]+".y");
				String z_string = config_tps.getString(args[1]+".z");
				String pitch_string = config_tps.getString(args[1]+".pitch");
				String yaw_string = config_tps.getString(args[1]+".yaw");
				if(mundo_string == null || x_string == null || y_string == null || z_string == null || pitch_string == null || yaw_string== null ){
					player.sendMessage(Metodos.Traducao("erro_tp_wrong"));
					return;
				}
				World mundo = plugin.getServer().getWorld(mundo_string);
				double x = Double.parseDouble(x_string);
				double y = Double.parseDouble(y_string);
				double z = Double.parseDouble(z_string);
				float pitch = Float.parseFloat(pitch_string);
				float yaw = Float.parseFloat(yaw_string);
				if(!plugin.getServer().getWorlds().contains(mundo)){
					player.sendMessage(Metodos.Traducao("erro_tp_wrong"));
					return;
				}
				Location loc = new Location(mundo, x, y, z, yaw, pitch);
				player.teleport(loc);
			} else {player.sendMessage(Metodos.Traducao("erro_tp_inexiste"));}
		}
		else if(args.length < 2){
			player.sendMessage("§0----------"+Metodos.Traducao("lista_tps")+"§0----------");
			for(String teleporte : teleportes_grupo){player.sendMessage(teleporte);}
			player.sendMessage("§0-------------------------------------");
		}
		else{
			player.sendMessage(Metodos.Traducao("erro_desconhecido", "/vip "+cmdhelp.get("vip_teleporte")+" ["+Metodos.Traducao("nome")+"]"));
		}
		
	}

}
