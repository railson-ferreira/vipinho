package com.mineupserver.plugin.vipinho.utilidades;

public class AreaSelecao {
	private Cordenadas min_pos;
	private Cordenadas max_pos;
	public AreaSelecao(Cordenadas pos1, Cordenadas pos2){
		double X1 = pos1.posX(); double Y1 = pos1.posY(); double Z1 = pos1.posZ();
		double X2 = pos2.posX(); double Y2 = pos2.posY(); double Z2 = pos2.posZ();
		double maxX; double maxY; double maxZ;
		double minX; double minY; double minZ;
		//
		if(X1 > X2){
			maxX = X1;
			minX = X2;
		} else {
			maxX = X2;
			minX = X1;
		}
		//
		if(Y1 > Y2){
			maxY = Y1;
			minY = Y2;
		} else {
			maxY = Y2;
			minY = Y1;
		}
		//
		if(Z1 > Z2){
			maxZ = Z1;
			minZ = Z2;
		} else {
			maxZ = Z2;
			minZ = Z1;
		}
		min_pos = new Cordenadas(minX, minY, minZ);
		max_pos = new Cordenadas(maxX, maxY, maxZ);
	}
	public Cordenadas pegarMin(){
		return min_pos;
	}
	public Cordenadas pegarMax(){
		return max_pos;		
	}
}

