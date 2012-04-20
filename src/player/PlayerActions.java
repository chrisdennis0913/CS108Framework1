package player;

import inventory.ItemSub;

import java.util.ArrayList;
import java.util.List;

import npc.NPC;
import actions.BaseActions;
import actions.Directions.Cardinal;

public class PlayerActions extends BaseActions {

	private Player player;
	private Cardinal curDirection;
	private Walking walking;
	private Standing standing;
	private ArrayList<Attacking> attacking= new ArrayList<Attacking>();
	private Grabbing grabbing;
	private Talking talking;
	
	public PlayerActions(Player player) {
		this.player = player;
		curDirection = Cardinal.DOWN;
		walking = new Walking(player);
		standing = new Standing(player);
		grabbing = new Grabbing(player);
		talking = new Talking(player);
	
		Attacking bowAttacking = new SwordAttacking(player);
		Attacking swordAttacking = new BowAttacking(player);
	
		attacking.add(bowAttacking);
		attacking.add(swordAttacking);
		
		getActions().add(walking);
		getActions().add(standing);
		getActions().add(grabbing);
		getActions().add(talking);
		getActions().add(bowAttacking);
		getActions().add(swordAttacking);
	}
	
	public boolean checkKeys(List<Integer> keys) {
		for (Integer key : keys)
			if (!player.getGame().keyDown(key))
				return false;
		return true;
	}
	
	public void enableNonAttack(boolean theswitch) {
		walking.setEnabled(theswitch, true);
		standing.setEnabled(theswitch, true);
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
	
	public boolean isGrabbable() {
		return grabbing.isEnabled();
	}
	
	public void setGrabbable(boolean enabled) {
		grabbing.setEnabled(enabled, true);
	}
	
	public void setGrabbableItem(ItemSub item) {
		grabbing.setItem(item);
	}
	
	public boolean isTalkable() {
		return talking.isEnabled();
	}
	
	public void setTalkable(boolean enabled) {
		talking.setEnabled(enabled, true);
	}
	
	public void setTalkingNPC(NPC npc) {
		talking.setNPC(npc);
	}
	
	public boolean isAttacking() {
	    for (Attacking atk: attacking)
	        if (atk.isActive())
	            return true;
		return false;
	}
}
