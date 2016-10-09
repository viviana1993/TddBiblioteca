package Biblioteca;

public class Libro {
	private String titolo;
	private String autore;
	private String serialNum;
	private int copieTot;
	private int copieDisp;
	
	public Libro() {
		super();
	}

	public Libro(String titolo, String autore, String serialNum, int copieTot,
			int copieDisp) {
		super();
		this.titolo = titolo;
		this.autore = autore;
		this.serialNum = serialNum;
		this.copieTot = copieTot;
		this.copieDisp = copieDisp;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getAutore() {
		return autore;
	}

	public void setAutore(String autore) {
		this.autore = autore;
	}

	public String getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}

	public int getCopieTot() {
		return copieTot;
	}

	public void setCopieTot(int copieTot) {
		this.copieTot = copieTot;
	}

	public int getCopieDisp() {
		return copieDisp;
	}

	public void setCopieDisp(int copieDisp) {
		this.copieDisp = copieDisp;
	}
	
	
	
	
	
}
