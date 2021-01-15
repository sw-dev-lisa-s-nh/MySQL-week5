/*
 *  Promineo Tech BESD Bootcamp
 *  MySQL Week 5 Coding Assignment
 * 
 *  Instrument Entity Class
 *  JavaSE-1.8
 */

package entity;

public class Instrument {


	private int instrument_id;
	private String section;
	private String name;
	
	/*
	 * Create a Constructor
	 */
	
	public Instrument(int instId, String sectionName, String instName) {
		this.instrument_id = instId;
		this.section = sectionName;
		this.name = instName;
	}
	
	/*
	 * Getters & Setters
	 */
	
	public int getInstrument_id() {
		return instrument_id;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
