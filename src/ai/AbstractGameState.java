package ai;

import java.util.*;
import app.RPGGame;

public abstract class AbstractGameState implements Comparable<AbstractGameState>{

	List<AbstractGameState> successors;

	//TODO: previous game state and action that led to this
	public AbstractGameState(){
		successors = new ArrayList<AbstractGameState>();		
	}

	public AbstractGameState(RPGGame game){
		this();
		extractGameInfo(game);
	}

	public abstract void extractGameInfo(RPGGame game);

	public abstract int getStateValue();

	public void addSuccessor(AbstractGameState ags){
		successors.add(ags);
	}

	public int compareTo(AbstractGameState ags){
		return getStateValue() - ags.getStateValue();
	}

}
