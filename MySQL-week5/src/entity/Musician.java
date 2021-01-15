/*
 *  Promineo Tech BESD Bootcamp
 *  MySQL Week 5 Coding Assignment
 * 
 *  Musician Entity Class
 *  JavaSE-1.8
 */

package entity;

import java.util.ArrayList;
import java.util.List;

public class Musician {

	private int musician_id;
	private String first_name;
	private String last_name;
	private List<Instrument> instruments = new ArrayList<Instrument>();
	

	/*
	 * Constructor
	 */
	
	public Musician(int musicId, String firstName, String lastName, List<Instrument> instruments) {
		this.musician_id = musicId;
		this.first_name = firstName;
		this.last_name = lastName;
		this.instruments = instruments;

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

	public List<Instrument> getInstruments() {
		return instruments;
	}

	public void setInstruments(List<Instrument> instruments) {
		this.instruments = instruments;
	}


}
