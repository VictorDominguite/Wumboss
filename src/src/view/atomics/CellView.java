package src.view.atomics;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.Border;

import src.utils.observer.Observer;
import src.view.ImageCache;
import src.view.panels.GamePanel;

public class CellView extends JButton implements Observer{
	private static final long serialVersionUID = -5086833428424017326L;
	private final Border emptyBorder = BorderFactory.createEmptyBorder();
	
	private class MouseListen implements MouseListener{
		private final Border over = BorderFactory.createLineBorder(Color.red);
		private final Border clicked = BorderFactory.createLineBorder(Color.orange);
		private CellView instance;
		
		public MouseListen(CellView instance) {
			this.instance = instance;
		}
		
		public void mouseReleased(MouseEvent e) {
			instance.setBorder(over);
		}
		
		public void mouseEntered(MouseEvent e) {
			instance.setBorder(over);
		}
		
		public void mouseExited(MouseEvent e) {
			instance.setBorder(emptyBorder);
		}
		
		public void mousePressed(MouseEvent e) {
			instance.setBorder(clicked);
		}
		
		public void mouseClicked(MouseEvent e) {}
	}
	
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
		
		setBorder(emptyBorder);
		
		this.x = x;
		this.y = y;
		
		this.parentPanel = parent;
		
		this.imgBackground = new JLabel();
		this.imgForeground = new JLabel();
		
		add(imgForeground);
		add(imgBackground);
		
		setActionCommand("attack " + x + ":" + y);
		setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		addMouseListener(new MouseListen(this));
		parent.getGameView().getController().setButtonMappings(this);
		
		inscrever();
	}
	
	public void onUpdate() {
		onUpdate(false);
	}

	public void onUpdate(boolean reinscrever) {
		if(reinscrever) 
			inscrever();
		
		String[] newData = getInfo();
		
		backgroundName = parentPanel.getAssetName(newData[0]);
		foregroundName = parentPanel.getAssetName(newData[1]);
		isVisible = newData[2].equals("true");
		
		imgForeground.setIcon(null);
		imgBackground.setIcon(null);
		
		if(isVisible) {
			if(backgroundName.equals("null") || backgroundName.equals("Passagem"))
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
		onUpdate();
	}

}
