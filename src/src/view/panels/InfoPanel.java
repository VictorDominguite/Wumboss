package src.view.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import src.utils.Constantes;
import src.utils.Priority;
import src.view.GameView;
import src.view.atomics.InfoView;

public class InfoPanel extends Panel{
	private static final long serialVersionUID = 7502620923273176398L;
	
	private static HashMap<Priority, Color> colorMaps = 
			new HashMap<Priority, Color>(10);
	static {
		colorMaps.put(Priority.LOW, Color.gray);
		colorMaps.put(Priority.MEDIUM, Color.blue);
		colorMaps.put(Priority.HIGH, Color.red);
		colorMaps.put(Priority.CRITICAL, Color.black);
	}
	
	private static HashMap<Priority, JLabel> labels = 
			new HashMap<Priority, JLabel>(10);
	static {
		for(Priority p : Priority.values()) {
			JLabel l = new JLabel();
			l.setForeground(colorMaps.get(p));
			labels.put(p, l);
		}
	}
	
	private static HashMap<Priority, String> pool = 
			new HashMap<Priority, String>(10);

	public InfoPanel(GameView gv){
        super(gv);
        
        setPreferredSize(new Dimension((int) (Constantes.WINDOW_SIZE_X*0.5), Constantes.WINDOW_SIZE_Y*8/10));
        
        Font f = this.masterView.getFont().deriveFont(Font.BOLD, 24f);
        
        JLabel title = new JLabel("O submundo de Wumboss", SwingConstants.CENTER);
        title.setFont(f);
        
        JPanel feed = new JPanel();
        Font fd = f.deriveFont(Font.ITALIC, 18f);
        for(JLabel l : labels.values())
        	l.setFont(fd);
        
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
        
        addToFeed("<html> Depois de um longo tempo de queda, <br>"
        		+ "voce finalmente chegou no fundo do buraco. <br>"
        		+ "Uma brisa muito gelada bate, e voce se treme. <br>"
        		+ "Voce esta sozinho e com frio no 'fundo do poco'. </html>", Priority.HIGH);
        
        for(JLabel l : labels.values())
        	feed.add(l);
        
        updateFeed(Priority.LOW);
    }
	
	public static void addToFeed(String feed) {
		addToFeed(feed, Priority.LOW);
	}
	
	public static void addToFeed(String feed, Priority p) {
		String text = pool.get(p) == null ? "" : pool.get(p).strip();
		pool.put(p, "<html>" + text + "<br> <br>" + feed + "</html>");
	}
	
	public static void updateFeed(Priority minimum) {
		for(Priority p : Priority.values()) {
			JLabel l = labels.get(p);
			if(p.lowerThan(minimum)) {
				l.setText("");
				continue;
			}
			
			l.setText(pool.get(p));
		}
		pool.clear();
	}
}