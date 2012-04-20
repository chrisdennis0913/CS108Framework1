package enemy;

import app.RPGGame;

public class BehaviorModifyingCollisionAttack extends AbstractCollisionAttack {


	private AbstractBehaviorModifier bm;

	public BehaviorModifyingCollisionAttack(RPGGame game, IEnemy enemy, String name, AbstractBehaviorModifier bm, boolean startActive) {
		super(game, enemy, name, startActive);
		this.bm = bm;
	}

	@Override
	public void performAttack(long elapsedTime) {
		target.registerBehaviorModifierExclusive(bm, true);
	}

	@Override
	public boolean isAttackValid(long elapsedTime) {
		return active;
	}
	
	public AbstractBehaviorModifier getBehaviorModifier(){
		return bm;
	}

}
