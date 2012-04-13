package quest;


import java.util.Queue;

import app.RPGGame;

import inventory.ItemSub;

//QuestLog
//Register to multiple game objects


public abstract class Quest
{
	protected Queue<Task> toDo;
	protected Queue<Task> done;
	private boolean isActive = false;
	
	public Quest(Queue<Task> required)
	{
		toDo = required;
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
	
	public abstract void completeQuest(RPGGame game2);

}
