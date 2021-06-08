package src.view.panels;

import java.awt.LayoutManager;

import javax.swing.JPanel;

public abstract class Panel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	protected int weightX = 1;
	protected int weightY = 1;
	
	public Panel(LayoutManager lm) {
		super(lm);
	}
	
	public Panel() {
		super();
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

}
