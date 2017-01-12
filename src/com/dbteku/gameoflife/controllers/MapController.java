package com.dbteku.gameoflife.controllers;

import com.dbteku.gamoeoflife.models.Tile;
import com.dbteku.orbit.core.World;

public class MapController {
	
	private final int TILE_SIZE;
	private Tile[][] tiles;
	private World world;
	
	public MapController(World world, int tileSize) {
		this.TILE_SIZE = tileSize;
		this.world = world;
	}
	
	public void createTiles(){
		int worldWidth = world.getWidth();
		int worldHeight = world.getHeight();
	}

}
