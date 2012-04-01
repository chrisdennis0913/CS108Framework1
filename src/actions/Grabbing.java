package actions;

import inventory.Item;
import inventory.ItemSub;

import java.awt.event.KeyEvent;

import app.Player;

public class Grabbing extends Action {

	private ItemSub grabItem;

	public Grabbing(Player player, int frames, String name) {
		super(player, frames, name);
		messageable = true;
	}

	public void setGrabItem(ItemSub item) {
		grabItem = item;
	}

	public void act() {
		if (!isActing() && player.getGame().keyDown(KeyEvent.VK_ENTER)) {
			setActing(true);
			player.getGame().getDialog().setMessage(grabItem.getMessage());
			grabItem.getGroup().setActive(false);
			player.addItem(grabItem);
		}

		if (getActionTime() > 2000) {
			setActing(false);
			setActionable(false);
			setGrabItem(null);
		}
	}

}
