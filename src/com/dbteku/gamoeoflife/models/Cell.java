package com.dbteku.gamoeoflife.models;

public class Cell {
	private boolean state;
	private int X;
	private int Y;
	
	
	public Cell(boolean state, int X, int Y){
		this.state = state;
		this.X = X;
		this.Y = Y;
	}


	public boolean isState() {
		return state;
	}


	public void setState(boolean state) {
		this.state = state;
	}


	public int getX() {
		return X;
	}


	public void setX(int x) {
		X = x;
	}


	public int getY() {
		return Y;
	}


	public void setY(int y) {
		Y = y;
	}

}
