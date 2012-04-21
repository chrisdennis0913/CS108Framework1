package player;

import inventory.ItemStore;
import inventory.ItemSub;
import inventory.PlayerInventory;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import quest.QuestJournal;
import enemy.AbstractBehaviorModifier;
import app.RPGGame;
import collisions.PlayerBoundaryCollision;
import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;


public class Player {

	private HashMap<String, ItemSub> inventoryWithNames =
	        new HashMap<String, ItemSub>();
	
	private SpriteGroup group = new SpriteGroup("Player");
	public SpriteGroup projectiles = new SpriteGroup("Projectiles");
	private AnimatedSprite character;
	private String startSprite = "resources/player/start_sprite.gif";
	
	private PlayerCounters pcs = new PlayerCounters(this);
	private PlayerActions pas;
	private RPGGame game;
	private PlayerInventory myInventory = new PlayerInventory(game);
	
	private LinkedList<AbstractBehaviorModifier> behaviorModifiers = new LinkedList<AbstractBehaviorModifier>();

	private ItemStore myStore;
	private QuestJournal myQuests;

	public Player(RPGGame rpgGame) {
		this.game = rpgGame;
		myInventory = new PlayerInventory(game);
		pas = new PlayerActions(this);
		myStore = new ItemStore(game);
		myQuests = new QuestJournal();
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
		
		if (game.keyPressed(java.awt.event.KeyEvent.VK_J))
			myQuests.toggleShow();
		
		myQuests.update();

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
		myQuests.showJournal(g);
	}
	

	public QuestJournal getQuestJournal()
	{
		return myQuests;
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
	
	public void setWalkingSpeed(double[] speed){
		pas.setWalkingSpeed(speed);
	}
	
	public double[] getWalkingSpeed(){
		return pas.getWalkingSpeed();
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

	public Sprite getSprite(){
		return character;
	}
}
