package enemy;

import inventory.ItemSub;
import app.RPGGame;

public class HealthMod extends ItemSub implements EnemyMod 
{
	private Enemy owner;
	private int healthChange;
	
	public HealthMod(Enemy owner, int healthChange)
	{
		this.owner = owner;
		this.healthChange = healthChange;
	}

	public void remove() 
	{
		owner.reduceHealth(healthChange);
	}

	public void apply()
	{
		owner.reduceHealth(-healthChange);
	}

	public boolean isThisKindOfItem(String toParse) 
	{
		String myCateg=super.parseCategory(toParse);
        if (myCateg.equalsIgnoreCase("mod")) return true;
        	return false;
	}


	public ItemSub parseItem(RPGGame game2, String toParse) 
	{
		return null;
	}

    @Override
    public void use ()
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void equip () {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void unequip () {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void drop () {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean isEquipped () {
        // TODO Auto-generated method stub
        return false;
    }

}
