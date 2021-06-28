# O submundo de Wumboss
## Descrição resumida
O _Submundo de Wumboss_ é um jogo criado em Java, pensado como uma continuação do jogo [O mundo de Wumpus](https://en.wikipedia.org/wiki/Hunt_the_Wumpus). Nele, o herói cai em um buraco do primeiro jogo, mas milagrosamente consegue sobreviver. Agora, sua missão é explorar as cavernas abaixo do mundo de Wumpus, coletando itens que o ajudarão em sua jornada, matando inimigos e finalmente, derrotando _Wumboss_, que guarda a saída da caverna.

## Equipe
* Victor Costa Dominguite - RA 245003
* Thiago Donato Ferreira - RA 194300

## Vídeos do Projeto
### Vídeo da prévia
[Vídeo de apresentação inicial do jogo](https://drive.google.com/file/d/1lccrVUTai-J20fjwlvtDMPQStChpDiqT/view?usp=sharing)
### Vídeo do jogo

## Slides do Projeto 
### Slides da prévia
[Slides de apresentação inicial do jogo](https://docs.google.com/presentation/d/1Me5ZMq8UdEnHFej91MhEyC3Btpk-YnCvo0WzEDYdrdM/edit?usp=sharing)
### Slides da Apresentação Final

## Relatório de Evolução
Ao longo do desenvolvimento do jogo, não houve mudanças fundamentais em relação ao design inicial planejado. Porém, vale ressaltar que a complexidade de se implementar o jogo se demonstrou maior do que esperada, o que fez com que, durante seu desenvolvimento, fosse necessária a criação de mais sub-componentes (como pode ser observado no diagrama de componentes) e interfaces para conectar efetivamente os principais componentes do jogo (isto é, model, view e controller).

Algumas dificuldades enfrentadas envolveram a conexão entre componentes, sem que houvesse uma interdependência muito grande entre classes. Ou seja, a divisão das funções em componentes que funcionam independentemente um do outro foi um desafio.

Além disso, também houve difiuldade em sincronizar o view e o model, para que as mudanças que ocorressem internamente no jogo fossem imediatamente atualizadas na interface gráfica.

Como nenhum dos integrantes do grupo havia anteriormente utilizado interfaces gráficas, essa foi uma grande novidade, que gerou dúvidas em sua implementação, porém, por fim, acabou somando como um aprendizado. Semelhantemente, a noção de definir um projeto de jogo com uma arquitetura específica também foi algo novo, que gerou certas dificuldades, porém, ao fim, foi algo positivo, uma vez que reforçou a importância de se pensar melhor na organização geral do programa como um todo, antes de começar a implementá-lo, para evitar que seja necessário realizar muitos ajustes no meio do desenvolvimento.

## Destaques do Código

A movimentação dos personagens foi feita baseada em direções, que foram implementadas como um "enum", facilitando consideravelmente a comunicação dos comandos do controller para o model, além de também facilitar a implementação da movimentação internamente no model.

~~~java
public enum Direcao {
    NORTE, LESTE, SUL, OESTE;
	
    public static Direcao randomDir(Random r) {
    	return Direcao.values()[r.nextInt(4)];
    }
    
    ...
    
    public static Direcao fromString(String s) {
    	if(s.equals("up") || s.equals("cima") || s.equals("norte"))
    		return Direcao.NORTE;
    	if(s.equals("down") || s.equals("baixo") || s.equals("sul"))
    		return Direcao.SUL;
    	if(s.equals("left") || s.equals("esquerda") || s.equals("oeste"))
    		return Direcao.OESTE;
    	if(s.equals("right") || s.equals("direita") || s.equals("leste"))
    		return Direcao.LESTE;
    	
    	return null;
    }

    ...
}
~~~

O espaço do jogo é uma caverna composta de 10 salas de espaço interno 9 x 9. A formação da caverna é feita com base em 30 arquivos modelos de salas, fazendo com que a experiência do jogador seja diferente a cada vez que o jogo é rodado, uma vez que a caverna é montada de maneira diferente toda vez que o jogo é iniciado.

~~~java
public static Caverna montar() {
    Caverna cave = new Caverna();
    ArrayList<Integer> tiposSalas = new ArrayList<Integer>();

    for (int i = 0; i < Constantes.NUM_SALAS_CAVERNA - 1; i ++) {
    	tiposSalas.add(i);
    }
    
    // Faz com que as salas de cada tipo estejam sempre em ordem diferente
    Collections.shuffle(tiposSalas);

    ...

    // As salas de índice i são montadas com base nos tipos de sala
    // que constam na lista "tiposSalas"
    for (int i = 1; i < Constantes.NUM_SALAS_CAVERNA - 1; i++) {
    	Sala atual = SalaFactory.montar(i, tiposSalas.get(i));

    	cave.setSala(i, atual);
    }
    
    ...

    // Conecta as salas subsequentes, com passagens geradas em lugares 
    // aleatórios ao longo de sua borda
    for (int i = 0; i < Constantes.NUM_SALAS_CAVERNA - 1; i++) {
    	anterior = criarPassagem(cave.getSala(i), cave.getSala(i + 1), anterior);
    }

    return cave;
}

...

public String[][] readSala(int tipo) throws TipoDeSalaInvalido, IOException {
		String[][] res;
		String path;
		
        // Há 3 modelos disponíveis para cada tipo de sala. O modelo da sala
        // é definido aleatoriamente
		int modelo = Constantes.rng.nextInt(3) + 1;

		if(tipo < 10)
			path = dataPath + "tipo0" + tipo + "/sala0" + modelo + ".csv";
		else
			path = dataPath + "tipo" + tipo + "/sala" + modelo + ".csv";
		
		try {
			res = fileIO.readCSV(path);
		} catch(FileNotFoundException e) {
			throw new TipoDeSalaInvalido(tipo);
		}
		
		if(res == null) 
			throw new IOException();
		
		return res;
	}
~~~

No jogo, quando os inimigos entram no campo de visao do herói, eles são alertados e passam a perseguir o herói. Assim, toda vez que o herói se move, os inimigos também se movem em sua direção. Porém, alguns inimigos mais pesados se movem mais lentamente. O destaque do trecho a seguir se deve à simplicidade com que a atualização da movimentação dos inimigos em ritmos diferentes é implementada.

~~~java
public void atualizarVisaoEInimigos() {
    
    ...
    // Faz com que todos os inimigos em alerta na sala se movam em direção ao
    // herói, caso o seu movimento não estiver em cooldown
	for (IInimigo i : inimigosAlerta) {
        // globalTimer é incrementado a cada movimento do herói e o cooldown do
        // movimento simboliza com que frequência (1 ou 2) o inimigo se move
		if (i != null && globalTimer % i.getCooldownMovimento() == 0)
			i.moverEmDirecaoA(heroiX, heroiY);
	}
}
~~~

## Destaques de Pattern

A trasmissão de comandos entre os componentes Controller e Model tem como intermediária a classe ModelAction.

~~~java

public class ModelAction implements IActionParser {
    private HashMap<String, IActionExecutor> mappings;
    
    public ModelAction() {
    	mappings = new HashMap<String, IActionExecutor>();
    }

    ...
	// A partir de uma string padronizada, o comando é obtido e repassado
	private void parseMessage(String message) {
		String[] splitMessage = message.split(" ");
		
		IActionExecutor actor = mappings.get(splitMessage[0]);
		if(actor == null)
			throw new MensagemInvalida("Controller", "ModelAction", message);
		
		String action = splitMessage[1];
		String[] args = new String[splitMessage.length - 2];
		
		for(int i = 2; i < splitMessage.length; i++)
			args[i-2] = splitMessage[i];
		
        // O comando é passado para ator especificado, o qual comunicará a ação
        // ao seu correspondente no model
		sendAction(actor, action, args);
	}

	...

}

~~~

A interface gráfica do jogo foi dividida em 3 seções principais: um painel para o inventário do herói, um painel de informaçoes gerais e um para a visualização da caverna em si. Cada um desses precisa ser atualizado conforme o decorrer do jogo. Essa atualização foi unificada pela interface Observer, que permite que a classe EventCreator, responsável pela atualização do View, não tenha que saber qual painel está sendo referenciado para atualizá-lo.

~~~java

public interface Observer {
	public void onUpdate();
	public void onUpdate(boolean reinscrever);
	public String[] getInfo();
}

public abstract class EventCreator implements IEventCreator{
	protected ArrayList<Observer> listeners;

~~~

## Documentação dos componentes
### Diagrama geral do projeto
![diagrama1](./diagramas/diagrama.png)

### Diagrama geral de componentes
![diagrama2](./diagramas/diagrama2.png)

### Detalhamento das interfaces

### Plano de exceções
