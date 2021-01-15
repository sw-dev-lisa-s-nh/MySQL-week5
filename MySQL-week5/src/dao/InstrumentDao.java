/*
 *  Promineo Tech BESD Bootcamp
 *  MySQL Week 5 Coding Assignment
 * 
 *  InstrumentDao Class
 *  JavaSE-1.8
 */

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Instrument;

public class InstrumentDao {
	private Connection connection;
	private final String GET_INSTRUMENTS_QUERY = "SELECT * FROM instruments";
	private final String GET_INSTRUMENT_BY_ID_QUERY = "SELECT * FROM instruments WHERE instrument_id = ?";
	private final String GET_INSTRUMENT_BY_NAME_QUERY = "SELECT * FROM instruments WHERE name = ?";
	private final String GET_INSTRUMENTS_BY_MUSICIAN_ID_QUERY = "SELECT * from instruments i INNER JOIN musician_instrument mi USING (instrument_id) WHERE mi.musician_id = ?";
	private final String CREATE_NEW_INSTRUMENT_UPDATE = "INSERT INTO instruments (section, name) VALUES (?,?)";
	private final String DELETE_INSTRUMENT_BY_NAME_UPDATE = "DELETE FROM instruments WHERE name = ?";
	private final String UPDATE_INSTRUMENT_BY_ID_UPDATE = "UPDATE instruments SET section = ?, name = ? WHERE instrument_id = ?";

	public InstrumentDao() {
		connection = DBConnection.getConnection();
	}
	
	public List<Instrument> getInstruments() throws SQLException {
		PreparedStatement ps = connection.prepareStatement (GET_INSTRUMENTS_QUERY);
		ResultSet rs = ps.executeQuery();
		List<Instrument> instruments = new ArrayList<Instrument>();
		while (rs.next()) {
			instruments.add(populateInstrument(rs.getInt(1),rs.getString(2),rs.getString(3)));
		}
		return instruments;
	}
	
	public Instrument getInstrumentById(int id) throws SQLException {
		Instrument instrument = null;
		PreparedStatement ps = connection.prepareStatement(GET_INSTRUMENT_BY_ID_QUERY);
		ps.setInt(1,id);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			instrument = populateInstrument(rs.getInt(1),rs.getString(2),rs.getString(3));
		} 
		return instrument;
	}
	
	public Instrument getInstrumentByName(String name) throws SQLException {
		Instrument instrument = null;
		PreparedStatement ps = connection.prepareStatement(GET_INSTRUMENT_BY_NAME_QUERY);
		ps.setString(1,name);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			instrument = populateInstrument(rs.getInt(1),rs.getString(2),rs.getString(3));
		} 
		return instrument;
	}
	
	public List<Instrument> getInstrumentsByMusicianId(int musicId) throws SQLException {
		PreparedStatement ps = connection.prepareStatement (GET_INSTRUMENTS_BY_MUSICIAN_ID_QUERY);
		ps.setInt(1, musicId);
		ResultSet rs = ps.executeQuery();
		List<Instrument> instruments = new ArrayList<Instrument>();
		while (rs.next()) {
			instruments.add(populateInstrument(rs.getInt(1),rs.getString(2),rs.getString(3)));
		}
		return instruments;
	}
	
	
	public void createNewInstrument(String sectName, String instName) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(CREATE_NEW_INSTRUMENT_UPDATE);
		ps.setString(1, sectName);
		ps.setString(2, instName);
		ps.executeUpdate();
	}
	
	public void deleteInstrument(String deleteName) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(DELETE_INSTRUMENT_BY_NAME_UPDATE);
		ps.setString(1, deleteName);
		ps.executeUpdate();
	}
	
	public void updateInstrument(int updateId, String section, String name) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(UPDATE_INSTRUMENT_BY_ID_UPDATE);
		Instrument instrument = getInstrumentById(updateId);
		if (section.equals("")) {
			ps.setString(1, instrument.getSection());
		} else {
			ps.setString(1, section);
		}		
		if (name.equals("")) {
			ps.setString(2, instrument.getName());
		} else {
			ps.setString(2, name);
		}
		ps.setInt(3, updateId);
		ps.executeUpdate();
	}
	
	private Instrument populateInstrument(int instId, String sectName, String instName) {
		return new Instrument(instId,sectName, instName);
		
	}
	
}
