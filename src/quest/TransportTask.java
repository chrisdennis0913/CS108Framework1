package quest;

import app.RPGGame;
import inventory.Inventory;
import inventory.ItemSub;
import npc.NPC;

/*
 * Type of task where the player transports an item from point A to point B
 */

public class TransportTask extends Task
{
	
	private ItemSub item;
	private NPC recipient;
	private Inventory inv;
	
	public TransportTask(RPGGame game, String description, ItemSub item, NPC recipient)
	{
		super(description);
		this.item = item;
		this.recipient = recipient;
		inv = game.getInventory();
	}

	public void update() {
		isComplete = inv.contains(item);
	}
	
	public String toString()
	{
		return "transport " + item.toString() + " to " + recipient.toString();
	}

}
