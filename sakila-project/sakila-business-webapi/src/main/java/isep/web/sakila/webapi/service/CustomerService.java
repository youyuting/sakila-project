package isep.web.sakila.webapi.service;

import java.util.List;

import isep.web.sakila.webapi.model.CustomerWO;

public interface CustomerService {

	List<CustomerWO> findAllCustomers();
	
	CustomerWO findById(int id);

	void saveCustomer(CustomerWO customerWO);

	void updateCustomer(CustomerWO customerWO);

	void deleteCustomerById(int id);

	

}