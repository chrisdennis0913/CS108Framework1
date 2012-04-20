package enemy;

import app.RPGGame;

public class SlowBehaviorModifier extends AbstractTimedBehaviorModifier {
	
	private static final int EFFECT_DURATION = 5000;
	private static final double SLOW_FACTOR = 0.4;
	private double slowFactor;

	public SlowBehaviorModifier(RPGGame game){
		this(game, EFFECT_DURATION, SLOW_FACTOR);
	}
	
	public SlowBehaviorModifier(RPGGame game, int effectDuration, double slowFactor) {
		super(game, effectDuration);
		this.slowFactor = slowFactor;
	}

	@Override
	public void doUnique(long elapsedTime) {
		player.setWalkingSpeed(new double[] {player.getWalkingSpeed()[0]*slowFactor,
											player.getWalkingSpeed()[1]*slowFactor});
	}
	
	@Override
	public void undoUnique(long elapsedTime) {
		player.setWalkingSpeed(new double[] {player.getWalkingSpeed()[0]/slowFactor,
											player.getWalkingSpeed()[1]/slowFactor});
	}	
}
