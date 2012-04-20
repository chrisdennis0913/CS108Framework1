package app;


import java.awt.Graphics2D;
import java.util.Comparator;

import level.Level;
import level.Map;
import saving_loading.LevelFromFile;
import player.Player;

import ai.GameStateProvider;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;
import com.golden.gamedev.engine.BaseIO;
import com.golden.gamedev.engine.BaseLoader;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.background.ImageBackground;

import inventory.Inventory;
import inventory.ItemSub;

public class RPGGame extends GameObject //implements GameStateProvider
{

    public RPGGame (GameEngine arg0)
    {
        super(arg0);
    }

    private PlayField field;
    public Map map;
    private Background bg;
    private Player player;
    private Dialog dialog;
    private Level level;
    private Inventory myInventory;
    private boolean pausedForInventory=false;
    public static String startLevelFilename= "map00.json";



    @SuppressWarnings("rawtypes")
    public void initResources ()
    {
    	map = new Map(bsLoader, bsIO);
        bg = new ImageBackground(getImage("resources/bg.jpg"), 600, 600);
        field = new PlayField(level);
        field.setBackground(bg);
        dialog = new Dialog();
        myInventory = new Inventory();

        player = new Player(this);
        int[] loc =
            new int[] { getBG().getWidth() / 2, getBG().getHeight() / 2 };
        player.generate(loc);

        //level = new Start(this);

        level = new LevelFromFile(bsLoader, bsIO, this, startLevelFilename);
        level.generate();

        field.setComparator(new Comparator()
        {
            public int compare (Object o1, Object o2)
            {
                Sprite s1 = (Sprite) o1, s2 = (Sprite) o2;
                return (int) (s1.getLayer() - s2.getLayer());
            }
        });
    }


    public void render (Graphics2D g)
    {
        if (pausedForInventory){
            player.getInventory().renderMenu(g);
            return;
        }
        field.render(g);
        player.render(g);
        level.render(g);
    }


    public void update (long elapsed)
    {
        if (pausedForInventory){
            player.getInventory().updateMenu(elapsed);
            return;
        }
        player.update(elapsed);
        field.update(elapsed);
        level.update(elapsed);

        
    }


    public Background getBG ()
    {
        return bg;
    }


    public Dialog getDialog ()
    {
        return dialog;
    }


    public PlayField getField ()
    {
        return field;
    }


    public Player getPlayer ()
    {
        return player;
    }


    public Level getLevel ()
    {
        return level;
    }


    public void setLevel (Level level)
    {
        this.level = level;
    }


    public void addItems (ItemSub itm)
    {
        myInventory.add(itm);

    }


    public Inventory getInventory ()
    {
        return myInventory;

    }
    public void pauseGame(){
        pausedForInventory = true;
    }
    public void unPauseGame(){
        pausedForInventory = false;
    }


    /*
	@Override
	public void copyGameState(GameStateProvider gsp) {
		gsp.player = player.clone();
		gsp.level = level.clone();
	}*/
}
