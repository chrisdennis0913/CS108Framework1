package enemy;

import app.RPGGame;

public abstract class AbstractRangeAttack extends AbstractAttack{


	public AbstractRangeAttack(RPGGame game, IEnemy enemy) {
		super(game, enemy);
	}

	@Override
	public void performAttack(long elapsedTime) {
		
	}

}
