package com.dbteku.gamoeoflife.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.dbteku.gameoflife.controllers.MapController;
import com.dbteku.orbit.animation.ImageAnimation;
import com.dbteku.orbit.core.Actor;
import com.dbteku.orbit.core.GameObject2D;
import com.dbteku.orbit.core.World;
import com.dbteku.orbit.imaging.OrbitImage;
import com.dbteku.orbit.models.ScreenEdge;

import javafx.scene.input.KeyCode;

public class MapUpdater extends Actor<MapUpdater> {

	private final int FRAME_COUNT = 30;
	private MapController controller;
	private int currentFrame;
	private boolean isRunning;
	private boolean update;

	public MapUpdater(MapController controller) {
		this.controller = controller;
		this.currentFrame = 0;
		setImage(new OrbitImage(1, 1));
		isRunning = true;
		update = false;
		checkInput();
	}

	@Override
	public void act() {
		if(update){
			currentFrame++;
			if(currentFrame >= FRAME_COUNT){
				checkTiles();
				currentFrame = 0;
			}
		}
	}

	private void checkTiles(){
		checkRules();
		//System.out.println("RUNNING");
	}

	private void checkRules(){
		List<Tile> alive = controller.getAliveList();
		List<Tile> dead = findDeadNeighbors(alive);
		Map<Tile, Boolean> toChange = new HashMap<Tile, Boolean>();

		for (Tile tile : alive) {
			boolean kill = false;
			kill = ruleOne(tile);
			if(!kill){
				kill = ruleTwo(tile);
			}
			if(kill){
				toChange.put(tile, !kill);
			}
		}
		
		for (Tile tile : dead) {
			boolean born = false;
			born = ruleFour(tile);
			if(born){
				toChange.put(tile, born);
			}
		}
		Set<Tile> tiles = toChange.keySet();
		for (Tile tile : tiles) {
			boolean tochange = toChange.get(tile);
			tile.setAlive(false);
		}
		System.out.println(toChange);

	}

	public List<Tile> findDeadNeighbors(List<Tile> alive){
		List<Tile> deadNeighbors = new ArrayList<Tile>();
		for (Tile tile : alive) {
			List<Tile> neighbors = controller.getNeighbors(tile);
			for (Tile neig : neighbors) {
				if(!neig.isAlive()){
					if(!deadNeighbors.contains(neig)){
						deadNeighbors.add(tile);
					}
				}
			}
		}
		return deadNeighbors;
	}

	private boolean ruleOne(Tile aliveTile){
		//each cell with one or no neighbors dies
		boolean dies = false;
		List<Tile> neighbors = controller.getNeighbors(aliveTile);
		int count = 0;
		for (Tile tile : neighbors) {
			if(tile.isAlive()){
				count++;
			}
		}
		if(count <= 1){
			dies = true;
		}
		return dies;
	}

	private boolean ruleTwo(Tile aliveTile){
		//each cell with four or more dies
		boolean dies = false;
		List<Tile> neighbors = controller.getNeighbors(aliveTile);
		int count = 0;
		for (Tile tile : neighbors) {
			if(tile.isAlive()){
				count++;
			}
		}
		if(count <= 1){
			dies = true;
		}
		return dies;
	}

	private void ruleThree(){
		//each cell with two or three cells lives
		//this will happen if the other two fail. 
		//the only other option. 
		//this method is not needed other then explaining ^
	}
	private boolean ruleFour(Tile deadTile){
		//each cell with three neighbors turns on
		boolean born = false;
		List<Tile> neighbors = controller.getNeighbors(deadTile);
		int count = 0;
		for (Tile tile : neighbors) {
			if(tile.isAlive()){
				count++;
			}
		}
		if(count == 3){
			born = true;
		}
		return born;
	}

	@Override
	public void onAnimationUpdate() {

	}

	@Override
	public void onTouchingEdge(World world, ScreenEdge edge) {

	}

	@Override
	public void onCollision(GameObject2D object, World world, Class<?> cls) {

	}

	@Override
	public void onKeyPressed(KeyCode code) {

	}

	@Override
	public void onKeyRelease(KeyCode code) {

	}

	@Override
	public void onAnimationFinish(ImageAnimation<MapUpdater> animation) {

	}

	@Override
	public void onClick() {

	}

	private void checkInput(){
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				Scanner input = new Scanner(System.in);
				while(isRunning){
					String line = input.nextLine();
					if(line.equals("start")){
						update = true;
					}
					else if(line.equals("stop")){
						update = false;
					}
				}
				input.close();
			}
		});
		thread.setDaemon(true);
		thread.start();
	}

}
