package src.model.entidade.dinamica;

import src.model.entidade.estatica.IEntidadeEstatica;

public interface IEntidadeViva extends IEntidadeDinamica {
	public void processarEfeitos();
	public void interagirComEntidadeEstatica(IEntidadeEstatica e);
	
	public boolean isInimigo();
    public boolean estaEnvenenado();
    
    public void moverEmDirecaoA(int x, int y);
    
    public int getAttackDamage();
    public int getDefense();
    public int getVida();
    public void receberDano(int dano);
}
