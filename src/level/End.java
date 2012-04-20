package level;


import inventory.ItemSub;

import java.awt.Graphics2D;

import quest.DestroyTask;
import quest.Quest;
import quest.RewardQuest;
import quest.Task;

import npc.NPC;
import npc.Priest;
import npc.TestQuestGiver;

import saving_loading.AttributeContainer;
import scenery.Scenery;
import app.RPGGame;
import enemy.Snake;

public class End extends Level {

	public End(RPGGame game) {
		super(game);
	}

	protected void addNPCs() {
		
	}
	
	protected void initQuests()
	{
	
	}

	protected void addScenery() {
		Scenery shrubs = new Scenery(game, "resources/scenery/shrub.gif");

		int row_size = game.getBG().getHeight() / 5;
		int bush_count = 0;
		for (int i = 0; i < 5; i++) {
			int y = row_size * i + shrubs.getImage().getHeight() / 2;
			if (i % 2 == 0)
				bush_count = 4;
			else
				bush_count = 3;
			for (int j = 0; j < bush_count; j++) {
				int x = (game.getBG().getWidth() / bush_count) * j
						+ shrubs.getImage().getWidth() / 4;
				int[] loc = new int[] { x, y };
				shrubs.add(loc, 0);
			}
		}

		scenery.put("shrubs", shrubs);
	}

	protected void addItems() {

	}

	public void render(Graphics2D g) {
		if (game.getDialog().getMessage().equals(""))
			game.getDialog().setMessage(
					"Press \"enter\" to talk to NPCs and pick up objects.");
		if (getLevelTime() < 1500)
			game.getDialog().showMessage(g);
	}

	public void nextLevel() {
		game.finish();
	}

	protected void addEnemies() {
		AttributeContainer ac = new AttributeContainer();
		ac.put("name", "snake");
		ac.put("type", "snake");
		ac.put("location", new int[]{100,100});
		Snake snake = new Snake(game, ac);
		snake.add( (int[]) ac.getObjectForKey("location", int[].class), 4);
		enemies.put("snake", snake);
		
		TestQuestGiver questGiver = new TestQuestGiver(game, ac);
		
		
    	int[] loc = new int[] {game.getBG().getWidth() / 2, 90 };
    	ac.put("location", loc);
    	ac.put("name", "QuestGiver");
    	ac.put("type", "priest");
        questGiver.add(loc, 6);
        npcs.put("QuestGiver", questGiver);
		
		Task t = new DestroyTask("Kill the snake", snake);
		
		ItemSub potion = MI.parseExpression("SuperPotion within Start, sword, HealthPotion, 5");
	       
	    items.put("potion", potion);
		
		Quest killSnake = new RewardQuest(potion, t);
		
		killSnake.addObserver(questGiver);
	}

}
