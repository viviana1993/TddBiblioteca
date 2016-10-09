package Biblioteca;

import static org.junit.Assert.*;



import java.util.Map;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)//eseguo i test in ordine alfabetico
public class testBiblioteca {
	//creo oggetto biblioteca di tipo static ,per poterla utilizzare all'interno del metodo @BeforeClass
	static Biblioteca b;
	static Gestione g;

	@BeforeClass
	//per il beforeClass Ë necessario un metodo di tipo static void ,esso viene svolto solo una volta all'inizio della classe
	public static void setUpBeforeClass() {
		b=new Biblioteca("Viviana");
		g=new Gestione();
	}



	@Test
	//test per verificare che gli oggetti biblioteca e gestione sono stati creati
	public void Test1_bibliotecaCreata(){
		assertNotNull(b);
		assertNotNull(g);
	}

	@Test
	//test per verificare che la mappa Libri Ë presente in biblioteca
	public void Test2_mappaLibriCreata(){
		Map <String,Libro> Libri=b.getLibri();
		assertNotNull(Libri);		
	}
	@Test
	//test per verificare che la mappa utentiRegistrati Ë presente in biblioteca

	public void Test3_mappaUtentiCreata(){
		Map <String,Utente> Utenti=b.getUtentiRegistrati();
		assertNotNull(Utenti);		
	}


	@Test
	//test per verificare l'aggiornamento delle copie tot e disponibili se registro un libro gi‡ esistente nella lista
	public void Test4_registraCopie(){
		int lungh=b.getLibri().size();

		Libro l=g.registraCopie(b, "aaa","bbb","ddd33ddd",4);//registro libro
		assertNotNull(l);//verifico che libro non null
		lungh=lungh+1;
		assertEquals(lungh, b.getLibri().size());//verifico di averlo registrato
		lungh=b.getLibri().size();//memorizzo la nuova lunghezza della lista

		Libro l2=g.registraCopie(b,"aaa","bbb","ddd33ddd",7);//richiamo il metodo
		assertEquals(lungh, b.getLibri().size());//verifico di NON aver inserito un altro libro

		assertEquals(11, l2.getCopieDisp());//verifico che siano state aggiornate
		assertEquals(11, l2.getCopieTot());

	}

	@Test
	//test per verificare di inserire il libro se esso non Ë gi‡ presente nella lista

	public void Test5_registrazioneCopie(){
		int lx=b.getLibri().size();//memorizzo lunghezza lista prima di richiamare il metodo
		Libro l=g.registraCopie(b,"sss","kkk","ttt55ttt",1);//registro libro non presente nella lista libri
		lx=lx+1;
		//richiamando il metodo
		assertNotNull(l);//verifico che libro non null
		assertEquals(lx, b.getLibri().size());
		//verifico che la lunghezza della lista cambi dopo aver richiamato il metodo

	}




	@Test
	//test per verificare che un utente NON presente nella lista utenti venga registrato
	public void Test6_registrazioneUtente()throws UtenteGi‡Presente{

		int lu1=b.getUtentiRegistrati().size();//memorizzo lunghezza lista utenti prima del metodo
		try{
			g.registraUtente(b,"Paki","Ruocco","pkkpppp99");//provo a registrare utente
		}
		catch(UtenteGi‡Presente e){//se nn lo registra lancia eccezione
			System.out.println(e.getMessage());
		}
		lu1=lu1+1;
		assertEquals(lu1, b.getUtentiRegistrati().size());//verifica di aver modificato la lunghezza della lista
	}

	@Test
	//test per verificare che un utente presente nella lista utenti NON  venga registrato
	public void Test7_nonRegistrazioneUtente()throws UtenteGi‡Presente{
		try{//provo a registrare dueutenti uguali
			g.registraUtente(b,"vivi","vaci","vvnn55nb");
			g.registraUtente(b,"vivi","vaci","vvnn55nb");
			fail("mi aspettavo un eccezione");//se registra anche il secondo utente allora il test nn va a buon fine
		}
		catch(UtenteGi‡Presente e){//altrimenti cattura eccezione
			System.out.println(e.getMessage());//stampa il messaggio
		}

	}



	@Test
	//test per verificare che un utente REGISTRATO che NON abbia gi‡ 3 libri in prestito possa prendere
	//un libro DISPONIBILE

	public void Test8_registrazionePrestito(){

		boolean b1=g.registraPrestito(b,"pkkpppp99","sss","kkk","ttt55ttt");
		assertTrue(b1);
		//adesso verifico che il num dicopie Ë stato aggiornato
		assertEquals(0,b.getLibri().get("sss kkk ttt55ttt").getCopieDisp());
	}

	@Test
	//test per verificare che utente non registrato non puÚ effettuare un prestito
	public void Test9_noRegistrazionePrestito(){
		int cd=b.getLibri().get("sss kkk ttt55ttt").getCopieDisp();
		boolean b1=g.registraPrestito(b,"ggdd5","sss","kkk","ttt55ttt");
		assertFalse(b1);
		assertEquals(cd,b.getLibri().get("sss kkk ttt55ttt").getCopieDisp());
	}

	@Test
	//test per verificare che utente registrato non puÚ effettuare un prestito di un libro non presente in biblioteca
	public void TestA_noRegistrazionePrestito(){

		boolean b1=g.registraPrestito(b,"pkkpppp99","ssi","kmk","ttt999t");
		assertFalse(b1);

	}
	@Test
	//test per verificare che utente registrato non puÚ effettuare un prestito di un libro non disponibile in biblioteca
	public void TestB_noRegistrazionePrestito(){

		boolean b1=g.registraPrestito(b,"pkkpppp99","sss","kkk","ttt55ttt");
		assertFalse(b1);

	}

	@Test
	//test per verificare che la restituzione viene effettuata
	public void TestC_restituzione(){//utente presente,prestito presente

		int p=b.getPrestiti().size();//lunghezza lista prestiti in biblioteca
		
		boolean r=g.registraRestituzione(b,"pkkpppp99","sss","kkk","ttt55ttt");
		assertTrue(r);//verifico che sia true
		p=p-1;
		assertEquals(p, b.getPrestiti().size());//verifico che Ë stato eliminato un prestito
		assertEquals(0, b.getUtentiRegistrati().get("pkkpppp99").getLibriInPrestito().size());
		//verifico che sia stato aggiornato copie disp
		assertEquals(1,b.getLibri().get("sss kkk ttt55ttt").getCopieDisp());
	}

	@Test
	//test per verificare che non avviene registrazione della restituzione se l'utente non Ë registrato
	public void TestD_restituzione(){//utente presente,prestito presente
		int p=b.getPrestiti().size();
		boolean r=g.registraRestituzione(b,"ttttt7","sss","kkk","ttt55ttt");
		assertFalse(r);
		assertEquals(p, b.getPrestiti().size());
		assertEquals(1,b.getLibri().get("sss kkk ttt55ttt").getCopieDisp());
	}
	
	@Test
	//test per verificare che non avviene registrazione della restituzione se il libro non Ë registrato
	public void TestE_restituzione(){//utente presente,prestito presente
		int p=b.getPrestiti().size();
		boolean r=g.registraRestituzione(b,"pkkpppp99","ssst","k4gkk","tt6666");
		assertFalse(r);
		assertEquals(p, b.getPrestiti().size());
		assertEquals(0, b.getUtentiRegistrati().get("pkkpppp99").getLibriInPrestito().size());
	}
	
	@Test
	//test per verificare che non avviene registrazione della restituzione se il prestito non Ë registrato
	public void TestF_restituzione(){//utente presente,prestito presente
		int p=b.getPrestiti().size();
		boolean r=g.registraRestituzione(b,"pkkpppp99","aaa","bbb","ddd33ddd");
		assertFalse(r);
		assertEquals(p, b.getPrestiti().size());
		
	}
}



