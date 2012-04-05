package counters;

public abstract class BaseCounter {
	private Integer count;
	private Integer init;
	private boolean full = true;
	private boolean empty = false;
	
	public BaseCounter (int count) {
		this.count = count;
		this.init = count;
	}
	
	public abstract void update(long elapsed);
	
	public void increase ()  {
		if (count <= init) count++;
		if (count == init) full = true;
	}
	
	public void decrease () {
		if (count >= 0) {
			count--;
			full = false;
		}
		else empty = true;
	}
	
	public void increase(int count) {
		if (!full) {
			this.count = this.count + count;
			if (this.count >= init) {
				this.count = init;
				full = true;
			}	
		}
	}
	
	public void decrease(int count) {
		if (!empty) {
			this.count = this.count - count;
			if (this.count <= 0) {
				this.count = 0;
				empty = true;
			}	
		}
	}
	
	public boolean isEmpty() {
		return empty;
	}
	
	public boolean isFull() {
		return full;
	}
	
	public void reset() {
		count = init;
	}
	
	public void setCount(int count) {
		this.count = count;
	}
	
	public Integer getCount() {
		return count;
	}
	
	public Integer getInitial() {
		return init;
	}
}
