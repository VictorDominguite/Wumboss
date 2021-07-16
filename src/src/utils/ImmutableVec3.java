package src.utils;

public class ImmutableVec3<T>{
	private T value1;
	private T value2;
	private T value3;
	
	public ImmutableVec3(T value1, T value2, T value3) {
		this.value1 = value1;
		this.value2 = value2;
		this.value3 = value3;
	}
	
	public T first() {
		return value1;
	}
	
	public T second() {
		return value2;
	}
	
	public T third() {
		return value3;
	}
}