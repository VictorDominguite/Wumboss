package src.view.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

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
	
	private static HashMap<Priority, JTextArea> labels = 
			new HashMap<Priority, JTextArea>(10);
	static {
		for(Priority p : Priority.values()) {
			JTextArea l = new JTextArea(4, 50);
			l.setForeground(colorMaps.get(p));
			l.setBackground(UIManager.getColor("Label.background"));
			l.setEditable(false);
			l.setFocusable(false);
			l.setWrapStyleWord(true);
			l.setLineWrap(true);
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
        
        JPanel feed = new JPanel();
        BoxLayout trueFeedLayout = new BoxLayout(feed, BoxLayout.Y_AXIS);
        feed.setLayout(trueFeedLayout);
        Font fd = f.deriveFont(Font.ITALIC, 18f);
        for(JTextArea l : labels.values())
        	l.setFont(fd);
        
        add(title);
        
        trueInfo.add(heroLifeInfo);
        trueInfo.add(heroDefenseInfo);
        trueInfo.add(heroAttackInfo);
        add(trueInfo);
        
        for(JTextArea l : labels.values())
        	feed.add(l);
        add(feed);
        
        addToFeed("Depois de um longo tempo de queda,"
        		+ "voce finalmente chegou no fundo do buraco."
        		+ "Uma brisa muito gelada bate, e voce se treme."
        		+ "Voce esta sozinho e com frio no 'fundo do poco'.", Priority.MEDIUM);
        
        updateFeed(Priority.LOW);
    }
	
	public static void addToFeed(String feed) {
		addToFeed(feed, Priority.LOW);
	}
	
	public static void addToFeed(String feed, Priority p) {
		String text = pool.get(p) == null ? "" : pool.get(p).strip();
		pool.put(p, text + "\n" + feed);
	}
	
	public static void updateFeed(Priority minimum) {
		for(Priority p : Priority.values()) {
			JTextArea l = labels.get(p);
			if(p.lowerThan(minimum)) {
				l.setText("");
				continue;
			}
			
			System.out.println(pool.get(p));
			
			l.setText(pool.get(p));
		}
		pool.clear();
	}
}