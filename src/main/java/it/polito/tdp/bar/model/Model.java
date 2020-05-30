package it.polito.tdp.bar.model;

public class Model {
	
	private Simulator s;
	
	public void simula() {
		this.s = new Simulator();
		s.run();
	}
	
	public int getTotClienti() {
		return s.getClienti();
	}
	
	public int getSoddisfatti() {
		return s.getSoddisfatti();
	}
	
	public int getInsoddisfatti() {
		return s.getInsoddisfatti();
	}

}
