package ai;

import app.RPGGame;
import enemy.AbstractAttackWithCollision;
import enemy.IEnemy;

public class BehaviorModifyingCollisionAttack extends AbstractAttackWithCollision {


	private AbstractBehaviorModifier bm;

	public BehaviorModifyingCollisionAttack(RPGGame game, IEnemy enemy, AbstractBehaviorModifier bm, boolean startActive) {
		super(game, enemy, startActive);
		this.bm = bm;
	}

	@Override
	public void performAttack(long elapsedTime) {
		target.registerBehaviorModifierExclusive(bm, true);
	}

	@Override
	public int valueOfAttack() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isAttackValid(long elapsedTime) {
		return active;
	}
	
	public AbstractBehaviorModifier getBehaviorModifier(){
		return bm;
	}

}
