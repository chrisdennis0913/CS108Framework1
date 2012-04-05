package player;

import actions.GraphicalAction;

public abstract class PlayerAction extends GraphicalAction {

	private Player player;
	
	public PlayerAction(Player player) {
		this.player = player;
	}
	
	public Player getPlayer() {
		return player;
	}

}
