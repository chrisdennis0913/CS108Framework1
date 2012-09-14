package dialogue;

public class YesNoDialogue extends AbstractDialogue{
	
	private Node currentNode;
	private int count;

	public YesNoDialogue(String url){
		String[] lines = readFile(url);
		currentNode = generateTree(lines);
	}
	
	@Override
	public void goToNextLine(DialogueObject choice) {
		YesNoDialogueObject thisChoice = (YesNoDialogueObject) choice;
		if (thisChoice.getChoice())
			currentNode = currentNode.left;
		else
			currentNode = currentNode.right;
	}

	@Override
	public String getCurrentLine() {
		return currentNode.info;
	}

	@Override
	public boolean isDone() {
		return currentNode.left == null && currentNode.right == null;
	}
	
	private Node generateTree(String[] lines){
		String message = lines[count];
		count ++;
		Node thisNode = new Node();
		if (!(message.startsWith("##Q "))){
			thisNode.info = message;
			thisNode.left = null; thisNode.right = null;
		}
		else {
			thisNode.info = message.substring(4);
			thisNode.left = generateTree(lines);
			thisNode.right = generateTree(lines);
		}
		return thisNode;
	}
	
	public class YesNoDialogueObject extends DialogueObject{
		
		boolean choice;
		
		public YesNoDialogueObject(boolean choice){
			this.choice = choice;
		}

		public boolean getChoice() {
			return choice;
		}
		
	}

}
