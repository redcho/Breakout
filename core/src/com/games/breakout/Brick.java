package com.games.breakout;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;

public class Brick extends GameObject{
	public static final int BWIDTH = 50;
	public static final int BHEIGHT = 10;

	private Color color;
	
	public Brick(int x, int y, int width, int height) {
		super(x, y, width, height);
		color = Color.GOLD;
	}
	
	public boolean isOverlappingWith(Ball B){
		return (getX() < (B.getX()+B.getWidth()) && (getX()+getWidth()) > B.getX() && getY() < (B.getY()+B.getHeight()) && (getY()+getHeight()) > B.getY()); 
	}
	
	public Color getColor(){
		return color;
	}
	
	public void remove(){
		setX(-20);
		setY(-20);
	}

}
