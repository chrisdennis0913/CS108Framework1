package inventory;

import app.RPGGame;


public class ItemFactory
{
    private ItemSub myItem;
    
    public ItemFactory(ItemSub item){
        myItem = item;
    }
    public boolean isThisKindOfItem (String toParse){
        return myItem.isThisKindOfItem(toParse);
    }
    public ItemSub parseItem (RPGGame game2, String toParse)
    {
        return myItem.parseItem(game2,toParse);
    }
}
