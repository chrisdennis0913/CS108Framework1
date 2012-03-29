package actions;

import java.awt.event.KeyEvent;

import app.Player;

import com.golden.gamedev.object.AnimatedSprite;

public class Attacking extends Action {

	public Attacking(Player player, int frames, String name) {
		super(player, frames, name);
	}

	public boolean isActionable() {
		return player.hasItem("sword");
	}
	
	private void turn(boolean actionSwitch) {
		if (!actionSwitch) {
			player.getAction("grabbing").setActionable(false);
			player.getAction("talking").setActionable(false);
			player.getAction("walking").setActionable(false);
			player.getAction("standing").setActionable(false);
		}
		else {
			player.getAction("walking").setActionable(true);
			player.getAction("standing").setActionable(true);
		}
	}
	
	private void animate() {
		AnimatedSprite character = player.getCharacter();
		Walking walking = (Walking) player.getAction("walking");
		
		if (walking.getDirection().equals(Walking.cardinal.RIGHT))
			character.setImages(images.get("right"));
		else if (walking.getDirection().equals(Walking.cardinal.LEFT))
			character.setImages(images.get("left"));
		if (walking.getDirection().equals(Walking.cardinal.DOWN))
			character.setImages(images.get("front"));
		else if (walking.getDirection().equals(Walking.cardinal.UP))
			character.setImages(images.get("back"));
		
		character.getAnimationTimer().setDelay(150);
		character.setLoopAnim(true);
		character.setAnimate(true);
		character.setAnimationFrame(0, 2);
		
		character.setSpeed(0, 0);
	}
	
	public void act() {
		if (isActionable() && !isActing() && player.getGame().keyDown(KeyEvent.VK_SPACE)) {
			turn(false);
			animate();
			setActing(true);
		}
		
		if (getActionTime() > 450) {
			turn(true);
			setActing(false);
			setActionable(false);
		}
	}

}
