package projetoJorgeMilena;

public class Baralho {
	// Classe que monta o baralho a partir de um array de cartas (classe Carta).
	public Carta setOfCards[] = new Carta[52];
	private int count = 52;
	private String numeros[] = { "2", "3", "4", "5", "6", "7", "8", "9", "10" };

	public Baralho() {
		// Método construtor que inicializa todas as cartas com seus valores e
		// respectivos naipes. O algoritmo usado foi baseado em mods (restos da
		// divisão).
		for (int i = 0; i <= 51; i++) {
			this.setOfCards[i] = new Carta();
			if ((i + 1) % 13 == 1) {
				this.setOfCards[i].setValue("A");
			} else if ((i + 1) % 13 == 11) {
				this.setOfCards[i].setValue("J");
			} else if ((i + 1) % 13 == 12) {
				this.setOfCards[i].setValue("Q");
			} else if ((i + 1) % 13 == 0) {
				setOfCards[i].setValue("K");
			} else {
				this.setOfCards[i].setValue(numeros[(i % 13) - 1]);
			}

			if (i < 13) {
				this.setOfCards[i].setSuits(" de Copas");
			} else if (i >= 13 && i < 26) {
				this.setOfCards[i].setSuits(" de Ouros");
			} else if (i >= 26 && i < 39) {
				this.setOfCards[i].setSuits(" de Paus");
			} else {
				this.setOfCards[i].setSuits(" de Espada");
			}
		}
	}

	// Get e set do atributo count, que é onde guardamos a quantidade de cartas do
	// baralho

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
