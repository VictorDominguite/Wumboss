package src.view.panels;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import src.utils.Constantes;
import src.view.GameView;
import src.view.atomics.ItemView;

public class InventoryPanel extends Panel{
	private static final long serialVersionUID = 5112485242913536772L;
	
	private ItemView[] items;
	
	private String[] collectableItems;
	private String[] cumulativeItems;

	public InventoryPanel(GameView gv){
        super(gv, new GridLayout(2, 5));
        
        setPreferredSize(new Dimension(Constantes.WINDOW_SIZE_X, Constantes.WINDOW_SIZE_Y*2/10));
        Font f = this.masterView.getFont().deriveFont(Font.PLAIN, 12f);
        
        this.items = new ItemView[10];
        getItemNames();
        
        int i = 0;
        int namesIndex = 0;
        
        while(i < 10) {
        	namesIndex = i % collectableItems.length;
        	items[i] = i < collectableItems.length
        				? new ItemView(collectableItems[namesIndex], this, true, f)
        				: new ItemView(cumulativeItems[namesIndex], this, false, f);
        	
        	add(items[i]);
        	
        	i += 1;
        }
    }
    
    private void getItemNames() {
    	collectableItems = masterView.getGameModel().getPossibleCollectableItems();
    	cumulativeItems = masterView.getGameModel().getPossibleCumulativeItems();
    }
}