package src.utils;

public enum Priority {
	LOW(0), MEDIUM(1), HIGH(2), CRITICAL(3);
	
	private int value;
	
	Priority(int i) {
		this.value = i;
	}
	
	private int getValue() {
		return  value;
	}

	public boolean lowerThan(Priority p) {
		return this.getValue() < p.getValue();
	}
}
