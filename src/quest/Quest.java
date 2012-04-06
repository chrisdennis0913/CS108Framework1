package quest;


import java.util.Queue;

import inventory.ItemSub;

public abstract class Quest
{
	Queue<Task> requirements;
	ItemSub reward;
	
	public Quest(Queue<Task> required, ItemSub prize)
	{
		requirements = required;
		reward = prize;
	}
	
	public boolean isDone()
	{
		return requirements.peek() == null;
	}
	
	public abstract void completeQuest();
	
	public ItemSub getReward()
	{
		return reward;
	}

}
