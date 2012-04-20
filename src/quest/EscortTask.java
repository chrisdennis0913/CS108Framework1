/*
 * Make sure the npc safely arrives at the appointed location
 */

package quest;

import npc.NPC;

public class EscortTask extends Task
{
	private int[] loc;
	private NPC recipient;

	public EscortTask(String description, NPC recipient, int[] loc) 
	{
		super(description);
		this.loc = loc;
		this.recipient = recipient;
	}
	
	public String toString()
	{
		return "Safely escort " + recipient.toString();
	}

	public boolean checkComplete() {
		isComplete = (recipient.getLocation()[0] == loc[0] && 
					  recipient.getLocation()[1] == loc[1]);
		return isComplete;
	}

}
