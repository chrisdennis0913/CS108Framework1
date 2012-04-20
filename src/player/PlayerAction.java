package player;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import actions.GraphicalAction;

public abstract class PlayerAction extends GraphicalAction {

	protected Player player;
	
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
	
	public void render(Graphics2D g) {
		
	}

}
