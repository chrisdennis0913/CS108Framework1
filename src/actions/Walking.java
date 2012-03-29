package actions;

import java.awt.event.KeyEvent;

import app.Player;

import com.golden.gamedev.object.AnimatedSprite;

public class Walking extends Action {

	public enum cardinal {
		LEFT, RIGHT, UP, DOWN, NONE
	};

	private cardinal direction = cardinal.NONE;

	public Walking(Player player, int frames, String name) {
		super(player, frames, name);
		setActionable(true);
	}

	public cardinal getDirection() {
		return direction;
	}

	private void setDirection(cardinal direction) {
		this.direction = direction;
	}

	public void act() {
		AnimatedSprite character = player.getCharacter();
		
		if (isActionable()) {
			double speedX = 0, speedY = 0;
			double curSpeedX = character.getHorizontalSpeed(), curSpeedY = character
					.getVerticalSpeed();
			if (player.getGame().keyDown(KeyEvent.VK_LEFT)) {
				if (curSpeedX == 0.0)
					character.setImages(images.get("left"));
				setDirection(cardinal.LEFT);
				speedX = -0.1;
			}
			if (player.getGame().keyDown(KeyEvent.VK_RIGHT)) {
				if (curSpeedX == 0.0)
					character.setImages(images.get("right"));
				setDirection(cardinal.RIGHT);
				speedX = 0.1;
			}
			if (player.getGame().keyDown(KeyEvent.VK_UP)) {
				if (curSpeedY == 0.0)
					character.setImages(images.get("back"));
				setDirection(cardinal.UP);
				speedY = -0.1;
			}
			if (player.getGame().keyDown(KeyEvent.VK_DOWN)) {
				if (curSpeedY == 0.0)
					character.setImages(images.get("front"));
				setDirection(cardinal.DOWN);
				speedY = 0.1;
			}

			if ((speedX != 0 || speedY != 0) && !isActing()) {
				character.getAnimationTimer().setDelay(100);
				character.setLoopAnim(true);
				character.setAnimate(true);
				character.setAnimationFrame(0, 5);
				setActing(true);
			} else if (speedX == 0 && speedY == 0)
				setActing(false);

			character.setSpeed(speedX, speedY);
		}
		player.getGame().getBG().setToCenter(character);
	}

}
