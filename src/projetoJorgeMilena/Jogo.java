package projetoJorgeMilena;

import java.util.Scanner;

public class Jogo {
	public static void main(String[] args) {
		int opReplay = 0;
		Scanner input = new Scanner(System.in);

		do {
			Carta aux;
			Dealer mesa = new Dealer();
			Jogador arrayJogadores[];
			int numJogadores = 0;
			int op = 0;
			int opAs = 0;
			int soma = 0;
			int i = 0;
			int j = 0;
			int resultVencedor = 0;
			
			mesa.embaralhar();

			System.out.println("\t\t\t\t    >>>> BEM VINDO AO BLACKJACK <<<<\n\n");
			System.out.println(
					" -------------------------------------------------------------------------------------------------------------");
			System.out.println(
					" |                                  >>>> ATENÇÃO PARA AS REGRAS <<<<                                          |");
			System.out.println(
					" | 1 - AO INICIAR O JOGO CADA JOGADOR RECEBERÁ 2 CARTAS.                                                      |");
			System.out.println(
					" | 2 - NA VEZ DO JOGADOR ELE TERÁ DUAS OPÇÕES: FAZER HIT OU STAND.                                            |");
			System.out.println(
					" | 2.1 - HIT: RECEBER UMA CARTA DO DEALER.                                                                    |");
			System.out.println(
					" | 2.1 - STAND: PARAR DE RECEBER CARTAS E PASSAR A VEZ.                                                       |");
			System.out.println(
					" | 3 - O JOGADOR DEVERÁ VERIFICAR A SUA SOMA A CADA HIT.                                                      |");
			System.out.println(
					" | 3.1 - SE A SOMA ULTRAPASSAR 21, O JOGADOR PERDEU.                                                          |");
			System.out.println(
					" | 3.2 - SE A SOMA FOR IGUAL A 21, O JOGADOR JÁ VENCEU O JOGO E DEVERÁ ESPERAR PELOS OUTROS JOGADORES.        |");
			System.out.println(
					" | 3.3 - SE A SOMA FOR MENOR QUE 21, O JOGADOR PODERÁ PEDIR HIT OU STAND.                                     |");
			System.out.println(
					" | 4 - VALOR DAS CARTAS NO BLACKJACK:                                                                         |");
			System.out.println(
					" | 4.1 - CADA CARTA NUMERADA DE 2 A 10 TEM O SEU VALOR NOMINAL (IGUAL AO NÚMERO DA CARTA).                    |");
			System.out.println(
					" | 4.2 - OS VALETES, AS DAMAS E OS REIS (AS FIGURAS), TÊM O VALOR DE 10 PONTOS.                               |");
			System.out.println(
					" | 4.3 - O ÁS VALE 1 PONTO OU 11 PONTOS, À ESCOLHA DO JOGADOR.                                                |");
			System.out.println(
					" -------------------------------------------------------------------------------------------------------------");

			System.out.println("\n\n\n\n\t\t\t\t   >>>>      VAMOS INICIAR      <<<<\n");
			do {
				System.out.print(" -> INFORME A QUANTIDADE DE JOGADORES (É POSSÍVEL JOGAR NO MÍNIMO 2 E MÁXIMO 8): ");
				numJogadores = -1;
				do{
					try{
						numJogadores = Integer.parseInt(input.nextLine());
					}catch(NumberFormatException e) {
						System.out.println("\nDIGITE UM NÚMERO: ");
					}
				}while(numJogadores == -1);
				if (numJogadores < 2 || numJogadores > 8) {
					System.err.println(" \n***NÚMERO DE JOGADORES INVÁLIDO. POR FAVOR INSIRA NOVAMENTE.***\n");
					numJogadores = -1;
				} else {
					System.out.println("\n\n\t\t\t\t   >>>> COMEÇANDO A PARTIDA!!!! <<<<\n");
				}
			} while (numJogadores == -1);

			arrayJogadores = new Jogador[numJogadores];

			System.out.println("\t\t\t\t   >>>>  ENTREGANDO AS CARTAS!  <<<<\n");

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

				System.out.println("\n\t\t\t\t   >>>> " + arrayJogadores[i].nome + " É A SUA VEZ!  <<<<\n");

				arrayJogadores[i].showPack();

				do {
					for (int auxAs : arrayJogadores[i].arrayInt) {
						if (auxAs == 1 || auxAs == 11) {
							do {
								System.out.println("\n -> VOCÊ TEM UM ÁS E SEU VALOR NO MOMENTO É: '" + auxAs + "'.");
								System.out.println("    -> DIGITE '1' PARA MUDAR O VALOR OU '0' PARA CONTINUAR");
								opAs = -1;
								do {
									try{
										opAs = Integer.parseInt(input.nextLine());
									}catch(NumberFormatException e) {
										System.out.println("\nDIGITE UM NÚMERO: ");
									}									
								}while(opAs == -1);

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
						op = -1;
						do{
							try{
								op = Integer.parseInt(input.nextLine());
							}catch(NumberFormatException e) {
								System.out.println("\nDIGITE UM NÚMERO: ");
							}
						}while(op == -1);

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
									opAs = -1;
									do{
										try{
											opAs = Integer.parseInt(input.nextLine());
										}catch(NumberFormatException e) {
											System.out.println("\nDIGITE UM NÚMERO: \n");
										}
									}while(opAs == -1);
									
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
			System.out.println(
					"\n\n\n\n-------------------------------------------------------------------------------------------------------------");
			if (i >= 0) {
				resultVencedor = arrayJogadores[i].getSoma();
				System.out.println(
						"|\t\t\t\t   >>>>  TEMOS NOSSO(S) VENCEDOR(ES)!  <<<<<                                |");
				System.out.println(
						"|                                                                                                           |");

				do {
					if (resultVencedor == arrayJogadores[i].getSoma()) {
						System.out.println("|\t\t\t\t    -> " + arrayJogadores[i].nome + ". Com pontuação igual a: "
								+ resultVencedor + "                                 |");
						i--;
					} else {
						break;
					}
				} while (i >= 0);

			} else {
				System.out.println(
						"|\t\t\t\t  >>>> NINGUÉM GANHOU, TODOS ULTRAPASSARAM 21! <<<<                         |");
			}
			System.out.println(
					"-------------------------------------------------------------------------------------------------------------");

			do {
				System.out.println("\n\n\n -> DESEJA JOGAR NOVAMENTE?");
				System.out.println(" 1 - SIM");
				System.out.println(" 2 - NÃO");
				
				opReplay = -1;
				do{
					try{
						opReplay = Integer.parseInt(input.nextLine());
					}catch(NumberFormatException e) {
						System.out.println("\nDIGITE UM NÚMERO: ");
					}
				}while(opReplay == -1);

				if (opReplay < 1 || opReplay > 2) {
					System.err.println(" \n***OPÇÃO INVÁLIDA, TENTE NOVAMENTE!***\n");
					opReplay = -1;
				} else {
					if(opReplay == 1) {
						System.out.println("\n\n\n\n\t\t\t\t    >>>>     REFAZENDO JOGO     <<<<\n\n\n\n");
					}
					else {
						System.out.println("\n\n\n\n\t\t\t    >>>>     AGRADECEMOS SUA PARTICIPAÇÃO NO BLACKJACK!     <<<<\n\n\n\n");
					}
				}
			} while (opReplay == -1);
		} while (opReplay != 2);
		
		input.close();
	}
}
