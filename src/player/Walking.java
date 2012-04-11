package player;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import actions.Direction;
import actions.Directions;

public class Walking extends PlayerAction {
	private Directions directions = new Directions();

	public Walking(Player player) {
		super(player);
		setEnabled(true);

		double speed = 0.1;
		BufferedImage[] upImages = new BufferedImage[] {
				player.getGame().getImage("resources/player/walk/back_walk_1.png"),
				player.getGame().getImage("resources/player/walk/back_walk_2.png"),
				player.getGame().getImage("resources/player/walk/back_walk_3.png"),
				player.getGame().getImage("resources/player/walk/back_walk_4.png"),
				player.getGame().getImage("resources/player/walk/back_walk_5.png"),
				player.getGame().getImage("resources/player/walk/back_walk_6.png")
		};
		directions.add(new PlayerDirection(player, upImages, 0, speed,
				KeyEvent.VK_UP));
		
		BufferedImage[] downImages = new BufferedImage[] {
				player.getGame().getImage("resources/player/walk/front_walk_1.png"),
				player.getGame().getImage("resources/player/walk/front_walk_2.png"),
				player.getGame().getImage("resources/player/walk/front_walk_3.png"),
				player.getGame().getImage("resources/player/walk/front_walk_4.png"),
				player.getGame().getImage("resources/player/walk/front_walk_5.png"),
				player.getGame().getImage("resources/player/walk/front_walk_6.png")
		};
		directions.add(new PlayerDirection(player, downImages, 0, -speed,
				KeyEvent.VK_DOWN));
		
		BufferedImage[] leftImages = new BufferedImage[] {
				player.getGame().getImage("resources/player/walk/left_walk_1.png"),
				player.getGame().getImage("resources/player/walk/left_walk_2.png"),
				player.getGame().getImage("resources/player/walk/left_walk_3.png"),
				player.getGame().getImage("resources/player/walk/left_walk_4.png"),
				player.getGame().getImage("resources/player/walk/left_walk_5.png"),
				player.getGame().getImage("resources/player/walk/left_walk_6.png"),
		};
		directions.add(new PlayerDirection(player, leftImages, -speed, 0,
				KeyEvent.VK_LEFT));
		
		BufferedImage[] rightImages = new BufferedImage[] {
				player.getGame().getImage("resources/player/walk/right_walk_1.png"),
				player.getGame().getImage("resources/player/walk/right_walk_2.png"),
				player.getGame().getImage("resources/player/walk/right_walk_3.png"),
				player.getGame().getImage("resources/player/walk/right_walk_4.png"),
				player.getGame().getImage("resources/player/walk/right_walk_5.png"),
				player.getGame().getImage("resources/player/walk/right_walk_6.png"),
		};
		directions.add(new PlayerDirection(player, rightImages, speed, 0,
				KeyEvent.VK_RIGHT));
	}
	
	public Direction getCurrentDirection() {
		return directions.getCu
	}

	public void update(long elapsed) {
		boolean checker = false;
		if (isEnabled())
			for (Direction direction : directions) {
				if (getPlayer().getGame().keyDown(direction.getKey())) {
					keyPress((PlayerDirection) direction);
					checker = true;
					break;
				}
			}
		if (!checker)
			setActive(false);
	}

	public void keyPress(PlayerDirection direction) {
		directions.setCurrentDirection(direction);
		setActive(true);
		direction.changeCharacter();
	}

	public void render(Graphics2D g) {

	}
}