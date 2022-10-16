import java.util.Scanner;

public class Inicio {

	public static void main(String[] args) {
		menu();
	}
	static void menu() {
		Scanner scan = new Scanner(System.in);
		int linha;
		int coluna;
		int[][] jogo = new int[3][3];
		int[][] jogo2 = new int[3][3];
		
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				jogo[i][j] = 0;
			}
		}
		int jogadaMaquina = 1;
		PossiveisJogadas possiveisJogadas = new PossiveisJogadas();
		possiveisJogadas.criarJogo(jogo,jogadaMaquina);
		
		while(true) {
			possiveisJogadas.mostrarQuemJoga();
			possiveisJogadas.mostrarPossiveisVitorias();
			possiveisJogadas = possiveisJogadas.proxMovimentoMaquina();
			possiveisJogadas.mostrarPossiveisVitorias();
			possiveisJogadas.mostrarQuemJoga();
			possiveisJogadas.mostrarjogo();
			System.out.print("digite linha");
			linha = scan.nextInt();
			System.out.print("digite coluna");
			coluna  = scan.nextInt();
			jogo = possiveisJogadas.getJogo();
			for(int i=0; i<3;i++) {
				for(int j=0;j<3;j++) {
					jogo2[i][j] = jogo[i][j];
				}
			}
			
			jogo2[linha][coluna]=2;
			possiveisJogadas = possiveisJogadas.proxMovimentoHumano(jogo2);
			
		}
	}

}
