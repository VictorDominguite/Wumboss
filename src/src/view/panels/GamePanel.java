package src.view.panels;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JComponent;

import src.utils.Constantes;
import src.view.GameView;
import src.view.atomics.CellView;

public class GamePanel extends Panel{
	private static final long serialVersionUID = 4246347561043763922L;

	public GamePanel(GameView gv){
        super(gv, new GridLayout(Constantes.TAM_SALAS, Constantes.TAM_SALAS));
        
        setPreferredSize(new Dimension(Constantes.WINDOW_SIZE_X*5/10, Constantes.WINDOW_SIZE_Y*8/10));
        
        gv.getController().setKeyboardMappings(getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW), getActionMap());
        
        for(int i = 0; i < Constantes.TAM_SALAS; i++) {
        	for(int j = 0; j < Constantes.TAM_SALAS; j++) {
        		add(new CellView(j, i, this));
        	}
        }
    }
}