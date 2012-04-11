package player;

import java.awt.image.BufferedImage;

import actions.Direction;

import com.golden.gamedev.object.AnimatedSprite;

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
		AnimatedSprite character = player.getCharacter();
		character.setSpeed(getHorSpeed(), getVerSpeed());
		if (images.length == 1) {
			character.setLoopAnim(false);
			character.setAnimate(false);
		}
		else {
			character.getAnimationTimer().setDelay(100);
			character.setLoopAnim(true);
			character.setAnimate(true);
			character.setAnimationFrame(0, images.length-1);
		}
		player.getGame().getBG().setToCenter(character);	
	}
}
