package player;

import actions.BaseAction;

public abstract class PlayerAction extends BaseAction {

	private PlayerActions pas;
	
	public PlayerAction(PlayerActions pas) {
		this.pas = pas;
	}
	
	public PlayerActions getActions() {
		return pas;
	}

}
