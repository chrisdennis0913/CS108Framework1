package quest;

public class fetchTask extends Task implements Observer
{

	public fetchTask(String description) 
	{
		super(description);
	}

	
	public void update(Observable o) 
	{
		/*if (o.getState() == something)
		 * isComplete = true;
		 */
	}
	
}
