package ai;

import java.util.HashMap;
import java.util.Iterator;
import app.RPGGame;
import enemy.AbstractAttack;
import enemy.IEnemy;

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
		HashMap<String,AbstractAttack> attacks = enemy.getSpontaneousAttacks();
		Iterator<AbstractAttack> itr = attacks.values().iterator();
		if(itr.hasNext())
			return itr.next();
		else
			throw new RuntimeException("No attacks available");
	}

	@Override
	public AbstractAttack pickBestReactiveAttack() {
		HashMap<String,AbstractAttack> attacks = enemy.getReactiveAttacks();
		Iterator<AbstractAttack> itr = attacks.values().iterator();
		if(itr.hasNext())
			return itr.next();
		else
			throw new RuntimeException("No attacks available");
	}

	@Override
	public void onCollision() {
		
	}

}
