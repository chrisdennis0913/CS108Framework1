package dialogue;

public class SimpleDialogue extends AbstractDialogue{
	
	private String[] script;
	private int index;

	public SimpleDialogue(String url){
		script = readFile(url);
		index = 0;
	}
	public void goToNextLine(DialogueObject choice) {
		if (!isDone())
			index++;
	}
	
	public String getCurrentLine(){
		return script[index];
	}

	@Override
	public boolean isDone() {
		return index == script.length-1;
	}
	
	public class SimpleDialogueObject extends DialogueObject{
		
		public SimpleDialogueObject(){};
	}

}
