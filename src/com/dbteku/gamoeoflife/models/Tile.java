package com.dbteku.gamoeoflife.models;

import java.awt.Color;

import com.dbteku.orbit.animation.ImageAnimation;
import com.dbteku.orbit.core.Actor;
import com.dbteku.orbit.core.GameObject2D;
import com.dbteku.orbit.core.World;
import com.dbteku.orbit.imaging.OrbitImage;
import com.dbteku.orbit.models.ScreenEdge;

import javafx.scene.input.KeyCode;

public class Tile extends Actor<Tile>{

	private static OrbitImage ON_IMAGE;
	private static OrbitImage OFF_IMAGE;
	private boolean isOn;
	
	public Tile(int tileSize) {
		if(ON_IMAGE == null){
			ON_IMAGE = new OrbitImage(tileSize, tileSize, Color.BLACK);
			OFF_IMAGE = new OrbitImage(tileSize, tileSize, Color.WHITE);
		}
		isOn = false;
		setImage(OFF_IMAGE);
	}
	
	@Override
	public void act() {
		
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
	public void onAnimationFinish(ImageAnimation<Tile> animation) {
		
	}
	
	@Override
	public void onClick() {
//		System.out.println("CLICKING ON TILE: " + getX() + "," + getY());
		setImage(ON_IMAGE);
	}

}
