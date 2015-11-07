package com.games.breakout;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class ZMainGame extends ApplicationAdapter {
	
	int WIDTH, HEIGHT;
	ShapeRenderer sp;
	SpriteBatch batch;
	BitmapFont font;
	
	Ball ball;
	Paddle paddle;
	GameState state;
	Game game;

	@Override
	public void create () {
		
		WIDTH = Gdx.graphics.getWidth();
		HEIGHT = Gdx.graphics.getHeight();
		
		sp = new ShapeRenderer();
		font = new BitmapFont();
		batch = new SpriteBatch();
		
		game = new Game();

		// Coordinates according to WIDTH and HEIGHT
		initializeObjects();		
	}

	@Override
	public void render () {
		// Main Loop
		
		handleInput();
		if(state == GameState.INGAME){
			logicUpdate();	
		}
		draw();
	}

	public void initializeObjects(){
		state = GameState.SET;
		paddle = new Paddle(WIDTH/2-40, HEIGHT/10, 80, 10);
		ball = new Ball(WIDTH/2-5,HEIGHT/10+10,10,10);
	}
	
	public void handleInput(){
		if( Gdx.input.isKeyPressed(Keys.SPACE) && state == GameState.SET ){
			state = GameState.INGAME;
			ball.initSpeed(5,0,5);
			paddle.initSpeed(10);
		}
		
		if(state == GameState.INGAME){
			
			if(Gdx.input.isKeyPressed(Keys.A)){
				if(paddle.getX() >= 0)
					paddle.decreaseX((int)paddle.getSpeed());
			}
			if(Gdx.input.isKeyPressed(Keys.D)){
				if(paddle.getX()+paddle.getWidth() <= WIDTH)
					paddle.increaseX((int)paddle.getSpeed());
			}
			
		}
		
	}
	
	public void logicUpdate(){
		
		if(ball.getY()+ball.getHeight() > HEIGHT){
			ball.reverseY();
		}else if(ball.getY() < 0){
			ball.reverseY();
		}
		
		if(ball.getX() < 0){
			ball.reverseX();
		}else if(ball.getX()+ball.getWidth() > WIDTH){
			ball.reverseX();
		}
		
		if(ball.getCenterY() < paddle.getCenterY()){
			ball.reset();
			paddle.reset();
			state = GameState.SET;
		}

		if(ball.isOverlappingWith(paddle)){
			double dx = ball.getCenterX() - paddle.getCenterX();
			double dy = ball.getCenterY() - paddle.getCenterY();
			ball.bounceFromPaddle(dx, dy);
		}

		boolean isLevelDone = game.updateBricks(ball);
		if(isLevelDone){
			if(game.loadNextLevel()){
				state = GameState.SET;
			}else{
				System.out.println("Congratulations");
				state = GameState.END;
			}
			ball.reset();
			paddle.reset();
		}

		ball.increaseX((int)ball.getSpeedX());
		ball.increaseY((int)ball.getSpeedY());

	}
	
	public void draw(){
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		sp.begin(ShapeType.Filled);
		sp.setColor(Color.LIGHT_GRAY);
		  sp.rect(paddle.getX(), paddle.getY(), paddle.getWidth(), paddle.getHeight());
		sp.setColor(Color.RED);
		  sp.rect(ball.getX(), ball.getY(), ball.getWidth(), ball.getHeight());
		sp.setColor(Color.WHITE);
		  sp.rect(ball.getCenterX(), ball.getCenterY(), 1, 1);
		  sp.rect(paddle.getCenterX(), paddle.getCenterY(), 1, 1);
		sp.end();
		
		game.loadLevel(sp);
		
		batch.begin();
		  String info;
		  if(state == GameState.END){
			  info = "Congratulations";
		  }else{
			  info = Integer.toString(game.getCurrentLevel());
		  }
		  font.draw(batch, info , 20, HEIGHT - 30);
		batch.end();

	}
	
}
