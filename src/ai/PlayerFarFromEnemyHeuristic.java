package ai;

import app.RPGGame;
import enemy.IEnemy;

public class PlayerFarFromEnemyHeuristic extends AbstractGameHeuristic {

	double threshold;
	private static double DEFAULT_THRESHOLD = 0.5;
	
	public PlayerFarFromEnemyHeuristic(RPGGame game, IEnemy enemy){
		this(game, enemy, DEFAULT_THRESHOLD);
	}
	
	public PlayerFarFromEnemyHeuristic(RPGGame game, IEnemy enemy, double threshold){
		super(game, enemy, "playerFarFromEnemy");
		this.threshold = threshold;
	}

	@Override
	public boolean getHeuristicBool() {
		double dist = enemy.getSprite().getDistance(game.getPlayer().getSprite());
		return (dist > threshold);
	}

}
