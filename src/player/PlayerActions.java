package player;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import actions.BaseActions;
import actions.Directions.Cardinal;

public class PlayerActions extends BaseActions {

	private Player player;
	private Walking walking;
	private Standing standing;
	private ArrayList<Attacking> attacking= new ArrayList<Attacking>();
	private Attacking bowAttacking;
	private Attacking swordAttacking;
	
	public PlayerActions(Player player) {
		this.player = player;
		walking = new Walking(this);
		standing = new Standing(this);
		Attacking bowAttacking = new SwordAttacking(this);
		Attacking swordAttacking = new BowAttacking(this);
		getActions().add(walking);
		getActions().add(standing);
		getActions().add(bowAttacking);
		getActions().add(swordAttacking);
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
	    for(Attacking atk: attacking)
	        if (atk.isActive())
	            return true;
		return false;
	}
}
//
