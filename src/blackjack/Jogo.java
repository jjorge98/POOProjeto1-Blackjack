package blackjack;

import java.util.Scanner;

public class Jogo {
	public static void main(String[] args) {
		Carta aux;
		Dealer mesa = new Dealer();
		Jogador arrayJogadores[];
		Scanner input = new Scanner(System.in);
		int numJogadores = 0;
		int op = 0;
		int opAs = 0;
		int soma = 0;
		int i = 0;
		int j = 0;
		int resultVencedor = 0;
		mesa.embaralhar();

		System.out.println("\t\t\t\t\t  >>>> BEM VINDO AO BLACKJACK! <<<<\n\n");
		System.out.println(
				" -----------------------------------------------------------------------------------------------------------------");
		System.out.println(
				" |                                 >>>> ATENÇÃO PARA AS REGRAS <<<<                                              |");
		System.out.println(
				" | 1- Depois de receber duas cartas, o jogador tira cartas (HIT) para se aproximar do valor 21 sem o ultrapassar |");
		System.out.println(
				" | 2- Caso exista empate, TODOS perdem                                                                           |");
		System.out.println(
				" | 3- Valor das cartas no blackjack                                                                              |");
		System.out.println(
				" |    - Cada carta numerada de 2 a 10 tem o seu valor nominal (igual ao número da carta)                         |");
		System.out.println(
				" |    - Os valetes, as damas e os reis (as figuras), têm o valor de 10 pontos                                    |");
		System.out.println(
				" |    - O Ás vale 1 ponto ou 11 pontos, à escolha do jogador                                                     |");
		System.out.println(
				" -----------------------------------------------------------------------------------------------------------------");

		System.out.println("\n\n\n\t\t\t\t>>>> VAMOS INICIAR <<<<\n");
		do {
			System.out.print(" -> INFORME A QUANTIDADE DE JOGADORES (É POSSÍVEL JOGAR NO MÍNIMO 2 E MÁXIMO 8): ");
			numJogadores = Integer.parseInt(input.nextLine());
			if (numJogadores < 2 || numJogadores > 8) {
				System.err.println(" \n***NÚMERO DE JOGADORES INVÁLIDO. POR FAVOR INSIRA NOVAMENTE.***\n");
			} else {
				System.out.println("\n\n\t\t\t\t>>>> COMEÇANDO A PARTIDA!!! <<<<\n");
			}
		} while (numJogadores < 2 || numJogadores > 8);

		arrayJogadores = new Jogador[numJogadores];

		System.out.println("\t\t\t\t>>>>   Entregando cartas!   <<<<\n");

		for (i = 0; i < numJogadores; i++) {
			arrayJogadores[i] = new Jogador();
			arrayJogadores[i].nome = arrayJogadores[i].nome + Integer.toString(i + 1);
			for (j = 0; j < 2; j++) {
				aux = mesa.entregaCarta();
				arrayJogadores[i].addCarta(aux, arrayJogadores[i].arrayCarta);
				if (aux.getValue() == "J" || aux.getValue() == "Q" || aux.getValue() == "K") {
					arrayJogadores[i].addInt(10, arrayJogadores[i].arrayInt);
				} else if (aux.getValue() == "A") {
					arrayJogadores[i].addInt(1, arrayJogadores[i].arrayInt);
				} else {
					arrayJogadores[i].addInt(Integer.parseInt(aux.getValue()), arrayJogadores[i].arrayInt);
				}
			}

			System.out.println("\n\t\t\t\t>>>> " + arrayJogadores[i].nome + " É A SUA VEZ! <<<<");

			arrayJogadores[i].showPack();

			do {
				for (int auxAs : arrayJogadores[i].arrayInt) {
					if (auxAs == 1 || auxAs == 11) {
						do {
							System.out.println("\n -> VOCÊ TEM UM ÁS E SEU VALOR NO MOMENTO É: '" + auxAs + "'.");
							System.out.println("    -> DIGITE '1' PARA MUDAR O VALOR OU '0' PARA CONTINUAR");
							opAs = Integer.parseInt(input.nextLine());

							switch (opAs) {
							case 0:
								break;
							case 1:
								arrayJogadores[i].switchAs(auxAs);
								arrayJogadores[i].showPack();
								break;
							default:
								System.err.println("\nDIGITE UMA OPÇÃO VÁLIDA!\n");
								opAs = -1;
								break;
							}
						} while (opAs == -1);
					}
				}

				soma = arrayJogadores[i].getSoma();

				if (soma > 21) {
					System.out.println("\nSUA SOMA ULTRAPASSOU 21, VOCÊ PERDEU!");
					op = 0;
				} else if (soma == 21) {
					System.out.println(
							"\nPARABÉNS, VOCÊ JÁ ATINGIU 21! AGUARDE OS PRÓXIMOS JOGADORES PARA VALIDAR SUA VITÓRIA");
					op = 0;
				} else {

					System.out.println(" -> GOSTARIA DE FAZER UM HIT OU STAND? DIGITE 1 PARA HIT E 0 PARA STAND: ");
					op = Integer.parseInt(input.nextLine());

					switch (op) {
					case 0:
						break;
					case 1:
						aux = mesa.entregaCarta();
						arrayJogadores[i].addCarta(aux, arrayJogadores[i].arrayCarta);
						if (aux.getValue() == "J" || aux.getValue() == "Q" || aux.getValue() == "K") {
							arrayJogadores[i].addInt(10, arrayJogadores[i].arrayInt);
						} else if (aux.getValue() == "A") {
							do {
								System.out.println("\n -> SUA SOMA É IGUAL A: " + arrayJogadores[i].getSoma());
								System.out.println(
										"\n -> VOCÊ RECEBEU UM ÁS. QUAL VALOR DESEJA QUE ELE ASSUMA? DIGITE 1 OU 11: ");
								opAs = Integer.parseInt(input.nextLine());
								switch (opAs) {
								case 1:
									arrayJogadores[i].addInt(opAs, arrayJogadores[i].arrayInt);
									break;
								case 11:
									arrayJogadores[i].addInt(opAs, arrayJogadores[i].arrayInt);
									break;
								default:
									System.err.println("DIGITE UM VALOR VÁLIDO!");
									opAs = -1;
									break;
								}
							} while (opAs == -1);

						} else {
							arrayJogadores[i].addInt(Integer.parseInt(aux.getValue()), arrayJogadores[i].arrayInt);
						}
						arrayJogadores[i].showPack();
						break;
					default:
						System.err.println("DIGITE UMA OPÇÃO VÁLIDA!");
						break;
					}
				}
			} while (op != 0);
		}

		for (i = 1; i < numJogadores; i++) {
			Jogador aux1 = arrayJogadores[i];
			for (j = i - 1; j >= 0 && arrayJogadores[j].getSoma() > aux1.getSoma(); j--) {
				arrayJogadores[j + 1] = arrayJogadores[j];
			}
			arrayJogadores[j + 1] = aux1;
		}

		i = numJogadores - 1;

		while (i >= 0) {
			if (arrayJogadores[i].getSoma() > 21) {
				i--;
			} else {
				break;
			}
		}

		if (i >= 0) {
			resultVencedor = arrayJogadores[i].getSoma();

			System.out.println("\n\n\n\t\t\t\t>>>>>> TEMOS NOSSO(S) VENCEDOR(ES)! <<<<<<<\n");

			do {
				if (resultVencedor == arrayJogadores[i].getSoma()) {
					System.out.println(
							"\n\t\t\t\t -> " + arrayJogadores[i].nome + ". Com pontuação igual a: " + resultVencedor);
					i--;
				} else {
					break;
				}
			} while (i >= 0);
		} else {
			System.out.println("\n\n\n\t\t\t\t>>>>>> NINGUÉM GANHOU, TODOS ULTRAPASSARAM 21! <<<<<<<\n");
		}

		input.close();
	}
}
