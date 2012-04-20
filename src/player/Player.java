package player;

import inventory.ItemStore;
import inventory.ItemSub;
import inventory.PlayerInventory;

import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import ai.AbstractBehaviorModifier;
import app.RPGGame;

import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.SpriteGroup;

public class Player implements Cloneable {

	private HashMap<String, ItemSub> inventoryWithNames =
	        new HashMap<String, ItemSub>();
	
	private SpriteGroup group = new SpriteGroup("Player");
	public SpriteGroup projectiles = new SpriteGroup("Projectiles");
	private AnimatedSprite character;
	private String startSprite = "resources/player/start_sprite.gif";
	
	private PlayerCounters pcs = new PlayerCounters(this);
	private PlayerActions pas;
	private ItemStore myStore;
	private RPGGame game;
	private PlayerInventory myInventory = new PlayerInventory(game);
	
	private LinkedList<AbstractBehaviorModifier> behaviorModifiers = new LinkedList<AbstractBehaviorModifier>();

	private static final double INITIAL_PLAYER_X_SPEED = 0.1;
	private static final double INITIAL_PLAYER_Y_SPEED = 0.1;

	private double maxXSpeed = INITIAL_PLAYER_X_SPEED;
	private double maxYSpeed = INITIAL_PLAYER_Y_SPEED;

	public Player(RPGGame rpgGame) {
		this.game = rpgGame;
		myInventory = new PlayerInventory(game);
		pas = new PlayerActions(this);
		myStore = new ItemStore(game);
	}

	public PlayerCounters getPCs() {
		return pcs;
	}

	public PlayerActions getActions() {
		return pas;
	}

	public void update(long elapsed) {
		pcs.update(elapsed);
		pas.update(elapsed);

		for (AbstractBehaviorModifier bm : behaviorModifiers)
			bm.setUp(elapsed);

		if (game.keyPressed(java.awt.event.KeyEvent.VK_I))
			myInventory.toggleShow();

		else if (game.keyPressed(java.awt.event.KeyEvent.VK_O))
			myInventory.showFullInventoryMenu();

		Iterator<AbstractBehaviorModifier> bmReverse = behaviorModifiers
				.descendingIterator();
		while (bmReverse.hasNext())
			if (bmReverse.next().unsetUp(elapsed))
				bmReverse.remove();
	}

	public void render(Graphics2D g) {
		pcs.render(g);
		pas.render(g);
		myInventory.showLimitedInventory(g);
	}
	
	public AnimatedSprite getCharacter() {
		return character;
	}
	
	public PlayerInventory getInventory() {
		return myInventory;
	}

	public ItemStore getItemStore() {
		return myStore;
	}

	public void addItem(ItemSub grabItem) {
		inventoryWithNames.put(grabItem.getName(), grabItem);
		myInventory.add(grabItem);
	}

	public ItemSub getEquipped() {
		return myInventory.getEquipped();
	}

	public void setEquipped(ItemSub itm) {
		myInventory.setEquipped(itm);
	}

	public double getMaxXSpeed() {
		return maxXSpeed;
	}

	public double getMaxYSpeed() {
		return maxYSpeed;
	}

	public boolean hasItem(ItemSub itm) {
		return myInventory.contains(itm);
	}
	
	public SpriteGroup getGroup() {
		return group;
	}

	public boolean hasItem(String itemName) {
		if (!inventoryWithNames.containsKey(itemName)) {
			return false;
		}
		return myInventory.contains(inventoryWithNames.get(itemName));
	}
	
	public void setMaxXSpeed(double xs) {
		maxXSpeed = xs;
	}

	public void setMaxYSpeed(double ys) {
		maxYSpeed = ys;
	}

	public void registerBehaviorModifier(AbstractBehaviorModifier bm) {
		// default is to insert at end of linked list
		registerBehaviorModifier(bm, false);
	}

	public void registerBehaviorModifier(AbstractBehaviorModifier bm,
			boolean insertAtTop) {
		if (insertAtTop)
			behaviorModifiers.addFirst(bm);
		else
			behaviorModifiers.addLast(bm);
	}

	public void registerBehaviorModifierExclusive(AbstractBehaviorModifier bm,
			boolean insertAtTop) {
		Iterator<AbstractBehaviorModifier> iter = behaviorModifiers.iterator();
		while (iter.hasNext())
			if (iter.next().getClass() == bm.getClass())
				iter.remove();

		registerBehaviorModifier(bm, insertAtTop);
	}

	public void deregisterBehaviorModifier(AbstractBehaviorModifier bm) {
		behaviorModifiers.remove(bm);
	}

	public RPGGame getGame() {
		return game;
	}
}
