package enemy;

import player.HealthCounter;
import counters.BaseCounter;
import app.RPGGame;

public class PoisonedBehaviorModifier extends AbstractRecurrentTimeBehaviorModifier{

	private static final int EXECUTIONS = 5;
	private static final int INTERVAL_TIME = 1500;
	
	public PoisonedBehaviorModifier(RPGGame game){
		this(game, INTERVAL_TIME, EXECUTIONS);
	}
	
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
