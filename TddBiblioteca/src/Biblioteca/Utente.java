package Biblioteca;

import java.util.HashMap;
import java.util.Map;

public class Utente {
	private String nome;
	private String cognome;
	private String cF;
	
	private Map<String, Prestito> LibriInPrestito=new HashMap<String, Prestito>();


	public Utente() {
		super();
	}
	public Utente(String nome, String cognome, String cF) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.cF = cF;
		
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getcF() {
		return cF;
	}
	public void setcF(String cF) {
		this.cF = cF;
	}
	public Map<String, Prestito> getLibriInPrestito() {
		return LibriInPrestito;
	}
	public void setLibriInPrestito(Map<String, Prestito> libriInPrestito) {
		LibriInPrestito = libriInPrestito;
	}
	
	
}
