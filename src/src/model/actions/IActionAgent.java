package src.model.actions;

public interface IActionAgent {
	public default void connect(IActionAgent agent) {}
}
