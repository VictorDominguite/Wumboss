package src.view.atomics;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;

import src.utils.observer.Observer;
import src.view.ImageCache;
import src.view.panels.GamePanel;

public class CellView extends JButton implements Observer{
	private static final long serialVersionUID = -5086833428424017326L;

	private GamePanel parentPanel;
	
	private int x, y;
	
	private JLabel imgBackground;
	private JLabel imgForeground;
	
	private String backgroundName;
	private String foregroundName;
	
	private boolean isVisible = false;
	
	public CellView(int x, int y, GamePanel parent) {
		super();
		
		setBackground(Color.black);
		setPreferredSize(new Dimension(64, 64));
		
		setBorder(BorderFactory.createEmptyBorder());
		
		this.x = x;
		this.y = y;
		
		this.parentPanel = parent;
		
		this.imgBackground = new JLabel();
		this.imgForeground = new JLabel();
		
		add(imgForeground);
		add(imgBackground);
		
		inscrever();
		onUpdate();
	}
	
	public void onUpdate() {
		onUpdate(false);
	}

	public void onUpdate(boolean reinscrever) {
		if(reinscrever) 
			inscrever();
		
		String[] newData = getInfo();
		
		backgroundName = newData[0];
		foregroundName = newData[1];
		isVisible = newData[2].equals("true");
		
		imgForeground.setIcon(null);
		imgBackground.setIcon(null);
		
		if(isVisible) {
			if(backgroundName.equals("null"))
				backgroundName = "Piso";
			
			imgBackground.setIcon(ImageCache.getIcon(backgroundName, 64, 64));
			if(!foregroundName.equals("null"))
				imgForeground.setIcon(ImageCache.getIcon(foregroundName, 64, 64));
		}
	}

	public String[] getInfo() {
		return parentPanel.getGameView().getGameModel().getCaveState(x, y);
	}
	
	private void inscrever() {
		parentPanel.getGameView().getGameModel().subToLocal(x, y, this);
	}

}
