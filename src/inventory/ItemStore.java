package inventory;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import app.RPGGame;

public class ItemStore
{
    private Inventory myInventory;
   
    public ItemStore(RPGGame game){
        myInventory = new Inventory(game);
    }
    
    public void addItems(ItemSub itm, int quantity){
        myInventory.add(itm, quantity);
        
        ArrayList<ItemSub> IBCArray;
        String itmCateg= itm.getCategory();
        IBCArray = new ArrayList<ItemSub>();
        IBCArray.add(itm);

    }
    public void removeItems(ItemSub itm, int quantity){
        myInventory.remove(itm, quantity); 
    }
    /*
     * Removes all traces of items from stores
     */
    public void removeItems(ItemSub itm){
        myInventory.remove(itm); 
    }
    
}
