package inventory;


/**
 * Class for items that must be collected to complete the game.
 * 
 * KeyItems are of the form
 * "Name, keyitem, boolean forSale, int Price"
 * for example
 * "Master Key, keyitem, false"
 * 
 * @author Chris Dennis
 */
public class KeyItem extends ItemSub
{

    private KeyItem(){
        
    }

    public KeyItem (String name,
                   boolean sale,
                   int price)
    {
        super(name, sale, price);
        category = "Key Item";
    }


    @Override
    public boolean isThisKindOfItem (String toParse)
    {    
        String myCateg=super.parseCategory(toParse);
        if (myCateg.equalsIgnoreCase("keyitem")) return true;
        return false;
    }


    @Override
    public ItemSub parseItem (String toParse)
    {
        if (!super.parseSale(toParse))
            return parseItemNotForSale(toParse);
        return new KeyItem(super.parseName(toParse),
                          true,
                          super.parsePrice(toParse));
    }
    public ItemSub parseItemNotForSale (String toParse)
    {
        return new KeyItem(super.parseName(toParse),
                          false,
                          0);
    }

    public static ItemFactory getFactory(String input){
        return new ItemFactory(new KeyItem());
    }

}
