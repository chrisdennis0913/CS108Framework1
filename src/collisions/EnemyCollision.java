package collisions;

import actions.Attacking;
import app.Main;
import app.RPGGame;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

import enemy.IEnemy;

public class EnemyCollision extends BasicCollisionGroup {

	private RPGGame game;
	private IEnemy enemy;

	public EnemyCollision(RPGGame game, String enemyname) {
		this.game = game;
		this.enemy = game.getLevel().getEnemy(enemyname);
	}

	public void collided(Sprite character, Sprite scenery) {
		overlap(character, scenery);
		Attacking attacking = (Attacking) game.getPlayer().getAction(
				"attacking");
		if (attacking.isActing()) {
			enemy.reduceHealth();
			if (enemy.getHealth() < 1) {
				enemy.die();
				game.getField().removeCollisionGroup(this);
			}
			jump(character, scenery);
		} else {
			jump(character, scenery);
			game.getPlayer().reduceHealth();
		}
	}

	protected void jump(Sprite character, Sprite scenery) {
		scenery.setX(character.getX() - 100);
		scenery.setY(character.getY() - 100);
	}
	
	protected void overlap(Sprite character, Sprite scenery) {
		double maxsep = scenery.getImage().getHeight()
				- character.getImage().getHeight() / 6 * 5;
		double separation = character.getY() - scenery.getY();

		if (separation <= maxsep) {
			character.setX(character.getOldX());
			character.setY(character.getOldY());
		}
	}

}
