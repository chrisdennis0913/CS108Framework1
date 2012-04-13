package ai;

import java.util.Collection;
import java.util.Iterator;

import enemy.AbstractAttack;
import enemy.IEnemy;

import ai.AbstractAI;
import app.RPGGame;

public class SimpleAI extends AbstractAI {

	public SimpleAI(RPGGame game, IEnemy enemy) {
		super(game, enemy);
	}

	@Override
	public void act(long elapsedTime) {
		//if (!enemy.isDead()){
			move(elapsedTime);
			if (shouldAttack())
				enemy.attack(pickBestSpontaneousAttack(),elapsedTime);
		//}
	}

	@Override
	public void move(long elapsedTime) {
		enemy.getSprite().moveTo(elapsedTime, game.getPlayer().getCharacter().getX(),
				game.getPlayer().getCharacter().getY(), 0.05);
	}

	@Override
	public boolean shouldAttack() {
		//This AI is aggressive: we should always attack
		return true;
	}

	@Override
	public AbstractAttack pickBestSpontaneousAttack() {
		//Just returns the first attack in the HashMap
		Collection<AbstractAttack> attacks = enemy.getSpontaneousAttacks();
		Iterator<AbstractAttack> itr = attacks.iterator();
		if(itr.hasNext())
			return itr.next();
		else
			throw new RuntimeException("No attacks available");
	}

	@Override
	public AbstractAttack pickBestReactiveAttack() {
		Collection<AbstractAttack> attacks = enemy.getReactiveAttacks();
		Iterator<AbstractAttack> itr = attacks.iterator();
		if(itr.hasNext())
			return itr.next();
		else
			throw new RuntimeException("No attacks available");
	}

	@Override
	public void onCollision() {
		
	}

}
