package com.dbteku.gameoflife.controllers;

import com.dbteku.gamoeoflife.models.GameOfLifeWorld;
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
		engine.loadWorld(world);
	}
	
}
