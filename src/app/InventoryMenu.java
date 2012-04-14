package app;

import inventory.ItemSub;
import inventory.PlayerInventory;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import com.golden.gamedev.object.GameFont;
import com.golden.gamedev.object.Timer;


public class InventoryMenu
{

    private GameFont font;
    private BufferedImage title;
    private BufferedImage arrow;
    private int option;
    private int numOptions;
    private boolean blink;
    private Timer blinkTimer = new Timer(400);
    private PlayerInventory PI;
    private RPGGame game;
    private ArrayList<ItemSub> optionsList = new ArrayList<ItemSub>();
    private boolean firstTime = true;


    public InventoryMenu (PlayerInventory playInv)
    {
        PI = playInv;
        game = PI.getGame();
        numOptions = PI.getSize();
        arrow = game.getImage("Arrow.png");
        title = game.getImage("resources/items/itemMenuBackground.gif", false);
        font = game.fontManager.getFont(game.getImage("BitmapFont.png"));
    }


    public void initResources ()
    {

    }


    public void update (long elapsedTime)
    {
        if (blinkTimer.action(elapsedTime))
        {
            blink = !blink;
        }

        switch (game.bsInput.getKeyPressed())
        {
            case KeyEvent.VK_ENTER:
                if (option == 0)
                {
                    // Back to main game screen.
                    game.unPauseGame();
                }
                else
                {
                    ItemSub currentItem = optionsList.get(option - 1);
                    currentItem.use();
                    PI.remove(currentItem, 1); //work on removing from inventory and from menu
                }
                break;

            case KeyEvent.VK_UP:
                option--;
                if (option < 0) option = numOptions;
                break;

            case KeyEvent.VK_DOWN:
                option++;
                if (option > numOptions) option = 0;
                break;

            case KeyEvent.VK_ESCAPE:
                game.finish();
                break;
        }
    }


    public void render (Graphics2D g)
    {
        g.drawImage(title, 0, 0, null);
        font.drawString(g, "BACK TO GAME", 20, 20);
        int count = 0;
        for (ItemSub itm : PI)
        {
            font.drawString(g, itm.getName().toUpperCase(), 20, 40 + count * 20);
            count++;
            if (firstTime) optionsList.add(itm);
        }
        firstTime = false;

        if (!blink)
        {
            g.drawImage(arrow, 4, 17 + (option * 20), null);
        }
    }

}