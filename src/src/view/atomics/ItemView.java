package src.view.atomics;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import src.utils.observer.Observer;
import src.view.ImageCache;
import src.view.panels.InventoryPanel;

public class ItemView extends JPanel implements Observer{
	private static final long serialVersionUID = -7610186360675545438L;
	
	private InventoryPanel parentPanel;
	
	private String name;
	private JLabel description;
	private JLabel img;
	private JButton equipButton;
	
	private boolean coletado;
	private boolean equipado;
	
	public ItemView(String name, InventoryPanel parent) {
		super(new BorderLayout());
		
		Border border = BorderFactory.createLineBorder(Color.black);
		setBorder(border);
		
		this.name = name;
		this.parentPanel = parent;
		this.img = new JLabel();
		
		updateIcon();
		
		this.description = new JLabel();
		this.equipButton = new JButton();
		
		inscrever();
		onUpdate();
		
		JLabel lName = new JLabel(name);
		lName.setHorizontalAlignment(SwingConstants.CENTER);
		
		add(lName, BorderLayout.NORTH);
		add(img, BorderLayout.EAST);
		add(description, BorderLayout.CENTER);
		add(equipButton, BorderLayout.SOUTH);
	}
	
	public void onUpdate() {
		onUpdate(false);
	}
	
	public void onUpdate(boolean reinscrever) {
		if(reinscrever) inscrever();
		
		String[] newInfo = getInfo();
		this.description.setText(newInfo[0]);
		this.coletado = newInfo[1].equals("true");
		this.equipado =  newInfo[2].equals("true");
		
		if(!this.coletado) {
			this.equipButton.setEnabled(false);
			this.equipButton.setText("Voce nao possui esse item!");
		}
		else if(this.equipado) {
			this.equipButton.setEnabled(false);
			this.equipButton.setText("Equipado!");
		}
		else {
			this.equipButton.setEnabled(true);
			this.equipButton.setText("Equipar");
		}
	}
	
	public void updateIcon() {
		img.setIcon(null);
		img.setIcon(ImageCache.getIcon(name, 42, 42));
	}
	
	public String[] getInfo() {
		return parentPanel.getGameView().getGameModel().getItemState(name);
	}
	
	private void inscrever() {
		parentPanel.getGameView().getGameModel().subToItem(name, this);
	}
}
