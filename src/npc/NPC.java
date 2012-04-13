package npc;

import java.awt.image.BufferedImage;

import saving_loading.AttributeContainer;
import saving_loading.Jsonable;

import app.RPGGame;
import collisions.NPCCollision;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.google.gson.Gson;

public abstract class NPC extends Sprite implements Jsonable{
	protected static RPGGame game;
	private SpriteGroup group;
	private BufferedImage image;
	protected String name;
	private String[] script;
	private boolean canDie = false;
	private boolean dead = false;
	private NPCCollision collision;
	protected int[] location = new int[2];
	protected AttributeContainer attributes;
	protected Gson gson = new Gson();
	
	public NPC (RPGGame game2, AttributeContainer ac) {
		NPC.game = game2;
		attributes = ac;
		name = ac.getName();
		String type = ac.getType();
		this.group = new SpriteGroup(name+"_"+game2.getRandom(0, 10000));
		this.image = game2.getImage("resources/npc/"+type+".gif");
	}
	
	public void add(int[] location, int layer) {
		this.location = location;
		Sprite npc = new Sprite(image, location[0], location[1]);
		npc.setLayer(layer);
		group.add(npc);
	}
	
	public void generate() {
		game.getField().addGroup(group);
		setCollision();
	}
	public abstract String getTalk();
	
	public void setCanDie(boolean canDie) {
		this.canDie = canDie;
	}
	
	public boolean canDie() {
		return canDie;
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
	
	public abstract void die();
	
	public void setCollision() {
		collision = new NPCCollision(game, name);
		collision.pixelPerfectCollision = true;
		game.getField().addCollisionGroup(game.getPlayer().getGroup(),
				getGroup(), collision);
	}
	
	public NPCCollision getCollision() {
		return collision;
	}
	
	public SpriteGroup getGroup() {
		return group;
	}
	
}
