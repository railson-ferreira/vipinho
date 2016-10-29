package com.mineupserver.plugin.vipinho.commands.vipadm;

import java.util.Date;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.mineupserver.plugin.vipinho.Metodos;
import com.mineupserver.plugin.vipinho.Vipinho;
import com.mineupserver.plugin.vipinho.utilidades.sql.PlayerVip;

public class Jogador {
	public Jogador(CommandSender sender, Command cmd, String label, String[] args, Vipinho plugin){
		sender.sendMessage("ID | Nick | VipID | DataAtivacao | DataValidade");
		for(PlayerVip vip : PlayerVip.PegarVips()){
			String id = vip.PegarId();
			String nick = vip.PegarNick();
			int vipid = vip.PegarVIP_id();
			long ativacao = vip.PegarDATA_ativacao();
			long validade = vip.PegarDATA_validade();
			
			if(nick != null ){
				if( !nick.isEmpty()){
					boolean aticavao_bool = false;
					if(ativacao > 0 && validade > 0 ){
						aticavao_bool =true;
					}
					Metodos.enviaTeste(ativacao+"  "+validade);
					if(aticavao_bool){
						Date data = new Date(ativacao);
						Date data1 = new Date(validade);
						sender.sendMessage(id+" | "+nick+" | "+vipid+" | "+data.getDay()+"/"+data.getMonth()+"/"+data.getYear()+" | "+data1.getDay()+"/"+data1.getMonth()+"/"+data1.getYear());
						
					} else {
						sender.sendMessage(id+" | "+nick+" | "+vipid);
					}
				}
			}
		}
		/*
		HashMap<String, String> cmdhelp = Db.cmd_help;
		
		if (args.length > 1){
			///////////////////////
			if(Db.cmd("vipadm_grupo_criar", args[1])){//create
			}//fim//create
			///////////////////////
			else if(Db.cmd("vipadm_grupo_remover", args[1])){//remove
			}//fim//remove
			///////////////////////
			else if(Db.cmd("vipadm_grupo_selecionar", args[1])){//sell
			}//fim//sell
			///////////////////////
			else if(Db.cmd("vipadm_grupo_lista", args[1])){//List			
			}
			////////Fim//List/////////////
			
			///////sel////////
			else if(Db.cmd("vipadm_grupo_adicionar", args[1])){//add
			}//fim//add
			///////////////////
			else if(Db.cmd("vipadm_grupo_deletar", args[1])){//dell
			}//fim//dell
			/////////////////////
			else if(Db.cmd("vipadm_grupo_informacoes", args[1])){//info
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
		*/
	}
}
