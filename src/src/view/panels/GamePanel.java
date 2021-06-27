package src.view.panels;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.HashMap;

import javax.swing.JComponent;

import src.utils.Constantes;
import src.view.GameView;
import src.view.atomics.CellView;

public class GamePanel extends Panel{
	private static final long serialVersionUID = 4246347561043763922L;
	
	private static HashMap<String, String> assetsNames =
			new HashMap<String, String>();
	
	static {
		assetsNames.put("orc", "Orc");
		assetsNames.put("goblin", "Goblin");
		assetsNames.put("serpente", "Serpente");
		assetsNames.put("gato", "Gato");
	}

	public GamePanel(GameView gv){
        super(gv, new GridLayout(Constantes.TAM_SALAS, Constantes.TAM_SALAS));
        
        setPreferredSize(new Dimension(Constantes.WINDOW_SIZE_X*5/10, Constantes.WINDOW_SIZE_Y*8/10));
        
        gv.getController().setKeyboardMappings(getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW), getActionMap());
        
        for(int i = 0; i < Constantes.TAM_SALAS; i++) {
        	for(int j = 0; j < Constantes.TAM_SALAS; j++) {
        		add(new CellView(j, i, this));
        	}
        }
    }
	
	public String getAssetName(String modelName) {
		String res = assetsNames.get(modelName.toLowerCase());
		if(res == null) 
			return modelName;
		return res;
	}
}