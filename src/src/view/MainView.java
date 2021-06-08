package src.view;

import javax.swing.*;
import java.awt.*;
import src.view.panels.*;

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

        constraints.fill = GridBagConstraints.BOTH;

        constraints.weighty = 5;
        constraints.weightx = 5;
        constraints.gridx = 0;
        constraints.gridy = 0;
        gbl.setConstraints(gp, constraints);

        constraints.gridx = 5;
        constraints.gridy = 0;
        constraints.weightx = 3;
        gbl.setConstraints(infop, constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 8;
        constraints.weighty = 3;
        constraints.weightx = 5;
        gbl.setConstraints(invp, constraints);

        setLayout(gbl);

        add(gp);
        add(infop);
        add(invp);
    }
}