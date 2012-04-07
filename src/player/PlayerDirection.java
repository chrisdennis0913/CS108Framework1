package player;

import java.awt.image.BufferedImage;

import actions.Direction;

public class PlayerDirection extends Direction {

	private Player player;
	private BufferedImage[] images;

	public PlayerDirection(Player player, BufferedImage[] images,
			double speedX, double speedY, int key) {
		super(speedX, speedY, key);
		this.player = player;
		this.images = images;
	}

	public void changeCharacter() {
		
	}
}
