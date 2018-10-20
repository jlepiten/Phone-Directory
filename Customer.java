package phoneDirectory;

public class Customer {
	
	private String firstName;
	private String lastName;
	private String phoneNum; // FORMAT: ###-###-####

	// Constructor
	public Customer(String first, String last, String phone) {
		firstName = first;
		lastName = last;
		phoneNum = phone;
	}
	
	// Change first name.
	public void setFirst(String first) {
		firstName = first;
	}
	
	// Change last name.
	public void setLast(String last) {
		lastName = last;
	}
	
	// Change phone number.
	public void setPhone(String phone) {
		phoneNum = phone;
	}
	
	// Returns first name.
	public String getFirst() {
		return firstName;
	}
	
	// Returns last name.
	public String getLast() {
		return lastName;
	}
	
	// Returns phone number.
	public String getPhone() {
		return phoneNum;
	}
	
	// String representation of a customer object.
	@Override
	public String toString() {
		return firstName + " " + lastName + " " + phoneNum;
	}

}
