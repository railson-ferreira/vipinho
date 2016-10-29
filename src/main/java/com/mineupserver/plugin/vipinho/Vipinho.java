package com.mineupserver.plugin.vipinho;

import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import com.mineupserver.plugin.vipinho.commands.Vip;
import com.mineupserver.plugin.vipinho.commands.Vipadm;
import com.mineupserver.plugin.vipinho.utilidades.AreaEvento;
import com.mineupserver.plugin.vipinho.utilidades.Db;
import com.mineupserver.plugin.vipinho.utilidades.MachadoEvento;
import com.mineupserver.plugin.vipinho.utilidades.VerificarConfigs;
import com.mineupserver.plugin.vipinho.utilidades.sql.MySQL;

public class Vipinho extends JavaPlugin {
	public static Vipinho referencia;
	public Vipinho(){
		referencia = this;
	}
	@Override
	public void onEnable() {
		MySQL.inicializar();
		this.getCommand("vip").setExecutor(new Vip(this));
		this.getCommand("vipadm").setExecutor(new Vipadm(this));
		getServer().getPluginManager().registerEvents(new MachadoEvento(), this);
		getServer().getPluginManager().registerEvents(new AreaEvento(), this);
		Metodos.salvarKits();
		saveDefaultConfig();
		Db.recarregar();
		Db.carregarDelayKit();
		///ajustar configs///
		new VerificarConfigs();
	}
	@Override
    public void onDisable(){
		HandlerList.unregisterAll(this);
		Db.salvarDelayKit();
		MySQL.Finalizar();
    }
}

