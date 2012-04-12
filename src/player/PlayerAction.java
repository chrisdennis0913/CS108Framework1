package player;

import java.awt.image.BufferedImage;

import actions.BaseAction;

public abstract class PlayerAction extends BaseAction {

	private Player player;
	
	public PlayerAction(Player player) {
		this.player = player;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public BufferedImage getImage(String url) {
		return player.getGame().getImage(url);
	}
	
	public PlayerActions getActions() {
		return player.getActions();
	}

}
