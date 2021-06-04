package POO.jogo.model.entidade.itens;

public class Elixir extends Item {
    private int bonusDano;
    private int rodadasAtivo;
    private int cooldown;
    private static int CD_INICIAL = 10;

    public int getBonusDano() {
        return bonusDano;
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
        if (rodadasAtivo < 5) {
            rodadasAtivo += 1;
        }
        else {
            resetRodadasAtivo();
        }
    }
}
