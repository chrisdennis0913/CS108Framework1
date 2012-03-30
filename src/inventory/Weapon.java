package inventory;


/**
 * Class for items that do damage.
 * Has instance variable myDamage to represent base damage value
 * Weapons are in the form of
 * "Name, weapon, boolean forSale, int Price, int Damage"
 * for example:
 * "Dagger, Weapon, true, 100, 55"
 * or
 * "Sword, Weapon, false, 85"
 * 
 * @author Chris Dennis
 */
public class Weapon extends ItemSub
{
    private int myDamage;
    private boolean equipped;

    private Weapon(){
        
    }

    private Weapon (String name,
                   boolean sale,
                   int price,
                   int damage)
    {
        super(name, sale, price);
        myDamage = damage;
        category = "Weapon";
        equipped = false;
    }


    @Override
    public boolean isThisKindOfItem (String toParse)
    {    
        String myCateg=super.parseCategory(toParse);
        if (myCateg.equalsIgnoreCase("weapon")) return true;
        return false;
    }


    @Override
    public ItemSub parseItem (String toParse)
    {
        if (!super.parseSale(toParse))
            return parseWeaponNotForSale(toParse);
        return new Weapon(super.parseName(toParse),
                          true,
                          super.parsePrice(toParse),
                          parseDamage(toParse));
    }
    public ItemSub parseWeaponNotForSale (String toParse)
    {
        return new Weapon(super.parseName(toParse),
                          false,
                          0,
                          parseDamage(toParse));
    }

    public int getDamage ()
    {
        return myDamage;
    }
    public void equip()
    {
        equipped=true;
    }
    public void unEquip()
    {
        equipped=false;
    }
    public boolean isEquipped()
    {
        return equipped;
    }


    public int parseDamage (String toParse)
    {
        String[] parseArray = toParse.split(",");
        return Integer.parseInt(parseArray[parseArray.length-1].trim());

    }
    public static ItemFactory getFactory(String input){
        return new ItemFactory(new Weapon());
    }
    
    public String toString(){
        StringBuffer result = new StringBuffer();
        result.append("(");
        result.append(myName + " ");
        result.append("is a weapon which does " + myDamage+ " damage and ");
        if (forSale)
        {
            result.append("is sold at " + myPrice + ".");
        }
        else result.append("is not for sale.");
        result.append(")");

        return result.toString();
    }

    public int compareTo (ItemSub it)
    {
        if (category != it.getCategory()) return category.compareTo(it.getCategory());
        return compareTo((Weapon) it);

    }


    public int compareTo (Weapon wp)
    {
        if (myDamage != wp.getDamage()) return myDamage - wp.getDamage();
        if (myName != wp.getName()) return myName.compareTo(wp.getName());
        if (forSale && wp.isForSale()) return myPrice - wp.getPrice();
        return 0;

    }

}
