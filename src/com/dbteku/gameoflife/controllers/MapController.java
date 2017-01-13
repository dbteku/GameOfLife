package com.dbteku.gameoflife.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dbteku.gamoeoflife.models.Tile;
import com.dbteku.gamoeoflife.models.TilePosition;
import com.dbteku.orbit.core.World;

public class MapController {

	private final int TILE_SIZE;
	private Tile[][] tiles;
	private Tile[][] copyList = new Tile[100][75];
	private World world;
	private Map<Tile, TilePosition> locations;
	private int xBound;
	private int yBound;

	public MapController(World world, int tileSize) {
		this.TILE_SIZE = tileSize;
		this.world = world;
		locations = new HashMap<>();
		xBound = 0;
		yBound = 0;
	}

	public void createTiles(){
		int worldWidth = world.getWidth();
		int worldHeight = world.getHeight();
		int numberOfXTiles = worldWidth / TILE_SIZE;
		int numberofYTiles = worldHeight / TILE_SIZE;
		xBound = numberOfXTiles;
		yBound = numberofYTiles;
		tiles = new Tile[numberOfXTiles][numberofYTiles];
		Tile lastTile = null;
		for (int x = 0; x < numberOfXTiles; x++) {
			for (int y = 0; y < numberofYTiles; y++) {
				int xPosition = 0;
				int yPosition = 0;
				Tile tile= new Tile(x + "," + y, TILE_SIZE);
				tiles[x][y] = tile;
				locations.put(tile, new TilePosition(x, y));
				if(lastTile == null){
					lastTile = tiles[x][y];
					xPosition = (int) ((x + 1) * tiles[x][y].getImage().getWidth()/2);
					yPosition = (int) ((y + 1) * tiles[x][y].getImage().getHeight()/2);
				}else{
					xPosition = (int) ((x + 1) * TILE_SIZE - tiles[x][y].getImage().getWidth()/2);
					yPosition = (int) ((y + 1) * TILE_SIZE - tiles[x][y].getImage().getHeight()/2);
				}
				world.addObject(tiles[x][y], xPosition, yPosition);
			}
		}
	}

	public List<Tile> getNeighbors(Tile tile){
		List<Tile> neighbors = new ArrayList<>();
		List<Tile> tryToAdd = new ArrayList<>();
		TilePosition position = getPostition(tile);
		if(position != null){
			tryToAdd.add(getLeft(position));
			tryToAdd.add(getLeftTop(position));
			tryToAdd.add(getTop(position));
			tryToAdd.add(getRightTop(position));
			tryToAdd.add(getRight(position));
			tryToAdd.add(getRightBottom(position));
			tryToAdd.add(getBottom(position));
			tryToAdd.add(getLeftBottom(position));
		}
		for (Tile possible : tryToAdd) {
			if(possible != null){
				neighbors.add(possible);
			}
		}
		return neighbors;
	}
	
	public List<Tile> getAliveList(){
		List<Tile> alive = new ArrayList<>();
		for (int y = 0; y < yBound; y++) {
			for (int x = 0; x < xBound; x++) {
				Tile tile = tiles[x][y];
				if(tile.isAlive()){
					alive.add(tile);
				}
			}	
		}
		return alive;
	}
	
	private TilePosition getPostition(Tile tile){
		TilePosition position = null;
		if(locations.containsKey(tile)){
			position = locations.get(tile);
		}
		return position;
	}
	
	private Tile getLeft(TilePosition position){
		Tile tile = null;
		int x = position.getX();
		int y = position.getY();
		int newX = x - 1;
		if(newX >=0){
			tile = tiles[newX][y];
		}
		return tile;
	}
	
	private Tile getLeftTop(TilePosition position){
		Tile tile = null;
		int x = position.getX();
		int y = position.getY();
		int newX = x - 1;
		int newY = y - 1;
		if(newX >= 0 && newY >= 0){
			tile = tiles[newX][newY];
		}
		return tile;
	}
	
	private Tile getTop(TilePosition position){
		Tile tile = null;
		int x = position.getX();
		int y = position.getY();
		int newY = y - 1;
		if(newY >= 0){
			tile = tiles[x][newY];
		}
		return tile;
	}
	
	private Tile getRightTop(TilePosition position){
		Tile tile = null;
		int x = position.getX();
		int y = position.getY();
		int newX = x + 1;
		int newY = y - 1;
		if(newX < xBound && newY >= 0){
			tile = tiles[newX][newY];
		}
		return tile;
	}
	
	private Tile getRight(TilePosition position){
		Tile tile = null;
		int x = position.getX();
		int y = position.getY();
		int newX = x + 1;
		if(newX < xBound){
			tile = tiles[newX][y];
		}
		return tile;
	}
	
	private Tile getRightBottom(TilePosition position){
		Tile tile = null;
		int x = position.getX();
		int y = position.getY();
		int newX = x + 1;
		int newY = y + 1;
		if(newX < xBound && newY < yBound){
			tile = tiles[newX][newY];
		}
		return tile;
	}
	
	private Tile getBottom(TilePosition position){
		Tile tile = null;
		int x = position.getX();
		int y = position.getY();
		int newY = y + 1;
		if(newY < yBound){
			tile = tiles[x][newY];
		}
		return tile;
	}
	
	private Tile getLeftBottom(TilePosition position){
		Tile tile = null;
		int x = position.getX();
		int y = position.getY();
		int newX = x - 1;
		int newY = y - 1;
		if(newX >= 0 && newY >= 0){
			tile = tiles[newX][newY];
		}
		return tile;
	}
	private void DoSimulation(){
		
		int aliveCounter = 0;
		Tile tile = null;
		Tile copiedTile = null;
		
		for(int i =0; i< tiles.length; i++ ){
			for(int j =0; j<tiles[i].length; j++){
				tile = tiles[i][j];
				
				if(tile.isAlive()){
				List<Tile> neigh = getNeighbors(tile);
				 for(Tile t : neigh){
					 if(t.isAlive()){
						 aliveCounter++;
					 }
				 }
				 if(aliveCounter < 2){
					  copiedTile = copyList[i][j];
				 }
				 else if(aliveCounter == 3 || aliveCounter ==2){
					 copiedTile = copyList[i][j];
				 }
				 else if(aliveCounter< 3){
					 copiedTile = copyList[i][j];
				 }
				}
				else if(!tile.isAlive()){
					List<Tile> neigh = getNeighbors(tile);
					for(Tile t: neigh){
						if(t.isAlive()){
							aliveCounter++;
						}
	
					}
					if(aliveCounter == 3){
						
						copiedTile = copyList[i][j];
					}	
				}	
			}
		}
		
	}

}
