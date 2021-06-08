package src.view.panels;
import javax.swing.*;

import src.model.entidade.itens.Item;

import java.awt.GridLayout;

public class InventoryPanel extends Panel{
	private static final long serialVersionUID = 5112485242913536772L;

	public InventoryPanel(){
        super(new GridLayout(2, 5));
        
        setWeightX(10);
        setWeightY(3);

        for(int i = 0; i < 10; i++){
            add(new JLabel("" + i, JLabel.CENTER));
        }
    }
    
    private Item[] getItems() {
    	//requer pela interface view-model os itens do inventario
    	return null;
    }
}