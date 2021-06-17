package src.view.panels;

import java.awt.GridLayout;

import src.view.GameView;
import src.view.atomics.CellView;

public class GamePanel extends Panel{
	private static final long serialVersionUID = 4246347561043763922L;

	public GamePanel(GameView gv){
        super(gv, new GridLayout(10, 10));
        
        setWeightX(7);
        setWeightY(7);
        
        for(int i = 0; i < 10; i++) {
        	for(int j = 0; j < 10; j++) {
        		add(new CellView(i, j, this));
        	}
        }
    }
}