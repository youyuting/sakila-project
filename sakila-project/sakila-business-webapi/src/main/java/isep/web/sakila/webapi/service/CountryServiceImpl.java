package isep.web.sakila.webapi.service;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isep.web.sakila.dao.repositories.CountryRepository;
import isep.web.sakila.jpa.entities.Country;
import isep.web.sakila.webapi.model.CountryWO;


@Service("countryService")
@Transactional
public class CountryServiceImpl implements CountryService {
	@Autowired
	private CountryRepository countryRepository;

	private static final Log log = LogFactory.getLog(CountryServiceImpl.class);

	@Override
	public List<CountryWO> findAllCountry() {
		List<CountryWO> countries = new LinkedList<CountryWO>();

		for (Country country : countryRepository.findAll()) {
			countries.add(new CountryWO(country));
			System.out.println("Adding " + country.getCountry());
		}

		return countries;
	}

	@Override
	public CountryWO findById(int id) {
		log.debug(String.format("Looking for country by Id %s", id));
		Country country = countryRepository.findOne(id);

		if (country != null) {
			return new CountryWO(country);
		}
		return null;
	}

	@Override
	public void saveCountry(CountryWO countryWO) {

		Country country = new Country();
		country.setCountryId(countryWO.getCountryId());
		country.setCountry(countryWO.getCountry());
		country.setLastUpdate(new Timestamp(System.currentTimeMillis()));
		countryRepository.save(country);
	}

	@Override
	public void updateCountry(CountryWO countryWO) {

		Country country = countryRepository.findOne(countryWO.getCountryId());

		country.setCountry(countryWO.getCountry());
		country.setCountryId(countryWO.getCountryId());

		country.setLastUpdate(new Timestamp(System.currentTimeMillis()));
		countryRepository.save(country);
	}

	@Override
	public void deleteCountryById(int id) {
		countryRepository.delete(id);
	}

}

