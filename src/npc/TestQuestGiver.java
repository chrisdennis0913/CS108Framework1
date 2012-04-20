package npc;

import java.util.ArrayList;

import dialogue.SimpleDialogue;
import dialogue.SimpleDialogue.SimpleDialogueObject;

import app.RPGGame;
import quest.Quest;
import quest.QuestGiver;
import saving_loading.AttributeContainer;

@SuppressWarnings("serial")
public class TestQuestGiver extends StationaryNPC implements QuestGiver
{
	private ArrayList<Quest> myQuests;
	private Quest justFinished = null;
	private boolean talkedTo = false;
	private boolean questCompleted = false;
	private SimpleDialogue dialogue;

	public TestQuestGiver(RPGGame game2, AttributeContainer ac) 
	{
		super(game2, ac);
		setCanDie(false);
		myQuests = new ArrayList<Quest>();	
		attributes = ac;
//		dialogue = new SimpleDialogue("resources/script/testquestgiver.txt");
		dialogue = new SimpleDialogue("resources/script/testquestgiver.txt");
	}

	public String getTalk() 
	{
		if (!talkedTo)
		{
			talkedTo = true;
			return dialogue.getCurrentLine();
		}
		if (myQuests.size() == 0 && !questCompleted)
		{
			questCompleted = true;
			justFinished.completeQuest(game);
			dialogue.goToNextLine(dialogue.new SimpleDialogueObject());
		}
		return dialogue.getCurrentLine();
	}

	//This QuestGiver cannot die
	public void die() {
		
		
	}

	public void update(Quest qu) 
	{
		if (qu.checkComplete())
		{
			myQuests.remove(qu);
			justFinished = qu;
		}
	}

	public void addQuest(Quest qu)
	{
		myQuests.add(qu);
	}
	
	public void removeQuest(Quest qu)
	{
		myQuests.remove(qu);
	}

	public void changeLocation(int[] location) 
	{
		
	}
}
