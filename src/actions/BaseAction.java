package actions;


public abstract class BaseAction {
	private boolean enabled = false;
	private boolean active = false;
	
	public abstract void update(long elapsed);
	
	public void setEnabled(boolean enabled, boolean modifyActive) {
		if (!enabled && modifyActive)
			active = false;
		this.enabled = enabled;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public boolean isEnabled() {
		return enabled;
	}
	
	public boolean isActive() {
		return active;
	}
}
