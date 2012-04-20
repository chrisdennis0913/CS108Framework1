package enemy;

import collisions.CollisionObserver;
import ai.AbstractGameHeuristic;
import app.RPGGame;

public class PlayerEnemyCollisionHeuristic extends AbstractGameHeuristic implements CollisionObserver{

	private boolean collided;

	public PlayerEnemyCollisionHeuristic(RPGGame game, Enemy enemy,
			String conditionName) {
		super(game, enemy, "playerEnemyCollision");
		enemy.collision.registerCollisionObserver(this);
	}
	
	@Override
	public boolean getHeuristicBool() {
		if(collided){
			collided = false;
			return true;
		}
		return false;
	}

	@Override
	public void notifyOfCollision() {
		collided = true;
	}

	
}
