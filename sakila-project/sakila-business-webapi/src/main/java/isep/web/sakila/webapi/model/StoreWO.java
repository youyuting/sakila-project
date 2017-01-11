package isep.web.sakila.webapi.model;

import isep.web.sakila.jpa.entities.Address;
import isep.web.sakila.jpa.entities.Staff;
import isep.web.sakila.jpa.entities.Store;

public class StoreWO extends WebObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5899560033222587890L;

	protected int storeId;
	protected Staff staff;
	protected Address address;

	public StoreWO() {
		super();
	}

	public StoreWO(int storeId, Staff staff, Address address) {
		super();
		this.storeId = storeId;
		this.staff = staff;
		this.address = address;
	}

	public StoreWO(final Store store) {
		super();
		this.storeId = store.getStoreId();
		this.staff = store.getStaff();
		this.address = store.getAddress();
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
}
