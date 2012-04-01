package inventory;

import app.RPGGame;


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

    public KeyItem (RPGGame game2, String name,
                   boolean sale,
                   int price)
    {
        super(game2, name, sale, price);
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
    public ItemSub parseItem (RPGGame game2, String toParse)
    {
        if (!super.parseSale(toParse))
            return parseItemNotForSale(game2, toParse);
        return new KeyItem(game2, 
                           super.parseName(toParse),
                          true,
                          super.parsePrice(toParse));
    }
    public ItemSub parseItemNotForSale (RPGGame game2, String toParse)
    {
        return new KeyItem(game2, super.parseName(toParse),
                          false,
                          0);
    }

    public static ItemFactory getFactory(){
        return new ItemFactory(new KeyItem());
    }

}
