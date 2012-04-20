package collisions;

import java.util.HashMap;
import actions.Grabbing;
import app.RPGGame;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

import inventory.ItemSub;

public class ItemCollision extends BasicCollisionGroup {

	private RPGGame game;
	private HashMap<Sprite, ItemSub> itemSpriteMap = new HashMap<Sprite, ItemSub>();
	
	public ItemCollision (RPGGame game, String itemName, ItemSub item, Sprite spr) {
		this.game = game;
		itemSpriteMap.put(spr, item);
	}
	
	public void collided(Sprite character, Sprite item) {
		Grabbing grabbing = (Grabbing) game.getPlayer().getAction("grabbing");
		if (!grabbing.isActionable(game)) {
			grabbing.setActionable(true);
			grabbing.setGrabItem(itemSpriteMap.get(item));
		}
	}

}
