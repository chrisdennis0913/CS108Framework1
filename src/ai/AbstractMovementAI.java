package ai;

public abstract class AbstractMovementAI {
	
	public abstract double[] getVelocities();
	
	public double getXVelocity(){
		return getVelocities()[0];
	}
	
	public double getYVelocity(){
		return getVelocities()[1];
	}

}
