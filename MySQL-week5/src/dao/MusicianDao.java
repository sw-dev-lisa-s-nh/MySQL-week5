/*
 *  Promineo Tech BESD Bootcamp
 *  MySQL Week 5 Coding Assignment
 * 
 *  MusicianDao Class
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
import entity.Musician;
import entity.Musician_Instrument;

public class MusicianDao {
	
	private Connection connection;
	private InstrumentDao instrumentDao = new InstrumentDao();
	private Musician_InstrumentDao musician_instrumentDao = new Musician_InstrumentDao();
	private final String GET_MUSICIANS_QUERY = "SELECT * FROM musicians";
	private final String GET_MUSICIAN_BY_ID_QUERY = "SELECT * FROM musicians WHERE musician_id = ?";
	private final String GET_MUSICIAN_BY_NAME_QUERY = "SELECT * FROM musicians WHERE first_name = ? AND last_name = ?";
	private final String DELETE_MUSICIAN_BY_NAME_UPDATE = "DELETE FROM musicians WHERE first_name = ? AND last_name = ?";
	private final String DELETE_MUSICIAN_BY_ID_UPDATE = "DELETE FROM musicians WHERE musician_id = ?";
	private final String UPDATE_MUSICIAN_BY_ID_UPDATE = "UPDATE musicians SET first_name = ?,last_name = ? WHERE musician_id = ?";
	private final String GET_MUSICIANS_BY_INSTRUMENT_NAME_QUERY = "SELECT * FROM musicians m INNER JOIN musician_instrument mi USING (musician_id) INNER JOIN instruments i USING (instrument_id) WHERE i.name = ?";
	private final String GET_MUSICIANS_BY_INSTRUMENT_ID_QUERY = "SELECT * FROM musicians m INNER JOIN musician_instrument mi USING (musician_id) INNER JOIN instruments i USING (instrument_id) WHERE i.instrument_id = ?";
	private final String CREATE_NEW_MUSICIAN_UPDATE = "INSERT INTO musicians (first_name,last_name) VALUES (?,?)";

	
	public MusicianDao() {
		connection = DBConnection.getConnection();
	}
	
	
	public List<Musician> getMusicians() throws SQLException {
		PreparedStatement ps = connection.prepareStatement (GET_MUSICIANS_QUERY);
		ResultSet rs = ps.executeQuery();
		List<Musician> musicians = new ArrayList<Musician>();
		while (rs.next()) {
			musicians.add(populateMusician(rs.getInt(1),rs.getString(2),rs.getString(3)));
		}
		return musicians;
	}
	
	public Musician getMusician(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement (GET_MUSICIAN_BY_ID_QUERY);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		rs.next();
		Musician musician = populateMusician(rs.getInt(1),rs.getString(2),rs.getString(3));
		return musician;
	}

	public List<Musician> getMusiciansByInstrument(int instId) throws SQLException {		
		PreparedStatement ps = connection.prepareStatement (GET_MUSICIANS_BY_INSTRUMENT_ID_QUERY);
		ps.setInt(1, instId);	
		ResultSet rs = ps.executeQuery();
		List<Musician> musicians = new ArrayList<Musician>();
		while (rs.next()) {
			musicians.add(populateMusician(rs.getInt(1),rs.getString(2),rs.getString(3)));
		}
		return musicians;
	}
	
	public List<Musician> getMusiciansByInstrumentName(String instName) throws SQLException {		
		PreparedStatement ps = connection.prepareStatement (GET_MUSICIANS_BY_INSTRUMENT_NAME_QUERY);
		ps.setString(1, instName);	
		ResultSet rs = ps.executeQuery();
		List<Musician> musicians = new ArrayList<Musician>();
		while (rs.next()) {
			musicians.add(populateMusician(rs.getInt(2),rs.getString(3),rs.getString(4)));
		}
		return musicians;
	}
	
	public Musician getMusicianByName(String firstName, String lastName) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_MUSICIAN_BY_NAME_QUERY);
		ps.setString(1, firstName);
		ps.setString(2, lastName);
		ResultSet rs = ps.executeQuery();
		rs.next();
		Musician musician = populateMusician(rs.getInt(1),rs.getString(2),rs.getString(3));
		return musician;
	}
	
	
	public void createNewMusician(String firstName, String lastName, String instOne) throws SQLException {				
		/*
		 * Add record to musician table for this musician
		 */
		PreparedStatement ps = connection.prepareStatement(CREATE_NEW_MUSICIAN_UPDATE);		
		ps.setString(1, firstName);
		ps.setString(2, lastName);		
		ps.executeUpdate();		
		/*
		 * Add record to musician_instrument table for each instrument associated with this musician
		 */
		Musician musician = getMusicianByName(firstName,lastName);		
		if (!instOne.equals("")) {		
			Instrument instrument = instrumentDao.getInstrumentByName(instOne);
			if (!(instrument == null)) {
				musician_instrumentDao.createMusicianInstruments(musician.getMusician_id(),instrument.getInstrument_id());	
			} else {
				System.out.println("Instrument Name doesn't exist!");
			}
		}	
	
	}

	public void deleteMusicianByName(String firstName, String lastName) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_MUSICIAN_BY_NAME_QUERY);
		ps.setString(1, firstName);
		ps.setString(2, lastName);	
		ResultSet rs = ps.executeQuery();
		rs.next();
		int deleteId = rs.getInt(1);
		List<Musician_Instrument> music_insts = musician_instrumentDao.getMusicianInstrumentsByMusicianId(deleteId);

		for (Musician_Instrument musician_instrument : music_insts) {			
			musician_instrumentDao.deleteMusicianInstrumentsByMusicianId(musician_instrument.getMusician_id());
		}

		ps = connection.prepareStatement(DELETE_MUSICIAN_BY_NAME_UPDATE);
		ps.setString(1, firstName);
		ps.setString(2, lastName);
		ps.executeUpdate();	

		
	}
	
	public void deleteMusicianById(int deleteId) throws SQLException {
		
		List<Musician_Instrument> music_insts = musician_instrumentDao.getMusicianInstrumentsByMusicianId(deleteId);
		for (Musician_Instrument musician_instrument : music_insts) {			
			musician_instrumentDao.deleteMusicianInstrumentsByMusicianId(musician_instrument.getMusician_id());
		}
			
		PreparedStatement ps = connection.prepareStatement(DELETE_MUSICIAN_BY_ID_UPDATE);
		ps.setInt(1, deleteId);
		ps.executeUpdate();							
	}

	public void updateMusicianById(int id, String firstName, String lastName, String instOne) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(UPDATE_MUSICIAN_BY_ID_UPDATE);
		Musician musician = getMusician(id);
		List<Instrument> new_instruments = new ArrayList<Instrument>(); 
		int counter = 1;
	
		/*
		 * Update the musician's record in the musician table				
		 */
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
		ps.setInt(3, id);
		ps.executeUpdate();		
		
		/*
		 *  Check for records in musician_instruments & delete in reinsert those records?  	 
		 */ 
		List<Instrument> instruments = musician.getInstruments();
		if (!(instruments == null)) {
			for (Instrument instrument : musician.getInstruments()) {			
				if (instOne.equals("")) {
					if (counter == 1) {
						new_instruments.add(instrument);
					}
				} else {
					/* create new instrument object and add it to the list.
					 * (1)  Find the instrument record
					 * (2)  Add it to new_instruments
					 */
					new_instruments.add(instrumentDao.getInstrumentByName(instOne));
					musician_instrumentDao.deleteMusicianInstruments(id,instrument.getInstrument_id());
					musician_instrumentDao.createMusicianInstruments(id,instrumentDao.getInstrumentByName(instOne).getInstrument_id());	
				}	
				counter++;
			}
		} else if (!(instOne.equals(""))) {
			new_instruments.add(instrumentDao.getInstrumentByName(instOne));
			musician_instrumentDao.createMusicianInstruments(id,new_instruments.get(1).getInstrument_id());	
		}	
		musician.setInstruments(new_instruments);	
	}

	
	private Musician populateMusician(int musicId, String firstName, String lastName) throws SQLException {
		return new Musician(musicId,firstName,lastName,instrumentDao.getInstrumentsByMusicianId(musicId));	

	} 
}
