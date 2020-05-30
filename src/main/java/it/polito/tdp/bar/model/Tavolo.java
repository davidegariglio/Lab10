package it.polito.tdp.bar.model;

public class Tavolo {

	private int capienza;
	private boolean occupato;
	
	public Tavolo(int capienza, boolean occupato) {
		super();
		this.capienza = capienza;
		this.occupato = occupato;
	}

	public int getCapienza() {
		return capienza;
	}

	public boolean isOccupato() {
		return occupato;
	}

	public void setOccupato(boolean occupato) {
		this.occupato = occupato;
	}
	
	
}
