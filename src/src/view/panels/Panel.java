package src.view.panels;

import java.awt.LayoutManager;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import src.view.GameView;

public abstract class Panel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	protected GameView masterView;
	
	public Panel(GameView gv) {
		super();
		this.masterView = gv;
		setBorder(BorderFactory.createEmptyBorder());
	}
	
	public Panel(GameView gv, LayoutManager lm) {
		super(lm);
		this.masterView = gv;
		setBorder(BorderFactory.createEmptyBorder());
	}
	
	public GameView getGameView() {
		return masterView;
	}

}
