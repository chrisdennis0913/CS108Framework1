package player;

import java.util.List;

import actions.BaseActions;
import actions.Directions.Cardinal;

public class PlayerActions extends BaseActions {

	private Player player;
	private Cardinal curDirection;
	private Walking walking;
	private Standing standing;
	private Attacking attacking;
	
	public PlayerActions(Player player) {
		this.player = player;
		curDirection = Cardinal.DOWN;
		walking = new Walking(player);
		standing = new Standing(player);
		attacking = new Attacking(player);
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
	
	public void enableNonAttack(boolean theswitch) {
		walking.setEnabled(theswitch);
		standing.setEnabled(theswitch);
	}
	
	public Cardinal getCurrentDirection() {
		return curDirection;
	}
	
	public void setCurrentDirection(Cardinal direction) {
		curDirection = direction;
	}
	
	public boolean isWalking() {
		return walking.isActive();
	}
	
	public boolean isAttacking() {
		return attacking.isActive();
	}
}
