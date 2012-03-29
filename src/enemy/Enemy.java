package enemy;

import java.awt.image.BufferedImage;

import app.Main;
import collisions.EnemyCollision;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

public abstract class Enemy {
	protected static Main game;
	private SpriteGroup group;
	private BufferedImage image;
	private String name;
	private Sprite enemy;
	protected int health = 1;
	private boolean dead = false;
	private EnemyCollision collision;
	
	public Enemy (Main game, String name) {
		Enemy.game = game;
		this.name = name;
		this.group = new SpriteGroup(name+"_"+game.getRandom(0, 10000));
		this.image = game.getImage("resources/npc/"+name+".gif");
	}
	
	public void add(int[] location, int layer) {
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
	
	public void reduceHealth() {
		health--;
	}
	
	public void generate() {
		game.getField().addGroup(group);
		setCollision();
	}
	
	public void setDead(boolean dead) {
		this.dead = dead;
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
		collision = new EnemyCollision(game, name);
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
