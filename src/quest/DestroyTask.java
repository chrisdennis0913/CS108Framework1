/* 
 * Type of task where player must destroy a certain NPC
 */

package quest;

import enemy.Enemy;
import npc.NPC;

public class DestroyTask extends Task
{
	private Enemy recipient;

	public DestroyTask(String description, Enemy recipient) 
	{
		super(description);
		this.recipient = recipient;
	}

	public boolean checkComplete()
	{
		isComplete = recipient.isDead();
		return isComplete;
	}

}
