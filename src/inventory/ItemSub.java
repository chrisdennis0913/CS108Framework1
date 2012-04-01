package inventory;

import java.awt.image.BufferedImage;
import collisions.ItemCollision;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import app.RPGGame;

public abstract class ItemSub implements Comparable<ItemSub>
{
    protected static RPGGame game;
    protected String myName;
    protected String category;
    protected int myPrice = 0;
    protected boolean forSale;
    protected SpriteGroup myGroup;
    protected BufferedImage image;


    // Can subclass to create other instance variables
    // such as weight
    protected ItemSub ()
    {}


    public ItemSub (RPGGame game2, String name, String categ, boolean sale, int price)
    {
        ItemSub.game= game2;
        this.myName = name;
        myGroup = new SpriteGroup(myName);
        this.image= game2.getImage("resources/items/" + myName + ".gif");
        category = categ;
        forSale = sale;
        if (forSale)
        {
            myPrice = price;
        }
    }
    public ItemSub (RPGGame game2, String name, boolean sale, int price)
    {
        ItemSub.game=game2;
        this.myName = name;
        category = "Item";
        forSale = sale;
        myGroup = new SpriteGroup(myName);
        this.image= game2.getImage("resources/items/" + myName + ".gif");
        if (forSale)
        {
            myPrice = price;
        }
    }


    public void add (int[] loc, int layer)
    {
        Sprite item = new Sprite(image, loc[0], loc[1]);
        item.setLayer(layer);
        myGroup.add(item);
    }
    public void generate ()
    {
        game.getField().addGroup(myGroup);
        setCollision();
    }
    public void setCollision ()
    {
        ItemCollision collision = new ItemCollision(game, myName);
        game.getField().addCollisionGroup(game.getPlayer().getGroup(),
                                          getGroup(),
                                          collision);
    }


    public SpriteGroup getGroup ()
    {
        return myGroup;
    }


    public String getName ()
    {
        return myName;
    }
    public String getMessage ()
    {
        return "Picked up " + myName + ".";
    }


    public int getPrice ()
    {
        return myPrice;
    }


    public String getCategory ()
    {
        return category;
    }


    public boolean isForSale ()
    {
        return forSale;
    }


    /**
     * @return string representation of item
     */
    public String toString ()
    {
        StringBuffer result = new StringBuffer();
        result.append("(");
        result.append(myName + " ");
        result.append("is a " + category + " ");
        if (forSale)
        {
            result.append("is sold at " + myPrice + ".");
        }
        else result.append("is not for sale.");
        result.append(")");

        return result.toString();
    }


    /**
     * Return value that meets criteria of compareTo conventions.
     * 
     * @param if is the Item to which this is compared Sort by category, then by
     *        name, then by price Higher price is greater
     * @return appropriate value less than zero, zero, or greater than zero
     */
    public int compareTo (ItemSub it)
    {
        if (category != it.getCategory()) return category.compareTo(it.getCategory());
        if (myName != it.getName()) return myName.compareTo(it.getName());
        if (forSale && it.isForSale()) return myPrice - it.getPrice();
        return 0;
    }


    public boolean equals (Object o)
    {
        ItemSub it = (ItemSub) o;

        return compareTo(it) == 0;
    }


    public abstract boolean isThisKindOfItem (String toParse);


    public abstract ItemSub parseItem (RPGGame game2, String toParse);


    public String parseName (String toParse)
    {
        String[] parseArray = toParse.split(",");
        return parseArray[0].trim();
    }


    public String parseCategory (String toParse)
    {
        String[] parseArray = toParse.split(",");
        return parseArray[1].trim();
    }


    public boolean parseSale (String toParse)
    {
        String[] parseArray = toParse.split(",");
        return !parseArray[2].trim().equals("false");
    }


    public int parsePrice (String toParse)
    {
        String[] parseArray = toParse.split(",");
        if (parseArray.length < 4) throw new MakeItemsException("Improper format for including price: " +
                                                                toParse);
        return Integer.parseInt(parseArray[3].trim());
    }

}
