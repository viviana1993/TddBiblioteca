package Biblioteca;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

public class testPrestito {

	@Test
	public void prestitoRegistrato() {
		Utente u=new Utente("Viviana","Vacirca","vcrvvn93");//creo l'utente
		assertNotNull(u);
		Libro l=new Libro("aaaa","bbb","ddd33ddd",5,7);//creo libro
		assertNotNull(l);
		Date data=new Date();//data odierna
		Prestito p=new Prestito(u,l,data);
		assertNotNull(p);
	}

}
