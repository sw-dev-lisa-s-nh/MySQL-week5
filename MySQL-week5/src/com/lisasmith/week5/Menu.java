/*
 *  Promineo Tech BESD Bootcamp
 *  MySQL Week 5 Coding Assignment
 * 
 *  Menu Class
 * 
 */


package com.lisasmith.week5;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Menu {
	
	private final String DATABASE_NAME = "orchestra";
	private InstrumentDao instrumentDao = new InstrumentDao();
	private MusicianDao musicianDao = new MusicianDao();
	private Scanner scanner = new Scanner(System.in);
	private List<String> options = Arrays.asList("Display all Instruments",
												 "Display an Instrument", 
												 "Add an Instrument", 
												 "Delete an Instrument",
												 "Update an Instrument's Record",
												 "Display all Musicians",
												 "Display Musicians by Instrument",
												 "Add a Musician",
												 "Delete a Musician",
												 "Update a Musician's Record");

	
	public void start() {
			
		String selection = "";
		
		do {
			printMenu();
			selection = scanner.nextLine();
			
			try {
				if (selection.equals("1")) {
					System.out.println();
					System.out.println("\tDisplay all Instruments...");
					System.out.println();
					displayInstruments();
				} else if (selection.equals("2")) {
					System.out.println();
					System.out.println("\tDisplay one Instrument...");
					System.out.println();
					displayInstrument();
				} else if (selection.equals("3")) {
					System.out.println();
					System.out.println("\tCreate an Instrument...");
					System.out.println();
					createInstrument();
				} else if (selection.equals("4")) {
					System.out.println();
					System.out.println("\tDelete an Instrument...");
					System.out.println();
					deleteInstrument();
				} else if (selection.equals("5")) {
					System.out.println();
					System.out.println("\tUpdate an Instrument...");
					System.out.println();
					updateInstrument();		
				} else if (selection.equals("6")) {
					System.out.println();
					System.out.println("\tDisplay all Musicians...");
					System.out.println();
					displayMusicians();
				} else if (selection.equals("7")) {
					System.out.println();
					System.out.println("\tDisplay Musicians By Instrument...");
					System.out.println();
					displayMusiciansByInstrument();
				} else if (selection.equals("8")) {
					System.out.println();
					System.out.println("\tAdd a Musician...");
					System.out.println();
					addMusician();		
				} else if (selection.equals("9")) {
					System.out.println();
					System.out.println("\tDelete a Musician...");
					System.out.println();
					deleteMusician();
				} else if (selection.equals("10")) {
					System.out.println();
					System.out.println("\tUpdate a Musician...");
					System.out.println();
					updateMusician();
				} else {
					
				} 
			} catch (SQLException e) {
				e.printStackTrace();		
			}
			/*
			 * Add a pause into the console output before displaying the menu again,
			 * 		unless the user has entered "-1" to exit the program!
			 */
			if (!selection.equals("-1")) {
				System.out.println("\nPress <enter> to continue...");
				scanner.nextLine();
			}		
			
		} while (!selection.equals("-1"));
		
		/*
		 * Exit Program message for the user.
		 */
		System.out.println("\nThank you for using the " + DATABASE_NAME + " database.");
		System.out.println("Program ended!");
		scanner.nextLine();
	}
	
	
	/*
	 * printMenu() method
	 */
	public void printMenu() {
		System.out.println();
		System.out.println("Select an option -- requests on the " + DATABASE_NAME + " database:\n-------------------------------------------------------");
		for (int i = 0; i<options.size(); i++) {
			System.out.println(" "+(i+1) + ") " + options.get(i));
		}		
		System.out.println("-1) Exit Program");
		System.out.println("-------------------------------------------------------");
	}
	
	/*
	 * Methods on the Instruments table
	 * 
	 * 
	 * displayInstruments() method
	 */	
	private void displayInstruments() throws SQLException {
		List<Instrument> instruments = instrumentDao.getInstruments();
		for (Instrument instrument : instruments) {
			System.out.println(instrument.getInstrument_id() + ": " + instrument.getName() + "\n\tSection: " + instrument.getSection());
		}		
	}
	
	/*
	 * displayInstrument() method
	 */
	private void displayInstrument() throws SQLException {
		System.out.print("Enter Instrument Id to Display: ");
		int id = Integer.parseInt(scanner.nextLine());
		Instrument instrument = instrumentDao.getInstrumentById(id);
		System.out.println("Id: " + instrument.getInstrument_id() + ": " + instrument.getName() + " \n\tSection: " + instrument.getSection());		
	}
	
	/*
	 * createInstrument() method
	 */
	private void createInstrument() throws SQLException {
		System.out.print("Enter Orchestra Section for New Instrument: ");
		String section = scanner.nextLine();
		System.out.print("Enter New Instrument Name: ");
		String name = scanner.nextLine();
		instrumentDao.createNewInstrument(section, name);	
	}
	
	/*
	 * deleteInstrument() method
	 */
	private void deleteInstrument() throws SQLException {
		System.out.print("Enter Instrument Name to Delete: ");
		String deleteName = scanner.nextLine();
		instrumentDao.deleteInstrument(deleteName);	
	}
	
	/*
	 * updateInstrument() method
	 */
	private void updateInstrument() throws SQLException {
		System.out.print("Enter Instrument Id to Update: ");
		int updateId = Integer.parseInt(scanner.nextLine());
		System.out.println();
		System.out.println("Updating This Instrument...");
		Instrument instrument = instrumentDao.getInstrumentById(updateId);
		System.out.println("Id: " + instrument.getInstrument_id() + ": " + instrument.getName() + " \n\tSection: " + instrument.getSection());						
		System.out.println();
			
		System.out.println("Simply press <enter> if the answer is \"no\"");
		System.out.print("If updating Orchestra Section, enter New Section Name: ");
		String section = scanner.nextLine();
		System.out.print("If updating Instrument Name, enter New Instrument Name: ");
		String instName = scanner.nextLine();	
		
		instrumentDao.updateInstrument(updateId,section,instName);	
		System.out.println("\nUpdated Instrument Id: " + updateId);
		instrument = instrumentDao.getInstrumentById(updateId);
		System.out.println("Id: " + instrument.getInstrument_id() + ": " + instrument.getName() + " \n\tSection: " + instrument.getSection());						
		System.out.println();
	}
	
	
	/*
	 * Methods on the Musicians Table
	 *
	 *
	 * displayMusicians() method
	 */
	private void displayMusicians() throws SQLException {
		List<Musician> musicians = musicianDao.getMusicians();
		for (Musician musician : musicians) {
			displayMusician(musician.getMusician_id());
		}
	}
			
	/*
	 * displayMusician() method
	 */
	private void displayMusician(int id) throws SQLException {		
		Musician musician = musicianDao.getMusician(id);
		StringBuilder lastInst = new StringBuilder();
		if (musician.getInstrument2().equals("")) {
			lastInst.append("");
		} else {
			lastInst.append(" & ");
			lastInst.append(musician.getInstrument2());			
		}
		System.out.println("Id: " + musician.getMusician_id() + " Name: " + musician.getFirst_name() + " " 
						+ musician.getLast_name() + "\n\tInstrument(s): " + musician.getInstrument1() + " " 
						+ lastInst.toString());	
	}
	
	/*
	 * displayMusiciansByInstrument() method
	 */
	private void displayMusiciansByInstrument() throws SQLException {
		System.out.print("Enter Instrument Name: ");
		String instName = scanner.nextLine();
		List<Musician> musicians  = musicianDao.getMusiciansByInstrument(instName);
		for (Musician musician : musicians) {
			System.out.println("\t\tId: " + musician.getMusician_id() + " Name: " 
									      + musician.getFirst_name() + " " + musician.getLast_name());	
		}
	}
	
	/*
	 * addMusician() method
	 */
	private void addMusician() throws SQLException {
		System.out.print("Enter First Name: ");
		String first_name = scanner.nextLine();
		System.out.print("Enter Last Name: ");
		String last_name = scanner.nextLine();
		System.out.print("Enter Primary Instrument Name: ");
		String instOne = scanner.nextLine();
		System.out.print("Enter Secondary Instrument Name: ");
		String instTwo = scanner.nextLine();		
		musicianDao.createNewMusician(first_name,last_name,instOne,instTwo);
	}
	
	/*
	 * deleteMusician() method
	 */
	private void deleteMusician() throws SQLException {
		System.out.print("Enter Id to Delete: ");
		int deleteId = Integer.parseInt(scanner.nextLine());
		System.out.println("Deleting Musician...");
		displayMusician(deleteId);
		System.out.println();
		System.out.print("Would you like to proceed, yes or no?  ");
		String response = scanner.nextLine();
		if (response.equalsIgnoreCase("yes")) {
			musicianDao.deleteMusicianById(deleteId);	
		} else {
			System.out.println("Delete Musician not performed!");
		}
		
	}
	/*
	 * updateMusician() method
	 */
	private void updateMusician() throws SQLException {
		System.out.print("Enter Musician Id you want to update:");	
		int id = Integer.parseInt(scanner.nextLine());
		System.out.println();
		System.out.println("Updating This Musician...");
		displayMusician(id);
		System.out.println();
		System.out.println("Simply <return> if the answer is \"no\"");
		System.out.print("If updating First Name, enter New First Name: ");
		String first_name = scanner.nextLine();
		System.out.print("If updating Last Name, enter New Last Name: ");
		String last_name = scanner.nextLine();
		System.out.print("If updating Primary Instrument, enter New Primary Instrument Name: ");
		String instOne = scanner.nextLine();
		System.out.print("If updating Secondary Instrument, enter New Secondary Instrument Name: ");
		String instTwo = scanner.nextLine();						
		musicianDao.updateMusicianById(id,first_name,last_name,instOne,instTwo);
		System.out.println("\nUpdated Musician Id: " + id);
		displayMusician(id);
	}



}


