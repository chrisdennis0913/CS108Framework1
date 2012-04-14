package inventory;

import java.util.ArrayList;
import java.util.TreeMap;
import app.RPGGame;

public class ItemStore
{
    private Inventory myInventory;
    private TreeMap<ItemSub, Integer> myPrices;
   
    public ItemStore(RPGGame game){

        myInventory = new Inventory();
    }
    
    public void addItems(ItemSub itm, int quantity){
        myInventory.add(itm, quantity);
        
        ArrayList<ItemSub> IBCArray;
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
    
//    public void adjustPrice(ItemSub item, int amtChange){
//        for (ItemSub i : myInventory){
//            int currentPrice = i.getPrice();
//            int newPrice = currentPrice.adjustPrice();
//            item.setPrice(newPrice);
//        }
//    }
    
}
