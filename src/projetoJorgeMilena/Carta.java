package projetoJorgeMilena;

public class Carta {
	//Classe que possui um valor (value) e um naipe (suits). Os dois atributos s�o privados e tem os m�todos set e get.
	private String value;
	private String suits;
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getSuits() {
		return suits;
	}
	public void setSuits(String suits) {
		this.suits = suits;
	}
}
