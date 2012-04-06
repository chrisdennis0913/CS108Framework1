package actions;


import player.Player;
import app.RPGGame;

import com.golden.gamedev.object.AnimatedSprite;

public class Standing extends Action1 {

	private Walking walking;

	public Standing(Player player, int frames, String name, Walking walking)  {
		super(player, frames, name);
		this.walking = walking;
		setActionable(true);
	}

	public void act(RPGGame game2) {
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
