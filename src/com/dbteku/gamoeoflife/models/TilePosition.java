package com.dbteku.gamoeoflife.models;

public class TilePosition {

	private int x;
	private int y;
	
	public TilePosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public boolean isNull(){
		return x >-1 && y >-1;
	}
	
}
