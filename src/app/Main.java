package app;

import java.awt.Dimension;

import level.LevelEditor;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameLoader;
import com.golden.gamedev.GameObject;


public class Main extends GameEngine {
	
	public static final int TITLE = 0, GAME_MODE = 1, LEVEL_EDITOR = 2;
	
	public void initResources() {
		nextGameID = GAME_MODE;
	}
	
	public GameObject getGame(int GameID) {
		switch (GameID) {
			case TITLE : return new Title(this);
			case GAME_MODE : return new RPGGame(this);
			case LEVEL_EDITOR : return new LevelEditor(this);
		}
		return null;
	}
	
	public static void main(String[] args) {
		GameLoader game = new GameLoader();
		game.setup(new Main(), new Dimension(400, 400), false);
		game.start();
	}
}
