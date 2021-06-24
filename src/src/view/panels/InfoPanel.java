package src.view.panels;
import java.awt.Dimension;

import javax.swing.JLabel;

import src.utils.Constantes;
import src.view.GameView;

public class InfoPanel extends Panel{
	private static final long serialVersionUID = 7502620923273176398L;

	public InfoPanel(GameView gv){
        super(gv);
        
        setPreferredSize(new Dimension(Constantes.WINDOW_SIZE_X*5/10, Constantes.WINDOW_SIZE_Y*8/10));

        add(new JLabel("InfoPanel"));
    }
}