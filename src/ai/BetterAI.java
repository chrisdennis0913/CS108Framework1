package ai;

import java.util.Collection;
import java.util.Iterator;

import app.RPGGame;
import enemy.AbstractAttack;
import enemy.IEnemy;

public class BetterAI extends AbstractAI{

	public BetterAI(RPGGame game, IEnemy enemy) {
		super(game, enemy);
	}

	@Override
	public void act(long elapsedTime) {
		move(elapsedTime);
		if (shouldAttack())
			enemy.attack(pickBestSpontaneousAttack(),elapsedTime);
	}

	@Override
	public void move(long elapsedTime) {
		enemy.getSprite().moveTo(elapsedTime, game.getPlayer().getCharacter().getX(),
				game.getPlayer().getCharacter().getY(), (enemy.getMaxXSpeed() + enemy.getMaxYSpeed())/2);
	}

	@Override
	public boolean shouldAttack() {
		//This AI is aggressive: we should always attack
		return true;
	}
	
	@Override
	public AbstractAttack pickBestSpontaneousAttack() {
		Collection<AbstractAttack> attacks = enemy.getSpontaneousAttacks();
		Iterator<AbstractAttack> itr = attacks.iterator();
		if(itr.hasNext())
			return itr.next();
		else
			throw new RuntimeException("No attacks available");
	}

	@Override
	public AbstractAttack pickBestReactiveAttack() {
		double playerMaxSpeed = (game.getPlayer().getMaxXSpeed() + game.getPlayer().getMaxYSpeed())/2;
		double enemyMaxSpeed = (enemy.getMaxXSpeed() + enemy.getMaxYSpeed())/2;

		if(playerMaxSpeed > enemyMaxSpeed){
			for(AbstractAttack a: enemy.getReactiveAttacks())
				if(a instanceof SlowPlayerAttack)
					return a;
		}
		else{
			for(AbstractAttack a: enemy.getReactiveAttacks())
			if( !(a instanceof SlowPlayerAttack) )
				return a;
		}
		throw new RuntimeException("No attacks available");
	}

	@Override
	public void onCollision() {
		pickBestReactiveAttack().performAttack(0);
	}

}
