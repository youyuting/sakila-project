package isep.web.sakila.webapi.service;

import java.text.ParseException;
import java.util.List;

import isep.web.sakila.webapi.model.RentalAndFilmWO;
import isep.web.sakila.webapi.model.RentalWO;

public interface RentalService {
	List<RentalWO> findAllRentals();

	RentalWO findById(int id);

	List<RentalWO> findByIdInventory(List<Integer> id);

	List<RentalAndFilmWO> findByIdCustomer(int id);

	void saveRental(RentalWO rentalWO) throws ParseException;

	void deleteRentalById(int id);

}
