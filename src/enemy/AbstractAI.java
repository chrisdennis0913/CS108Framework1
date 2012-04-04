package enemy;

import app.RPGGame;


public abstract class AbstractAI{

	private static RPGGame game;
	private Enemy enemy;
	
	public abstract void act();
	public abstract void attack();
	public abstract void move();
	public abstract void collided();
	/*
	public abstract void onCollision();
	public abstract DamageStats calculateAttackEffects();
	private abstract void commitAttackEffects(DamageStats damage);
	public abstract DamageStats performAttack(){
		DamageStats damage = calculateAttackEffects();
		commitAttackEffects(damage);
	}

	
	public DamageStats calculateAttackEffects(){
		double dist = calculateDistance(player,enemy);
		//...
			if(player.defenseXP < enemy.attackXP){
				playerHPdelta -= Math.random()*5;
				enemyXPdelta += Math.random()*3;
			}
		//...
		return new DamageStats(playerHPdelta, enemyHPdelta, playerXPdelta, enemyXPdelta);
	}*/
}
