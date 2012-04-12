package player;

import actions.BaseActions;

public class PlayerActions extends BaseActions {

	Player player;
	Walking walking;
	Standing standing;
	
	public PlayerActions(Player player) {
		this.player = player;
//		walking = new Walking(player);
//		standing = new Standing(player);
//		getActions().add(walking);
//		getActions().add(standing);
	}
	
	public boolean isWalking() {
		return walking.isActive();
	}
}
//