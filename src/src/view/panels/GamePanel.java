package src.view.panels;

import javax.swing.JLabel;

public class GamePanel extends Panel{
    public GamePanel(){
        super();
        
        setWeightX(7);
        setWeightY(7);

        add(new JLabel("gamePanel"));
    }
}