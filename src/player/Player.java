package player;

import inventory.ItemSub;
import inventory.PlayerInventory;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import app.RPGGame;
import collisions.PlayerBoundaryCollision;

import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.SpriteGroup;


public class Player {
	private RPGGame game;
	private SpriteGroup group = new SpriteGroup("Player");
	private AnimatedSprite character;
	private String startSprite = "resources/player/start_sprite.gif";
	private PlayerInventory myInventory= new PlayerInventory(game);
	private PlayerCounters pcs = new PlayerCounters(this);
	private PlayerActions pas;
	private HashMap<String, ItemSub> inventoryWithNames = new HashMap<String, ItemSub>();

	public Player(RPGGame rpgGame) {
		this.game = rpgGame;
		myInventory = new PlayerInventory(game);
		pas = new PlayerActions(this);
	}

	public PlayerCounters getPCs() {
		return pcs;
	}
	
	public PlayerActions getActions() {
		return pas;
	}
	
	public void generate(int[] location) {
		initCharacter(location);
		initCollisions();
	}

	private void initCharacter(int[] location) {
		BufferedImage[] image = new BufferedImage[] { game
				.getImage(startSprite) };
		character = new AnimatedSprite(image, location[0], location[1]);
		character.setLayer(10);
		group.add(character);
		game.getField().addGroup(group);
	}

	private void initCollisions() {
		game.getField().addCollisionGroup(group, null,
				new PlayerBoundaryCollision(game.getBG()));
	}

	public void update(long elapsed) {
		pcs.update(elapsed);
		pas.update(elapsed);
	}

	public void render(Graphics2D g) {
		pcs.render(g);
		pas.render(g);
	}

	public RPGGame getGame() {
		return game;
	}

	public AnimatedSprite getCharacter() {
		return character;
	}

	public SpriteGroup getGroup() {
		return group;
	}

	public HashMap<String, ItemSub> getInventory() {
		return inventoryWithNames;
	}
	

	public void addItem(ItemSub grabItem) {
		inventoryWithNames.put(grabItem.getName(), grabItem);
		myInventory.add(grabItem);
	}

	public boolean hasItem(ItemSub itm) {
	    return myInventory.contains(itm);
	}
	
	public boolean hasItem(String itemName){
	    if (!inventoryWithNames.containsKey(itemName)){
	        return false;
	    }
	     return myInventory.contains(inventoryWithNames.get(itemName));
	}
	

}
