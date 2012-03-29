package inventory;

import java.awt.image.BufferedImage;

import app.Main;
import collisions.ItemCollision;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

public class Item {
	protected static Main game;
	protected String itemName;
	protected SpriteGroup group;
	protected BufferedImage image;
	
	public Item (Main game, String itemName) {
		Item.game = game;
		this.itemName = itemName;
		group = new SpriteGroup(itemName);
		image = game.getImage("resources/items/"+itemName+".gif");
	}
	
	public void add(int[] loc, int layer) {
		Sprite item = new Sprite(image, loc[0], loc[1]);
		item.setLayer(layer);
		group.add(item);
	}
	
	public void generate() {
		game.getField().addGroup(group);
		setCollision();
	}
	
	public void setCollision() {
		ItemCollision collision = new ItemCollision(game, itemName);
		game.getField().addCollisionGroup(game.getPlayer().getGroup(),
				getGroup(), collision);
	}
	
	public SpriteGroup getGroup() {
		return group;
	}

	public String getItemName() {
		return itemName;
	}
	
	public String getMessage() {
		return "Picked up "+itemName+".";
	}
}
