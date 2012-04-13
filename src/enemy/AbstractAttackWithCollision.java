package enemy;

import app.RPGGame;
import collisions.AttackVectorCollision;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

public abstract class AbstractAttackWithCollision extends AbstractAttack {

	protected boolean active = true;
	
	public AbstractAttackWithCollision(RPGGame game, IEnemy enemy, boolean startActive) {
		super(game, enemy);
		active = startActive;
	}
	
	public void setActive(boolean active){
		this.active = active;
	}
	
}
