package it.uniroma3.diadia.comandi;

import java.util.Scanner;

import it.uniroma3.diadia.IO;

public class FabbricaDiComandiRiflessiva implements FabbricaDiComandi {
	
	private IO io;
	public FabbricaDiComandiRiflessiva(IO io) {
		this.io = io;
	}
	
	@SuppressWarnings("deprecation")
	public Comando costruisciComando(String istruzione) throws Exception {
		try (Scanner scannerDiParole = new Scanner(istruzione)) {
			String nomeComando = null; // es. ‘vai’
			String parametro = null; // es. ‘sud’
			Comando comando = null;
			
			if (scannerDiParole.hasNext())
				nomeComando = scannerDiParole.next();//prima parola: nome del comando
			if (scannerDiParole.hasNext())
				parametro = scannerDiParole.next();//seconda parola: eventuale parametro
			
			StringBuilder nomeClasse
			= new StringBuilder("it.uniroma3.diadia.comandi.Comando");
			nomeClasse.append( Character.toUpperCase(nomeComando.charAt(0)) );
			// es. nomeClasse: ‘it.uniroma3.diadia.comandi.ComandoV’
			nomeClasse.append( nomeComando.substring(1) ) ;
			// es. nomeClasse: ‘it.uniroma3.diadia.comandi.ComandoVai’
			comando = (Comando)Class.forName(nomeClasse.toString()).newInstance();
			comando.setParametro(parametro);
			comando.setIo(io);
			return comando;
		}
	}	
}