/*
 * interface for QuestGivers to implement, to ensure that a quest has been completed
 */

package quest;

public interface Observer 
{
	public void update(Quest qu);
}
