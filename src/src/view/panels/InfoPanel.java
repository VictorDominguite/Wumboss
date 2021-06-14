package src.view.panels;
import javax.swing.JLabel;

import src.view.GameView;

public class InfoPanel extends Panel{
	private static final long serialVersionUID = 7502620923273176398L;

	public InfoPanel(GameView gv){
        super(gv);
        
        setWeightX(3);
        setWeightY(7);

        add(new JLabel("InfoPanel"));
    }
}