package inventory;

import java.util.ArrayList;


public class MakeItems
{
    // Items are constructed in the form "ItemName, Category, forSale, price"
    // where forSale is a boolean and price is an int
    // Be sure to separate by commas and do no use within the Item's Name

    public MakeItems ()
    {

    }


    public ItemSub parseExpression (String input)
    {
        ArrayList<ItemFactory> itemList = new ArrayList<ItemFactory>();
        itemList.add(Weapon.getFactory(input));
        itemList.add(KeyItem.getFactory(input));
        
        for (ItemFactory itemFact : itemList)
        {
            if (itemFact.isThisKindOfItem(input))
            {
                return itemFact.parseItem(input);
            }
        }
        throw new MakeItemsException("Unexpected item type " + input);
    }
}
