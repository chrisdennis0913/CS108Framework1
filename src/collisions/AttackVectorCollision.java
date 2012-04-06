package collisions;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

import enemy.AbstractAttackWithVector;

public class AttackVectorCollision extends BasicCollisionGroup{

	AbstractAttackWithVector owner;
	
	public AttackVectorCollision(AbstractAttackWithVector owner){
		this.owner = owner;
	}
	
	@Override
	public void collided(Sprite arg0, Sprite arg1) {
		owner.onCollision(arg0,arg1);
	}

}
