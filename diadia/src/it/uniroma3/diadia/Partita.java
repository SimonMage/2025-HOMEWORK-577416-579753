package it.uniroma3.diadia;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * Questa classe modella una partita del gioco
 *
 * @author  docente di POO
 * @see Stanza
 * @version base
 */

public class Partita {
    static final private int CFU_INIZIALI = 20;

    private Labirinto labirinto;
    private Stanza stanzaCorrente;
    private boolean finita;
    private Giocatore giocatore; // referenza per Giocatore

    public Partita() {
        this.labirinto = new Labirinto();
        this.stanzaCorrente = labirinto.getStanzaIniziale();
        this.finita = false;
        this.giocatore = new Giocatore(); // inizializza Giocatore
        this.giocatore.setCfu(CFU_INIZIALI); // setta i CFU in Giocatore
    }

    public Stanza getStanzaCorrente() {
        return this.stanzaCorrente;
    }

    public void setStanzaCorrente(Stanza stanzaCorrente) {
        this.stanzaCorrente = stanzaCorrente;
    }

    public boolean vinta() {
        return this.getStanzaCorrente().equals(this.labirinto.getStanzaFinale());
    }

    public boolean isFinita() {
        return finita || vinta() || (this.giocatore.getCfu() == 0); // get CFU dal Giocatore
    }

    public void setFinita() {
        this.finita = true;
    }

    public Giocatore getGiocatore() {
        return this.giocatore; // getter di Giocatore
    }

	public Labirinto getLabirinto() {
		return labirinto;
	}
}