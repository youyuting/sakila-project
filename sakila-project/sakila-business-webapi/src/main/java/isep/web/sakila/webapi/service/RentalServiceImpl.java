package isep.web.sakila.webapi.service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isep.web.sakila.dao.repositories.CustomerRepository;
import isep.web.sakila.dao.repositories.FilmRepository;
import isep.web.sakila.dao.repositories.InventoryRepository;
import isep.web.sakila.dao.repositories.RentalRepository;
import isep.web.sakila.dao.repositories.StaffRepository;
import isep.web.sakila.jpa.entities.Customer;
import isep.web.sakila.jpa.entities.Film;
import isep.web.sakila.jpa.entities.Inventory;
import isep.web.sakila.jpa.entities.Rental;
import isep.web.sakila.jpa.entities.Staff;
import isep.web.sakila.webapi.model.RentalAndFilmWO;
import isep.web.sakila.webapi.model.RentalWO;

@Service("rentalService")
@Transactional
public class RentalServiceImpl implements RentalService {

	@Autowired
	private RentalRepository rentalRepository;
	@Autowired
	private InventoryRepository inventoryRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private FilmRepository filmRepository;
	@Autowired
	private StaffRepository staffRepository;

	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private static final Log log = LogFactory.getLog(RentalServiceImpl.class);

	@Override
	public List<RentalWO> findAllRentals() {
		List<RentalWO> rentals = new LinkedList<RentalWO>();

		for (Rental rental : rentalRepository.findAll()) {
			rentals.add(new RentalWO(rental));
			log.debug("Adding " + rental);
		}

		return rentals;
	}

	@Override
	public RentalWO findById(int id) {
		log.debug(String.format("Looking for rental by Id %s", id));
		Rental rental = rentalRepository.findOne(id);

		if (rental != null) {
			return new RentalWO(rental);
		}
		return null;
	}

	@Override
	public List<RentalWO> findByIdInventory(List<Integer> listIdInteger) {
		List<RentalWO> rentals = new LinkedList<RentalWO>();

		List<Inventory> listInventory = new ArrayList<Inventory>();

		for (int j = 0; j < listIdInteger.size(); j++) {
			System.out.println(listIdInteger.get(j));
			Inventory inventory1 = inventoryRepository.findOne(listIdInteger.get(j));
			listInventory.add(inventory1);
		}

		for (Rental rental : rentalRepository.findAllRentalByIdInventory(listInventory)) {
			rentals.add(new RentalWO(rental));
			System.out.println(rental.getRentalId());
		}
		return rentals;
	}

	@Override
	public List<RentalAndFilmWO> findByIdCustomer(int id) {
		List<RentalAndFilmWO> rentals = new LinkedList<RentalAndFilmWO>();

		Customer customer = customerRepository.findOne(id);

		for (Rental rental : rentalRepository.findAllRentalByIdCustomer(customer)) {
			RentalAndFilmWO rentalAndFilm = new RentalAndFilmWO(rental);

			Inventory inv = inventoryRepository.findOne(rental.getInventory().getInventoryId());

			int filmid = inv.getFilm().getFilmId();
			Film film = filmRepository.findOne(filmid);
			System.out.println(rental.getRentalId());
			rentalAndFilm.setFilmId(film.getFilmId());
			rentalAndFilm.setTitle(film.getTitle());
			rentals.add(rentalAndFilm);
		}

		return rentals;
	}

	@Override
	public void saveRental(RentalWO rentalWO) throws ParseException {

		Inventory inventory = inventoryRepository.findOne(rentalWO.getInventoryId());
		Customer customer = customerRepository.findOne(rentalWO.getCustomerId());
		Staff staff = staffRepository.findOne(rentalWO.getStaffId());

		Rental rental = new Rental();
		rental.setRentalDate(formatter.parse(rentalWO.getRentalDate()));
		rental.setInventory(inventory);
		rental.setCustomer(customer);
		if (rentalWO.getReturnDate() != null) {
			rental.setReturnDate(formatter.parse(rentalWO.getReturnDate()));
		}
		rental.setStaff(staff);
		rental.setLastUpdate(new Timestamp(System.currentTimeMillis()));
		rentalRepository.save(rental);
	}

	@Override
	public void deleteRentalById(int id) {
		rentalRepository.delete(id);
	}

}
