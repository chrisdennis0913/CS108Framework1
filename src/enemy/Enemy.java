package enemy;


import java.awt.image.BufferedImage;
import java.util.Collection;
import java.util.HashMap;
import java.util.ArrayList;

import ai.AbstractAI;
import app.Jsonable;
import app.RPGGame;
import collisions.EnemyCollision;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import inventory.ItemSub;

public abstract class Enemy implements Jsonable, IEnemy {

	protected static RPGGame game;
	private SpriteGroup group;
	private BufferedImage image;
	private String name;
	private Sprite enemy;
	private int health;
	private EnemyCollision collision;
	protected HashMap<String,AbstractAttack> attacks;
	private AbstractAI myAI;
	protected int[] location = new int[2];
	private ArrayList<EnemyMod> mods; 
	
	private static final int DEFAULT_INITIAL_HEALTH = 1;
	
	public Enemy (RPGGame game, String name, int health) {
		Enemy.game = game;
		this.name = name;
		this.health = health;
		
		//change to check for duplicates
		this.group = new SpriteGroup(name+"_"+game.getRandom(0, 10000));
		String type = getType(name);
		this.image = game.getImage("resources/npc/"+type+".gif");
		initMods();
		initAttacks();

	}
	
	public Enemy (RPGGame game, String name) {
		this(game,name,DEFAULT_INITIAL_HEALTH);		
	}
	
	public void initMods()
	{
		mods = new ArrayList<EnemyMod>();
		mods.add(new HealthMod(this, 5));
	}
	
	public void initAttacks(){
		attacks = new HashMap<String,AbstractAttack>();
	}
	
	public void setAI(AbstractAI ai){
		myAI = ai;
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
	
	public void reduceHealth(int delta)
	{
		health -= delta;
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

	public ItemSub getItem()
	{
		return EnemyItemGen.getInstance(game).getDroppedItem(this);
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
	public void act(long elapsedTime){
		myAI.act(elapsedTime);
	}
	
	public void attack(String attackName, long elapsedTime){
		attack(attacks.get(attackName), elapsedTime);
	}
	
	public void attack(AbstractAttack attack, long elapsedTime){
		attack.performAttack(elapsedTime);
	}
	
	public Collection<AbstractAttack> getAttacks(){
		return attacks.values();
	}
	
	@Override
	public abstract void die();

	@Override
	public void setCollision() {
		collision = new EnemyCollision(game, game.getPlayer(), name);
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
	
	public String getType(String name){
		String type =name;
		if(name.indexOf('_') >0)
			type = name.substring(0,name.indexOf('_'));
		return type;
	}
}
