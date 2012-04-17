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
    private int price = 1;

    private KeyItem(){
        
    }

    public KeyItem (RPGGame game2, String name, String gifName)
    {
        super(game2, name, gifName);
        category = "Key Item";
        game2.addItems(this);
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
        return new KeyItem(game2, 
                           super.parseName(toParse),
                           super.parseGifName(toParse));
    }

    public static ItemFactory getFactory(){
        return new ItemFactory(new KeyItem());
    }

    @Override
    public void use ()
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void equip ()
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void unequip ()
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void drop ()
    {
        System.out.println("Cannot drop key items");
        
    }

}
