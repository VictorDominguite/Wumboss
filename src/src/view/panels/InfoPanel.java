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
	
	private static JLabel textFeed = new JLabel("");
	private static boolean trancado = false;

	public InfoPanel(GameView gv){
        super(gv);
        
        setPreferredSize(new Dimension((int) (Constantes.WINDOW_SIZE_X*0.5), Constantes.WINDOW_SIZE_Y*8/10));
        
        Font f = this.masterView.getFont().deriveFont(Font.BOLD, 24f);
        
        JLabel title = new JLabel("O submundo de Wumboss", SwingConstants.CENTER);
        title.setFont(f);
        
        JPanel feed = new JPanel();
        textFeed.setFont(f.deriveFont(Font.ITALIC, 18f));
        
        JPanel trueInfo = new JPanel();
        BoxLayout trueInfoLayout = new BoxLayout(trueInfo, BoxLayout.Y_AXIS);
        trueInfo.setLayout(trueInfoLayout);
        trueInfo.setBorder(BorderFactory.createLineBorder(Color.black));
        trueInfo.setPreferredSize(new Dimension((int) (Constantes.WINDOW_SIZE_X*0.505), 200));
		
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
        
        trancado = false;
        setFeed("<html> Depois de um longo tempo de queda, <br>"
        		+ "voce finalmente chegou no fundo do buraco. <br>"
        		+ "Uma brisa muito gelada bate, e voce se treme. <br>"
        		+ "Voce esta sozinho e com frio no 'fundo do poco'. </html>");
        
        feed.add(textFeed);
    }
	
	public static void setFeed(String feed) {
		setFeed(feed, Color.black);
	}
	
	public static void setFeed(String feed, Color c) {
		if(c == Color.red || c == Color.blue) {
			trancado = true;
			textFeed.setForeground(c);
			
			String text = textFeed.getText().strip();
			text = "<html>" + text + "<br> <br>" + feed + "</html>";
			
			textFeed.setText(text);
		}
		
		if(textFeed != null && !trancado) {
			textFeed.setForeground(c);
			textFeed.setText(feed);
		}
	}
}