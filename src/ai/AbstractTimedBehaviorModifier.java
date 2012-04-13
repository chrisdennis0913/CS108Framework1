package ai;

import app.RPGGame;

import com.golden.gamedev.object.Timer;

public abstract class AbstractTimedBehaviorModifier extends AbstractBehaviorModifier{

	Timer timer;
	boolean uniqueDone = false;
	
	public AbstractTimedBehaviorModifier(RPGGame game, int effectDuration) {
		super(game);
		timer = new Timer(effectDuration);
	}

	@Override
	public void setUp(long elapsedTime){
		if(!uniqueDone){
			doUnique(elapsedTime);
			uniqueDone = true;
		}
	}
	
	public abstract void doUnique(long elapsedTime);
	public abstract void undoUnique(long elapsedTime);
	
	@Override
	public boolean unsetUp(long elapsedTime) {
		if(timer.action(elapsedTime)){
			undoUnique(elapsedTime);
			return true;
		}
		return false;
	}
	
}
