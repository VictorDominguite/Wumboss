package src.view.panels;

import java.awt.GridLayout;

import javax.swing.JComponent;

import src.view.GameView;
import src.view.atomics.CellView;

public class GamePanel extends Panel{
	private static final long serialVersionUID = 4246347561043763922L;

	public GamePanel(GameView gv){
        super(gv, new GridLayout(10, 10));
        
        setWeightX(7);
        setWeightY(7);
        
        gv.getController().setKeyboardMappings(getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW), getActionMap());
        
        for(int i = 0; i < 10; i++) {
        	for(int j = 0; j < 10; j++) {
        		add(new CellView(j, i, this));
        	}
        }
    }
}