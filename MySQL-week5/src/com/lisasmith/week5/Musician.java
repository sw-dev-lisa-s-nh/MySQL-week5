/*
 *  Promineo Tech BESD Bootcamp
 *  MySQL Week 5 Coding Assignment
 * 
 *  Musician Entity Class
 * 
 */

package com.lisasmith.week5;

public class Musician {

	private int musician_id;
	private String first_name;
	private String last_name;
	private String instrument1;
	private String instrument2;
	

	/*
	 * Constructor
	 */
	
	public Musician(int musicId, String firstName, String lastName, String instOne, String instTwo) {
		this.musician_id = musicId;
		this.first_name = firstName;
		this.last_name = lastName;
		this.instrument1 = instOne;
		this.instrument2 = instTwo;
	}

	/*
	 * Getters and Setters
	 */

	public int getMusician_id() {
		return musician_id;
	}


	public String getFirst_name() {
		return first_name;
	}


	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}


	public String getLast_name() {
		return last_name;
	}


	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getInstrument1() {
		return instrument1;
	}

	public void setInstrument1(String instrument1) {
		this.instrument1 = instrument1;
	}

	public String getInstrument2() {
		return instrument2;
	}

	public void setInstrument2(String instrument2) {
		this.instrument2 = instrument2;
	}


}
