/*
 *  Promineo Tech BESD Bootcamp
 *  MySQL Week 5 Coding Assignment
 * 
 *  MusicianDao Class
 * 
 */


package com.lisasmith.week5;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MusicianDao {
	
	private Connection connection;
	private final String GET_MUSICIANS_QUERY = "SELECT * FROM musicians";
	private final String GET_MUSICIANS_BY_INSTRUMENT_QUERY = "SELECT * FROM musicians WHERE instrument1 = ? OR instrument2 = ?";
	private final String GET_MUSICIAN_BY_ID_QUERY = "SELECT * FROM musicians WHERE musician_id = ?";
	private final String CREATE_NEW_MUSICIAN_UPDATE = "INSERT INTO musicians (first_name,last_name,instrument1,instrument2) VALUES (?,?,?,?)";
	private final String DELETE_MUSICIAN_BY_NAME_UPDATE = "DELETE FROM musicians WHERE first_name = ? AND last_name = ?";
	private final String DELETE_MUSICIAN_BY_ID_UPDATE = "DELETE FROM musicians WHERE musician_id = ?";
	private final String UPDATE_MUSICIAN_BY_ID_UPDATE = "UPDATE musicians SET first_name = ?,last_name = ?,instrument1 = ?,instrument2=? WHERE musician_id = ?";

	
	public MusicianDao() {
		connection = DBConnection.getConnection();
		/*
		 * Maybe add an: instrumentDao = new InstrumentDao(); -- If I change the database for that...
		 */
	}
	
	
	public List<Musician> getMusicians() throws SQLException {
		PreparedStatement ps = connection.prepareStatement (GET_MUSICIANS_QUERY);
		ResultSet rs = ps.executeQuery();
		List<Musician> musicians = new ArrayList<Musician>();
		while (rs.next()) {
			musicians.add(populateMusician(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getString(4),rs.getString(5)));
		}
		return musicians;
	}
	
	public Musician getMusician(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement (GET_MUSICIAN_BY_ID_QUERY);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		rs.next();
		Musician musician = populateMusician(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getString(4),rs.getString(5));
		return musician;
	}

	public List<Musician> getMusiciansByInstrument(String instrument) throws SQLException {
		PreparedStatement ps = connection.prepareStatement (GET_MUSICIANS_BY_INSTRUMENT_QUERY);
		ps.setString(1, instrument);
		ps.setString(2, instrument);		
		ResultSet rs = ps.executeQuery();
		List<Musician> musicians = new ArrayList<Musician>();
		while (rs.next()) {
			musicians.add(populateMusician(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getString(4),rs.getString(5)));
		}
		return musicians;
	}
	
	public void createNewMusician(String firstName, String lastName, String instOne, String instTwo) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(CREATE_NEW_MUSICIAN_UPDATE);
		ps.setString(1, firstName);
		ps.setString(2, lastName);
		ps.setString(3, instOne);
		ps.setString(4, instTwo);
		ps.executeUpdate();			
	}

	public void deleteMusicianByName(String firstName, String lastName) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(DELETE_MUSICIAN_BY_NAME_UPDATE);
		ps.setString(1, firstName);
		ps.setString(2, lastName);
		ps.executeUpdate();							
	}
	
	public void deleteMusicianById(int deleteId) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(DELETE_MUSICIAN_BY_ID_UPDATE);
		ps.setInt(1, deleteId);
		ps.executeUpdate();							
	}

	public void updateMusicianById(int id, String firstName, String lastName, String instOne, String instTwo) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(UPDATE_MUSICIAN_BY_ID_UPDATE);
		Musician musician = getMusician(id);
		if (firstName.equals("")) {
			ps.setString(1, musician.getFirst_name());
		} else {
			ps.setString(1, firstName);
		}
		
		if (lastName.equals("")) {
			ps.setString(2, musician.getLast_name());
		} else {
			ps.setString(2, lastName);
		}
		
		if (instOne.equals("")) {
			ps.setString(3, musician.getInstrument1());
		} else {
			ps.setString(3, instOne);
		}
		if (instTwo.equals("")) {
			ps.setString(4, musician.getInstrument2());
		} else {
			ps.setString(4, instTwo);
		}
		ps.setInt(5, id);
		ps.executeUpdate();			
	}

	
	private Musician populateMusician(int musicId, String firstName, String lastName, String instOne, String instTwo) {
		return new Musician(musicId,firstName,lastName,instOne,instTwo);

	} 
}
