package ai;

import enemy.IEnemy;
import app.RPGGame;

public abstract class AbstractGameHeuristic {

	RPGGame game;
	IEnemy enemy;
	String conditionName;
	
	public AbstractGameHeuristic(RPGGame game, IEnemy enemy, String conditionName){
		this.game = game;
		this.enemy = enemy;
		this.conditionName = conditionName;
	}
	
	public abstract boolean getHeuristicBool();
	
}
