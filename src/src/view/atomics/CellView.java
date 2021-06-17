package src.view.atomics;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;

import src.utils.events.EventListener;
import src.view.panels.GamePanel;

public class CellView extends JButton implements EventListener{
	private static final long serialVersionUID = -431171023250216935L;
	
	private GamePanel parentPanel;
	
	private int x, y;
	private String backgroundName;
	private String foregroundName;
	
	public CellView(int x, int y, GamePanel parent) {
		super();
		
		setBackground(Color.white);
		setMaximumSize(new Dimension(32, 32));
		setMinimumSize(new Dimension(32, 32));
		
		this.x = x;
		this.y = y;
		
		this.parentPanel = parent;
		
		parentPanel.getGameView().getGameModel().subToLocal(x, y, this);
		
		onUpdate();
	}

	public void onUpdate() {
		String[] newData = getInfo();
		
		backgroundName = newData[0];
		foregroundName = newData[1];
		
		add(new JLabel(!foregroundName.equals("null") ? foregroundName : backgroundName));
	}

	public String[] getInfo() {
		return parentPanel.getGameView().getGameModel().getCaveState(x, y);
	}

}
