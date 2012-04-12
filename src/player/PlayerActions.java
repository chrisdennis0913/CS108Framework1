package player;

import java.awt.image.BufferedImage;
import java.util.List;

import actions.BaseActions;
import actions.Directions.Cardinal;

public class PlayerActions extends BaseActions {

	private Player player;
	private Walking walking;
	private Standing standing;
	private Attacking attacking;
	
	public PlayerActions(Player player) {
		this.player = player;
		walking = new Walking(this);
		standing = new Standing(this);
		attacking = new Attacking(this);
		getActions().add(walking);
		getActions().add(standing);
		getActions().add(attacking);
	}
	
	public boolean checkKeys(List<Integer> keys) {
		for (Integer key : keys)
			if (!player.getGame().keyDown(key))
				return false;
		return true;
	}
	
	public void switchForAttack(boolean theswitch) {
		walking.setEnabled(theswitch);
		standing.setEnabled(theswitch);
	}
	
	public BufferedImage getImage(String url) {
		return player.getGame().getImage(url);
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public Cardinal getCurrentDirection() {
		return walking.getCurrentDirection();
	}
	
	public boolean isWalking() {
		return walking.isActive();
	}
	
	public boolean isAttacking() {
		return attacking.isActive();
	}
}
//
