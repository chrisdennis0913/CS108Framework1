package collisions;

import inventory.ItemSub;

import java.util.HashMap;

import player.PlayerActions;
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
		System.out.println(itemSpriteMap.keySet());
	}
	
	public void collided(Sprite character, Sprite item) {
		PlayerActions actions = game.getPlayer().getActions();
		if (!actions.isGrabbable()) {
			actions.setGrabbableItem(itemSpriteMap.get(item));
			actions.setGrabbable(true);
		}
	}

}
