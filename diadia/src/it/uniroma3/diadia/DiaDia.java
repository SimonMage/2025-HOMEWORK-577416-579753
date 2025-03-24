package it.uniroma3.diadia;

//import java.util.Scanner;

import IOConsole.IOConsole;
import it.uniroma3.attrezzi.Attrezzo;
import it.uniroma3.diadia.ambienti.Stanza;

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""
			+ "Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n"
			+ "Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"
			+ "I locali sono popolati da strani personaggi, " + "alcuni amici, altri... chissa!\n"
			+ "Ci sono attrezzi che potrebbero servirti nell'impresa:\n"
			+ "puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n"
			+ "o regalarli se pensi che possano ingraziarti qualcuno.\n\n"
			+ "Per conoscere le istruzioni usa il comando 'aiuto'.";

	static final private String[] elencoComandi = { "vai", "aiuto", "fine", "prendi", "posa" };

	private Partita partita;
	private IOConsole io; // ROBA PER INPUT OUTPUT

	public DiaDia() {
		this.partita = new Partita();
		this.io = new IOConsole(); // ROBA PER INPUT OUTPUT
	}

	public void gioca() {
		String istruzione;

		io.mostraMessaggio(MESSAGGIO_BENVENUTO); // ROBA PER INPUT OUTPUT
		do
			istruzione = io.leggiRiga(); // ROBA PER INPUT OUTPUT
		while (!processaIstruzione(istruzione));
	}

	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire = new Comando(istruzione);

		if (comandoDaEseguire.getNome().equals("fine")) {
			this.fine();
			return true;
		} else if (comandoDaEseguire.getNome().equals("vai"))
			this.vai(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("prendi"))
			this.prendi(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("posa"))
			this.posa(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("aiuto"))
			this.aiuto();
		else
			io.mostraMessaggio("ERRORE: Comando inserito non corretto!!!");
		if (this.partita.vinta()) {
			io.mostraMessaggio("CONGRATULAZIONI, HAI VINTO LA PARTITA!");
			return true;
		} else
			return false;
	}

	private void aiuto() {
		for (int i = 0; i < elencoComandi.length; i++)
			io.mostraMessaggio(elencoComandi[i] + " ");
		io.mostraMessaggio(" ");
	}

	private void vai(String direzione) {
		if (direzione == null)
			io.mostraMessaggio("Dove vuoi andare ?");
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			io.mostraMessaggio("ERRORE: La direzione che hai inserito non esiste!");
		else {
			this.partita.setStanzaCorrente(prossimaStanza);
			int cfu = this.partita.getGiocatore().getCfu();
			this.partita.getGiocatore().setCfu(--cfu);
		}
		io.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
	}

	private void prendi(String nomeAttrezzo) {
		if (nomeAttrezzo == null)
			io.mostraMessaggio("Quale attrezzo vuoi prendere?");
		else {
			Attrezzo attrezzo = this.partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo);
			if (attrezzo == null)
				io.mostraMessaggio("ERRORE: L'attrezzo che hai inserito non esiste nella stanza!");
			else {
				if (this.partita.getGiocatore().prendereAttrezzo(attrezzo)) {
					this.partita.getStanzaCorrente().removeAttrezzo(attrezzo);
					io.mostraMessaggio("Hai preso: " + nomeAttrezzo);
				} else
					io.mostraMessaggio("ERRORE: Non puoi prendere l'attrezzo!");
			}
		}
	}

	private void posa(String nomeAttrezzo) {
		if (nomeAttrezzo == null)
			io.mostraMessaggio("Quale attrezzo vuoi posare?");
		else {
			Attrezzo attrezzo = this.partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);
			if (attrezzo == null)
				io.mostraMessaggio("ERRORE: L'attrezzo che hai inserito non esiste nella borsa!");
			else {
				this.partita.getGiocatore().posareAttrezzo(nomeAttrezzo);
				this.partita.getStanzaCorrente().addAttrezzo(attrezzo);
				io.mostraMessaggio("Hai posato: " + nomeAttrezzo);
			}
		}
	}

	private void fine() {
		io.mostraMessaggio("Grazie per aver giocato al DiaDia!"); // si desidera smettere
	}

	public static void main(String[] argc) {
		// IOConsole io = new IOConsole();
		DiaDia gioco = new DiaDia();
		gioco.gioca();
	}
}