package ai;

import player.HealthCounter;
import counters.BaseCounter;
import enemy.SimulateableAttack;
import app.RPGGame;

public class PoisonedBehaviorModifier extends AbstractRecurrentTimeBehaviorModifier{

	public PoisonedBehaviorModifier(RPGGame game, int intervalTime, int executions) {
		super(game, intervalTime, executions);
	}

	@Override
	public void doRecurrent(long elapsedTime) {
		for (BaseCounter bc : player.getPCs().getCounters()){
			if (bc instanceof HealthCounter)
				bc.decrease();
		}
		//TODO: add resource for game.playSound("resources/sick.wav");
	}

}
