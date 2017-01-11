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

	public Tile() {
		setImage(new OrbitImage(10,10, Color.white));
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

}
