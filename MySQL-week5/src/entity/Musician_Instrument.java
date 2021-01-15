/*
 *  Promineo Tech BESD Bootcamp
 *  MySQL Week 5 Coding Assignment
 * 
 *  Musician_Instrument Entity Class
 *  JavaSE-1.8
 */

package entity;

public class Musician_Instrument {

	private int musician_id;
	private int instrument_id;
	
	/*
	 * Constructor
	 */
	
	public Musician_Instrument (int musicId, int instId) {
		this.musician_id = musicId;
		this.instrument_id = instId;
	}

	/*
	 * Getters and Setters
	 */
	public int getMusician_id() {
		return musician_id;
	}

	public void setMusician_id(int musician_id) {
		this.musician_id = musician_id;
	}

	public int getInstrument_id() {
		return instrument_id;
	}

	public void setInstrument_id(int instrument_id) {
		this.instrument_id = instrument_id;
	}	
	
}
