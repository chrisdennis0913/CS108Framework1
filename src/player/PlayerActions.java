package player;

import actions.BaseActions;
import actions.Direction;

public class PlayerActions extends BaseActions {

	private Player player;
	private Walking walking;
	private Standing standing;
	
	public PlayerActions(Player player) {
		this.player = player;
		walking = new Walking(player);
		standing = new Standing(player);
		getActions().add(walking);
		getActions().add(standing);
	}
	
	public Direction getCurrentDirection() {
		walking
		return null;
	}
	
	public boolean isWalking() {
		return walking.isActive();
	}
}
