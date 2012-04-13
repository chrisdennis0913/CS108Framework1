package quest;

import java.util.ArrayList;

import inventory.ItemSub;

import app.RPGGame;

public class QuestItem extends ItemSub implements Observable
{
	private ItemSub myItem;
	private ArrayList<Task> myObservers;
	
	public QuestItem (ItemSub item)
	{
		myItem = item;
	}

	public void addObserver(Task t) 
	{
		myObservers.add(t);
	}

	public void removeObserver(Task t) 
	{
		myObservers.remove(t);
	}

	@Override
	public String getState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setState(String state) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isThisKindOfItem(String toParse) 
	{
		return myItem.isThisKindOfItem(toParse);
	}

	public ItemSub parseItem(RPGGame game2, String toParse) 
	{
		return myItem.parseItem(game2, toParse);
	}

    @Override
    public void use ()
    {
        // TODO Auto-generated method stub
        
    }

}
