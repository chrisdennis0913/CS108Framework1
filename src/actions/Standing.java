package actions;


import app.Player;

import com.golden.gamedev.object.AnimatedSprite;

public class Standing extends Action {

	private Walking walking;

	public Standing(Player player, int frames, String name, Walking walking)  {
		super(player, frames, name);
		this.walking = walking;
		setActionable(true);
	}

	public void act() {
		AnimatedSprite character = player.getCharacter();
		
		if (!walking.isActing() && !isActing()) {
			if (walking.getDirection().equals(Walking.cardinal.RIGHT))
				character.setImages(images.get("right"));
			else if (walking.getDirection().equals(Walking.cardinal.LEFT))
				character.setImages(images.get("left"));
			if (walking.getDirection().equals(Walking.cardinal.DOWN))
				character.setImages(images.get("front"));
			else if (walking.getDirection().equals(Walking.cardinal.UP))
				character.setImages(images.get("back"));
			setActing(true);
		}
		else setActing(false);
	}

}
