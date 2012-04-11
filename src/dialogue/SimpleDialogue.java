package dialogue;

public class SimpleDialogue extends AbstractDialogue{
	
	private String[] script;
	private int index;

	public SimpleDialogue(String url){
		script = readFile(url);
		index = 0;
	}
	
	@Override
	public void goToNextLine(boolean choice) {
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
		return index == script.length;
	}

}
