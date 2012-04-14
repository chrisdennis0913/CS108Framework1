package ai;

import app.RPGGame;

public class SimpleGameState extends AbstractGameState {

	int playerHealth;

	public SimpleGameState(RPGGame game) {
		super(game);
	}

	@Override
	public void extractGameInfo(RPGGame game) {
		playerHealth = game.getPlayer().getPCs().getHealth().getCount();
	}

	@Override
	public int getStateValue() {
		return -playerHealth;
	}

	@Override
	public void copyGameState(GameStateProvider gsp) {
		// TODO Auto-generated method stub
		
	}

}