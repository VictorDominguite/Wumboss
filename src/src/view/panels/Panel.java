package src.view.panels;

import java.awt.LayoutManager;

import javax.swing.JPanel;

import src.view.GameView;

public abstract class Panel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	protected GameView masterView;
	
	protected int weightX = 1;
	protected int weightY = 1;
	
	public Panel(GameView gv) {
		super();
		this.masterView = gv;
	}
	
	public Panel(GameView gv, LayoutManager lm) {
		super(lm);
		this.masterView = gv;
	}

	public int getWeightX() {
		return weightX;
	}
	
	protected void setWeightX(int weightX) {
		this.weightX = weightX;
	}
	
	public int getWeightY() {
		return weightY;
	}
	
	protected void setWeightY(int weightY) {
		this.weightY = weightY;
	}
	
	public GameView getGameView() {
		return masterView;
	}

}
