package inventory;

import app.RPGGame;


/**
 * Class for items that heal the hero.
 * 
 * Health potions are of the form
 * "Name, healthpotion, boolean forSale, int Price"
 * for example
 * "Super Potion, healthpotion, false"
 * 
 * @author Chris Dennis
 */
public class HealthPotion extends ItemSub implements UseItemInter
{
    private int healthChange;
    private HealthPotion(){
        
    }

    public HealthPotion (RPGGame game2, String name, String gifName, int value)
    {
        super(game2, name, gifName);
        category = "Health Potion";
        game2.addItems(this);
        healthChange= value;
    }
    

    @Override
    public boolean isThisKindOfItem (String toParse)
    {    
        String myCateg=super.parseCategory(toParse);
        if (myCateg.equalsIgnoreCase("HealthPotion")) return true;
        return false;
    }


    @Override
    public ItemSub parseItem (RPGGame game2, String toParse)
    {
        return new HealthPotion(game2, 
                           super.parseName(toParse),
                           super.parseGifName(toParse),
                          parsePotionValue(toParse));
    }
    private int parsePotionValue (String toParse)
    {
        String[] parseArray = toParse.split(",");
        return Integer.parseInt(parseArray[parseArray.length-1].trim());
    }


    public static ItemFactory getFactory(){
        return new ItemFactory(new HealthPotion());
    }

    @Override
    public void use ()
    {
        game.getPlayer().getPCs().getHealth().increase(healthChange);   
    }
    public int getHealthChange(){
        return healthChange;
    }
    
    public int compareTo (ItemSub it)
    {
        if (category != it.getCategory()) return category.compareTo(it.getCategory());
        return compareTo((HealthPotion) it);
    }


    public int compareTo (HealthPotion hp)
    {
        if (healthChange != hp.getHealthChange()) return healthChange - hp.getHealthChange();
        if (myName != hp.getName()) return myName.compareTo(hp.getName());
        return 0;
    }

}
