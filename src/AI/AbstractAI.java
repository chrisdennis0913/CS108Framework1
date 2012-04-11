package ai;

import enemy.AbstractAttack;
import enemy.IEnemy;
import app.RPGGame;


public abstract class AbstractAI{

	protected static RPGGame game;
	protected IEnemy enemy;
	
	public AbstractAI(RPGGame game, IEnemy enemy){
		this.game = game;
		this.enemy = enemy;
	}
	
	public abstract void act(long elapsedTime);
	public abstract void move(long elapsedTime);
	public abstract boolean shouldAttack();
	public abstract AbstractAttack pickBestAttack();
	public abstract void onCollision();

}
