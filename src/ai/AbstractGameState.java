package ai;

import java.util.*;

import player.Player;

import level.Level;

import com.golden.gamedev.GameObject;

import app.RPGGame;

public abstract class AbstractGameState implements GameStateProvider,Cloneable,Comparable<AbstractGameState>{

	List<AbstractGameState> successors = new ArrayList<AbstractGameState>();
	public Player player;
	public Level level;
	
	public AbstractGameState(RPGGame game){
		extractGameInfo(game);
	}

	public void extractGameInfo(RPGGame game){
		//game.copyGameState(this);
	}

	public abstract int getStateValue();

	public void addSuccessor(AbstractGameState ags){
		successors.add(ags);
	}

	public int compareTo(AbstractGameState ags){
		return getStateValue() - ags.getStateValue();
	}

}
