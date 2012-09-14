package ai;

import app.RPGGame;
import enemy.AbstractAttack;
import enemy.IEnemy;


public abstract class AbstractAI{

	protected static RPGGame game;
	protected IEnemy enemy;

	public AbstractAI(RPGGame game, IEnemy enemy){
		AbstractAI.game = game;
		this.enemy = enemy;
	}

	public abstract void act(long elapsedTime);
	public abstract void move(long elapsedTime);
	public abstract boolean shouldAttack();
	public abstract AbstractAttack pickBestSpontaneousAttack();
	public abstract AbstractAttack pickBestReactiveAttack();
	public abstract void onCollision();
	
}
