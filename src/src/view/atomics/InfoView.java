package src.view.atomics;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import src.utils.observer.Observer;
import src.view.panels.InfoPanel;

public class InfoView extends JPanel implements Observer{
	private static final long serialVersionUID = 7142155254194870838L;
	
	private InfoPanel parentPanel;
	
	private String who;
	private String what;
	
	private int maxLife;
	private int value = 0;
	
	private JLabel infoItself;
	
	public InfoView(String who, String what, Font f, InfoPanel parent){
		setLayout(new FlowLayout(FlowLayout.LEFT));
		this.parentPanel = parent;
		
		this.who = who;
		this.what = what;
		
		this.infoItself = new JLabel("");
		JLabel label = new JLabel(who + "'s " + what + ": ");
		
		label.setFont(f);
		infoItself.setFont(f);
		
		add(label);
		add(infoItself);
		
		inscrever();
	}

	public void onUpdate() {
		onUpdate(false);
	}

	public void onUpdate(boolean reinscrever) {
		String[] info = getInfo();
		if(info == null)
			return;
		
		this.maxLife = Math.max(value, maxLife);
		this.value = Integer.parseInt(info[0]);
		
		setDisplay();
	}
	
	private void setDisplay() {
		String tempString = "";
		
		if(what.equalsIgnoreCase("vida")) {
			int i = 0;
			for(; i < value; i++) {
				tempString += "♥";
			}
			for(; i < maxLife; i++) {
				tempString += "♡";
			}
			
		}
		
		if(what.equalsIgnoreCase("defense")) {
			
			for(int i = 0; i < value; i++) {
				tempString += "☗";
			}
		}
		
		if(what.equalsIgnoreCase("attack")) {
			for(int i = 0; i < value; i++) {
				tempString += "⚔";
			}
		}
		
		infoItself.setText("<html>" + tempString + "</html>");
	}

	public String[] getInfo() {
		if(this.who.equalsIgnoreCase("hero"))
			return parentPanel.getGameView().getGameModel().getHeroState(what);
		
		return null;
	}
	
	private void inscrever() {
		if(this.who.equalsIgnoreCase("hero")) {
			parentPanel.getGameView().getGameModel().subToHeroi(this.what, this);
		}
		else {
			return;
		}
		
		onUpdate();
	}
	
	public void setInfoColor(Color c) {
		infoItself.setForeground(c);
	}

}
