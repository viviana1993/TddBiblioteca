package Biblioteca;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Biblioteca {
	private String nome;
	private Map<String,Libro> Libri=new HashMap<String,Libro>();
	private Map<String,Utente> UtentiRegistrati=new TreeMap<String,Utente>();
	private Map<String,Prestito> Prestiti=new HashMap<String,Prestito>();

	public Biblioteca() {
		super();
	}

	public Biblioteca(String nome) {
		super();
		this.nome = nome;
	}

	public Biblioteca(String nome, Map<String, Libro> libri,
			Map<String, Utente> utentiRegistrati) {
		super();
		this.nome = nome;
		Libri = libri;
		UtentiRegistrati = utentiRegistrati;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Map<String, Libro> getLibri() {
		return Libri;
	}

	public void setLibri(Map<String, Libro> libri) {
		Libri = libri;
	}

	public Map<String, Utente> getUtentiRegistrati() {
		return UtentiRegistrati;
	}

	public void setUtentiRegistrati(Map<String, Utente> utentiRegistrati) {
		UtentiRegistrati = utentiRegistrati;
	}
	public Map<String,Prestito> getPrestiti() {
		return Prestiti;
	}

	public void setPrestiti(Map<String,Prestito> prestiti) {
		Prestiti = prestiti;
	}

	//metodo per inserire libro

	public Libro registraLibro(String titolo, String autore, String serialNumber,
			int cp) {
		Libro l=null;
		if(Libri.containsKey(titolo+" "+autore+" "+serialNumber)) //se la lista libri contiene il libro entro
		{
			l=Libri.get(titolo+" "+autore+" "+serialNumber);//prendo quel libro dalla lista della biblioteca

			l.setCopieDisp(l.getCopieDisp()+cp);//aggiorno copieDisp
			l.setCopieTot(l.getCopieTot()+cp);//aggiorno copieTot
			return l;
		}
		//se non Ë presente nella biblioteca allora lo creo e lo inserisco
		l=new Libro(titolo,autore,serialNumber,cp,cp);
		Libri.put(titolo+" "+autore+" "+serialNumber,l);
		return l;
	}


	//metodo per inserire utente alla lista

	public Utente inserisciUtente(String nome, String cognome,String cF) throws UtenteGi‡Presente{
		Utente u=null;
		if(UtentiRegistrati.containsKey(cF))throw new UtenteGi‡Presente ("Gi‡ esistente");
		else{//se l'utente Ë gi presente lancio l'eccezione altrimenti creo un nuovo utente
			u=new Utente (nome,cognome,cF);
			UtentiRegistrati.put(cF, u);//inserisco utente alla lista
			return u;
		}
	}
	//metodo per registrare prestito
	public boolean inserisciPrestito(String cF,String titolo,String autore,String serialNumber){

		//inizializzo variabile di ritorno
		Utente u1=UtentiRegistrati.get(cF);//se Ë presente prendo l'utente dalla lista
		//se Ë presente prendo il libro dalla lista libri
		Libro l1=Libri.get(titolo+" "+autore+" "+serialNumber);
		if(u1!=null && u1.getLibriInPrestito().size()<3 && l1!=null && l1.getCopieDisp()>0){
			//entro solo se ho trovato utente ,esso non ha gi‡ 3 libri in prestito
			//e se ho trovato il libro nella biblioteca e se c'Ë almeno una copia di quel libro

			Calendar dataOdierna = Calendar.getInstance();//registro data odierna
			Calendar calendar = Calendar.getInstance();//inizializzo variabile calendar
			//scorro nella mappa prestiti dell'utente per verificare che non ci siano prestiti scaduti

			for(Prestito p:u1.getLibriInPrestito().values()){

				calendar.setTime(p.getDataScad());//di prestito in prestito registro scadenza
				//entro nell'if se il prestito Ë scaduto,quindi ritorno false se lo Ë.
				if(dataOdierna.compareTo(calendar)>=0) return false;
			}

			Date dataDiOggi=new Date();
			//se non ha ritornato false allora vado avanti e creo e registro il prestito
			Prestito p=new Prestito(u1,l1,dataDiOggi);
			//registro nella lista prestiti dell'utente
			u1.getLibriInPrestito().put(l1.getSerialNum(),p);

			//registro nella lista prestiti della biblioteca
			Prestiti.put(u1.getcF()+" "+l1.getSerialNum(),p);
			l1.setCopieDisp(l1.getCopieDisp()-1);//decremento di uno il num di copie disp
			return true;
		}
		return false;	
	}

	//metodo per registrare restituzione
	public boolean inserisciRestituzione(String cF,String titolo,String autore,String serialNumber){

		//inizializzo variabile
		Utente u1=UtentiRegistrati.get(cF);//se Ë presente prendo l'utente dalla lista
		//se Ë presente prendo il libro dalla lista libri
		Libro l1=Libri.get(titolo+" "+autore+" "+serialNumber);
		
		if(u1!=null && l1!=null){
			//entro solo se ho trovato utente e il libro nella biblioteca
			Prestito p=Prestiti.get(u1.getcF()+" "+l1.getSerialNum());
			if(p!=null){//entro solo se ho trovato il prestito
				Prestiti.remove(u1.getcF()+" "+l1.getSerialNum());//rimuovo il prestito dalla biblioteca 
				u1.getLibriInPrestito().remove(l1.getSerialNum());//e poi anche dalla lista prestiti dell'utente
				l1.setCopieDisp(l1.getCopieDisp()+1);
				return true;
			}
			
		}
		return false;	
	}
}

