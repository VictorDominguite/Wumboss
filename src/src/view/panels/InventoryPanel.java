package src.view.panels;
import javax.swing.*;
import java.awt.GridLayout;

public class InventoryPanel extends Panel{
    public InventoryPanel(){
        super(new GridLayout(2, 5));
        
        setWeightX(10);
        setWeightY(3);

        for(int i = 0; i < 10; i++){
            add(new JLabel("a"));
        }
    }
}