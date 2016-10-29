package com.mineupserver.plugin.vipinho.utilidades;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import com.mineupserver.plugin.vipinho.Metodos;

public class AddCmdsPadroes {
	public static void AddComandosPadroes(){
		////"vip"////
		if(Db.cmds.containsKey("vip")){
			List<String> lista_cmds = Db.cmds.get("vip");
			if(!lista_cmds.contains("vip")){
			lista_cmds.add("vip");
			Db.cmds.put("vip", lista_cmds);
			}
		} else{
			List<String> cmd = new ArrayList<String>();
			cmd.add("vip");
			Db.cmds.put("vip", cmd);
		}
		///////////

		////"vip_ajuda"////
		if(Db.cmds.containsKey("vip_ajuda")){
			List<String> lista_cmds = Db.cmds.get("vip_ajuda");
			if(!lista_cmds.contains("ajuda")){
			lista_cmds.add("ajuda");
			Db.cmds.put("vip_ajuda", lista_cmds);
			}
		} else{
			List<String> cmd = new ArrayList<String>();
			cmd.add("ajuda");
			Db.cmds.put("vip_ajuda", cmd);
		}
		///////////

		////"vip_kit"////
		if(Db.cmds.containsKey("vip_kit")){
			List<String> lista_cmds = Db.cmds.get("vip_kit");
			if(!lista_cmds.contains("kit")){
			lista_cmds.add("kit");
			Db.cmds.put("vip_kit", lista_cmds);
			}
		} else{
			List<String> cmd = new ArrayList<String>();
			cmd.add("kit");
			Db.cmds.put("vip_kit", cmd);
		}
		///////////

		////"vip_tp"////
		if(Db.cmds.containsKey("vip_teleporte")){
			List<String> lista_cmds = Db.cmds.get("vip_teleporte");
			if(!lista_cmds.contains("tp")){
			lista_cmds.add("tp");
			Db.cmds.put("vip_teleporte", lista_cmds);
			}
		} else{
			List<String> cmd = new ArrayList<String>();
			cmd.add("tp");
			Db.cmds.put("vip_teleporte", cmd);
		}
		///////////

		////"vipadm"////
		if(Db.cmds.containsKey("vipadm")){
			List<String> lista_cmds = Db.cmds.get("vipadm");
			if(!lista_cmds.contains("vipadm")){
			lista_cmds.add("vipadm");
			Db.cmds.put("vipadm", lista_cmds);
			}
		} else{
			List<String> cmd = new ArrayList<String>();
			cmd.add("vipadm");
			Db.cmds.put("vipadm", cmd);
		}
		///////////

		////"vipadm_ajuda"////
		if(Db.cmds.containsKey("vipadm_ajuda")){
			List<String> lista_cmds = Db.cmds.get("vipadm_ajuda");
			if(!lista_cmds.contains("ajuda")){
			lista_cmds.add("ajuda");
			Db.cmds.put("vipadm_ajuda", lista_cmds);
			}
		} else{
			List<String> cmd = new ArrayList<String>();
			cmd.add("ajuda");
			Db.cmds.put("vipadm_ajuda", cmd);
		}
		///////////

		////"vipadm_grupo"////
		if(Db.cmds.containsKey("vipadm_grupo")){
			List<String> lista_cmds = Db.cmds.get("vipadm_grupo");
			if(!lista_cmds.contains("grupo")){
			lista_cmds.add("grupo");
			Db.cmds.put("vipadm_grupo", lista_cmds);
			}
		} else{
			List<String> cmd = new ArrayList<String>();
			cmd.add("grupo");
			Db.cmds.put("vipadm_grupo", cmd);
		}
		///////////

		////"vipadm_grupo_criar"////
		if(Db.cmds.containsKey("vipadm_grupo_criar")){
			List<String> lista_cmds = Db.cmds.get("vipadm_grupo_criar");
			if(!lista_cmds.contains("criar")){
			lista_cmds.add("criar");
			Db.cmds.put("vipadm_grupo_criar", lista_cmds);
			}
		} else{
			List<String> cmd = new ArrayList<String>();
			cmd.add("criar");
			Db.cmds.put("vipadm_grupo_criar", cmd);
		}
		///////////

		////"vipadm_grupo_remover"////
		if(Db.cmds.containsKey("vipadm_grupo_remover")){
			List<String> lista_cmds = Db.cmds.get("vipadm_grupo_remover");
			if(!lista_cmds.contains("remover")){
			lista_cmds.add("remover");
			Db.cmds.put("vipadm_grupo_remover", lista_cmds);
			}
		} else{
			List<String> cmd = new ArrayList<String>();
			cmd.add("remover");
			Db.cmds.put("vipadm_grupo_remover", cmd);
		}
		///////////

		////"vipadm_grupo_selecionar"////
		if(Db.cmds.containsKey("vipadm_grupo_selecionar")){
			List<String> lista_cmds = Db.cmds.get("vipadm_grupo_selecionar");
			if(!lista_cmds.contains("sel")){
			lista_cmds.add("sel");
			Db.cmds.put("vipadm_grupo_selecionar", lista_cmds);
			}
		} else{
			List<String> cmd = new ArrayList<String>();
			cmd.add("sel");
			Db.cmds.put("vipadm_grupo_selecionar", cmd);
		}
		///////////

		////"vipadm_grupo_lista"////
		if(Db.cmds.containsKey("vipadm_grupo_lista")){
			List<String> lista_cmds = Db.cmds.get("vipadm_grupo_lista");
			if(!lista_cmds.contains("lista")){
			lista_cmds.add("lista");
			Db.cmds.put("vipadm_grupo_lista", lista_cmds);
			}
		} else{
			List<String> cmd = new ArrayList<String>();
			cmd.add("lista");
			Db.cmds.put("vipadm_grupo_lista", cmd);
		}
		///////////

		////"vipadm_grupo_adicionar"////
		if(Db.cmds.containsKey("vipadm_grupo_adicionar")){
			List<String> lista_cmds = Db.cmds.get("vipadm_grupo_adicionar");
			if(!lista_cmds.contains("add")){
			lista_cmds.add("add");
			Db.cmds.put("vipadm_grupo_adicionar", lista_cmds);
			}
		} else{
			List<String> cmd = new ArrayList<String>();
			cmd.add("add");
			Db.cmds.put("vipadm_grupo_adicionar", cmd);
		}
		///////////

		////"vipadm_grupo_deletar"////
		if(Db.cmds.containsKey("vipadm_grupo_deletar")){
			List<String> lista_cmds = Db.cmds.get("vipadm_grupo_deletar");
			if(!lista_cmds.contains("del")){
			lista_cmds.add("del");
			Db.cmds.put("vipadm_grupo_deletar", lista_cmds);
			}
		} else{
			List<String> cmd = new ArrayList<String>();
			cmd.add("del");
			Db.cmds.put("vipadm_grupo_deletar", cmd);
		}
		///////////

		////"vipadm_grupo_add-del_jogador"////
		if(Db.cmds.containsKey("vipadm_grupo_add-del_jogador")){
			List<String> lista_cmds = Db.cmds.get("vipadm_grupo_add-del_jogador");
			if(!lista_cmds.contains("jogador")){
			lista_cmds.add("jogador");
			Db.cmds.put("vipadm_grupo_add-del_jogador", lista_cmds);
			}
		} else{
			List<String> cmd = new ArrayList<String>();
			cmd.add("jogador");
			Db.cmds.put("vipadm_grupo_add-del_jogador", cmd);
		}
		///////////

		////"vipadm_grupo_add-del_area"////
		if(Db.cmds.containsKey("vipadm_grupo_add-del_area")){
			List<String> lista_cmds = Db.cmds.get("vipadm_grupo_add-del_area");
			if(!lista_cmds.contains("area")){
			lista_cmds.add("area");
			Db.cmds.put("vipadm_grupo_add-del_area", lista_cmds);
			}
		} else{
			List<String> cmd = new ArrayList<String>();
			cmd.add("area");
			Db.cmds.put("vipadm_grupo_add-del_area", cmd);
		}
		///////////

		////"vipadm_grupo_add-del_kit"////
		if(Db.cmds.containsKey("vipadm_grupo_add-del_kit")){
			List<String> lista_cmds = Db.cmds.get("vipadm_grupo_add-del_kit");
			if(!lista_cmds.contains("kit")){
			lista_cmds.add("kit");
			Db.cmds.put("vipadm_grupo_add-del_kit", lista_cmds);
			}
		} else{
			List<String> cmd = new ArrayList<String>();
			cmd.add("kit");
			Db.cmds.put("vipadm_grupo_add-del_kit", cmd);
		}
		///////////

		////"vipadm_grupo_add-del_teleporte"////
		if(Db.cmds.containsKey("vipadm_grupo_add-del_teleporte")){
			List<String> lista_cmds = Db.cmds.get("vipadm_grupo_add-del_teleporte");
			if(!lista_cmds.contains("tp")){
			lista_cmds.add("tp");
			Db.cmds.put("vipadm_grupo_add-del_teleporte", lista_cmds);
			}
		} else{
			List<String> cmd = new ArrayList<String>();
			cmd.add("tp");
			Db.cmds.put("vipadm_grupo_add-del_teleporte", cmd);
		}
		///////////

		////"vipadm_grupo_informacoes"////
		if(Db.cmds.containsKey("vipadm_grupo_informacoes")){
			List<String> lista_cmds = Db.cmds.get("vipadm_grupo_informacoes");
			if(!lista_cmds.contains("info")){
			lista_cmds.add("info");
			Db.cmds.put("vipadm_grupo_informacoes", lista_cmds);
			}
		} else{
			List<String> cmd = new ArrayList<String>();
			cmd.add("info");
			Db.cmds.put("vipadm_grupo_informacoes", cmd);
		}
		///////////

		////"vipadm_jogador"////
		if(Db.cmds.containsKey("vipadm_jogador")){
			List<String> lista_cmds = Db.cmds.get("vipadm_jogador");
			if(!lista_cmds.contains("jogador")){
			lista_cmds.add("jogador");
			Db.cmds.put("vipadm_jogador", lista_cmds);
			}
		} else{
			List<String> cmd = new ArrayList<String>();
			cmd.add("jogador");
			Db.cmds.put("vipadm_jogador", cmd);
		}
		///////////

		////"vipadm_area"////
		if(Db.cmds.containsKey("vipadm_area")){
			List<String> lista_cmds = Db.cmds.get("vipadm_area");
			if(!lista_cmds.contains("area")){
			lista_cmds.add("area");
			Db.cmds.put("vipadm_area", lista_cmds);
			}
		} else{
			List<String> cmd = new ArrayList<String>();
			cmd.add("area");
			Db.cmds.put("vipadm_area", cmd);
		}
		///////////

		////"vipadm_area_criar"////
		if(Db.cmds.containsKey("vipadm_area_criar")){
			List<String> lista_cmds = Db.cmds.get("vipadm_area_criar");
			if(!lista_cmds.contains("criar")){
			lista_cmds.add("criar");
			Db.cmds.put("vipadm_area_criar", lista_cmds);
			}
		} else{
			List<String> cmd = new ArrayList<String>();
			cmd.add("criar");
			Db.cmds.put("vipadm_area_criar", cmd);
		}
		///////////

		////"vipadm_area_remover"////
		if(Db.cmds.containsKey("vipadm_area_remover")){
			List<String> lista_cmds = Db.cmds.get("vipadm_area_remover");
			if(!lista_cmds.contains("remover")){
			lista_cmds.add("remover");
			Db.cmds.put("vipadm_area_remover", lista_cmds);
			}
		} else{
			List<String> cmd = new ArrayList<String>();
			cmd.add("remover");
			Db.cmds.put("vipadm_area_remover", cmd);
		}
		///////////

		////"vipadm_area_recriar"////
		if(Db.cmds.containsKey("vipadm_area_recriar")){
			List<String> lista_cmds = Db.cmds.get("vipadm_area_recriar");
			if(!lista_cmds.contains("recriar")){
			lista_cmds.add("recriar");
			Db.cmds.put("vipadm_area_recriar", lista_cmds);
			}
		} else{
			List<String> cmd = new ArrayList<String>();
			cmd.add("recriar");
			Db.cmds.put("vipadm_area_recriar", cmd);
		}
		///////////

		////"vipadm_area_lista"////
		if(Db.cmds.containsKey("vipadm_area_lista")){
			List<String> lista_cmds = Db.cmds.get("vipadm_area_lista");
			if(!lista_cmds.contains("lista")){
			lista_cmds.add("lista");
			Db.cmds.put("vipadm_area_lista", lista_cmds);
			}
		} else{
			List<String> cmd = new ArrayList<String>();
			cmd.add("lista");
			Db.cmds.put("vipadm_area_lista", cmd);
		}
		///////////

		////"vipadm_area_tp"////
		if(Db.cmds.containsKey("vipadm_area_tp")){
			List<String> lista_cmds = Db.cmds.get("vipadm_area_tp");
			if(!lista_cmds.contains("tp")){
			lista_cmds.add("tp");
			Db.cmds.put("vipadm_area_tp", lista_cmds);
			}
		} else{
			List<String> cmd = new ArrayList<String>();
			cmd.add("tp");
			Db.cmds.put("vipadm_area_tp", cmd);
		}
		///////////

		////"vipadm_ajustar"////
		if(Db.cmds.containsKey("vipadm_ajustar")){
			List<String> lista_cmds = Db.cmds.get("vipadm_ajustar");
			if(!lista_cmds.contains("ajustar")){
			lista_cmds.add("ajustar");
			Db.cmds.put("vipadm_ajustar", lista_cmds);
			}
		} else{
			List<String> cmd = new ArrayList<String>();
			cmd.add("ajustar");
			Db.cmds.put("vipadm_ajustar", cmd);
		}
		///////////

		////"vipadm_area_machado"////
		if(Db.cmds.containsKey("vipadm_machado")){
			List<String> lista_cmds = Db.cmds.get("vipadm_machado");
			if(!lista_cmds.contains("machado")){
			lista_cmds.add("machado");
			Db.cmds.put("vipadm_machado", lista_cmds);
			}
		} else{
			List<String> cmd = new ArrayList<String>();
			cmd.add("machado");
			Db.cmds.put("vipadm_machado", cmd);
		}
		///////////

		////"vipadm_area_radar"////
		if(Db.cmds.containsKey("vipadm_radar")){
			List<String> lista_cmds = Db.cmds.get("vipadm_radar");
			if(!lista_cmds.contains("radar")){
			lista_cmds.add("radar");
			Db.cmds.put("vipadm_radar", lista_cmds);
			}
		} else{
			List<String> cmd = new ArrayList<String>();
			cmd.add("radar");
			Db.cmds.put("vipadm_radar", cmd);
		}
		///////////

		////"vipadm_area_recarregar"////
		if(Db.cmds.containsKey("vipadm_recarregar")){
			List<String> lista_cmds = Db.cmds.get("vipadm_recarregar");
			if(!lista_cmds.contains("recarregar")){
			lista_cmds.add("recarregar");
			Db.cmds.put("vipadm_recarregar", lista_cmds);
			}
		} else{
			List<String> cmd = new ArrayList<String>();
			cmd.add("recarregar");
			Db.cmds.put("vipadm_recarregar", cmd);
		}
		///////////
		
	}
	public static void colocarTraducaoNaAjuda(){
		Properties idioma = Metodos.pegarProperties("com/mineupserver/resources/languages/commands/us_cmds.properties");
		if(idioma != null){
			Set<Object> keys = idioma.keySet();
			for(Object key : keys){
				if(!Db.cmd_help.containsKey(key)){
					String valor = idioma.getProperty(key+"");
					Db.cmd_help.put(key+"", valor);
				}
			}
		}
		////Inserir o resto como br///
		////"vip"////
		if(!Db.cmd_help.containsKey("vip")){
			Db.cmd_help.put("vip", "vip");
		}
		/////////////
		////"vip_ajuda"////
		if(!Db.cmd_help.containsKey("vip_ajuda")){
			Db.cmd_help.put("vip_ajuda", "ajuda");
		}
		/////////////
		////"vip_kit"////
		if(!Db.cmd_help.containsKey("vip_kit")){
			Db.cmd_help.put("vip_kit", "kit");
		}
		/////////////
		////"vip_tp"////
		if(!Db.cmd_help.containsKey("vip_teleporte")){
			Db.cmd_help.put("vip_teleporte", "tp");
		}
		/////////////
		////"vipadm"////
		if(!Db.cmd_help.containsKey("vipadm")){
			Db.cmd_help.put("vipadm", "vipadm");
		}
		/////////////
		////"vipadm_ajuda"////
		if(!Db.cmd_help.containsKey("vipadm_ajuda")){
			Db.cmd_help.put("vipadm_ajuda", "ajuda");
		}
		/////////////
		////"vipadm_grupo"////
		if(!Db.cmd_help.containsKey("vipadm_grupo")){
			Db.cmd_help.put("vipadm_grupo", "grupo");
		}
		/////////////
		////"vipadm_grupo_criar"////
		if(!Db.cmd_help.containsKey("vipadm_grupo_criar")){
			Db.cmd_help.put("vipadm_grupo_criar", "criar");
		}
		/////////////
		////"vipadm_grupo_remover"////
		if(!Db.cmd_help.containsKey("vipadm_grupo_remover")){
			Db.cmd_help.put("vipadm_grupo_remover", "remover");
		}
		/////////////
		////"vipadm_grupo_selecionar"////
		if(!Db.cmd_help.containsKey("vipadm_grupo_selecionar")){
			Db.cmd_help.put("vipadm_grupo_selecionar", "selecionar");
		}
		/////////////
		////"vipadm_grupo_lista"////
		if(!Db.cmd_help.containsKey("vipadm_grupo_lista")){
			Db.cmd_help.put("vipadm_grupo_lista", "lista");
		}
		/////////////
		////"vipadm_grupo_adicionar"////
		if(!Db.cmd_help.containsKey("vipadm_grupo_adicionar")){
			Db.cmd_help.put("vipadm_grupo_adicionar", "add");
		}
		/////////////
		////"vipadm_grupo_deletar"////
		if(!Db.cmd_help.containsKey("vipadm_grupo_deletar")){
			Db.cmd_help.put("vipadm_grupo_deletar", "del");
		}
		/////////////
		////"vipadm_grupo_add-del_jogador"////
		if(!Db.cmd_help.containsKey("vipadm_grupo_add-del_jogador")){
			Db.cmd_help.put("vipadm_grupo_add-del_jogador", "jogador");
		}
		/////////////
		////"vipadm_grupo_add-del_area"////
		if(!Db.cmd_help.containsKey("vipadm_grupo_add-del_area")){
			Db.cmd_help.put("vipadm_grupo_add-del_area", "area");
		}
		/////////////
		////"vipadm_grupo_add-del_kit"////
		if(!Db.cmd_help.containsKey("vipadm_grupo_add-del_kit")){
			Db.cmd_help.put("vipadm_grupo_add-del_kit", "kit");
		}
		/////////////
		////"vipadm_grupo_add-del_teleporte"////
		if(!Db.cmd_help.containsKey("vipadm_grupo_add-del_teleporte")){
			Db.cmd_help.put("vipadm_grupo_add-del_teleporte", "tp");
		}
		/////////////
		////"vipadm_grupo_informacoes"////
		if(!Db.cmd_help.containsKey("vipadm_grupo_informacoes")){
			Db.cmd_help.put("vipadm_grupo_informacoes", "info");
		}
		/////////////
		////"vipadm_jogador"////
		if(!Db.cmd_help.containsKey("vipadm_jogador")){
			Db.cmd_help.put("vipadm_jogador", "jogador");
		}
		/////////////
		////"vipadm_area"////
		if(!Db.cmd_help.containsKey("vipadm_area")){
			Db.cmd_help.put("vipadm_area", "area");
		}
		/////////////
		////"vipadm_area_criar"////
		if(!Db.cmd_help.containsKey("vipadm_area_criar")){
			Db.cmd_help.put("vipadm_area_criar", "criar");
		}
		/////////////
		////"vipadm_area_remover"////
		if(!Db.cmd_help.containsKey("vipadm_area_remover")){
			Db.cmd_help.put("vipadm_area_remover", "remover");
		}
		/////////////
		////"vipadm_area_recriar"////
		if(!Db.cmd_help.containsKey("vipadm_area_recriar")){
			Db.cmd_help.put("vipadm_area_recriar", "recriar");
		}
		/////////////
		////"vipadm_area_lista"////
		if(!Db.cmd_help.containsKey("vipadm_area_lista")){
			Db.cmd_help.put("vipadm_area_lista", "lista");
		}
		/////////////
		////"vipadm_area_tp"////
		if(!Db.cmd_help.containsKey("vipadm_area_tp")){
			Db.cmd_help.put("vipadm_area_tp", "tp");
		}
		/////////////
		////"vipadm_ajustar"////
		if(!Db.cmd_help.containsKey("vipadm_ajustar")){
			Db.cmd_help.put("vipadm_ajustar", "ajustar");
		}
		/////////////
		////"vipadm_machado"////
		if(!Db.cmd_help.containsKey("vipadm_machado")){
			Db.cmd_help.put("vipadm_machado", "machado");
		}
		/////////////
		////"vipadm_radar"////
		if(!Db.cmd_help.containsKey("vipadm_radar")){
			Db.cmd_help.put("vipadm_radar", "radar");
		}
		/////////////
		////"vipadm_recarregar"////
		if(!Db.cmd_help.containsKey("vipadm_recarregar")){
			Db.cmd_help.put("vipadm_recarregar", "recarregar");
		}
		/////////////
		
	}
}
