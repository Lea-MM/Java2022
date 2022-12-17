//	Author: Lea Marie Magbalot
// 	Date: September 12, 2022

package exercise1;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class Singers {
	
	// declare variables to hold data regarding a singer
	private int id;
	private String name;
	private String address;
	private LocalDate birthdate;
	private int numOfAlbums;
	
	// create constructors with no, 1, 2, 3, 4, and 5 parameter(s)
	public Singers() {}
	
	public Singers(int id) {
		this.id = id;
	}
	
	public Singers(int id, String name) {
		this.id = id;
		this.name = name == "" ? null : name;
	}
	
	public Singers(int id, String name, String address) {
		this.id = id;
		this.name = name == "" ? null : name;
		this.address = address == "" ? null : address;
	}
	
	public Singers(int id, String name, String address, LocalDate birthdate) {
		this.id = id;
		this.name = name == "" ? null : name;
		this.address = address == "" ? null : address;
		if(birthdate.compareTo(LocalDate.of(1, 1, 1)) == 0) {
			this.birthdate = null;	
		}
		else {
			this.birthdate = birthdate;	
		}
	}
	
	public Singers(int id, String name, String address, LocalDate birthdate, int numOfAlbums) {
		this.id = id;
		this.name = name == "" ? null : name;
		this.address = address == "" ? null : address;
		if(birthdate.compareTo(LocalDate.of(1, 1, 1)) == 0) {
			this.birthdate = null;	
		}
		else {
			this.birthdate = birthdate;	
		}
		this.numOfAlbums = numOfAlbums;
	}
	
	// create individual getters for each variable 
	public int GetId() {
		return id;
	}
	
	public String GetName() {
		return name;
	}
	
	public String GetAddress() {
		return address;
	}
	
	public String GetBirthdate() {
		if(birthdate == null)
			return null;
		return birthdate.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));
	}
	
	public int GetNumOfAlbums() {
		return numOfAlbums;
	}
	

	// create individual setters for each variable
	public void SetId(int id) {
		this.id = id;
	}
	
	public void SetName(String name) {
		this.name = name == "" ? null : name;
	}
	
	public void SetAddress(String address) {
		this.address = address == "" ? null : address;
	}
	
	public void SetBirthdate(LocalDate birthdate) {
		if(birthdate.compareTo(LocalDate.of(1, 1, 1)) == 0) {
			this.birthdate = null;	
		}
		else {
			this.birthdate = birthdate;	
		}
	}
	
	public void SetNumOfAlbums(int numOfAlbums) {
		this.numOfAlbums = numOfAlbums;
	}
	
	// create a setter that accepts multiple values 
	public void SetAllVariables(int id, String name, String address, LocalDate birthdate, int numOfAlbums) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.birthdate = birthdate;
		this.numOfAlbums = numOfAlbums;
	}
	
	// check for valid integer
		public static boolean IsValidIntegerInput(String id) {
			if(Pattern.matches("\\d+", id)) {
				return true;
			}
			else {
				return false;
			}
		}
	
	// check for valid birthdate
	public static boolean IsBirthdateValid(String[] splittedValues) {
		
		boolean isValidYear = false, isValidMonth = false, isValidDay = false;
		
		if(splittedValues.length < 3) {
			return false;
		}
		
		if(splittedValues[0] == "0000" || splittedValues[1] == "00" || splittedValues[2] == "00") {
			return false;
		}
		
		if(splittedValues[0].length()== 4 && Pattern.matches("[0-9]{4}", splittedValues[0])) {
			isValidYear = true;
		}
		
		if(splittedValues[1].length()== 2 && Pattern.matches("[0-9]{2}", splittedValues[1]) && Integer.parseInt(splittedValues[1]) <= 12) {
			isValidMonth = true;
		}
		
		if(splittedValues[2].length()== 2 && Pattern.matches("[0-9]{2}", splittedValues[2]) && Integer.parseInt(splittedValues[2]) <= 31) {
			isValidDay = true;
		}
		
		if(isValidYear && isValidMonth && isValidDay) {
			return true;
		}
		else {
			return false;
		}
	}
}