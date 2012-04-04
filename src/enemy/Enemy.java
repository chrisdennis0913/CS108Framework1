package enemy;

import java.awt.image.BufferedImage;
import java.util.HashMap;

import app.Jsonable;
import app.Main;
import app.RPGGame;
import collisions.EnemyCollision;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

public abstract class Enemy implements Jsonable, IEnemy {
	protected static RPGGame game;
	private SpriteGroup group;
	private BufferedImage image;
	private String name;
	private Sprite enemy;
	protected int health;
	private EnemyCollision collision;
	private HashMap<String,AbstractAttack> attacks;
	private AbstractAI ai;
	protected int[] location = new int[2];
	
	private static final int DEFAULT_INITIAL_HEALTH = 1;
	
	public Enemy (RPGGame game, String name, int initialHealth) {
		Enemy.game = game;
		this.name = name;
		this.group = new SpriteGroup(name+"_"+game.getRandom(0, 10000));
		this.image = game.getImage("resources/npc/"+name+".gif");
		health = initialHealth;
	}
	
	public Enemy (RPGGame game, String name) {
		this(game,name,DEFAULT_INITIAL_HEALTH);
	}
	
	@Override
	public void add(int[] location, int layer) {
		this.location = location;
		enemy = new Sprite(image, location[0], location[1]);
		enemy.setLayer(layer);
		group.add(enemy);
	}
	
	@Override
	public Sprite getSprite() {
		return enemy;
	}

	@Override
	public int getHealth() {
		return health;
	}

	@Override
	@Deprecated
	public void reduceHealth() {
		addToHealth(-1);
	}

	@Override
	public void addToHealth(int delta) {
		health += delta;
	}

	@Override
	public void generate() {
		game.getField().addGroup(group);
		setCollision();
	}

	@Override
	public void setDead(boolean dead) {
		if(dead)
			health = 0;
	}

	@Override
	public boolean isDead() {
		return (health>0);
	}

	@Override
	public BufferedImage getImage()  {
		return image;
	}

	@Override
	public void act(long elapsed){
		ai.act();
	}
	
	public void attack(String attackName){
		attacks.get(attackName);
	}
	
	@Override
	public abstract void die();

	@Override
	public void setCollision() {
		collision = new EnemyCollision(game, name);
		collision.pixelPerfectCollision = true;
		game.getField().addCollisionGroup(game.getPlayer().getGroup(),
				getGroup(), collision);
	}

	@Override
	public EnemyCollision getCollision() {
		return collision;
	}

	@Override
	public SpriteGroup getGroup() {
		return group;
	}
}
