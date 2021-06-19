package src.view.atomics;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import src.utils.events.EventListener;
import src.view.panels.InventoryPanel;

public class ItemView extends JPanel implements EventListener{
	private static final long serialVersionUID = -7610186360675545438L;
	
	private InventoryPanel parentPanel;
	
	private String name;
	private String description;
	private ImageIcon img;
	private boolean isCollected;
	private boolean isEquippable;
	private boolean isEquipped;
	
	public ItemView(String name, InventoryPanel parent) {
		this.name = name;
		this.parentPanel = parent;
		
		this.img = new ImageIcon();
		
		parentPanel.getGameView().getGameModel().subToItem(name, this);
		
		add(new JLabel(name));
		//add(new JLabel(description));
	}
	
	public void onUpdate() {
		String[] newInfo = getInfo();
		this.description = newInfo[0];
		this.isCollected = newInfo[1].equals("true") ? true : false;
		this.isEquipped =  newInfo[2].equals("true") ? true : false;
	}
	
	public String[] getInfo() {
		return parentPanel.getGameView().getGameModel().getItemState(name);
	}
}
