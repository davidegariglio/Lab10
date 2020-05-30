package it.polito.tdp.bar.model;

import java.time.LocalDateTime;

public class EventoUscitaClienti extends Event{
		
	private Tavolo tavolo;
	
	public EventoUscitaClienti(EventoArrivoClienti evento, Tavolo tavolo) {
		super(evento.getTime());
		this.tavolo = tavolo;
	}

	public LocalDateTime getTime() {
		return super.getTime();
	}
	
	public Tavolo getTavolo() {
		return this.tavolo;
	}
	
	public String toString() {
		return "EVENTO USCITA alle ore: "+ super.getTime();
	}

}
