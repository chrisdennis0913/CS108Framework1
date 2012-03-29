package npc;

import java.awt.image.BufferedImage;
import java.io.File;

import app.Main;

import collisions.NPCCollision;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.util.FileUtil;

public abstract class NPC {
	protected static Main game;
	private SpriteGroup group;
	private BufferedImage image;
	private String name;
	private String[] script;
	private boolean canDie = false;
	private boolean dead = false;
	private NPCCollision collision;
	
	public NPC (Main game, String name) {
		NPC.game = game;
		this.name = name;
		this.group = new SpriteGroup(name+"_"+game.getRandom(0, 10000));
		this.image = game.getImage("resources/npc/"+name+".gif");
		String scriptURL = "resources/script/"+name+".txt";
		script = FileUtil.fileRead(new File(scriptURL));
	}
	
	public void add(int[] location, int layer) {
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
	
	public String[] getScript() {
		return script;
	}
}
