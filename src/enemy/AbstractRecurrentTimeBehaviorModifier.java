package enemy;

import com.golden.gamedev.object.Timer;

import app.RPGGame;

public abstract class AbstractRecurrentTimeBehaviorModifier extends AbstractBehaviorModifier{

	Timer timer;
	int executions;
	
	public AbstractRecurrentTimeBehaviorModifier(RPGGame game, int intervalTime, int executions) {
		super(game);
		timer = new Timer(intervalTime);
		this.executions = executions;
	}
	
	public abstract void doRecurrent(long elapsedTime);
	
	@Override
	public void setUp(long elapsedTime){
		if(!timer.action(elapsedTime))
			return;
		doRecurrent(elapsedTime);
		executions--;
	}
	
	@Override
	public boolean unsetUp(long elapsedTime){
		if(executions <= 0)
			return true;
		return false;
	}

}
