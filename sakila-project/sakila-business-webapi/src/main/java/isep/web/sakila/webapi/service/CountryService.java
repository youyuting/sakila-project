package isep.web.sakila.webapi.service;

import java.util.List;

import isep.web.sakila.webapi.model.CountryWO;


public interface CountryService {
	List<CountryWO> findAllCountry();

	CountryWO findById(int id);

	void saveCountry(CountryWO countryWO);

	void updateCountry(CountryWO countryWO);

	void deleteCountryById(int id);

}
