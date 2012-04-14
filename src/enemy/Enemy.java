package enemy;


import inventory.ItemSub;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import saving_loading.AttributeContainer;
import saving_loading.Jsonable;

import ai.AbstractAI;
import app.RPGGame;
import collisions.EnemyCollision;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.google.gson.Gson;

public abstract class Enemy implements Jsonable, IEnemy {

	protected static RPGGame game;
	private SpriteGroup group;
	private BufferedImage image;
	private String name;
	private Sprite enemy;
	private int health;
	private EnemyCollision collision;
	private AbstractAI myAI;
	protected int[] location = new int[2];
	private ArrayList<EnemyMod> mods; 
	protected AttributeContainer attributes;
	protected Gson gson = new Gson();
	protected HashMap<String,AbstractAttack> spontaneousAttacks = new HashMap<String,AbstractAttack>();
	protected HashMap<String, AbstractAttack> reactiveAttacks = new HashMap<String,AbstractAttack>();
	
	private static final int DEFAULT_INITIAL_HEALTH = 1;
	
	public Enemy (RPGGame game, AttributeContainer ac, int health) {
		Enemy.game = game;
		name = ac.getName();
		attributes = ac;
		this.health = health;
		
		//change to check for duplicates
		this.group = new SpriteGroup(name+"_"+game.getRandom(0, 10000));
		this.image = game.getImage("resources/npc/"+attributes.getType()+".gif");
		initMods();
		initAttacks();

	}
	
	public Enemy (RPGGame game, AttributeContainer ac) {
		this(game,ac,DEFAULT_INITIAL_HEALTH);		
	}
	
	public void initMods()
	{
		mods = new ArrayList<EnemyMod>();
		mods.add(new HealthMod(this, 5));
	}
	
	public abstract void initAttacks();
	
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
	
	@Override
	public void onCollision(){
		myAI.onCollision();
	}

	public void attack(String attackName, long elapsedTime){
		attack(spontaneousAttacks.get(attackName), elapsedTime);
	}
	
	public void attack(AbstractAttack attack, long elapsedTime){
		attack.performAttack(elapsedTime);
	}
	
	public Collection<AbstractAttack> getSpontaneousAttacks(){
		return spontaneousAttacks.values();
	}
	
	public Collection<AbstractAttack> getReactiveAttacks(){
		return reactiveAttacks.values();
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
	
	public double getMaxXSpeed(){
		return 0.05;
	}
	public double getMaxYSpeed(){
		return 0.05;
	}
	
}
