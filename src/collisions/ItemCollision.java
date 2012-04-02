package collisions;

import inventory.ItemSub;
import java.util.HashMap;
import actions.Grabbing;
import app.RPGGame;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

public class ItemCollision extends BasicCollisionGroup {

	private RPGGame game;
	private String itemName;
	private HashMap<Sprite, ItemSub> itemSpriteMap = new HashMap<Sprite, ItemSub>();
	
	public ItemCollision (RPGGame game, String itemName, ItemSub item, Sprite spr) {
		this.game = game;
		this.itemName = itemName;
		itemSpriteMap.put(spr, item);
//		System.out.println(itemSpriteMap.keySet());
	}
	
	public void collided(Sprite character, Sprite item) {
		Grabbing grabbing = (Grabbing) game.getPlayer().getAction("grabbing");
		if (!grabbing.isActionable(game)) {
			grabbing.setActionable(true);
			grabbing.setGrabItem(itemSpriteMap.get(item));
		}
	}

}
