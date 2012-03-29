package app;

import inventory.Item;

import java.awt.Color;
import java.awt.Font;
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
import com.golden.gamedev.object.font.SystemFont;

public class Player {
	private Main game;
	private SpriteGroup group = new SpriteGroup("Player");
	private AnimatedSprite character;
	private String startSprite = "resources/player/start_sprite.gif";
	
	private Integer health = 10;
	
	private HashMap<String, Action> actions = new HashMap<String, Action>();
	private HashMap<String, Item> inventory = new HashMap<String, Item>();

	public Player(Main game) {
		this.game = game;
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
		actions.put("attacking", new Attacking(this, 3, "attack"));
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
		if (getHealth() < 1)
			game.finish();
		for (String name : actions.keySet())
			if (actions.get(name).isActionable())
				actions.get(name).act();
	}

	public void render(Graphics2D g) {
		for (String name : actions.keySet()) {
			Action action = actions.get(name);
			if (action.isActing() && action.isActionable()
					&& action.isMessageable())
				game.getDialog().showMessage(g);
		}
		
		SystemFont font = new SystemFont(new Font("Arial", Font.BOLD, 20), new Color(255,0,0));
		g.setColor(new Color(0));
		g.drawRect(10, 10, 25, 25);
		g.fillRect(10, 10, 25, 25);
		font.drawText(g, health.toString(), SystemFont.LEFT, 12, 12, 25, 2, 0);
	}

	public Action getAction(String name) {
		return actions.get(name);
	}
	
	public HashMap<String, Action> getActions() {
		return actions;
	}

	public Main getGame() {
		return game;
	}
	
	public Integer getHealth() {
		return health;
	}
	
	public void reduceHealth() {
		health--;
	}

	public AnimatedSprite getCharacter() {
		return character;
	}

	public SpriteGroup getGroup() {
		return group;
	}

	public HashMap<String, Item> getInventory() {
		return inventory;
	}

	public void addItem(Item item) {
		inventory.put(item.getItemName(), item);
	}

	public boolean hasItem(String name) {
		return inventory.containsKey(name);
	}

	public Item getItem(String name) {
		return inventory.get(name);
	}

}
