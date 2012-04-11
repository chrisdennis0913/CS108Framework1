package player;

import java.awt.Graphics2D;

public class Standing extends PlayerAction {

	public Standing(Player player) {
		super(player);
	}

	public void update(long elapsed) {
		if (!getPlayer().getPAs().isWalking()) {
			setActive(true);
		}
		else {
			setActive(false);
		}
	}

	public void render(Graphics2D g) {

	}
}
