package src.model.entidade.dinamica;

import src.model.entidade.estatica.IEntidadeEstatica;
import src.model.entidade.estatica.PocoVenenoso;
import src.utils.Direcao;
import src.utils.exceptions.ErroDeInteracao;

public abstract class EntidadeViva extends EntidadeDinamica implements IEntidadeViva{
    protected int vida, ataque, defesa;
    protected int alcance = 1, envenenado = 0;
    protected int cooldownMovimento = 1;

    public EntidadeViva(int vida, int ataque, int defesa) {
        this.vida = vida;
        this.ataque = ataque;
        this.defesa = defesa;
    }
    
    public int getCooldownMovimento() {
    	return cooldownMovimento;
    }
    
    public String interagir(IEntidadeDinamica e) {
        try {
            String interacao = objInteracao.interagir(this, e);
            return interacao;
        } catch (ErroDeInteracao erro) {
            //TODO: melhorar excecao
            System.err.println(erro.getMessage());
            return null;
        }
    }
    
    public void processarEfeitos() {
    	
        if (this instanceof Heroi) {
            ((Heroi) this).processarEfeitos();
            return;
        }
        
        if(estaEnvenenado()) {
    		receberDano(PocoVenenoso.getDano());
    		envenenado -= 1;
    	}
    }
    
    public void interagirComEntidadeEstatica(IEntidadeEstatica e) {
    	if (e == null) return;
        if(e.efeito().equals("veneno"))
    		this.envenenar();
    }
    
    public boolean estaEnvenenado() {
    	return envenenado > 0;
    }
    
    public void moverEmDirecaoA(int x, int y) {
    	int deltaX = Math.abs(x - posX);
    	int deltaY = Math.abs(y - posY);
    	//Primeiro tenta se mover para a direção de maior delta, depois para a de menor, caso ambos falharem,
        // tenta mover para qualquer outro lado, mesmo que se afaste de (x, y) nesse caso
    	Direcao d;
    	
    	if (deltaX > deltaY) 
    		d = (x > posX) ? Direcao.LESTE : Direcao.OESTE;
    	else 
    		d = (y > posY) ? Direcao.SUL : Direcao.NORTE;
    	
    	while(!mover(d))
    		d = Direcao.proxima(d);
    }

    public int getAttackDamage() {
        return ataque;
    }

    public int getDefense() {
        return defesa;
    }
    
    public int getVida() {
    	return vida;
    }
    
    public void receberDano(int dano) {
    	int vidaRestante = this.vida - dano;
    	if (vidaRestante <= 0) {
    		this.vida = 0;
    		this.morrer();
    	}
    	else {
    		this.vida = vidaRestante;
    	}
    }

    public int getAlcance() {
        return alcance;
    }

    protected boolean estaVivo() {
        return vida > 0;
    }

    protected void morrer() {
        this.space.removerEntidade(posX, posY);
    }

    protected boolean mover(Direcao dir) {
        return this.space.moverEntidade(posX, posY, dir);
    }

    protected void envenenar() {
        envenenado = PocoVenenoso.getDuracaoEfeito();
    }

    protected void receberDanoVeneno() {
        receberDano(PocoVenenoso.getDano());
        envenenado -= 1;
    }

}