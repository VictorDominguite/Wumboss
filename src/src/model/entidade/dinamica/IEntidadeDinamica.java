package src.model.entidade.dinamica;

import src.model.entidade.IEntidade;
import src.model.entidade.estatica.IEntidadeEstatica;

public interface IEntidadeDinamica extends IEntidade{
	public void processarEfeitos();
	public void interagirComEntidadeEstatica(IEntidadeEstatica e);
	
	public boolean isInimigo();
    public boolean estaEnvenenado();
    
    public void moverEmDirecaoA(int x, int y);
    
    public int getAttackDamage();
    public int getDefense();
    public void receberDano(int dano);
}
