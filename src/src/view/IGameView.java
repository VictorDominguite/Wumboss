package src.view;

import src.controller.IController;
import src.model.IGameModel;

public interface IGameView {
	public void setControl(IController c);
	public void setModel(IGameModel g);
	
	public void montarView();
}
