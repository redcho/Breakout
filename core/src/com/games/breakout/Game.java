package com.games.breakout;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Game {

	private LinkedList<Level> levels = new LinkedList<Level>();
	private Level currentLevel;

	public Game(){
		try {
			int totalLevel;
			Scanner inp = new Scanner(new File("../levels.txt"));
			totalLevel = inp.nextInt();
			for(int i=1; i<=totalLevel; i++){
				levels.add(new Level(i, inp.nextInt()));
			}

			currentLevel = levels.remove();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public void loadLevel(ShapeRenderer sp){
		currentLevel.loadLevel(sp);
	}

	public boolean loadNextLevel(){
		if(levels.isEmpty()){
			return false;
		}else{
			currentLevel = levels.remove();
			return true;
		}
	}

	public boolean updateBricks(Ball ball){
		return currentLevel.updateBricks(ball);
	}

	public int getCurrentLevel() {
		return currentLevel.getLevelNo();
	}

}
