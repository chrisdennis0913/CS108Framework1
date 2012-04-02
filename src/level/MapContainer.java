package level;

import inventory.ItemSub;

import java.util.HashMap;
import java.util.Map;

import npc.NPC;
import scenery.Scenery;
import enemy.Enemy;

public class MapContainer {
		
	public Map<String, NPC> npcs;
	public Map<String, Enemy> enemies;
	public Map<String, Scenery> scenery;
	public Map<String, ItemSub> items;
		
	public MapContainer(){
		npcs = new HashMap<String, NPC>();
		enemies = new HashMap<String, Enemy>();
		scenery = new HashMap<String, Scenery>();
		items = new HashMap<String, ItemSub>();		
	}
	
	public MapContainer(Map<String, NPC> npcs, 
						Map<String, Enemy> enemies, 
						Map<String, Scenery> scenery,
						Map<String, ItemSub> items)
	{
		this.npcs = npcs;
		this.enemies = enemies;
		this.scenery = scenery;
		this.items = items;		
	}
}
