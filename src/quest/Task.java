package quest;

public abstract class Task 
{
	protected boolean isComplete =  false;
	protected String description;
	
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
