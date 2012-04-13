package quest;

import npc.QuestGiver;

//An interface to be implemented by quests. 
//QuestGivers will observe quests in order to decide what dialogue to share with the player

public interface Observable 
{
	 public void addObserver(QuestGiver qu);
	 
	 public void removeObserver(QuestGiver qu);
	 
	 public String getState();
	 
	 public void setState(String state);
}
