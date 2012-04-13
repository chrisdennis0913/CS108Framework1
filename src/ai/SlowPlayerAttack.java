package ai;

import app.RPGGame;
import enemy.AbstractAttackWithCollision;
import enemy.IEnemy;

public class SlowPlayerAttack extends AbstractAttackWithCollision {

	private static final int EFFECT_DURATION = 5000;
	private static final double SLOW_FACTOR = 0.4;


	public SlowPlayerAttack(RPGGame game, IEnemy enemy, boolean startActive) {
		super(game, enemy, startActive);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void performAttack(long elapsedTime) {
		target.registerBehaviorModifierExclusive(new SlowBehaviorModifier(game,EFFECT_DURATION,SLOW_FACTOR), true);
	}

	@Override
	public int valueOfAttack() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isAttackValid(long elapsedTime) {
		return true;
	}

}
