package Biblioteca;

import java.util.Calendar;
import java.util.Date;

public class Prestito {
	private Utente u;
	private Libro l;
	private Date dataPrest;
	private Date dataScad;
	
	
	public Prestito() {
		super();
	}


	public Prestito(Utente u, Libro l, Date dataPrest) {
		super();
		this.u = u;
		this.l = l;
		this.dataPrest = dataPrest;
		calcolaScadenza(dataPrest);
		
	}


	public Utente getU() {
		return u;
	}


	public void setU(Utente u) {
		this.u = u;
	}


	public Libro getL() {
		return l;
	}


	public void setL(Libro l) {
		this.l = l;
	}


	public Date getDataPrest() {
		return dataPrest;
	}


	public void setDataPrest(Date dataPrest) {
		this.dataPrest = dataPrest;
	}


	public Date getDataScad() {
		return dataScad;
	}


	public void setDataScad(Date dataScad) {
		this.dataScad = dataScad;
	}
	
	//creo metodo per calcolare scadenza prestito
	
	private Date calcolaScadenza(Date dataPrest){
		
		Calendar data = Calendar.getInstance();//inizializzo una variaboile calendar
		data.setTime(dataPrest);//passo la data in cui è stato effettuato il prestito
		data.add(Calendar.DATE, 14);//aggiungo 14 giorni
		return dataScad;//ritorno la scadenza
	}
	
}
