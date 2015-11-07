package com.games.breakout;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Level {
	
	int WIDTH = Gdx.graphics.getWidth();
	int HEIGHT = Gdx.graphics.getHeight();

	private int levelNo;
	private ArrayList<Brick> bricks;
	
	public Level(int levelNo, int brickNo){
		this.levelNo = levelNo;
		
		this.bricks = new ArrayList<Brick>();
		
		int baseX;
		int baseY = HEIGHT/2+50;

		while(brickNo > 11){

			baseX = 300 - (11*25);
			for(int i=0; i<11; i++){
				Brick brick = new Brick(baseX, baseY, Brick.BWIDTH  , Brick.BHEIGHT);
				baseX += Brick.BWIDTH + 1;
				bricks.add(brick);
			}
			baseY += Brick.BHEIGHT+1;
			brickNo -= 11;
		}
		baseX = 300 - (brickNo*25);
		for(int i=0; i<brickNo; i++){
			Brick brick = new Brick(baseX, baseY, Brick.BWIDTH  , Brick.BHEIGHT);
			baseX += Brick.BWIDTH + 1;
			bricks.add(brick);
		}
	}

	public void loadLevel(ShapeRenderer sp){
		sp.begin(ShapeRenderer.ShapeType.Filled);
		for(int i=0; i<bricks.size(); i++){
			Brick brick = bricks.get(i);
			sp.setColor(brick.getColor());
			sp.rect(brick.getX(), brick.getY(), Brick.BWIDTH, Brick.BHEIGHT);
		}
		sp.end();
	}

	public boolean updateBricks(Ball ball){
		for(int i=0; i<bricks.size(); i++){
			Brick brick = bricks.get(i);
			if(brick.isOverlappingWith(ball)){
				ball.bounceFromBrick(brick);
				brick.remove();
				bricks.remove(i);
				if(bricks.isEmpty()){
					return true;
				}
			}
		}
		return false;
	}

	public int getLevelNo(){
		return levelNo;
	}

	public String toString(){
		return "Level : " + levelNo + ", Bricks : " + bricks.size() +"\n" ;
	}

}
