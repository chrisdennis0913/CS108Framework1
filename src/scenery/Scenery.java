package scenery;

import java.awt.image.BufferedImage;

import app.Main;
import collisions.SceneryCollision;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

public class Scenery {
	protected static Main game;
	private SpriteGroup group;
	private BufferedImage image;
	
	public Scenery (Main game, String imageURL) {
		Scenery.game = game;
		this.image = game.getImage(imageURL);
		this.group = new SpriteGroup("scenery_"+game.getRandom(0, 1000000));
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	public void add(int[] location, int layer) {
		Sprite scenery = new Sprite(image, location[0], location[1]);
		scenery.setLayer(layer);
		group.add(scenery);
	}
	
	public void generate() {
		game.getField().addGroup(group);
		setCollision();
	}
	
	public void setCollision() {
		SceneryCollision collision = new SceneryCollision();
		collision.pixelPerfectCollision = true;
		game.getField().addCollisionGroup(game.getPlayer().getGroup(),
				getGroup(), collision);
	}
	
	public SpriteGroup getGroup() {
		return group;
	}
}
