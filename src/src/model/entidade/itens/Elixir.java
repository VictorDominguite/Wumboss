package src.model.entidade.itens;

public class Elixir extends Item {
    private int rodadasAtivo;
    private int cooldown;
    private static final int BONUS_DANO = 4;
    private static final int CD_INICIAL = 10;
    private static final int DURACAO_EFEITO = 5;

    public Elixir(boolean coletado, boolean equipado) {
        super(coletado, equipado);
        
        setDescricao("Um elixir");
    }

    public int getBonusDano() {
        return BONUS_DANO;
    }

    public boolean emCooldown() {
        if (cooldown > 0)
            return true;
        else 
            return false;
    }

    public void resetCooldown() {
        cooldown = CD_INICIAL;
    }

    public void diminuirCooldown() {
        if (cooldown > 0)
            cooldown -= 1;
    }

    public boolean isAtivo() {
        if (rodadasAtivo > 0)
            return true;
        else
            return false;
    }

    public void resetRodadasAtivo() {
        rodadasAtivo = 0;
    }

    public void incrementarRodadasAtivo() {
        if (rodadasAtivo < DURACAO_EFEITO) {
            rodadasAtivo += 1;
        }
        else {
            resetRodadasAtivo();
        }
    }

    public void consumir() {
        if (!emCooldown())
            rodadasAtivo = 1;
    }
}
