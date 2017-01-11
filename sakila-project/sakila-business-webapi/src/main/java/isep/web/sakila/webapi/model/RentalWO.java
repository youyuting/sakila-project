package isep.web.sakila.webapi.model;

import java.text.SimpleDateFormat;

import isep.web.sakila.jpa.entities.Rental;

public class RentalWO extends WebObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected int rentalId;
	protected String rentalDate;
	protected int inventoryId;
	protected int customerId;
	protected String returnDate;
	protected byte staffId;

	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public RentalWO(final Rental rental) {
		super();
		this.rentalId = rental.getRentalId();
		this.rentalDate = formatter.format(rental.getRentalDate());
		this.inventoryId = rental.getInventory().getInventoryId();
		this.customerId = rental.getCustomer().getCustomerId();
		if (rental.getReturnDate() != null) {
			this.returnDate = formatter.format(rental.getReturnDate());
		}

		this.staffId = rental.getStaff().getStaffId();
	}

	public RentalWO(int rentalId, String rentalDate, int inventoryId, int customerId, String returnDate, byte staffId) {
		super();
		this.rentalId = rentalId;
		this.rentalDate = rentalDate;
		this.inventoryId = inventoryId;
		this.customerId = customerId;
		this.returnDate = returnDate;
		this.staffId = staffId;
	}

	public RentalWO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getRentalId() {
		return rentalId;
	}

	public void setRentalId(int rentalId) {
		this.rentalId = rentalId;
	}

	public String getRentalDate() {
		return rentalDate;
	}

	public void setRentalDate(String rentalDate) {
		this.rentalDate = rentalDate;
	}

	public int getInventoryId() {
		return inventoryId;
	}

	public void setInventoryId(int inventoryId) {
		this.inventoryId = inventoryId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}

	public byte getStaffId() {
		return staffId;
	}

	public void setStaffId(byte staffId) {
		this.staffId = staffId;
	}

}