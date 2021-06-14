package src.view.panels;

import java.awt.GridLayout;

import javax.swing.JLabel;

import src.view.GameView;

public class GamePanel extends Panel{
	private static final long serialVersionUID = 4246347561043763922L;

	public GamePanel(GameView gv){
        super(gv, new GridLayout(10, 10));
        
        setWeightX(7);
        setWeightY(7);

        add(new JLabel("gamePanel"));
    }
}