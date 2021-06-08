package src.view.panels;
import javax.swing.*;

public class InfoPanel extends Panel{
    public InfoPanel(){
        super();
        
        setWeightX(3);
        setWeightY(7);

        add(new JLabel("InfoPanel"));
    }
}