package src.model.actions;

public interface IActionCreator extends IActionAgent{
	public void connect(String name, IActionAgent agent);
}
