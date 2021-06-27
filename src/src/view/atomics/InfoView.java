package src.view.atomics;

import javax.swing.JLabel;
import javax.swing.JPanel;

import src.utils.observer.Observer;
import src.view.panels.InfoPanel;

public class InfoView extends JPanel implements Observer{
	private static final long serialVersionUID = 7142155254194870838L;
	
	private InfoPanel parentPanel;
	
	private String who;
	private String what;
	
	private int value;
	
	private JLabel infoItself;
	
	public InfoView(String who, String what, InfoPanel parent){
		this.parentPanel = parent;
		
		this.who = who;
		this.what = what;
		
		this.infoItself = new JLabel();
		
		add(new JLabel(who + "'s " + what + ": "));
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
		
		this.value = Integer.parseInt(info[0]);
		
		setDisplay();
	}
	
	private void setDisplay() {
		String tempString = "";
		
		if(what.equalsIgnoreCase("vida")) {
			for(int i = 0; i < value; i++) {
				tempString += "♥";
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

}
