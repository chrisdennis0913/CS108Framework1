package player;

import inventory.ItemSub;

import java.awt.Graphics2D;

import actions.DirectionlessKeys;
import app.Dialog;

public class Grabbing extends PlayerAction {

	private long timer = 0;
	private ItemSub item;
	private DirectionlessKeys keys;

	public Grabbing(Player player) {
		super(player);
		setEnabled(false, true);
		keys = new DirectionlessKeys("resources/player/actions/grabbing.json");
	}

	private void reset() {
		timer = 0;
	}

	private void unsetItem() {
		setActive(false);
		item = null;
		reset();
	}

	public void setItem(ItemSub item) {
		this.item = item;
	}

	public void update(long elapsed) {
		if (isEnabled()) {
			if (getActions().checkKeys(keys.getKeys())) {
				setActive(true);
				Dialog dialog = getPlayer().getGame().getDialog();
				dialog.setMessage(item.getMessage());
				item.getGroup().setActive(false);
				getPlayer().addItem(item);
			} else {
				timer += elapsed;
				if (timer > 500)
					setEnabled(false, false);
			}
		} else if (isActive()) {
			timer += elapsed;
			if (timer > 3000)
				unsetItem();
		}

	}

	public void render(Graphics2D g) {
		if (isActive()) {
			Dialog dialog = getPlayer().getGame().getDialog();
			dialog.showMessage(g);
		}
	}

}
