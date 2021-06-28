package src.utils.actions;

public interface IActionCreator{
	public void connect(IActionParser agent);
	public void disconnect();
}
