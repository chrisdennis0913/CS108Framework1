/*
 * Type of quest where the game state changes after quest completion. 
 * For example, a previously locked door may now become locked.
 */

package quest;

import app.RPGGame;

public class StateChangeQuest extends Quest
{

	public StateChangeQuest(Task... required) 
	{
		super(null, required);
	}

	public void completeQuest(RPGGame game2) 
	{	
	}
}
