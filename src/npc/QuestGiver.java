package npc;

import dialogue.SimpleDialogue;
import quest.Observable;
import quest.Observer;
import quest.Quest;
import saving_loading.AttributeContainer;
import app.RPGGame;

@SuppressWarnings("serial")
public class QuestGiver extends NPC implements Observer
{
	private SimpleDialogue dialogue;
	private Quest qu;
	private boolean talkedTo = false;
	private boolean questComplete = false;
	private RPGGame game2;
	
	public QuestGiver(RPGGame game2, AttributeContainer ac, Quest qu) 
	{
		super(game2, ac);
		setCanDie(false);
		attributes = ac;
		dialogue = new SimpleDialogue("resources/script/"+attributes.getType()+".txt");
		this.game2 = game2;
	}


	public String getTalk() 
	{
		if (talkedTo == false)
		{
			talkedTo = true;
			return dialogue.getCurrentLine();
		}
		if (questComplete)
		{
			dialogue.goToNextLine(true);
			qu.completeQuest(game2);
		}
		
		return dialogue.getCurrentLine();
	}

	//QuestGivers cannot die
	public void die()
	{

	}

	public void update(Quest qu) 
	{
		questComplete = qu.isDone();
	}
}
