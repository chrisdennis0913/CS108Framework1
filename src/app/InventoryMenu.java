package app;

import inventory.ItemSub;
import inventory.PlayerInventory;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import com.golden.gamedev.object.GameFont;
import com.golden.gamedev.object.Timer;


public class InventoryMenu {

    private GameFont font;
    private BufferedImage menuBackground;
    private BufferedImage arrow;
    private BufferedImage star;
    private int option;
    private int numOptions;
    private boolean blink;
    private Timer blinkTimer = new Timer(400);
    private PlayerInventory PI;
    private RPGGame game;
    private ArrayList<ItemSub> optionsList = new ArrayList<ItemSub>();
    private boolean firstTime = true;


    public InventoryMenu (PlayerInventory playInv) {
        PI = playInv;
        game = PI.getGame();
        numOptions = PI.getSize();
        arrow = game.getImage("Arrow.png");
        menuBackground = game.getImage("resources/items/itemMenuBackground.gif", false);
        font = game.fontManager.getFont(game.getImage("BitmapFont.png"));
        star = game.getImage("resources/items/goldStar.gif");
    }


    public void initResources () {

    }


    public void update (long elapsedTime) {
        if (blinkTimer.action(elapsedTime)) {
            blink = !blink;
        }

        switch (game.bsInput.getKeyPressed()) {
            case KeyEvent.VK_ENTER:
                if (option == 0) {
                    // Back to main game screen.
                    game.unPauseGameForInventory();
                }
                else {
                    ItemSub currentItem = optionsList.get(option - 1);
                    currentItem.use();
                    if (currentItem.getCategory()
                                   .equalsIgnoreCase("healthpotion")) PI.remove(currentItem,
                                                                                1);
                }
                optionsList = new ArrayList<ItemSub>();
                firstTime=true;
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


    public void render (Graphics2D g) {
        g.drawImage(menuBackground, 0, 0, null);
        font.drawString(g, "BACK TO GAME", 30, 20);
        int count = 0;
        for (ItemSub itm : PI) {
            font.drawString(g, itm.getName().toUpperCase(), 30, 40 + count * 20);
            count++;
            if (itm.isEquipped())
                g.drawImage(star, 4, 17+count*20,null);
            if (firstTime) optionsList.add(itm);
        }
        firstTime = false;

        if (!blink) {
            g.drawImage(arrow, 14, 17 + (option * 20), null);
        }
    }

}
