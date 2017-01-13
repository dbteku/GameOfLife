package com.dbteku.gamoeoflife.models;

import java.awt.Color;

import com.dbteku.gameoflife.controllers.MapController;
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
	private MapController controller;
	private String id;
	private boolean isAlive;

	public Tile(String id, int tileSize) {;
		this.id = id;
		if(ON_IMAGE == null){
			ON_IMAGE = new OrbitImage(tileSize, tileSize, Color.BLACK);
			OFF_IMAGE = new OrbitImage(tileSize, tileSize, Color.WHITE);
		}
		isAlive = false;
		setCorrectImage();
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
		isAlive = !isAlive;
		setCorrectImage();
	}
	
	public boolean isAlive() {
		return isAlive;
	}
	
	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
		setCorrectImage();
	}
	
	private void setCorrectImage(){
		if(isAlive){
			setImage(ON_IMAGE);	
		}else{
			setImage(OFF_IMAGE);
		}
	}
	
	public String getId() {
		return id;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((controller == null) ? 0 : controller.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + (isAlive ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		boolean areEqual = false;
		
		try{
			Tile other = (Tile)obj;
			areEqual = id.equals(other.id);
		}catch(ClassCastException e){
			
		}
		
		return areEqual;
	}

	@Override
	public String toString() {
		return id;
	}

}
