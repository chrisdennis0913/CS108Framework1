package inventory;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class ItemStore
{
    private Inventory myInventory;
    private Map<String, ArrayList<ItemSub>> itemsByCategory;
    
    public ItemStore(){
        myInventory = new Inventory();
        itemsByCategory= new TreeMap<String, ArrayList<ItemSub>>();
    }
    public void addItems(ItemSub itm, int quantity){
        myInventory.add(itm, quantity);
        
        ArrayList<ItemSub> IBCArray;
        String itmCateg= itm.getCategory();
        if (itemsByCategory.containsKey(itmCateg)){
            IBCArray=itemsByCategory.get(itmCateg);
        }
        else{
            IBCArray = new ArrayList<ItemSub>();
        }
        IBCArray.add(itm);
        itemsByCategory.put(itmCateg, IBCArray);
    }
    public void removeItems(ItemSub itm, int quantity){
        myInventory.remove(itm, quantity); 
    }
    /*
     * Removes all traces of items from stores
     */
    public void removeItems(ItemSub itm){
        myInventory.remove(itm); 
        itemsByCategory.remove(itm);
    }
    
}
