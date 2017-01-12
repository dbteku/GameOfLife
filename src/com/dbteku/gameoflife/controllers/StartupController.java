package com.dbteku.gameoflife.controllers;

import com.dbteku.gamoeoflife.models.GameOfLifeWorld;
import com.dbteku.gamoeoflife.models.MapUpdater;
import com.dbteku.orbit.core.OrbitEngine;
import com.dbteku.orbit.core.World;

public class StartupController {

	private OrbitEngine engine;
	private World world;
	private MapController controller;
	private MapUpdater updater;
	
	public StartupController() {
		engine = new OrbitEngine();
		world = new GameOfLifeWorld();
		controller = new MapController(world, 10);
		updater = new MapUpdater(controller);
	}
	
	public void start(){
		controller.createTiles();
		world.addObject(updater, 0, 0);
		engine.loadWorld(world);
		engine.start();
	}
	
}
