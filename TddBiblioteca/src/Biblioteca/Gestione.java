package Biblioteca;



public class Gestione {
	//metodo 1<:per registrare un numero di copie qualsiasi all'interno della biblioteca, 
	//le copie disponibili e totali devono essere ogni volta aggiornate;
	
	public Libro registraCopie(Biblioteca b,String titolo, String autore, String serialNumber,
			int cp) {
		
		Libro l=b.registraLibro(titolo,autore,serialNumber,cp);//richiamo metodo presente nella classe biblioteca
		return l;
		}

	//metodo2:per registrare l'utente, nel caso in cui l'utente fosse già registrato deve lanciare un'eccezione;
	public Utente registraUtente(Biblioteca b, String nome, String cognome,
			String cF) throws UtenteGiàPresente{
		Utente u=b.inserisciUtente(nome, cognome, cF);
		return u;
	}
	//metodo3 per registrare prestito
	public boolean registraPrestito(Biblioteca b,String cF,String titolo,String autore,String serialNumber) {
		boolean r=false;
		r=b.inserisciPrestito(cF,titolo,autore,serialNumber);
		return r;
	}
	
	
	//metodo4 per regostrare restituzione
	public boolean registraRestituzione(Biblioteca b, String cF,String titolo,
			String autore,String serialNumber) {
		boolean r=b.inserisciRestituzione(cF, titolo, autore, serialNumber);
		return r;
	}

}
