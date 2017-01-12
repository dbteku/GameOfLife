package com.dbteku.gamoeoflife.models;

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
	
	public MapUpdater(MapController controller) {
		this.controller = controller;
		this.currentFrame = 0;
		setImage(new OrbitImage(1, 1));
	}
	
	@Override
	public void act() {
		currentFrame++;
		if(currentFrame >= FRAME_COUNT){
			checkTiles();
			currentFrame = 0;
		}
	}
	
	private void checkTiles(){
		System.out.println("RUNNING");
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

}
