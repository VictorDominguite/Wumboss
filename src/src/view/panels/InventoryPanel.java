package src.view.panels;
import java.awt.Dimension;
import java.awt.GridLayout;

import src.utils.Constantes;
import src.view.GameView;
import src.view.atomics.ItemView;

public class InventoryPanel extends Panel{
	private static final long serialVersionUID = 5112485242913536772L;
	
	private ItemView[] items;

	public InventoryPanel(GameView gv){
        super(gv, new GridLayout(2, 5));
        
        setPreferredSize(new Dimension(Constantes.WINDOW_SIZE_X, Constantes.WINDOW_SIZE_Y*2/10));
        
        this.items = new ItemView[10];
        String[] itemNames = getItemNames();

        for(int i = 0; i < itemNames.length; i++){
        	items[i] = new ItemView(itemNames[i], this);
            add(items[i]);
        }
    }
    
    private String[] getItemNames() {
    	return masterView.getGameModel().getPossibleItems();
    }
}