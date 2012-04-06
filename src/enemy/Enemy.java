package enemy;

import inventory.ItemSub;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import app.Jsonable;
import app.RPGGame;
import collisions.EnemyCollision;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

public abstract class Enemy implements Jsonable{
	protected static RPGGame game;
	private SpriteGroup group;
	private BufferedImage image;
	private String name;
	private Sprite enemy;
	protected int health;
	private boolean dead = false;
	private EnemyCollision collision;
	protected int[] location = new int[2];
	
	private ArrayList<EnemyMod> mods; 
	
	public Enemy (RPGGame game, String name, int health) {
		Enemy.game = game;
		this.name = name;
		this.health = health;
		
		//change to check for duplicates
		this.group = new SpriteGroup(name+"_"+game.getRandom(0, 10000));
		this.image = game.getImage("resources/npc/"+name+".gif");
		
		initMods();
	}
	
	public void initMods()
	{
		mods = new ArrayList<EnemyMod>();
		mods.add(new HealthMod(this, 5));
	}
	
	public void add(int[] location, int layer) {
		this.location = location;
		enemy = new Sprite(image, location[0], location[1]);
		enemy.setLayer(layer);
		group.add(enemy);
	}
	
	public Sprite getSprite() {
		return enemy;
	}
	
	public int getHealth() {
		return health;
	}
	
	public void reduceHealth(int delta)
	{
		health -= delta;
	}
	
	public void generate() {
		game.getField().addGroup(group);
		setCollision();
	}
	
	public void setDead(boolean dead) {
		this.dead = dead;
	}
	
	public ItemSub getItem()
	{
		return EnemyItemGen.getInstance(game).getDroppedItem(this);
	}
	
	public boolean isDead() {
		return dead;
	}
	
	public BufferedImage getImage()  {
		return image;
	}
	
	public abstract void act(long elapsed);
	public abstract void die();
	
	public void setCollision() {
		collision = new EnemyCollision(game, game.getPlayer(), name);
		collision.pixelPerfectCollision = true;
		game.getField().addCollisionGroup(game.getPlayer().getGroup(),
				getGroup(), collision);
	}
	
	public EnemyCollision getCollision() {
		return collision;
	}
	
	public SpriteGroup getGroup() {
		return group;
	}
}
