package inventory;

public class ItemExample
{
    public static void main (String[] args)
    {
        MakeItems MI = new MakeItems(null);
        Inventory gameInventory = new Inventory();
        gameInventory.add(MI.parseExpression("Cool Dagger,Dagger, Weapon, true, 100, 55"));
        gameInventory.add(MI.parseExpression("Golden Sword, Weapon, true, 80, 165"));
        gameInventory.add(MI.parseExpression("Golden Sword2, Weapon, false, 165"));
        gameInventory.add(MI.parseExpression("Master Key, KeyItem, false"));
        gameInventory.add(MI.parseExpression("Skeleton Key, KeyItem, true, 85"));
        gameInventory.add(MI.parseExpression("Room Key, KeyItem, true, 130"));
    }
}
