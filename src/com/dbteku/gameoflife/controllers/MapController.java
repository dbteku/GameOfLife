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
	private Map<String, TilePosition> locations;
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
				Tile tile= new Tile((x + "," + y), TILE_SIZE);
				tiles[x][y] = tile;
				locations.put(tile.getId(), new TilePosition(x, y));
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
		if(locations.containsKey(tile.getId())){
			position = locations.get(tile.getId());
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
		int newY = y + 1;
		if(newX >= 0 && newY >= 0){
			tile = tiles[newX][newY];
		}
		return tile;
	}

	public void checkBoard(){
		boolean[][] temp = new boolean[xBound][yBound];
		for (int y = 0; y < yBound; y++) {
			for (int x = 0; x < xBound; x++) {
				temp[x][y] = tiles[x][y].isAlive();
			}
		}

		for (int y = 0; y < yBound; y++) {
			for (int x = 0; x < xBound; x++) {
				int n = getAliveNeighborsCount(tiles[x][y]);
			       if (n > 3  ||  n < 2)
			           temp[x][y] = false;
			       else if (n == 3)
			           temp[x][y] = true;
			       else
			           temp[x][y] = tiles[x][y].isAlive();
			}
		}
		
		for (int y = 0; y < yBound; y++) {
			for (int x = 0; x < xBound; x++) {
				tiles[x][y].setAlive(temp[x][y]);
			}
		}

	}

	private int getAliveNeighborsCount(Tile tile){
		int count =0;
		List<Tile> neighbors = getNeighbors(tile);
		for (Tile toCheck : neighbors) {
			if(toCheck.isAlive()){
				count++;
			}
		}
		return count;
	}

}
