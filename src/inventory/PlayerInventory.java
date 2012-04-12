package inventory;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import app.RPGGame;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.font.SystemFont;


public class PlayerInventory extends Inventory
{
    public boolean showInventory=false;
    public PlayerInventory (RPGGame rpggame)
    {
        super(rpggame);
    }


    public ItemSub getItem (String itemName)
    {
        return null;
    }


    public void showInventory (Graphics2D g)
    {  
        if (!showInventory)
            return;
        SystemFont font =
            new SystemFont(new Font("Arial", Font.BOLD, 12), new Color(255,
                                                                       255,
                                                                       255));
        drawFiveItemBoxes(g);
        int iter = 0;
        for (ItemSub currentItem : this)
        {
            if (iter > 5) break;
            String currentItemName = currentItem.getName();
            if (currentItemName == null) continue;
            if (currentItemName.length() > 12)
            {
                currentItemName = currentItemName.substring(0, 12) + "...";
            }
            font.drawText(g,
                          currentItemName,
                          SystemFont.LEFT,
                          (iter * 80) + 8,
                          345,
                          70,
                          2,
                          0);
            Sprite itemSprite =
                new Sprite(currentItem.image,
                           iter * 80 + 10,
                           360);
            itemSprite.render(g);
            iter++;
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
    public void toggleShow(){
        showInventory=!showInventory;
    }
    public void turnShowOff(){
        showInventory=false;
    }
    
}
