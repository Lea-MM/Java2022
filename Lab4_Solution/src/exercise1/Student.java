package exercise1;

import java.util.ArrayList;
import java.util.List;

public class Student {
	private String name;
	private String address;
	private String province;
	private String city;
	private String postalCode;
	private String phone;
	private String email;
	private String program;
	private Boolean isStudentCouncil;
	private Boolean isVolunteer;
	private List<String> courses = new ArrayList<String>();
	
	public Student() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProgram() {
		return program;
	}

	public void setProgram(String program) {
		this.program = program;
	}

	public Boolean getIsStudentCouncil() {
		return isStudentCouncil;
	}

	public void setIsStudentCouncil(Boolean isStudentCouncil) {
		this.isStudentCouncil = isStudentCouncil;
	}

	public List<String> getCourses() {
		return courses;
	}

	public void setCourses(List<String> courses) {
		this.courses = courses;
	}

	@Override
	public String toString() {
		
		StringBuilder allCourses = new StringBuilder();
		
		for(String c:courses){
			allCourses.append(c);
			allCourses.append("\n");
		}
		
		String volunteer = isVolunteer? "- Volunteer" : "n/a";
		String studentCouncil = isStudentCouncil? "- Student Council" : "n/a";
		String schoolActivities = volunteer.equals("n/a") && studentCouncil.equals("n/a")? "Not currently active." : 
			(!studentCouncil.equals("n/a")? studentCouncil : (!volunteer.equals("n/a")? volunteer : ""));
		
		return name + "\n" + 
			   address + ", " + 
			   city + ", " +
			   province + " " + 
			   postalCode + "\n" + 
			   phone + "\n" + 
			   email + "\n" + 
			   "Program: " + program + "\n" +
			   "Courses " + "\n" +
			   allCourses +
			   "School Activities" + "\n" + schoolActivities;	   
	}
	
	
}
