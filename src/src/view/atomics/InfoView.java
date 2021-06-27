package src.view.atomics;

import java.awt.Color;
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
	
	private int prevValue;
	private int value = 999;
	
	private JLabel infoItself;
	
	public InfoView(String who, String what, Font f, InfoPanel parent){
		this.parentPanel = parent;
		
		this.who = who;
		this.what = what;
		
		this.infoItself = new JLabel();
		
		JLabel l = new JLabel(who + "'s " + what + ": ");
		l.setFont(f);
		infoItself.setFont(f);
		
		add(l);
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
		this.prevValue = value;
		this.value = Integer.parseInt(info[0]);
		
		setDisplay();
	}
	
	private void setDisplay() {
		String tempString = "";
		
		if(what.equalsIgnoreCase("vida")) {
			if(value > prevValue)
				InfoPanel.setFeed("Voce se sente mais vivo...");
			else if(value < prevValue)
				InfoPanel.setFeed("Voce se sente menos vivo...");
			
			for(int i = 0; i < value; i++) {
				tempString += "♥";
			}
		}
		
		if(what.equalsIgnoreCase("defense")) {
			if(value > prevValue)
				InfoPanel.setFeed("Voce se sente mais resiliente...");
			else if(value < prevValue)
				InfoPanel.setFeed("Voce se sente menos resiliente...");
			
			for(int i = 0; i < value; i++) {
				tempString += "☗";
			}
		}
		
		if(what.equalsIgnoreCase("attack")) {
			if(value > prevValue)
				InfoPanel.setFeed("Voce se sente mais forte...");
			else if(value < prevValue)
				InfoPanel.setFeed("Voce se sente mais fraco...");
			
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
