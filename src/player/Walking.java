package player;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import actions.Direction;
import actions.Directions;

public class Walking extends PlayerAction {
	public Directions directions = new Directions();

	public Walking(Player player) {
		super(player);
		setEnabled(true);

		double speed = 0.1;
		directions.add(new PlayerDirection(player, player.getGame().getIm, 0, speed,
				KeyEvent.VK_UP));
		directions.add(new PlayerDirection(player, images, 0, -speed,
				KeyEvent.VK_DOWN));
		directions.add(new PlayerDirection(player, images, -speed, 0,
				KeyEvent.VK_LEFT));
		directions.add(new PlayerDirection(player, images, speed, 0,
				KeyEvent.VK_RIGHT));
	}

	public void update(long elapsed) {
		if (isEnabled())
			for (Direction direction : directions)
				if (getPlayer().getGame().keyDown(direction.getKey())) {
					keyPress((PlayerDirection) direction);
				}
	}

	public void keyPress(PlayerDirection direction) {
		getPlayer().getCharacter().setSpeed(direction.getHorSpeed(),
				direction.getVerSpeed());
	}

	public void render(Graphics2D g) {

	}
}
