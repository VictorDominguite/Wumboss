package src.view.panels;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import src.utils.Constantes;
import src.view.GameView;
import src.view.atomics.InfoView;

public class InfoPanel extends Panel{
	private static final long serialVersionUID = 7502620923273176398L;
	
	private static JLabel textFeed;

	public InfoPanel(GameView gv){
        super(gv);
        
        //BoxLayout infoPanelLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        
        //setLayout(infoPanelLayout);
        setPreferredSize(new Dimension(Constantes.WINDOW_SIZE_X*5/10, Constantes.WINDOW_SIZE_Y*8/10));
        
        JPanel trueInfo = new JPanel();
        BoxLayout trueInfoLayout = new BoxLayout(trueInfo, BoxLayout.Y_AXIS);
        trueInfo.setLayout(trueInfoLayout);
        trueInfo.setBorder(BorderFactory.createLineBorder(Color.black));
		
        Font f = this.masterView.getFont().deriveFont(Font.BOLD, 24f);
        
        JPanel feed = new JPanel();
        textFeed = new JLabel("howdy");
        textFeed.setFont(f.deriveFont(Font.ITALIC, 18f));
        feed.add(textFeed);
		
        JLabel title = new JLabel("O submundo de Wumboss", SwingConstants.CENTER);
        title.setFont(f);

        InfoView heroLifeInfo = new InfoView("Hero", "vida", f, this);
        heroLifeInfo.setInfoColor(Color.red);
        
        InfoView heroDefenseInfo = new InfoView("Hero", "defense", f, this);
        heroDefenseInfo.setInfoColor(Color.blue);
        
        InfoView heroAttackInfo = new InfoView("Hero", "attack", f, this);
        heroAttackInfo.setInfoColor(new Color(220, 100, 100));
        
        add(title);
        trueInfo.add(heroLifeInfo);
        trueInfo.add(heroDefenseInfo);
        trueInfo.add(heroAttackInfo);
        add(trueInfo);
        add(feed);
        
        setFeed("<html> Depois de um longo tempo de queda, <br>"
        		+ "voce finalmente chegando fundo do buraco. <br>"
        		+ "Uma brisa muito gelada bate, e voce se treme. <br>"
        		+ "Voce esta sozinho e com frio no 'fundo do poco'. </html>");
    }
	
	public static void setFeed(String feed) {
		if(textFeed != null)
			textFeed.setText(feed);
	}
}