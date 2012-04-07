package enemy;

import app.RPGGame;

public class SuperTelepathicAttack extends AbstractAttack {
	
	int strikes;
	
	public SuperTelepathicAttack(RPGGame game, IEnemy enemy) {
		super(game, enemy);
		strikes = 3;
	}

	@Override
	public boolean isAttackValid(long elapsedTime) {
		// This attack is performed by mental power, therefore it is always valid (doesn't need collision or attack vectors)
		// However, to prevent excessive brain damage, it is limited to 3 strikes per game
		return strikes > 0;
	}

	@Override
	public void performAttack(long elapsedTime) {
		if(isAttackValid(elapsedTime)){
			strikes--;
			target.getPCs().reactToEnemy(enemy);
		}
	}

	@Override
	public int valueOfAttack() {
		// TODO Auto-generated method stub
		return 0;
	}

}
