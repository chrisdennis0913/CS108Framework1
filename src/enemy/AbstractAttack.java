package enemy;

import player.Player;
import app.RPGGame;

public abstract class AbstractAttack {
	protected static RPGGame game;
	protected IEnemy enemy;
	protected Player target;
	
	public AbstractAttack (RPGGame game, IEnemy enemy){
		AbstractAttack.game = game;
		this.enemy = enemy;
		setDefaultPlayerAsTarget();
	}
	
	public void setTarget(Player p){
		this.target = p;
	}
	
	public void setDefaultPlayerAsTarget(){
		setTarget(game.getPlayer());
	}
	
	public double getAttackerX(){
		return enemy.getSprite().getX();
	}
	
	public double getAttackerY(){
		return enemy.getSprite().getY();
	}
	
	public abstract boolean isAttackValid(long elapsedTime);
	
	public abstract void performAttack(long elapsedTime);
	
	public abstract int valueOfAttack();
		
}
