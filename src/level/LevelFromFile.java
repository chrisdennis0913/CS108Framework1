package level;


import inventory.ItemSub;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import npc.Priest;
import scenery.Scenery;
import app.RPGGame;
import app.RWGameObject;

import com.golden.gamedev.engine.BaseIO;
import com.golden.gamedev.util.FileUtil;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import enemy.Snake;

public class LevelFromFile extends Level{
	
	private String[] fileLines;
	private List<RWGameObject> gameObjects;
		
    public LevelFromFile (RPGGame game, String levelFilename, BaseIO bsIO)
    {
        super(game);        
        
        Priest priest = new Priest(game, "priest");
        Snake snake = new Snake(game, "snake");
        /*
        try {
	    	FileWriter f1 = new FileWriter("savedmaps/map00.json"); 
	    	f1.write(priestJson+"\n"); 
	    	f1.write(snakeJson);
	    	f1.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}     
		*/           
        
        gameObjects = new ArrayList<RWGameObject>();
        gameObjects.add(new Priest.Factory());
        gameObjects.add(new Snake.Factory());
        Gson gson = new Gson();
        MapContainer maps = new MapContainer(npcs, enemies, scenery, items);
		String[] event = FileUtil.fileRead(bsIO.getStream("savedmaps/map00.json"));		
		
		for(int i=0;i<event.length; i++){
			String json = event[i];
		    System.out.println("Using Gson.toJson() on a raw collection: " + json);
		    JsonParser parser = new JsonParser();
		    JsonArray array = parser.parse(json).getAsJsonArray();
		    String tag = gson.fromJson(array.get(0), String.class);	
			for(int j=0; j< gameObjects.size();j++){
				if(gameObjects.get(j).isThisKindOfObject(tag)){
					gameObjects.get(j).createAndAddToMap(json, maps);
				}
			}
		}
    }


    protected void addNPCs ()
    {
        /*
    	Priest priest = new Priest(game, "priest");
        int[] loc =
            new int[] {
                    game.getBG().getWidth() / 2 - priest.getImage().getWidth() /
                            4,
                    90 };
        priest.add(loc, 6);
        npcs.put("priest", priest);
        */
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
            MI.parseExpression("Golden Sword of Paradise, sword, Weapon, true, 100, 55");
        int[] loc =
            new int[] {
                    game.getBG().getWidth() / 2 - 10,
                    game.getBG().getHeight() / 4 * 3 };
        sword.add(loc, 0);
        items.put("sword", sword);
    }


    public void render (Graphics2D g)
    {
        if (game.getDialog().getMessage().equals("")) game.getDialog()
                                                          .setMessage("Press \"enter\" to talk to NPCs and pick up objects.");
	        if (getLevelTime() < 1500) game.getDialog().showMessage(g);
	    }


	    public void nextLevel ()
	    {
	        game.setLevel(new End(game));
	        game.getLevel().generate();
	    }


	    protected void addEnemies ()
	    {}

	
}
