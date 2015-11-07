package com.games.breakout;

import com.badlogic.gdx.math.Rectangle;

public abstract class GameObject {
	private Rectangle rec;
	private int startPositionX, startPositionY;
	private double startSpeed;
	
	public GameObject(int x, int y, int width, int height){
		rec = new Rectangle(x, y, width, height);
		
		startPositionX = x;
		startPositionY = y;
	}
	
	public void reset(){
		rec.x = startPositionX;
		rec.y = startPositionY;
	}
	
	public boolean isOverlappingWith(Rectangle B){
		return (getX() < (B.x+B.width) && (getX()+getWidth()) > B.x && getY() < (B.y+B.height) && (getY()+getHeight()) > B.y); 
	}
	
	protected void increaseX(int speed){
		rec.x += speed;
	}
	
	protected void decreaseX(int speed){
		rec.x -= speed;
	}
	
	protected void increaseY(int speed){
		rec.y += speed;
	}
	
	protected void decreaseY(int speed){
		rec.y -= speed;
	}
	
	public void setX(int x){
		rec.x = x;
	}
	
	public void setY(int y){
		rec.y = y;
	}
	
	public int getX(){
		return (int) rec.x;
	}
	
	public int getY(){
		return (int) rec.y;
	}
	
	public int getWidth(){
		return (int) rec.width;
	}
	
	public int getHeight(){
		return (int) rec.height;
	}
	
	public int getCenterX(){
		return (int) (rec.x + (rec.width/2)); 
	}
	
	public int getCenterY(){
		return (int) (rec.y + (rec.height/2) ); 
	}
	
	public void setSpeed(double speed){
		startSpeed = speed;
	}
	
	public double getSpeed(){
		return startSpeed;
	}
	
}
