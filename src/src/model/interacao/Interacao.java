package src.model.interacao;

import src.model.entidade.*;
import src.model.entidade.dinamica.EntidadeDinamica;

public class Interacao implements IInteracao {

    public void interagir(Entidade e1, Entidade e2) {

    }
    public void atacar(EntidadeDinamica agressor, EntidadeDinamica atacado) {
        int danoCausado;
        if (agressor.getAtaque() > atacado.getDefesa()) 
            danoCausado = agressor.getAtaque() - atacado.getDefesa();
        else
            danoCausado = 0;

        atacado.receberDano(danoCausado);
    }
}