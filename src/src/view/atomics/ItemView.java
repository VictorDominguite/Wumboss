package src.view.atomics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import src.utils.observer.Observer;
import src.view.panels.InventoryPanel;

public class ItemView extends JPanel implements Observer{
	private static final long serialVersionUID = -7610186360675545438L;
	
	private InventoryPanel parentPanel;
	
	private String name;
	private JLabel description;
	private JLabel img;
	private boolean isCollected;
	private boolean isEquippable;
	private boolean isEquipped;
	
	public ItemView(String name, InventoryPanel parent) {
		super(new BorderLayout());
		
		Border border = BorderFactory.createLineBorder(Color.black);
		setBorder(border);
		
		this.name = name;
		this.parentPanel = parent;
		this.img = new JLabel();
		
		try {
			BufferedImage buffImg = parentPanel.getGameView().getController().readIcon(name);
			ImageIcon icon = new ImageIcon(new ImageIcon(buffImg).getImage().getScaledInstance(64, 64, Image.SCALE_DEFAULT));
			img.setIcon(icon);
		} catch(IOException e) {
			
		}
		
		this.description = new JLabel();
		
		inscrever();
		onUpdate();
		
		add(new JLabel(name), BorderLayout.NORTH);
		add(img, BorderLayout.EAST);
		add(description, BorderLayout.CENTER);
	}
	
	public void onUpdate() {
		onUpdate(false);
	}
	
	public void onUpdate(boolean reinscrever) {
		if(reinscrever) inscrever();
		
		String[] newInfo = getInfo();
		this.description.setText(newInfo[0]);
		this.isCollected = newInfo[1].equals("true") ? true : false;
		this.isEquipped =  newInfo[2].equals("true") ? true : false;
	}
	
	public String[] getInfo() {
		return parentPanel.getGameView().getGameModel().getItemState(name);
	}
	
	private void inscrever() {
		parentPanel.getGameView().getGameModel().subToItem(name, this);
	}
}
