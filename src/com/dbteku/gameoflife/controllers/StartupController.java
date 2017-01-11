package com.dbteku.gameoflife.controllers;

import com.dbteku.gamoeoflife.models.GameOfLifeWorld;
import com.dbteku.gamoeoflife.models.Tile;
import com.dbteku.orbit.core.OrbitEngine;
import com.dbteku.orbit.core.World;

public class StartupController {

	private OrbitEngine engine;
	private World world;
	
	public StartupController() {
		engine = new OrbitEngine();
		world = new GameOfLifeWorld();
	}
	
	public void start(){
		world.addObject(new Tile(), 500 ,250);
		engine.loadWorld(world);
	}
	
}
