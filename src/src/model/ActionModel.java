package src.model;

import src.model.entidade.dinamica.IActionExecutor;

public class ActionModel implements IRActionExecutor {
    private String action;
    private IActionExecutor actor;

    public void connect(IActionExecutor actor) {
        this.actor = actor;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
