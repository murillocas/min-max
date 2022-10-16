import java.util.ArrayList;

public class PossiveisJogadas {
	
	ArrayList<PossiveisJogadas> listajogos = new ArrayList<>();
	ArrayList<Integer> possiveisVitorias = new ArrayList<>();
	int[][] jogo = new int [3][3];
	int quemGanhou = 0;
	int melhorMovimento = 0;
	// quem Joga == 1 1 = x == max
	// quem Joga == 2 -1= o == min
	int quemJoga;
	Boolean vitoria;
	
	int criarJogo(int jogo[][],int quemJoga){
		this.jogo = jogo;
		this.quemJoga = quemJoga;
		/*
		 * se ha um ganhador nesta rodada ele retorna o ganhador
		 * senao ele continua criando a arvore e seleciona a melhor jogada possivel(min - max)
		 */
		//System.out.print("teste2");
		if(verificaVitoria()) {
			return quemGanhou;
		}else {
			criarArvore();
			return verificaGanhador();
		}
	}
	int verificaGanhador() {
		int melhor=0;
		//System.out.println(" tamanho :" + possiveisVitorias.size() );
		for(int x=0 ; x < possiveisVitorias.size() ; x++) {
			if(quemJoga == 1) {
				if(possiveisVitorias.get(x) >= melhor) {
					melhor = possiveisVitorias.get(x);
					melhorMovimento = x;
				}
			}else {
				if(possiveisVitorias.get(x) <= melhor) {
					melhor = possiveisVitorias.get(x);
					melhorMovimento = x;
				}	
			}
//			if(possiveisVitorias.get(x) == quemJoga) {
//				melhorMovimento = x;
//				//System.out.print(" valor :" +possiveisVitorias.get(x)  );
//				return possiveisVitorias.get(x);
//			}else if (possiveisVitorias.get(x).equals(0)){
//				empate = x;
//			}
		}
		//System.out.println("empate " + empate);
		return possiveisVitorias.get(melhorMovimento);
	}
	
	public int[][] getJogo() {
		return jogo;
	}
	
	void criarArvore(){
		//cria um objeto de possiveisjogadas com todas as possibilidades 
		//e coloca na lista de possiveisvitorias o ganhador do proximo jogo 
		for(int i=0;i<3;i++) {
			for(int j=0 ; j<3 ; j++) {
				if(jogo[i][j] == 0 ) {
					if(quemJoga == 1) {
						jogo[i][j] = 1;
						PossiveisJogadas proxJogada = new PossiveisJogadas();
						listajogos.add(proxJogada);
						possiveisVitorias.add(proxJogada.criarJogo(copiar(), 2));
						jogo[i][j] = 0;
					}else {
						jogo[i][j] = 2;
						PossiveisJogadas proxJogada = new PossiveisJogadas();
						listajogos.add(proxJogada);
						possiveisVitorias.add(proxJogada.criarJogo(copiar(), 1));
						jogo[i][j] = 0;
					}
					
				}
			}
		}
	}
	
	Boolean verificaVitoria(){
		if(verificaLinha()) {
			vitoria = true;
			return true;
		}else if (verificaColuna()) {
			vitoria = true;
			return true;
		}else if (verificaDiagonal()) {
			vitoria = true;
			return true;
		}else if(verificaEmpate()){
			vitoria = true;
			return true;
		}else{
			vitoria = false;
			return false;
		}
	}
	Boolean verificaLinha() {
		int aux1 = 0;
		int aux2 = 0;
		for(int i = 0; i < 3; i++) {
			for(int j=0; j < 3 ; j++) {
				if(jogo[i][j] == 1) {
					aux1++;
				}else if (jogo[i][j] == 2) {
					aux2++;
				}
			}
			if(aux1 == 3) {
				quemGanhou = 1;
				return true;
			}else if (aux2 == 3) {
				quemGanhou = -1;
				return true;
			}else {
				aux1 = 0;
				aux2 = 0;
			}
			
		}
		return false;
	}
	Boolean verificaColuna() {
		int aux1 = 0;
		int aux2 = 0;
		for(int i = 0; i < 3; i++) {
			for(int j=0; j < 3 ; j++) {
				if(jogo[j][i] == 1) {
					aux1++;
				}else if (jogo[j][i] == 2) {
					aux2++;
				}
			}
			if(aux1 == 3) {
				quemGanhou = 1;
				return true;
			}else if (aux2 == 3) {
				quemGanhou = -1;
				return true;
			}else {
				aux1 = 0;
				aux2 = 0;
			}
			
		}
		return false;
	}
	Boolean verificaDiagonal() {
		int aux1 = 0;
		int aux2 = 0;
		for(int j=0; j < 3 ; j++) {
			if(jogo[j][j] == 1) {
				aux1++;
			}else if (jogo[j][j] == 2) {
				aux2++;
			}
		}
		if(aux1 == 3) {
			quemGanhou = 1;
			return true;
		}else if (aux2 == 3) {
			quemGanhou = -1;
			return true;
		}else {
			aux1 = 0;
			aux2 = 0;
		}
		
		for(int i=0,j=2;i<3;i++,j--) {
			if(jogo[i][j] == 1) {
				aux1++;
			}else if (jogo[i][j] == 2) {
				aux2++;
			}
		}
		if(aux1 == 3) {
			quemGanhou = 1;
			return true;
		}else if (aux2 == 3) {
			quemGanhou = -1;
			return true;
		}
		return false;
		
		
	}
	Boolean verificaEmpate() {
		for(int i = 0; i < 3; i++) {
			for(int j=0; j < 3 ; j++) {
				if(jogo[j][i] == 0) {
					return false;
				}
			}
		}
		return true;
	}

	PossiveisJogadas proxMovimentoMaquina() {
		//retorna a melhor jogada para a maquina
		return listajogos.get(melhorMovimento);
	}
//	PossiveisJogadas proxMovimentoHumano(int jogoHumano[][]) {
//		return  verificaProxjogoHumano(jogoHumano);
//	}
	PossiveisJogadas proxMovimentoHumano(int jogohumano[][]){
		// procura qual foi a joga feita pelo ser humano
		for(int x=0; x< listajogos.size();x++) {
			if(verificaIgual(jogohumano,listajogos.get(x).getJogo())) {
				return listajogos.get(x);
			}
		}
		return null;
	}
	boolean verificaIgual(int jogo1[][],int jogo2[][]) {
		for(int i=0; i<3;i++) {
			for(int j=0;j<3;j++) {
				if(jogo1[i][j] != jogo2[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
	void mostrarjogo() {
		for(int i=0; i<3;i++) {
			for(int j=0;j<3;j++) {
				System.out.print(jogo[i][j] + " | ");
			}
			System.out.print("\n------------\n");
		}
	}
	void mostrarJogos() {
		for(int j=0;j< listajogos.size();j++) {
			System.out.print("_____" + j +"_____\n");
			listajogos.get(j).mostrarjogo();
		}
	}
	void mostrarPossiveisVitorias() {
		System.out.print(" possiveis vitorias ");
		for(int j=0;j< possiveisVitorias.size();j++) {
			System.out.print(possiveisVitorias.get(j) + " | ");
			
		}
		System.out.print("\n");
	}
	
	public int[][] copiar(){
		int[][] jogoAux = new int [3][3];
		for(int i=0; i<3;i++) {
			for(int j=0;j<3;j++) {
				jogoAux[i][j] = jogo[i][j];
			}
		}
		return jogoAux;
	}
	void mostrarQuemJoga() {
		System.out.println("player "+ quemJoga);
	}
}
