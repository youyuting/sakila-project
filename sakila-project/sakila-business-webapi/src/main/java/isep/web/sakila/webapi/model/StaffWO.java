package isep.web.sakila.webapi.model;

import isep.web.sakila.jpa.entities.Staff;

public class StaffWO extends WebObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String username;
	protected String password;
	protected byte staffId;
	protected String firstName;
	protected String lastName;
	protected String email;
	protected byte storeId;

	public StaffWO() {
		super();
	}

	public StaffWO(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public StaffWO(final Staff staff) {
		super();
		this.staffId = staff.getStaffId();
		this.username = staff.getUsername();
		this.firstName = staff.getFirstName();
		this.lastName = staff.getLastName();
		this.email = staff.getEmail();
		this.storeId = (byte) staff.getStore().getStoreId();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Staff [username=" + this.username + ", Password=" + this.password + "]";
	}

	public byte getStaffId() {
		return staffId;
	}

	public void setStaffId(byte staffId) {
		this.staffId = staffId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public byte getStoreId() {
		return storeId;
	}

	public void setStoreId(byte storeId) {
		this.storeId = storeId;
	}

}	
