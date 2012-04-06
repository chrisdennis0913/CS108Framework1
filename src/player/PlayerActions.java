package player;

import actions.BaseActions;

public class PlayerActions extends BaseActions {

	Player player;
	Walking walking;
	
	public PlayerActions(Player player) {
		this.player = player;
		walking = new Walking(player);
		getActions().add(walking);
	}
}
