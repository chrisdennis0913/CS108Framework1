package level;

import inventory.ItemSub;
import inventory.MakeItems;

import java.awt.Graphics2D;
import java.util.HashMap;
import com.golden.gamedev.engine.timer.SystemTimer;
import enemy.Enemy;
import scenery.Scenery;
import npc.NPC;
import app.RPGGame;

public abstract class Level {
    protected MakeItems MI;
	protected RPGGame game;
	protected HashMap<String, NPC> npcs = new HashMap<String, NPC>();
	protected HashMap<String, Enemy> enemies = new HashMap<String, Enemy>();
	protected HashMap<String, Scenery> scenery = new HashMap<String, Scenery>();
	protected HashMap<String, ItemSub> items = new HashMap<String, ItemSub>();
	protected SystemTimer levelTimer = new SystemTimer();
	protected long levelStartTime;
	
	public Level(RPGGame game2) {
		this.game = game2;
		MI = new MakeItems(game);
		addScenery(); addNPCs(); addItems(); addEnemies();
		
		levelTimer.setFPS(100);
		levelTimer.startTimer();
		levelStartTime = levelTimer.getTime();
	}
	
	public void addSceneryObject(Scenery scenery) {
		int[] loc = new int[]{
			game.getBG().getWidth()/2,
			game.getBG().getHeight()/2
		};
		scenery.add(loc, 0);
		scenery.generate();
	}
	
	protected abstract void addScenery();
	protected abstract void addNPCs();
	protected abstract void addItems();
	protected abstract void addEnemies();
	
	public NPC getNPC(String name) {
		return npcs.get(name);
	}
	
	public Enemy getEnemy(String name) {
		return enemies.get(name);
	}
	
	public ItemSub getItem(String name) {
	    
		return items.get(name);
	}
	
	public void generate() {
		for (String name : npcs.keySet())
			npcs.get(name).generate();
		for (String name : scenery.keySet())
			scenery.get(name).generate();
		for (String name : items.keySet())
			items.get(name).generate();
		for (String name : enemies.keySet())
			enemies.get(name).generate();
	}
	
	public void update(long elapsed) {
		for (String name : enemies.keySet())
			enemies.get(name).act(elapsed);
	}
	
	public long getLevelTime() {
		return levelTimer.getTime() - levelStartTime;
	}
	
	public void endLevel() {
		for (String name : npcs.keySet())
			npcs.get(name).getGroup().setActive(false);
		for (String name : scenery.keySet())
			scenery.get(name).getGroup().setActive(false);
		for (String name : items.keySet())
			items.get(name).getGroup().setActive(false);
		nextLevel();
	}
	
	public abstract void nextLevel();
	public abstract void render(Graphics2D g);
}
