package level;

import java.awt.Graphics2D;
import java.util.HashMap;

import quest.DestroyTask;
import quest.FetchTask;
import quest.Quest;
import quest.RewardQuest;
import quest.Task;


import com.golden.gamedev.engine.BaseIO;
import com.golden.gamedev.engine.BaseLoader;


import inventory.ItemSub;
import npc.Priest;
import npc.TestQuestGiver;
import saving_loading.AttributeContainer;
import scenery.Scenery;
import app.RPGGame;


public class Start extends Level
{

	BaseLoader bsLoader;
	BaseIO bsIO;
	
    public Start (BaseLoader bsLoader, BaseIO bsIO, RPGGame game2, String levelFilename) {
        super(bsLoader, bsIO, game2, levelFilename);
	}


    protected void addNPCs ()
    {
    	AttributeContainer ac = new AttributeContainer();
    	Priest priest = new Priest(game, ac); 
    	int[] loc =
            new int[] {
                    game.getBG().getWidth() / 2 - priest.getImage().getWidth() /
                            4,
                    90 };
    	ac.put("location", loc);
    	ac.put("name", "priest");
    	ac.put("type", "priest");
        priest.add(loc, 6);
        npcs.put("priest", priest);
    }


    protected void addScenery ()
    {
        Scenery shrubs = new Scenery(game, "resources/scenery/plant.gif");
        int reverser = -1;
        int leveler = 0;
        for (int i = 1; i <= 10; i++)
        {
            reverser *= -1;
            if ((i - 1) % 2 == 0) leveler++;
            int[] location =
                new int[] {
                        game.getBG().getWidth() / 2 + 30 * reverser * i,
                        20 + leveler * 10 };
            shrubs.add(location, 0);
        }

        Scenery statue = new Scenery(game, "resources/scenery/statue.gif");
        int[] statloc =
            new int[] {
                    game.getBG().getWidth() / 2 - statue.getImage().getWidth() /
                            4,
                    0 };
        statue.add(statloc, 4);

        Scenery arch = new Scenery(game, "resources/scenery/arch.gif");
        int[] archloc = new int[] { game.getBG().getWidth() / 2 - 65, -50 };
        arch.add(archloc, 3);

        scenery.put("shrubs", shrubs);
        scenery.put("statue", statue);
        scenery.put("arch", arch);
    }


    protected void addItems ()
    {
        ItemSub sword =
            MI.parseExpression("Golden Sword within Start, sword, Weapon, 55");
        int[] swordLoc =
            new int[] {
                    game.getBG().getWidth() / 2 - 10,
                    game.getBG().getHeight() / 4 * 3 };
        sword.add(swordLoc, 0);
        items.put("sword", sword);
        
        ItemSub potion =
            MI.parseExpression("SuperPotion within Start, sword, HealthPotion, 5");
        int[] potLoc =
            new int[] {
                    game.getBG().getWidth() / 2 - 10,
                    game.getBG().getHeight() / 4 * 3 + 10 };
        potion.add(potLoc, 0);
        items.put("potion", potion);
    }


    public void render (Graphics2D g)
    {
        if (game.getDialog().getMessage().equals("")) game.getDialog()
                                                          .setMessage("Press \"enter\" to talk to NPCs and pick up objects.");
        if (getLevelTime() < 1500) game.getDialog().showMessage(g);
    }


    public void nextLevel (String levelFilename)
    {
        game.setLevel(new End(bsLoader, bsIO, game, levelFilename));
        game.getLevel().generate();
    }


    protected void addEnemies ()
    {}


}
