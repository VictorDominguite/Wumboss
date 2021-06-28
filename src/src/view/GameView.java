package src.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;

import javax.swing.JFrame;

import src.controller.IController;
import src.model.IGameModel;
import src.utils.Constantes;
import src.utils.exceptions.SemReferenciaAComponente;
import src.view.panels.GamePanel;
import src.view.panels.InfoPanel;
import src.view.panels.InventoryPanel;

public class GameView extends JFrame implements IGameView{
	private static final long serialVersionUID = -6985235430976713413L;
	
	private IController control;
	private IGameModel model;
	
	private GamePanel gp;
    private InfoPanel infop;
    private InventoryPanel invp;

    public GameView() {
        super("O submundo de Wumboss");
        
        setSize(Constantes.WINDOW_SIZE_X, Constantes.WINDOW_SIZE_Y);
        setLocation(200, 100);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void montarView() {
    	createPanels();
        configurePanels();
        
        ImageCache.setGameView(this);
    }
    
    public void showView() {
    	setVisible(true);
    }

    private void createPanels() {
        gp = new GamePanel(this);
        infop = new InfoPanel(this);
        invp = new InventoryPanel(this);
    }

    private void configurePanels() {
        setLayout(new BorderLayout());

        add(gp, BorderLayout.CENTER);
        add(infop, BorderLayout.EAST);
        add(invp, BorderLayout.SOUTH);
    }
    
    private void configureFont() {
    	try {
			setFont(Font.createFont(Font.TRUETYPE_FONT, getController().hackFontFile("Regular")));
		} catch (FontFormatException e) {
			System.err.println("There's a problem with the format of the font: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("There was a problem opening the font file: " + e.getMessage());
		}
    }
    
    public void rebuild() {
    	remove(gp);
    	remove(infop);
    	remove(invp);
    	
    	montarView();
    	showView();
    }
    
    public IGameModel getGameModel() {
    	if(this.model == null)
    		throw new SemReferenciaAComponente("model", "view");
    	return this.model;
    }
    
    public IController getController() {
    	if(this.control == null)
    		throw new SemReferenciaAComponente("controller", "view");
    	return this.control;
    }

	public void setControl(IController c) {
		this.control = c;
		ImageCache.setIOHandler(c);
		configureFont();
	}

	public void setModel(IGameModel g) {
		this.model = g;
	}
}