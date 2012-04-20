package quest;

import java.util.ArrayList;

public class QuestJournal
{
	private ArrayList<Quest> myQuests;
	
	public QuestJournal()
	{
		myQuests = new ArrayList<Quest>();
	}
	
	public void addQuest(Quest qu)
	{
		myQuests.add(qu);
	}
	
	public void removeQuest(Quest qu)
	{
		myQuests.remove(qu);
	}

	public void update() 
	{
		for (Quest qu: myQuests)
		{
			qu.update();
		}
	}

}
