package inventory;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;
import app.InventoryMenu;
import app.RPGGame;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.font.SystemFont;


public class PlayerInventory extends Inventory {
    private boolean showInventory = false;
    private RPGGame game;
    private InventoryMenu menu;
    private ItemSub equippedItem;
    private ArrayList<Accessory> accessoryList;


    public PlayerInventory (RPGGame rpggame) {
        super();
        game = rpggame;
        accessoryList = new ArrayList<Accessory>();
    }


    public ItemSub getItem (String itemName) {
        return null;
    }


    public void add (ItemSub itm, int quantity) {
        if (myItemMap.isEmpty()) {
            equippedItem = itm;
        }
        super.add(itm, quantity);
    }


    public void add (ItemSub itm) {
        if (itm.getCategory().equalsIgnoreCase("accessory")){
            Accessory acc = (Accessory) itm;
            acc.equip();
        }
        if (myItemMap.isEmpty()) {
            equippedItem = itm;
        }
        super.add(itm);
    }


    public void remove (ItemSub itm) {
        if (itm.getCategory().equalsIgnoreCase("accessory")){
            Accessory acc = (Accessory) itm;
            acc.unequip();
        }
        if (equippedItem == itm) {
            equippedItem = null;
        }
        super.remove(itm);
    }


    public void remove (ItemSub itm, int quantity) {
        super.remove(itm);
        if (!myItemMap.containsKey(itm)) {
            equippedItem = null;
        }
    }
<<<<<<< .merge_file_3jAlyY
    
    
    public void showFullInventoryMenu(){
        menu=new InventoryMenu(this);
        game.pauseGameForInventory();
=======


    public void showFullInventoryMenu () {
        menu = new InventoryMenu(this);
        game.pauseGame();
>>>>>>> .merge_file_QGM59I
    }


    public void renderMenu (Graphics2D g) {
        menu.render(g);
    }


    public void updateMenu (long elapsed) {
        menu.update(elapsed);
    }


    public void showLimitedInventory (Graphics2D g) {
        if (!showInventory) return;
        SystemFont font =
            new SystemFont(new Font("Arial", Font.BOLD, 12), new Color(255,
                                                                       255,
                                                                       255));
        drawFiveItemBoxes(g);
        int iter = 0;
        for (ItemSub currentItem : this) {
            if (iter > 5) break;
            String currentItemName = currentItem.getName();
            if (currentItemName == null) break;
            if (currentItemName.length() > 12) {
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
                new Sprite(currentItem.image, iter * 80 + 10, 360);
            itemSprite.render(g);
            iter++;
        }
    }


    public void drawAccessories (Graphics2D g) {
        for (Accessory acc : accessoryList) {
            acc.render(g);
        }
    }


    private void drawFiveItemBoxes (Graphics2D g) {
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


    public void toggleShow () {
        showInventory = !showInventory;
    }


    public void turnShowOff () {
        showInventory = false;
    }


    public RPGGame getGame () {
        return game;
    }


    public int getSize () {
        return myItemMap.size();
    }


    public ItemSub getEquipped () {
        return equippedItem;
    }


    public void equipAcc (Accessory acc) {
        if (!accessoryList.contains(acc)) {
            accessoryList.add(acc);
        }
    }


    public void unEquipAcc (Accessory acc) {
        if (accessoryList.contains(acc)) accessoryList.remove(acc);
    }


    public void setEquipped (ItemSub itm) {
        if (contains(itm)) {
            equippedItem = itm;
        }
    }


    public boolean hasAccessory (Accessory accessory) {
        return accessoryList.contains(accessory);
    }

}
