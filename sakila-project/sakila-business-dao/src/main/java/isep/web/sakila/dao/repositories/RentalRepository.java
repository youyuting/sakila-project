package isep.web.sakila.dao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import isep.web.sakila.jpa.entities.Customer;
import isep.web.sakila.jpa.entities.Inventory;
import isep.web.sakila.jpa.entities.Rental;

public interface RentalRepository extends JpaRepository<Rental, Integer> {
	@Query("select r from Rental r where r.inventory in (?1) and r.returnDate IS NULL")
	List<Rental> findAllRentalByIdInventory(List<Inventory> listRentalId);

	@Query("select r from Rental r where r.customer in (?1) and r.returnDate IS NULL")
	List<Rental> findAllRentalByIdCustomer(Customer customer);
}
