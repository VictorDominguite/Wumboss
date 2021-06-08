package src.view;

import javax.swing.*;
import java.awt.*;
import src.view.panels.*;
import src.view.panels.Panel;

public class MainView extends JFrame {

    private GamePanel gp;
    private InfoPanel infop;
    private InventoryPanel invp;

    public MainView() {
        super("O submundo de Wumboss");

        setSize(1200, 800);
        setResizable(true);

        createPanels();
        configurePanels();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void createPanels() {
        gp = new GamePanel();
        infop = new InfoPanel();
        invp = new InventoryPanel();
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
}