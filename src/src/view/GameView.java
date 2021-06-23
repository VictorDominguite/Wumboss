package src.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import src.controller.IController;
import src.model.IGameModel;
import src.utils.exceptions.SemReferenciaAComponente;
import src.view.panels.GamePanel;
import src.view.panels.InfoPanel;
import src.view.panels.InventoryPanel;
import src.view.panels.Panel;

public class GameView extends JFrame implements IGameView{
	private static final long serialVersionUID = -6985235430976713413L;
	
	private IController control;
	private IGameModel model;
	
	private GamePanel gp;
    private InfoPanel infop;
    private InventoryPanel invp;

    public GameView() {
        super("O submundo de Wumboss");

        setSize(1200, 800);
        setResizable(true);
    }
    
    public void montarView() {
    	createPanels();
        configurePanels();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void createPanels() {
        gp = new GamePanel(this);
        infop = new InfoPanel(this);
        invp = new InventoryPanel(this);
    }

    private void configurePanels() {
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();
        
        constraints.gridx = GridBagConstraints.RELATIVE;
        constraints.gridy = 0;
        constraints.fill = GridBagConstraints.BOTH;
        
        Panel[] panels = {gp, infop, invp};

        for(Panel p : panels) {
        	constraints.weightx = p.getWeightX();
        	constraints.weighty = p.getWeightY();
        	
        	if(p instanceof InventoryPanel) {
        		constraints.gridx = 0;
        		constraints.gridy = GridBagConstraints.RELATIVE;
        		constraints.gridwidth = GridBagConstraints.REMAINDER;
        	}
        	
        	gbl.setConstraints(p, constraints);
        }

        setLayout(gbl);

        add(gp);
        add(infop);
        add(invp);
    }
    
    public IGameModel getGameModel() {
    	if(this.model == null)
    		throw new SemReferenciaAComponente("model", "view");
    	return this.model;
    }
    
    public IController getController() {
    	if(this.model == null)
    		throw new SemReferenciaAComponente("controller", "view");
    	return this.control;
    }

	public void setControl(IController c) {
		this.control = c;
	}

	public void setModel(IGameModel g) {
		this.model = g;
	}
}