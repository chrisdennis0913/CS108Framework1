/*
 * Type of quest where the player is given a reward at the end
 */

package quest;

import inventory.ItemSub;
import java.util.Queue;

import app.RPGGame;

public class RewardQuest extends Quest
{
	private ItemSub reward;

	public RewardQuest(String description, ItemSub reward, Task... required ) {
		super(description, required);
		this.reward = reward;
	}

	public void completeQuest(RPGGame game2) 
	{
		game2.getPlayer().addItem(reward);
	}
}
