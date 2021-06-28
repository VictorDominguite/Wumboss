package src.view.atomics;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import src.utils.observer.Observer;
import src.view.ImageCache;
import src.view.panels.InventoryPanel;

public class ItemView extends JPanel implements Observer{
	private static final long serialVersionUID = -7610186360675545438L;
	
	private InventoryPanel parentPanel;
	
	private CardLayout layout;
	
	private JPanel content;
	private JPanel darkMask;
	
	private String name;
	private JLabel description;
	private JLabel img;
	private JButton equipButton;
	
	private boolean coletado;
	private boolean equipado;
	private boolean coletavel;
	
	public ItemView(String name, InventoryPanel parent, Font f) {
		this(name, parent, true, f);
	}
	
	public ItemView(String name, InventoryPanel parent, boolean coletavel, Font f) {
		super();
		
		this.layout = new CardLayout();
		setLayout(layout);
		
		this.darkMask = new JPanel();
		this.darkMask.setBackground(new Color(0, 0, 0, 120));
		this.darkMask.setPreferredSize(new Dimension(64, 64));
		this.darkMask.setOpaque(true);
		this.darkMask.setBorder(BorderFactory.createLineBorder(Color.black));
		
		this.content = new JPanel(new BorderLayout(1, 1));
		this.content.setBorder(BorderFactory.createLineBorder(Color.black));
		
		this.name = name;
		this.parentPanel = parent;
		this.img = new JLabel();
		
		this.coletavel = coletavel;
		if(coletavel)
			this.coletado = true;
		
		updateIcon();
		
		this.description = new JLabel();
		description.setFont(f);
		this.equipButton = new JButton();
		equipButton.setFont(f);
		
		equipButton.setActionCommand("inventory " + name);
		parent.getGameView().getController().setButtonMappings(equipButton);
		
		JLabel lName = new JLabel(name);
		lName.setHorizontalAlignment(SwingConstants.CENTER);
		
		this.content.add(lName, BorderLayout.NORTH);
		this.content.add(img, BorderLayout.EAST);
		this.content.add(description, BorderLayout.CENTER);
		this.content.add(equipButton, BorderLayout.SOUTH);
		
		add(content, "content");
		add(darkMask, "mask");
		
		inscrever();
	}
	
	public boolean isColetado() {
		return this.coletado;
	}
	
	public void onUpdate() {
		onUpdate(false);
	}
	
	public void onUpdate(boolean reinscrever) {
		if(reinscrever) { 
			inscrever();
			return;
		}
		
		String[] newInfo = getInfo();
		this.description.setText(newInfo[0]);
		
		if(this.coletavel)
			updateColetavel(newInfo);
		else
			updateCumulativo(newInfo);
	}
	
	private void updateColetavel(String[] args) {
		this.coletado = args[1].equals("true");
		this.equipado =  args[2].equals("true");
		
		if(!this.coletado) {
			this.layout.show(this, "mask");
			this.equipButton.setEnabled(false);
			this.equipButton.setText("Voce nao possui esse item!");
		}
		else if(this.equipado) {
			this.layout.show(this, "content");
			this.equipButton.setEnabled(false);
			this.equipButton.setText("Equipado!");
		}
		else {
			this.layout.show(this, "content");
			this.equipButton.setEnabled(true);
			this.equipButton.setText("Equipar");
		}
	}
	
	private void updateCumulativo(String[] args) {
		this.layout.show(this, "content");
		this.equipButton.setEnabled(false);
		this.equipButton.setText("Voce possui " + args[1] + " " + this.name.toLowerCase() + "s!");
	}
	
	private void updateIcon() {
		img.setIcon(null);
		img.setIcon(ImageCache.getIcon(name, 42, 42));
	}
	
	public String[] getInfo() {
		return parentPanel.getGameView().getGameModel().getItemState(name);
	}
	
	private void inscrever() {
		parentPanel.getGameView().getGameModel().subToItem(name, this);
		onUpdate();
	}
}
