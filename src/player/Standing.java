package player;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import actions.Directions;

public class Standing extends PlayerAction {

	public Directions directions = new Directions();

	public Standing(Player player) {
		super(player);
		setEnabled(true);

		double speed = 0;
		BufferedImage[] upImages = new BufferedImage[] {
				player.getGame().getImage("resources/player/stand/back_stand_1.gif")
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
