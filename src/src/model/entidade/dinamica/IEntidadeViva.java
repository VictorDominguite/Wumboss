package src.model.entidade.dinamica;

import src.model.entidade.estatica.IEntidadeEstatica;

public interface IEntidadeViva extends IEntidadeDinamica {
	public int getCooldownMovimento();
	
	public void processarEfeitos();
	public String interagir(IEntidadeDinamica e);
	public void interagirComEntidadeEstatica(IEntidadeEstatica e);
	
    public boolean estaEnvenenado();
    
    public void moverEmDirecaoA(int x, int y);
    public boolean estaVivo();
    
    public int getAttackDamage();
    public int getDefense();
    public int getVida();
    public int getAlcance();
    public void receberDano(int dano);
}
