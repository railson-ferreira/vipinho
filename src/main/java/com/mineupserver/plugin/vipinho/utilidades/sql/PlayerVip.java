package com.mineupserver.plugin.vipinho.utilidades.sql;

import java.util.ArrayList;

public class PlayerVip {
	private final String id;
	private final String Nick;
	private final int VIP_id;
	private final long DATA_ativacao;
	private final long DATA_validade;
	public PlayerVip(String id, String Nick, int VIP_id, long DATA_ativacao, long DATA_validade){
		this.id = id;
		this.Nick = Nick;
		this.VIP_id = VIP_id;
		this.DATA_ativacao = DATA_ativacao;
		this.DATA_validade = DATA_validade;
		vips.add(this);
	}
	public String PegarId(){
		return id;
	}
	public String PegarNick(){
		return Nick;
	}
	public int PegarVIP_id(){
		return VIP_id;
	}
	public long PegarDATA_ativacao(){
		return DATA_ativacao;
	}
	public long PegarDATA_validade(){
		return DATA_validade;
	}
	//////////////
	//////////////
	////STATIC////
	//////////////
	//////////////
	private static ArrayList<PlayerVip> vips = new ArrayList<PlayerVip>();

	public static ArrayList<PlayerVip> PegarVips(){
		return vips;
	}
	public static void Limpar(){
		vips = new ArrayList<PlayerVip>();
	}
	public static ArrayList<PlayerVip> SubstituirVips(ArrayList<PlayerVip> substituto){
		ArrayList<PlayerVip> retorno = vips;
		vips = substituto;
		return retorno;
	}
	public static boolean Adicionar(PlayerVip vip){
		return vips.add(vip);
	}
	public static boolean RemoverPorNick(String Nick){
		boolean retorno = false;
		for(PlayerVip vip : vips){
			if(vip.PegarNick() == Nick){
				vips.remove(vip);
				retorno = true;
			}
		}
		return retorno;
	}
	public static boolean RemoverPorId(String id){
		boolean retorno = false;
		for(PlayerVip vip : vips){
			if(vip.PegarId() == id){
				vips.remove(vip);
				retorno = true;
			}
		}
		return retorno;
	}
	public static ArrayList<PlayerVip> PegarPorId(String id){
		ArrayList<PlayerVip> retorno = null;
		for(PlayerVip vip : vips){
			if(vip.PegarId() == id){
				if(retorno == null){
					retorno = new ArrayList<PlayerVip>();
				}
				retorno.add(vip);
			}
		}
		return retorno;
	}
	public static ArrayList<PlayerVip> PegarPorNick(String Nick){
		ArrayList<PlayerVip> retorno = null;
		for(PlayerVip vip : vips){
			if(vip.PegarNick() == Nick){
				if(retorno == null){
					retorno = new ArrayList<PlayerVip>();
				}
				retorno.add(vip);
			}
		}
		return retorno;
	}
	

}
