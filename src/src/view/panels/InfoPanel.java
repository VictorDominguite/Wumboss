package src.view.panels;
import java.awt.Dimension;

import src.utils.Constantes;
import src.view.GameView;
import src.view.atomics.InfoView;

public class InfoPanel extends Panel{
	private static final long serialVersionUID = 7502620923273176398L;

	public InfoPanel(GameView gv){
        super(gv);
        
        setPreferredSize(new Dimension(Constantes.WINDOW_SIZE_X*5/10, Constantes.WINDOW_SIZE_Y*8/10));

        add(new InfoView("hero", "vida", this));
    }
}