package blackjack;

public class Jogador extends ArrayListBlackjack {
	// Classe do jogador. Nela temos o nome do jogador, a parada e a soma como
	// atributos. Como m�todos, temos o getSoma que realiza a soma da m�o do
	// jogador,
	// getPack, que retorna a m�o do jogador
	// showPack que mostra toda a m�o do jogador
	public String nome = "Jogador ";
	private int soma = 0;

	public int getSoma() {
		soma = 0;

		for (int i : arrayInt) {
			soma += i;
		}

		return soma;
	}

	public void showPack() {
		System.out.println("\n -> SUAS CARTAS S�O: ");
		for (Carta aux : arrayCarta) {
			System.out.println("    - " + aux.getValue() + aux.getSuits());
		}
		System.out.println("\n -> SUA SOMA � IGUAL A: " + this.getSoma());
	}
}
