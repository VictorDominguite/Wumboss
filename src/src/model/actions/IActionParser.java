package src.model.actions;

public interface IActionParser extends IActionCreator, IActionExecutor{
	public void connect(IActionExecutor actor);
}
