package src.view.atomics;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

import src.utils.observer.Observer;
import src.view.panels.GamePanel;

public class CellView extends JButton implements Observer{
	private static final long serialVersionUID = -431171023250216935L;
	
	private GamePanel parentPanel;
	
	private int x, y;
	//temp
	private JLabel text;
	private String backgroundName;
	private String foregroundName;
	
	public CellView(int x, int y, GamePanel parent) {
		super();
		
		setBackground(Color.white);
		setMaximumSize(new Dimension(32, 32));
		setMinimumSize(new Dimension(32, 32));
		
		Border emptyBorder = BorderFactory.createEmptyBorder();
		setBorder(emptyBorder);
		
		this.x = x;
		this.y = y;
		
		this.parentPanel = parent;
		
		this.text = new JLabel();
		add(text);
		
		inscrever();
		onUpdate();
	}
	
	public void onUpdate() {
		onUpdate(false);
	}

	public void onUpdate(boolean reinscrever) {
		if(reinscrever) inscrever();
		String[] newData = getInfo();
		
		backgroundName = newData[0];
		foregroundName = newData[1];
		
		text.setText(!foregroundName.equals("null") ? foregroundName : backgroundName);
	}

	public String[] getInfo() {
		return parentPanel.getGameView().getGameModel().getCaveState(x, y);
	}
	
	private void inscrever() {
		parentPanel.getGameView().getGameModel().subToLocal(x, y, this);
	}

}
