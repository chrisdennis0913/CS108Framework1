package ai;

import java.util.HashMap;
import java.util.HashSet;

import app.RPGGame;
import enemy.AbstractAttack;
import enemy.IEnemy;
import enemy.ShootingAttack;
import enemy.SlowBehaviorModifier;

public class SnakeDecisionTableAI extends AbstractDecisionTableAI {

	public SnakeDecisionTableAI(RPGGame game, IEnemy enemy) {
		super(game, enemy);
		generateDecisionTable();
	}

	//right now the decision table is hard-coded in a class
	//but will soon be implemented as json-config file
	private void generateDecisionTable() {
		decisionTable = new HashMap<String,HashMap<AbstractGameHeuristic,Integer>>();
		HashMap<String, AbstractGameHeuristic> gameHeuristics = new HashMap<String, AbstractGameHeuristic>();
		
		decisionTable.put("shooting", new HashMap<AbstractGameHeuristic,Integer>());
		decisionTable.put("poison", new HashMap<AbstractGameHeuristic,Integer>());
		decisionTable.put("slow", new HashMap<AbstractGameHeuristic,Integer>());

		gameHeuristics.put("playerFasterThanEnemy", new PlayerFasterThanEnemyHeuristic(game,enemy));
		gameHeuristics.put("playerFarFromEnemy", new PlayerFarFromEnemyHeuristic(game,enemy));
		
		decisionTable.get("shooting").put(gameHeuristics.get("playerFarFromEnemy"), 2);
		
		decisionTable.get("poison").put(gameHeuristics.get("playerFarFromEnemy"), 1);
		
		decisionTable.get("slow").put(gameHeuristics.get("playerFasterThanEnemy"), 4);
	}
	
	

}
