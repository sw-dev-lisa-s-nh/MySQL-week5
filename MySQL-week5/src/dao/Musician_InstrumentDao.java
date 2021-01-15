/*
 *  Promineo Tech BESD Bootcamp
 *  MySQL Week 5 Coding Assignment
 * 
 *  Musician_InstrumentDao Class
 *  JavaSE-1.8
 */

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Musician_Instrument;

public class Musician_InstrumentDao {

	private Connection connection;
	private final String GET_MUSIC_INSTS_BY_MUSICIAN_ID_QUERY = "SELECT * FROM musician_instrument WHERE musician_id = ?";
	private final String GET_MUSIC_INSTS_BY_INSTRUMENT_ID_QUERY = "SELECT * FROM musician_instrument WHERE instrument_id = ?";
	private final String CREATE_NEW_MUSICIAN_INSTRUMENT_UPDATE = "INSERT INTO musician_instrument (musician_id,instrument_id) VALUES (?,?)";
	private final String DELETE_MUSICIAN_INSTRUMENT_UPDATE = "DELETE FROM musician_instrument WHERE musician_id = ? AND instrument_id = ?";
	private final String DELETE_MUSICIAN_INSTRUMENT_BY_MUSICIAN_ID_UPDATE = "DELETE FROM musician_instrument WHERE musician_id = ?";
	
	
	public Musician_InstrumentDao() {
		connection = DBConnection.getConnection();
	}
	
	
	public List<Musician_Instrument> getMusicianInstrumentsByMusicianId(int musicId) throws SQLException {
		PreparedStatement ps = connection.prepareStatement (GET_MUSIC_INSTS_BY_MUSICIAN_ID_QUERY);
		ps.setInt(1, musicId);
		ResultSet rs = ps.executeQuery();
		List<Musician_Instrument> musician_instruments = new ArrayList<Musician_Instrument>();
		while (rs.next()) {
			musician_instruments.add(populateMusicianInstrument(rs.getInt(1),rs.getInt(2)));
		}
		return musician_instruments;
	}
	
	public List<Musician_Instrument> getMusicianInstrumentsByInstrumentId(int instId) throws SQLException {
		PreparedStatement ps = connection.prepareStatement (GET_MUSIC_INSTS_BY_INSTRUMENT_ID_QUERY);
		ps.setInt(1, instId);
		ResultSet rs = ps.executeQuery();
		List<Musician_Instrument> musician_instruments = new ArrayList<Musician_Instrument>();
		while (rs.next()) {
			musician_instruments.add(populateMusicianInstrument(rs.getInt(1),rs.getInt(2)));
		}
		return musician_instruments;
	}
	
	public void createMusicianInstruments(int musicId,int instId) throws SQLException {
		PreparedStatement ps = connection.prepareStatement (CREATE_NEW_MUSICIAN_INSTRUMENT_UPDATE);
		ps.setInt(1, musicId);
		ps.setInt(2, instId);
		ps.executeUpdate();
	}
		
	public void deleteMusicianInstruments(int musicId,int instId) throws SQLException {
		PreparedStatement ps = connection.prepareStatement (DELETE_MUSICIAN_INSTRUMENT_UPDATE);
		ps.setInt(1, musicId);
		ps.setInt(2, instId);
		ps.executeUpdate();
	}
	
	public void deleteMusicianInstrumentsByMusicianId(int musicId) throws SQLException {
		PreparedStatement ps = connection.prepareStatement (DELETE_MUSICIAN_INSTRUMENT_BY_MUSICIAN_ID_UPDATE);
		ps.setInt(1, musicId);
		ps.executeUpdate();
	}
	private Musician_Instrument populateMusicianInstrument(int musicId, int instId) {		
		return new Musician_Instrument(musicId,instId);		
	}
}
