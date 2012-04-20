/*
 * The QuestGiver interface is an example of Observer.
 * QuestGivers can be more than just npc's. For instance a sign on the road, or a bulletin board
 * or a letter could be questgivers. 
 * QuestGivers observe quests.
 */

package quest;

public interface QuestGiver 
{
	public void update(Quest qu);
	
	public void addQuest(Quest qu);
}
