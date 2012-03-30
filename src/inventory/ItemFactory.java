package inventory;


public class ItemFactory
{
    private ItemSub myItem;
    
    public ItemFactory(ItemSub item){
        myItem = item;
    }
    public boolean isThisKindOfItem (String toParse){
        return myItem.isThisKindOfItem(toParse);
    }
    public ItemSub parseItem (String toParse)
    {
        return myItem.parseItem(toParse);
    }
}
