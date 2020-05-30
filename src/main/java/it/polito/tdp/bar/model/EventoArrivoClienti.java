package it.polito.tdp.bar.model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Random;
 

public class EventoArrivoClienti extends Event{
	
	private int nPersone;
	private Duration durata;
	private double toll;
	private Random r;

	public EventoArrivoClienti(LocalDateTime time) {
		super(time);
		this.r = new Random();
		this.nPersone = r.nextInt(10)+1; //Ritorna un numero tra 1 e 10 compreso
		this.durata = Duration.ofMinutes(r.nextInt(61) + 60);	//Genero un num tra 0 e 60 e gli aggiungo 60
		this.toll = (double)(r.nextInt(9)+1)/(double)10;	//Genero num da 1 a 9 e divido per 10
	}

	public LocalDateTime getTime() {
		return super.getTime();
	}

	public int getnPersone() {
		return nPersone;
	}

	public Duration getDurata() {
		return durata;
	}

	public double getToll() {
		return toll;
	}
	
	public String toString() {
		return "EVENTO ARRIVO: "+this.nPersone+"persone alle ore: " + super.getTime() + "per :"+this.durata;
	}

}
