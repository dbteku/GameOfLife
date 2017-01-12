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
		int numberOfXTiles = worldWidth / TILE_SIZE;
		int numberofYTiles = worldHeight / TILE_SIZE;
		tiles = new Tile[numberOfXTiles][numberofYTiles];
		for (int x = 0; x < numberOfXTiles; x++) {
			for (int y = 0; y < numberofYTiles; y++) {
				tiles[x][y] = new Tile(TILE_SIZE);
			}
		}
	}
}
