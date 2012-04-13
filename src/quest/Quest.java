package quest;


import java.util.ArrayList;
import java.util.Queue;

import npc.QuestGiver;

import app.RPGGame;

import inventory.ItemSub;

//QuestLog
//Register to multiple game objects


public abstract class Quest implements Observable
{
	protected Queue<Task> toDo;
	protected Queue<Task> done;
	private boolean isActive = false;
	protected ArrayList<QuestGiver> observers;
	
	public Quest(Queue<Task> required)
	{
		toDo = required;
		observers = new ArrayList<QuestGiver>();
	}
	
	public boolean isDone()
	{
		return toDo.peek() == null;
	}
	
	public void setActive(boolean b)
	{
		isActive = b;
	}
	
	public boolean isActive()
	{
		return isActive;
	}
	
	public void update()
	{
		for (Task t: toDo)
		{
			t.update();
			if(!t.isDone())
				return;
			done.offer(toDo.remove());
		}	
	}
	
	public void addObserver(QuestGiver qu)
	{
		for (QuestGiver qg: observers)
			qg.update(this);
	}


	public void removeObserver(QuestGiver qu)
	{
		observers.remove(qu);
	}
	
	public abstract void completeQuest(RPGGame game2);

}
