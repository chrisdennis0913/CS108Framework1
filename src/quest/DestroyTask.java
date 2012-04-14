/* 
 * Type of task where player must destroy a certain NPC
 */

package quest;

import npc.NPC;

public class DestroyTask extends Task
{
	private NPC recipient;

	public DestroyTask(String description, NPC recipient) 
	{
		super(description);
		this.recipient = recipient;
	}

	public void update() 
	{
		isComplete = recipient.isDead();
	}

}
