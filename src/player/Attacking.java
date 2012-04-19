package player;

import inventory.ItemSub;

import java.util.TreeSet;

import actions.Direction;

public class Attacking extends PlayerAction {

	private PlayerDirections directions;
	private TreeSet<ItemSub> myWeaponList = new TreeSet<ItemSub>();
	private long timer = 0;

	public Attacking(Player player) {
		super(player);
		setEnabled(false, true);
		directions = new PlayerDirections(getPlayer(),
				"resources/player/actions/attacking.json");
	}

	public boolean isEnabled() {
		Player player = getPlayer();
		for (ItemSub itm : player.getGame().getInventory()) {
			myWeaponList.add(itm);
		}
		for (ItemSub itm : myWeaponList) {
			if (player.hasItem(itm)) {
				return true;
			}
		}
		return false;
	}
	
	private void reset() {
		timer = 0;
	}

	public void update(long elapsed) {
		if (isEnabled()) {
			if (!isActive()) {
				PlayerDirection direction = (PlayerDirection) directions
						.getDirections()[0];
				if (getActions().checkKeys(direction.getKeys()))
					for (Direction dir : directions) {
						if (getActions().getCurrentDirection().equals(
								dir.getCardinality())) {
							dir.changeCharacter(true, 150);
							setActive(true);
							getActions().enableNonAttack(false);
							break;
						}
					}
			}
			else {
				timer += elapsed;
				if (timer > 500) {
					reset();
					setActive(false);
					getActions().enableNonAttack(true);
				}
			}
		}
	}
}
