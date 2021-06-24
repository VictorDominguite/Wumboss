package src.view.atomics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.Border;

import src.utils.observer.Observer;
import src.view.panels.GamePanel;

public class CellView extends JButton implements Observer{
	private static final long serialVersionUID = -5086833428424017326L;

	private GamePanel parentPanel;
	
	private int x, y;
	//temp
	private JLabel img;
	private String backgroundName;
	private String foregroundName;
	
	public CellView(int x, int y, GamePanel parent) {
		super();
		
		setBackground(Color.white);
		setPreferredSize(new Dimension(64, 64));
		
		Border emptyBorder = BorderFactory.createEmptyBorder();
		setBorder(emptyBorder);
		
		this.x = x;
		this.y = y;
		
		this.parentPanel = parent;
		
		this.img = new JLabel();
		add(img);
		
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
		
		System.out.println(foregroundName);
		System.out.println(backgroundName);
		
		img.setIcon(null);
		
		try {
			BufferedImage buffImg = parentPanel.getGameView().getController().readIcon(!foregroundName.equals("null") ? foregroundName : backgroundName);
			ImageIcon icon = new ImageIcon(new ImageIcon(buffImg).getImage().getScaledInstance(64, 64, Image.SCALE_DEFAULT));
			img.setIcon(icon);
		} catch(IOException e) {
			
		}
		
	}

	public String[] getInfo() {
		return parentPanel.getGameView().getGameModel().getCaveState(x, y);
	}
	
	private void inscrever() {
		parentPanel.getGameView().getGameModel().subToLocal(x, y, this);
	}

}
