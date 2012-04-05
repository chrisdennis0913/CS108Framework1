package app;

import inventory.Inventory;
import inventory.ItemSub;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import actions.Action;
import actions.Attacking;
import actions.Grabbing;
import actions.Standing;
import actions.Talking;
import actions.Walking;
import collisions.PlayerBoundaryCollision;

import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.SpriteGroup;

import counters.PlayerCounters;

public class Player {
	private RPGGame game;
	private SpriteGroup group = new SpriteGroup("Player");
	private AnimatedSprite character;
	private String startSprite = "resources/player/start_sprite.gif";
	private Inventory myInventory= new Inventory();
	private PlayerCounters pcs = new PlayerCounters(this);
	private HashMap<String, Action> actions = new HashMap<String, Action>();
	private HashMap<String, ItemSub> inventoryWithNames = new HashMap<String, ItemSub>();

	public Player(RPGGame rpgGame) {
		this.game = rpgGame;
	}

	public PlayerCounters getPCs() {
		return pcs;
	}
	
	public void generate(int[] location) {
		initCharacter(location);
		initCollisions();
		initActions();
	}

	private void initActions() {
		actions.put("walking", new Walking(this, 6, "walk"));
		actions.put("standing", new Standing(this, 1, "stand",
				(Walking) actions.get("walking")));
		actions.put("talking", new Talking(this, 1, "stand"));
		actions.put("grabbing", new Grabbing(this, 1, "stand"));
        for (ItemSub itm : game.getInventory())
            System.out.println(itm + "in Player");
		actions.put("attacking", new Attacking(this, 3, "attack", game));
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

	public void update() {
		pcs.updated(0);
		for (String name : actions.keySet())
			if (actions.get(name).isActionable(game))
				actions.get(name).act(game);
	}

	public void render(Graphics2D g) {
		pcs.render(g);
		for (String name : actions.keySet()) {
			Action action = actions.get(name);
			if (action.isActing() && action.isActionable(game)
					&& action.isMessageable())
				game.getDialog().showMessage(g);
		}
	}

	public Action getAction(String name) {
		return actions.get(name);
	}
	
	public HashMap<String, Action> getActions() {
		return actions;
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
	     return myInventory.contains(inventoryWithNames.get(itemName));
	}
	

//	public ItemSub getItem(String name) {
//	    myInventory.
//		return inventory.get(name);
//	}

}
