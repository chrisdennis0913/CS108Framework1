package saving_loading;



import inventory.ItemSub;
import java.awt.Graphics2D;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import level.End;
import level.Level;
import npc.Priest;
import scenery.Scenery;
import app.RPGGame;
import com.golden.gamedev.util.FileUtil;
import enemy.Snake;


public class LevelFromFile extends Level{

    private List<RWGameObject> gameObjects;

    public LevelFromFile (RPGGame game, String levelFilename)
    {
        super(game);
        
//        //FOR DEBUGGING : Create a dummy AttributeCollection to write out for debugging purposes
//        AttributeContainer testAC = new AttributeContainer();
//        testAC.put("type", "priest");
//        testAC.put("name", "priest_1");
//        testAC.put("location", new int[]{300,100});
//        
//        FileWriter f1;
//      try {
//          f1 = new FileWriter("savedmaps/map00.json");
//          f1.write(testAC.asJsonString());
//          f1.close();
//      } catch (IOException e) {
//          // TODO Auto-generated catch block
//          e.printStackTrace();
//      }         

		
        AttributeContainer pac = new AttributeContainer();
        pac.put("name", "priest");
        pac.put("type", "priest");
        AttributeContainer sac = new AttributeContainer();
        sac.put("name", "priest");
        sac.put("type", "priest");
        Priest priest = new Priest(game, pac);
        Snake snake = new Snake(game, sac);     
        
        gameObjects = new ArrayList<RWGameObject>();
        gameObjects.add(new Priest.Factory());
        gameObjects.add(new Snake.Factory());
        MapContainer maps = new MapContainer(npcs, enemies, scenery, items);
        String[] event = FileUtil.fileRead(new File("savedmaps/"+levelFilename));           

        for(int i=0;i<event.length; i++){
            String json = event[i];
            System.out.println("Using Gson.toJson() on a raw collection: " + json);         
            // Create an attribute collection from the line of JSON from file
            AttributeContainer ac = new AttributeContainer(json);
            String type = ac.getType();
            for(int j=0; j< gameObjects.size();j++){
                if(gameObjects.get(j).isThisKindOfObject(type)){
                    gameObjects.get(j).createAndAddToMap(ac, maps);
                }
            }
        }
    }


    protected void addNPCs ()
    {

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
        
        Scenery archStore = new Scenery(game, "resources/scenery/arch.gif");
        int[] archloc2 = new int[] { game.getBG().getWidth() / 300, 15 };
        arch.add(archloc2, 3);

        scenery.put("shrubs", shrubs);
        scenery.put("statue", statue);
        scenery.put("arch", arch);
        scenery.put("store", archStore);
    }


    protected void addItems ()
    { // name, gifName, type/category, damage/value
        ItemSub sword =
            MI.parseExpression("Golden Sword, sword, Sword, 55");
        int[] loc =
            new int[] {
                    game.getBG().getWidth() / 2 - 10,
                    game.getBG().getHeight() / 4 * 3 };
        sword.add(loc, 0);
        items.put("sword", sword);
        ItemSub potion =
            MI.parseExpression("Super Potion, potion, HealthPotion, 2");
        int[] potLoc =
            new int[] {
                    game.getBG().getWidth() / 3 * 2,
                    game.getBG().getHeight() / 4 * 3 };
        potion.add(potLoc, 0);
        items.put("potion", potion);
        
        ItemSub bowAndArrows =
                MI.parseExpression("Twin Bow, bowAndArrow, BowAndArrows, 30");
            int[] bowLoc =
                new int[] {
                        game.getBG().getWidth() / 3,
                        game.getBG().getHeight() / 4 * 3 };
            bowAndArrows.add(bowLoc, 0);
            items.put("BowAndArrows", bowAndArrows);
            
//        ItemSub key =
//                MI.parseExpression("Key, key, KeyItem, 5");
//            int[] keyLoc =
//                new int[] {
//                        game.getBG().getWidth() / 6 * 1,
//                        game.getBG().getHeight() / 4 * 3 };
//            key.add(keyLoc, 0);
//            items.put("key", key);
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
