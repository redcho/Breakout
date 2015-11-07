package com.games.breakout;

import java.util.Random;

import com.badlogic.gdx.math.Rectangle;

public class Ball extends GameObject{

	private double speedx, speedy;
	
	public Ball(int x, int y, int width, int height) {
		super(x, y, width, height);
	}
	
	public void reset(){
		super.reset();
		setSpeed(0, getSpeed());
	}

	public boolean isOverlappingWith(Paddle B){
		return (getX() < (B.getX()+B.getWidth()) && (getX()+getWidth()) > B.getX() && getY() < (B.getY()+B.getHeight()) && (getY()+getHeight()) > B.getY()); 
	}
	
	public void bounceFromPaddle(double dx, double dy){
	
		double newAngle = Math.atan2(dy, dx);
		double newSpeedX = Math.cos(newAngle)*getSpeed();
		double newSpeedY = Math.sin(newAngle)*getSpeed();
		setSpeed(newSpeedX, newSpeedY);
	}
	
	public void bounceFromBrick(Brick brick){
		Rectangle intersect = intersection(brick);
		if(intersect.width >= intersect.height){
			reverseY();
		}else{
			reverseX();
		}
	}
	
	public Rectangle intersection(Brick r2){
		Rectangle result = new Rectangle();
		
		float x1 = Math.max(getX(), r2.getX());
		float y1 = Math.max(getY(), r2.getY());
		float x2 = Math.min(getX() + getWidth(), r2.getX() + r2.getWidth());
		float y2 = Math.min(getY() + getHeight(), r2.getY() + r2.getHeight());
		
		result.x = x1;
		result.y = y1;
		result.width = Math.abs(x2-x1);
		result.height = Math.abs(y2-y1);
		
		return result;
	}
	
	
	public void initSpeed(double startSpeed, double speedx, double speedy){
		setSpeed(startSpeed);
		this.speedx = speedx;
		this.speedy = speedy;
	}
	
	public void setSpeed(double speedx, double speedy){
		this.speedx = speedx;
		this.speedy = speedy;
	}
	
	public void setSpeedX(int speed){
		speedx = speed;
	}
	
	public void setSpeedY(int speed){
		speedy = speed;
	}
	
	public void reverseX(){
		speedx = -speedx;
	}
	
	public void reverseY(){
		speedy = -speedy;
	}
	
	public double getSpeedX(){
		return speedx;
	}
	
	public double getSpeedY(){
		return speedy;
	}

}
