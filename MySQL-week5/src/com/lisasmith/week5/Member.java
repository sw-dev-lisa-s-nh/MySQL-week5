package com.lisasmith.week5;

public class Member {

	private int member_id;
	private int instrument_id;
	private int position_id;
	
	
	/*
	 * Create a constructor
	 */
	public Member (int memId, int instId, int posId) {
		this.member_id = memId;
		this.instrument_id = instId;
		this.position_id = posId;
	}


	/*
	 * Getters & Setters
	 */

	
	public int getMember_id() {
		return member_id;
	}


	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}


	public int getInstrument_id() {
		return instrument_id;
	}


	public void setInstrument_id(int instrument_id) {
		this.instrument_id = instrument_id;
	}


	public int getPosition_id() {
		return position_id;
	}


	public void setPosition_id(int position_id) {
		this.position_id = position_id;
	}
	
}


