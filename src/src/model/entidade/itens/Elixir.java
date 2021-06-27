package src.model.entidade.itens;

import src.view.IGameView;

public class Elixir extends Item {
    private int rodadasAtivo;
    private int cooldown;
    private static final int BONUS_DANO = 4;
    private static final int CD_INICIAL = 10;
    private static final int DURACAO_EFEITO = 5;

    public Elixir() {
        super(false, false);
        
        setDescricao("<html> Um elixir de dano <br> <em> +" + BONUS_DANO + " atk por " + DURACAO_EFEITO + " rodadas </em> </html>");
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
        cooldown = CD_INICIAL;
        IGameView.setFeedMessage("O efeito do elixir acabou!");
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
        if (!emCooldown()) {
            rodadasAtivo = 1;
            IGameView.setFeedMessage("Voce consumiu o elixir!");
        }

    }
}
