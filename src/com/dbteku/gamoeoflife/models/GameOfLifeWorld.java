package com.dbteku.gamoeoflife.models;

import com.dbteku.orbit.core.World;

import javafx.scene.paint.Color;

public class GameOfLifeWorld extends World{

	public GameOfLifeWorld() {
		super(1000,750,"Game Of Life");
		setBackgroundColor(Color.BLACK);
	}
	
}
