package src.model.actions;

public interface IActionExecutor extends IActionAgent{
    public void receiveMessage(String message);
}
