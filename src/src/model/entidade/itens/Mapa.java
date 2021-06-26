package src.model.entidade.itens;

public class Mapa extends Item {
    public Mapa(boolean coletado, boolean equipado) {
        super(coletado, equipado);
        
        setDescricao("Um mapa");
    }

    @Override
    public void coletar() {
        super.coletar();
        equipar();
    }
}
