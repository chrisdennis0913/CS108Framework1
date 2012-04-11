package inventory;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import app.RPGGame;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.font.SystemFont;


public class PlayerInventory extends Inventory
{
    private RPGGame game;
    
    public PlayerInventory(RPGGame game){
        this.game=game;
    }
    public ItemSub getItem (String itemName)
    {
        return null;
    }


    public void showMessage (Graphics2D g)
    {
        myItemMap.keySet();
        ItemSub[] itemSubArray = (ItemSub[]) myItemMap.keySet().toArray();

        SystemFont font =
            new SystemFont(new Font("Arial", Font.BOLD, 12), new Color(255,
                                                                       255,
                                                                       255));
        drawFiveItemBoxes(g);

        for (int num = 0; num < 5; num++)
        {
            ItemSub currentItem = itemSubArray[num];
            String currentItemName = currentItem.getName();
            if (currentItemName == null) continue;
            if (currentItemName.length() > 20)
            {
                currentItemName = currentItemName.substring(0, 20) + "...";
            }
            font.drawText(g,
                          currentItemName,
                          SystemFont.LEFT,
                          (num * 70) + 5,
                          345,
                          70,
                          2,
                          0);
            Sprite item =
                new Sprite(game.getImage("resources/items/sword.gif"),
                           num * 70 + 5,
                           360);
            item.render(g);
        }
    }


    private void drawFiveItemBoxes (Graphics2D g)
    {
        g.setColor(new Color(0));
        g.drawRect(5, 340, 70, 50); //item one
        g.fillRect(5, 340, 70, 50);
        g.drawRect(85, 340, 70, 50); //item two
        g.fillRect(85, 340, 70, 50);
        g.drawRect(165, 340, 70, 50); //item three
        g.fillRect(165, 340, 70, 50);
        g.drawRect(245, 340, 70, 50); //item four
        g.fillRect(245, 340, 70, 50);
        g.drawRect(325, 340, 70, 50); //item five
        g.fillRect(325, 340, 70, 50);

    }

}
