package player;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import actions.Direction;
import actions.Directions.Cardinal;

import com.golden.gamedev.object.AnimatedSprite;

public class PlayerDirection extends Direction {

	private Player player;
	private List<Integer> keys = new ArrayList<Integer>();

	public PlayerDirection(Player player, BufferedImage[] images,
			double speedX, double speedY, Cardinal cardinal, List<Integer> keys) {
		super(speedX, speedY, cardinal, images);
		this.player = player;
		this.keys.addAll(keys);
	}

	public void changeCharacter(boolean animate, int delay) {
		AnimatedSprite character = player.getCharacter();
		character.setSpeed(getHorSpeed(), getVerSpeed());
		if (animate) {
			character.setImages(getImages());
			if (frameCount() == 1) {
				character.setLoopAnim(false);
				character.setAnimate(false);
			} else {
				character.getAnimationTimer().setDelay(delay);
				character.setLoopAnim(true);
				character.setAnimate(true);
				character.setAnimationFrame(0, frameCount() - 1);
			}
		}
		//player.getGame().getBG().setToCenter(character);
	}
	
	public List<Integer> getKeys() {
		return keys;
	}
}
