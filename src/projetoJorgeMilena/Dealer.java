package projetoJorgeMilena;

import java.util.Random;

public class Dealer {
	public Baralho pack = new Baralho();

	public void embaralhar() {
		// M�todo que embaralha todo o pack de cartas.
		Carta aux = new Carta();
		Random random = new Random();

		for (int i = 0; i < pack.getCount(); i++) {

			int j = random.nextInt(52);

			aux = pack.setOfCards[i];
			pack.setOfCards[i] = pack.setOfCards[j];
			pack.setOfCards[j] = aux;
		}
	}

	public Carta entregaCarta() {
		// M�todo que retorna uma carta que ser� recebida por um jogador
		Carta saida = remover();

		return saida;
	}

	public Carta remover() {
		// M�todo que remove carta do baralho. A carta a ser removida, sempre ser� a da
		// �ltima posi��o.
		// Uma Carta auxiliar recebe a Carta da �ltima posi��o do baralho.
		// Um array auxiliar do tipo baralho � alocado com o tamanho do Baralho atual
		// menos.
		// � feita uma copia de todas as cartas do baralho pro auxiliar
		// O Baralho recebe o auxiliar que estar� sem a Carta que foi removida.
		Carta ret = pack.setOfCards[pack.getCount() - 1];
		Carta aux[] = new Carta[pack.getCount() - 1];

		for (int i = 0; i < pack.getCount() - 1; i++) {
			aux[i] = pack.setOfCards[i];
		}

		pack.setCount(pack.getCount() - 1);

		pack.setOfCards = aux;

		return ret;
	}
}
