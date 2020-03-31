package projetoJorgeMilena;

public class ArrayListBlackjack{
	public int arrayInt[] = new int[1];
	public Carta arrayCarta[] = new Carta[1];
	private int j = 0;
	
	//'ArrayList' próprio que vai aumentando ou diminuindo o tamanho do array.
	//Existem dois tipos de array dentro do ArrayListBlackjack: inteiro e de Cartas.
	//Essa classe é extendida diretamente ao jogador.
	
	public void addInt(int newNumber, int arrayInit[]) {
		j = 0;
		
		if(arrayInit[0] != 0) {
			this.arrayInt = new int[arrayInit.length+1];
			
			for(int i : arrayInit) {
				this.arrayInt[j] = i;
				j++;
			}
		}
		
		arrayInt[j] = newNumber;
	}

	public void addCarta(Carta newCarta, Carta arrayInit[]) {
		j = 0;
		
		if(arrayInit[0] == null) {
			arrayCarta[0] = new Carta();
		}
		else {
			arrayCarta = new Carta[arrayInit.length+1];
			
			for(Carta i : arrayInit) {
				arrayCarta[j] = new Carta();
				arrayCarta[j] = i;
				j++;
			}
		}
		
		arrayCarta[j] = newCarta;
	}
	
	public void switchAs(int trade) {
		for(int i=0; i<arrayInt.length; i++) {
			if(arrayInt[i] == trade && trade == 1) {
				arrayInt[i] = 11;
				break;
			}
			else if(arrayInt[i] == trade && trade == 11) {
				arrayInt[i] = 1;
				break;
			}
		}
	}
}
