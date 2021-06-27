package src.model.entidade.itens;

public class Flecha extends Item {
    
    int numFlechas = 0;
    
    public Flecha() {
        super(false, true);
        
        setDescricao("<html> Infelizmente ainda nao se atira sozinha... </html>");
    }

    public int getNumFlechas() {
        return numFlechas;
    }

    public void usarFlecha() {
        if (numFlechas > 0) numFlechas -= 1;

        onUpdate();
    }

    public void addFlechas(int numFlechas) {
        this.numFlechas += numFlechas;
        
        onUpdate();
    }
}
