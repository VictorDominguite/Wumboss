package src.view.panels;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import src.utils.Constantes;
import src.view.GameView;
import src.view.atomics.InfoView;

public class InfoPanel extends Panel{
	private static final long serialVersionUID = 7502620923273176398L;

	public InfoPanel(GameView gv){
        super(gv);
        
        setPreferredSize(new Dimension(Constantes.WINDOW_SIZE_X*5/10, Constantes.WINDOW_SIZE_Y*8/10));
		
        Font f = this.masterView.getFont().deriveFont(Font.BOLD, 24f);
		
        JLabel title = new JLabel("O submundo de Wumboss", SwingConstants.CENTER);
        title.setFont(f);

        InfoView heroLifeInfo = new InfoView("Hero", "vida", f, this);
        heroLifeInfo.setInfoColor(Color.red);
        
        add(title);
        add(heroLifeInfo);
    }
}