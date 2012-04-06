package quest;

public class Task 
{
	private boolean isComplete =  false;
	private String description;
	
	public Task (String description)
	{
		this.description = description;
	}
	
	public String getDescription()
	{
		return this.toString();
	}
	
	public boolean getStatus()
	{
		return isComplete;
	}

}
