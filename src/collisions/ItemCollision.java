package collisions;

import actions.Grabbing;
import app.RPGGame;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

public class ItemCollision extends BasicCollisionGroup {

	private RPGGame game;
	private String itemName;
	
	public ItemCollision (RPGGame game, String itemName) {
		this.game = game;
		this.itemName = itemName;
	}
	
	public void collided(Sprite character, Sprite scenery) {
		Grabbing grabbing = (Grabbing) game.getPlayer().getAction("grabbing");
		if (!grabbing.isActionable()) {
			grabbing.setActionable(true);
			grabbing.setGrabItem(game.getLevel().getItem(itemName));
		}
	}

}
