package dialogue;

public class SimpleDialogue extends AbstractDialogue{
	
	private String[] script;
	private int index;

	public SimpleDialogue(String url){
		script = readFile(url);
		index = 0;
	}
	
	@Override
	public void goToNextLine(DialogueObject choice) {
		if (isDone()){
			System.out.println("tried to access past index of current script array");
			return;
		}
		index ++;
	}
	
	public String getCurrentLine(){
		return script[index];
	}

	@Override
	public boolean isDone() {
		return index >= script.length-1;
	}
	
	public class SimpleDialogueObject extends DialogueObject{
		
		public SimpleDialogueObject(){};
		
	}

}
