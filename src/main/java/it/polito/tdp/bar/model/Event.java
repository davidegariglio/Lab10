package it.polito.tdp.bar.model;

import java.time.LocalDateTime;

public class Event implements Comparable<Event>{

	private LocalDateTime time;
	
	public Event(LocalDateTime time) {
		super();
		this.time = time;
	}

	@Override
	public int compareTo(Event other) {
		return this.time.compareTo(other.getTime());
	}

	public LocalDateTime getTime() {
		return time;
	}

}
