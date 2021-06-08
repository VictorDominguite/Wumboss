package POO.jogo.model;

public class Caverna {
    private static Caverna instance;
    private static final int NUM_SALAS = 8;

    private Sala[] salas;
    /*
     * A posicao [i][j] indica se ha passagem entre as salas i e j e onde ela se
     * encontra
     */
    private Passagem[][] conexoes;

    private Caverna() {
        salas = new Sala[NUM_SALAS];
        conexoes = new Passagem[NUM_SALAS][NUM_SALAS];
    }

    public Sala getSala(int id) {
        if (id < 0 || id >= NUM_SALAS) {
            // erro
            return null;
        }

        Sala s = salas[id];
        if (s.getID() != id) {
            // erro
            return null;
        }

        return s;
    }

    public Passagem obterPassagemEntre(Sala s1, Sala s2) {
        return conexoes[s1.getID()][s2.getID()];
    }

    public static Caverna getInstance() {
        if (instance == null)
            instance = new Caverna();

        return instance;
    }

}