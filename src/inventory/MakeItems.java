package inventory;

import java.util.ArrayList;
import app.RPGGame;


/*
 * Items are constructed in the form 
 * (RPGgame, "ItemName, GifName, Category, forSale, price")
 * where forSale is a boolean and price is an int
 * Be sure to separate by commas and do no use within the Item's Name 
 */
public class MakeItems
{
    protected RPGGame game;

    public MakeItems (RPGGame game2)
    {
        game=game2;
    }


    public ItemSub parseExpression (String input)
    {
        ArrayList<ItemFactory> itemList = new ArrayList<ItemFactory>();
        itemList.add(KeyItem.getFactory());
        itemList.add(HealthPotion.getFactory());
        itemList.add(Sword.getFactory());
        itemList.add(BowAndArrows.getFactory());
        itemList.add(Accessory.getFactory());
        
        
        for (ItemFactory itemFact : itemList)
        {
            if (itemFact.isThisKindOfItem(input))
            {
                return itemFact.parseItem(game, input);
            }
        }
        throw new MakeItemsException("Unexpected item type " + input);
    }
}
