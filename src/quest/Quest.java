package quest;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;


import app.RPGGame;

import inventory.ItemSub;

public abstract class Quest implements Observable
{
	protected List<Task> toDo;
	protected List<Task> done;
	protected boolean isActive = false;
	protected ArrayList<QuestGiver> observers;
	protected String description;
	
	public Quest(String description, Task... required)
	{
		toDo = Arrays.asList(required);
		observers = new ArrayList<QuestGiver>();
		this.description = description;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public void setActive(boolean b)
	{
		isActive = b;
	}
	
	public boolean isActive()
	{
		return isActive;
	}
	
	public boolean checkComplete()
	{
		for (Task t: toDo)
		{
			if(!t.checkComplete())
				return false;
			done.add(toDo.remove(0));
		}	
		return true;
	}
	
	public void update()
	{
		for (QuestGiver qg: observers)
		{
			qg.update(this);
		}
	}
	
	public void addObserver(QuestGiver qu)
	{
		observers.add(qu);
	}


	public void removeObserver(QuestGiver qu)
	{
		observers.remove(qu);
	}
	
	public abstract void completeQuest(RPGGame game2);

}
