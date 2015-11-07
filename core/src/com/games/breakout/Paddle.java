package com.games.breakout;

public class Paddle extends GameObject {

	private int score;
	
	public Paddle(int x, int y, int width, int height) {
		super(x, y, width, height);
	}
	
	public void increaseScore(){
		score++;
	}
	
	public int getScore(){
		return score;
	}

	public void initSpeed(int speed) {
		// TODO Auto-generated method stub
		setSpeed(speed);
	}

}
