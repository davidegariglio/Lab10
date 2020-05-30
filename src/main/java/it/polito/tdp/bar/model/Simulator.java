package it.polito.tdp.bar.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

public class Simulator {
	
	//Coda degli eventi
	private PriorityQueue<Event> queue = new PriorityQueue<>();
	
	//Parametri di simulazione
	private Random r = new Random();
	private List<Tavolo> tavoli = new ArrayList<>();
	private LocalDateTime apertura = LocalDateTime.of(2020, 1, 1, 8, 00);
	private int numeroEventi = 2000;
	private double tollBancone = 0.2;
	//Modello del mondo
	//Tavoli disponibili
	
	//Valori da calcolare
	private int totClienti;
	private int soddisfatti;
	private int insoddisfatti;
	
	//Metodi per impostare i parametri
	//Inizializziamoli comunque
	
	public void setTavoli() {
		this.tavoli.add(new Tavolo(10, false));
		this.tavoli.add(new Tavolo(10, false));
		this.tavoli.add(new Tavolo(8, false));
		this.tavoli.add(new Tavolo(8, false));
		this.tavoli.add(new Tavolo(8, false));
		this.tavoli.add(new Tavolo(8, false));
		this.tavoli.add(new Tavolo(6, false));
		this.tavoli.add(new Tavolo(6, false));
		this.tavoli.add(new Tavolo(6, false));
		this.tavoli.add(new Tavolo(6, false));
		this.tavoli.add(new Tavolo(4, false));
		this.tavoli.add(new Tavolo(4, false));
		this.tavoli.add(new Tavolo(4, false));
		this.tavoli.add(new Tavolo(4, false));
		this.tavoli.add(new Tavolo(4, false));
	}
	
	//Metodi per restituire i risultati
	public int getClienti() {
		return this.totClienti;
	}
	
	public int getSoddisfatti() {
		return this.soddisfatti;
	}
	
	public int getInsoddisfatti() {
		return this.insoddisfatti;
	}
	
	//Simulaione vera e propria
	
	public void run() {
		this.totClienti = 0;
		this.soddisfatti = 0;
		this.insoddisfatti = 0;
		
		this.queue.clear();
		LocalDateTime arrivoGruppo = this.apertura;
		do {
			//Aggiunta evento alla coda
			EventoArrivoClienti arr = new EventoArrivoClienti(arrivoGruppo);
			this.queue.add(arr);
			//prossimo arrivo tra 1,2, ..., 10 mins
			arrivoGruppo = arrivoGruppo.plus(r.nextInt(10)+1, ChronoUnit.MINUTES);
		}
		while(this.queue.size() < this.numeroEventi);
		
		//Esecuzione ciclo di simulazione
		while(!this.queue.isEmpty()) {
			//Estraggo e li analizzo
			Event e = this.queue.poll();
			System.out.println(e);
			processEvent(e);
		}
	}

	private void processEvent(Event e) {
		if(e instanceof EventoArrivoClienti) {
			Tavolo t = cercaTavoloDisponibile(((EventoArrivoClienti) e).getnPersone());
			//Se ho trovato un tavolo disponibile aggiorno stats, occupo il tavolo
			//e creo evento uscita associato a quel tavolo
			if(t != null) {
				this.totClienti += ((EventoArrivoClienti) e).getnPersone();
				this.soddisfatti += ((EventoArrivoClienti) e).getnPersone();
				t.setOccupato(true);
				this.queue.add(new EventoUscitaClienti((EventoArrivoClienti) e, t));
			}
			//Se t == null non esiste un tavolo che possa ospitarli, allora provo
			//a metterli al bancone e vedo se sono soddisfatti
			else {
				//Confronto la tollBancone con la toll dell'evento
				if(tollBancone <= ((EventoArrivoClienti) e).getToll()) {
					//Sono soddisfatti e si mettono al bancone -> Aggiorno solo stats
					this.totClienti += ((EventoArrivoClienti) e).getnPersone();
					this.soddisfatti += ((EventoArrivoClienti) e).getnPersone();
				}
				//Se la toll non è sufficiente, se ne vanno insoddisfatti
				else {
					this.totClienti += ((EventoArrivoClienti) e).getnPersone();
					this.insoddisfatti += ((EventoArrivoClienti) e).getnPersone();
				}
			}
		}
		
		if(e instanceof EventoUscitaClienti) {
			//Quando sei clienti che erano seduti se ne vanno libero il tavolo
			Tavolo t = ((EventoUscitaClienti)e).getTavolo();
			t.setOccupato(false);
		}
	}

	private Tavolo cercaTavoloDisponibile(int n) {
		for(Tavolo t : this.tavoli) {
			if(t.isOccupato() == false && t.getCapienza() <= n && 2*n >= t.getCapienza()) {
				return t;
			}
		}
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
