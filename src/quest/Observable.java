package quest;

//An interface to be implemented by quest items. Tasks will monitor quest items
//to follow progression of quest.

public interface Observable 
{
	 public void addObserver(Task t);
	 
	 public void removeObserver(Task t);
	 
	 public String getState();
	 
	 public void setState(String state);
}
