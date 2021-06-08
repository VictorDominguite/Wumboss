package src.view.panels;
import javax.swing.*;
import java.awt.GridLayout;

public class InventoryPanel extends JPanel{
    public InventoryPanel(){
        super(new GridLayout(2, 5));

        for(int i = 0; i < 10; i++){
            add(new JLabel("a"));
        }
    }
}