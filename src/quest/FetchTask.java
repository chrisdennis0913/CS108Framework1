/*
 * The classic fetch x amount of this_item and bring it to so and so
 * A HashMap is used in the constructor in case items of multiple types are required
 */

package quest;

import java.util.HashMap;

import app.RPGGame;

import npc.NPC;

import inventory.Inventory;
import inventory.ItemSub;

public class FetchTask extends Task 
{

	private HashMap<ItemSub,Integer> itemsToFetch;
	private NPC recipient;
	private Inventory inv;
	
	public FetchTask(RPGGame game, String description, NPC recipient, HashMap<ItemSub, Integer> itemsToFetch) 
	{
		super(description);
		this.itemsToFetch = itemsToFetch;
		this.recipient = recipient;
		inv = game.getInventory();
	}
	

	public void update()
	{
		isComplete = true;
		for (ItemSub s: itemsToFetch.keySet())
		{
			if (inv.getCount(s) != itemsToFetch.get(s))
				isComplete = false;
		}
	}

	public String toString()
	{
		String str = "Collect";
		for (ItemSub s: itemsToFetch.keySet())
		{
			Integer amount = itemsToFetch.get(s);
			str += amount + " " + s.toString(); 
			if (amount > 1)
			{
				str += "s";
			}
		}
		str += " for" + recipient.toString();
		return str;
	}


	
}
