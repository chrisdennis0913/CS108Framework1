package ai;

import app.RPGGame;
import enemy.IEnemy;

public class PlayerFasterThanEnemyHeuristic extends AbstractGameHeuristic {
	
	public PlayerFasterThanEnemyHeuristic(RPGGame game, IEnemy enemy) {
		super(game, enemy, "playerFaster");
	}

	@Override
	public boolean getHeuristicBool() {
		System.err.println(game.getPlayer().getWalkingSpeed()[0]);
		return Math.abs(game.getPlayer().getWalkingSpeed()[0]) >= enemy.getMaxXSpeed() ||
			   Math.abs(game.getPlayer().getWalkingSpeed()[1]) >= enemy.getMaxYSpeed();
	}

}
