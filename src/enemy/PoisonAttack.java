package enemy;

import ai.AbstractGameState;
import app.RPGGame;

import com.golden.gamedev.object.Sprite;

public class PoisonAttack extends AbstractAttackWithCollision {//implements SimulateableAttack{
	
	private static final int EXECUTIONS = 5;
	private static final int INTERVAL_TIME = 1500;

	public PoisonAttack(RPGGame game, IEnemy enemy, boolean startActive) {
		super(game, enemy, startActive);
	}
	
	@Override
	public boolean isAttackValid(long elapsedTime) {
		return active;
	}

	@Override
	public void performAttack(long elapsedTime) {
		target.registerBehaviorModifierExclusive(new PoisonedBehaviorModifier(game, INTERVAL_TIME, EXECUTIONS), true);
	}

	@Override
	public int valueOfAttack() {
		// TODO Auto-generated method stub
		return 0;
	}
	/*
	@Override
	public AbstractGameState simulateAttack(AbstractGameState ags) {
		AbstractGameState newState = (AbstractGameState) ags.clone();
		newState.player.getPCs().getHealth().decrease(executions);
		return newState;
	}*/
}