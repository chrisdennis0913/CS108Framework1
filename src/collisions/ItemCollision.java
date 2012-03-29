package collisions;

import actions.Grabbing;
import app.Main;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

public class ItemCollision extends BasicCollisionGroup {

	private Main game;
	private String itemName;
	
	public ItemCollision (Main game, String itemName) {
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
