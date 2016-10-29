package com.mineupserver.plugin.vipinho.utilidades;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import com.mineupserver.plugin.vipinho.Metodos;

public class VerificarConfigs {

	public VerificarConfigs() {
		FileConfiguration config_grupos = Metodos.PegarConfig("grupos.yml");
		ConfigurationSection sessao_grupos = Metodos.PegarConfSessao("grupos", config_grupos);
		Set<String> set_nome_grupos = sessao_grupos.getKeys(false);

		FileConfiguration config_areas = Metodos.PegarConfig("dados/areas.yml");
		ConfigurationSection sessao_areas = Metodos.PegarConfSessao("areas", config_areas);
		Set<String> set_nome_areas = sessao_areas.getKeys(false);

		FileConfiguration config_teleportes = Metodos.PegarConfig("dados/teleportes.yml");
		ConfigurationSection sessao_teleportes = Metodos.PegarConfSessao("teleportes", config_teleportes);
		Set<String> set_nome_teleportes = sessao_teleportes.getKeys(false);

		FileConfiguration config_kits = Metodos.PegarConfig("kits.yml");
		ConfigurationSection sessao_kits = Metodos.PegarConfSessao("kits", config_kits);
		Set<String> set_nome_kits = sessao_kits.getKeys(false);
		
		List<String> todos_teleportes = new ArrayList<String>();
		
		boolean teste = false;
		
		for(String nome_grupo : set_nome_grupos){
			ConfigurationSection sessao_grupo = Metodos.PegarConfSessao(nome_grupo, sessao_grupos);
			
			List<String> lista_teleportes = sessao_grupo.getStringList("teleportes");
			List<String> lista_areas = sessao_grupo.getStringList("areas");
			List<String> lista_kits = sessao_grupo.getStringList("kits");
			
			todos_teleportes.addAll(lista_teleportes);

			List<String> lista_teleportes_teste = new ArrayList<String>();
			List<String> lista_areas_teste = new ArrayList<String>();
			List<String> lista_kits_teste = new ArrayList<String>();

			lista_teleportes_teste.addAll(lista_teleportes);
			lista_areas_teste.addAll(lista_areas);
			lista_kits_teste.addAll(lista_kits);
			
			for(String teleporte : lista_teleportes_teste){
				if(!set_nome_teleportes.contains(teleporte)){
					lista_teleportes.remove(teleporte);
					teste = true;
				}
			}
			
			for(String area : lista_areas_teste){
				if(!set_nome_areas.contains(area)){
					lista_areas.remove(area);
					teste = true;
				}
			}
			
			for(String kit : lista_kits_teste){
				if(!set_nome_kits.contains(kit)){
					lista_kits.remove(kit);
					teste = true;
				}
			}

			sessao_grupo.set("teleportes", lista_teleportes);
			sessao_grupo.set("areas", lista_areas);
			sessao_grupo.set("kits", lista_kits);
		}
		
		boolean teste_teleporte = false;
		
		for(String nome_teleporte : set_nome_teleportes){
			if(!todos_teleportes.contains(nome_teleporte)){
				sessao_teleportes.set(nome_teleporte, null);
				teste_teleporte = true;
			}
		}
		if(teste_teleporte){
			Metodos.salvarConfig("dados/teleportes.yml", config_teleportes);
		}
		
		if(teste){
			Metodos.salvarConfig("grupos.yml", config_grupos);
		}
		
	}
}
