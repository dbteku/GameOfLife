package com.dbteku.gameoflife.controllers;

import com.dbteku.gamoeoflife.models.GameOfLifeWorld;
import com.dbteku.orbit.core.OrbitEngine;
import com.dbteku.orbit.core.World;

public class StartupController {

	private OrbitEngine engine;
	private World world;
	private MapController controller;
	
	public StartupController() {
		engine = new OrbitEngine();
		world = new GameOfLifeWorld();
		controller = new MapController(world, 10);
	}
	
	public void start(){
		engine.loadWorld(world);
		controller.createTiles();
		engine.start();
	}
	
}
