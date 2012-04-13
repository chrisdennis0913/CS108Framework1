package quest;

import java.util.ArrayList;

import npc.QuestGiver;

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
	public void addObserver(QuestGiver qu) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeObserver(QuestGiver qu) {
		// TODO Auto-generated method stub
		
	}

}
