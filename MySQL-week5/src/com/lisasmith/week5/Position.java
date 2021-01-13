package com.lisasmith.week5;

public class Position {

	private int position_id;
	private int instrument_id;
	private int chair;
	private String special_title;
	
	/*
	 * Create a Constructor
	 */
	
	public Position(int posId, int instId, int chair, String title) {
		this.position_id = posId;
		this.instrument_id = instId;
		this.chair = chair;
		this.special_title = title;		
	}

	
	/*
	 * Getters & Setters
	 */
	
	
	public int getPosition_id() {
		return position_id;
	}

	public void setPosition_id(int position_id) {
		this.position_id = position_id;
	}

	public int getInstrument_id() {
		return instrument_id;
	}

	public void setInstrument_id(int instrument_id) {
		this.instrument_id = instrument_id;
	}

	public int getChair() {
		return chair;
	}

	public void setChair(int chair) {
		this.chair = chair;
	}

	public String getSpecial_title() {
		return special_title;
	}

	public void setSpecial_title(String special_title) {
		this.special_title = special_title;
	}
	

}
