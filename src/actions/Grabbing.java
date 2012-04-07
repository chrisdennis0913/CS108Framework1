package actions;


import java.awt.event.KeyEvent;

import inventory.ItemSub;

import player.Player;
import app.RPGGame;

public class Grabbing extends Action1 {

	private ItemSub grabItem;

	public Grabbing(Player player, int frames, String name) {
		super(player, frames, name);
		messageable = true;
	}

	public void setGrabItem(ItemSub item) {
		grabItem = item;
	}

	public void act(RPGGame game2) {
		if (!isActing() && player.getGame().keyDown(KeyEvent.VK_ENTER)) {
			setActing(true);
			System.out.println(grabItem);
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
