package isep.web.sakila.webapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import isep.web.sakila.webapi.model.CountryWO;
import isep.web.sakila.webapi.service.CountryService;

@RestController
public class CountryRestController {

	@Autowired
	CountryService countryService;

	@RequestMapping(value = "/country/", method = RequestMethod.GET)
	public ResponseEntity<List<CountryWO>> listAllCities() {
		List<CountryWO> countries = countryService.findAllCountry();
		if (countries.isEmpty()) {
			System.out.println("vide");
			return new ResponseEntity<List<CountryWO>>(HttpStatus.NO_CONTENT);
		}
		System.out.println(countries.size());
		return new ResponseEntity<List<CountryWO>>(countries, HttpStatus.OK);
	}

	@RequestMapping(value = "/country/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CountryWO> getCountry(@PathVariable("id") int id) {
		System.out.println("Fetching Country with id " + id);
		CountryWO countryWO = countryService.findById(id);
		if (countryWO == null) {
			System.out.println("Film with id " + id + " not found");
			return new ResponseEntity<CountryWO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<CountryWO>(countryWO, HttpStatus.OK);
	}

	// ---------------------------------------------------------------------------------------------

	@RequestMapping(value = "/createCountry/", method = RequestMethod.POST)
	public ResponseEntity<Void> createCountry(@RequestBody CountryWO countryWO, UriComponentsBuilder ucBuilder) {
		System.out.println("Creating Country " + countryWO.getCountry());
		countryService.saveCountry(countryWO);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/country/{id}").buildAndExpand(countryWO.getCountryId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/updateCountry/", method = RequestMethod.POST)
	public ResponseEntity<Void> updateCity(@RequestBody CountryWO countryWO, UriComponentsBuilder ucBuilder) {
		System.out.println(String.format("Updating Country %s ", countryWO.getCountry()));

		CountryWO currentCountry = countryService.findById(countryWO.getCountryId());

		if (currentCountry == null) {
			System.out.println("Country with id " + countryWO.getCountry() + " not found");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}

		countryService.saveCountry(countryWO);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/country/{id}").buildAndExpand(countryWO.getCountryId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/deleteCountry/{id}", method = RequestMethod.GET)
	public ResponseEntity<CountryWO> deleteCity(@PathVariable("id") int id) {

		System.out.println("Fetching & Deleting City with id " + id);

		CountryWO currentCountry = countryService.findById(id);
		if (currentCountry == null) {
			System.out.println("Unable to delete. City with id " + id + " not found");
			return new ResponseEntity<CountryWO>(HttpStatus.NOT_FOUND);
		}
		countryService.deleteCountryById(id);
		return new ResponseEntity<CountryWO>(HttpStatus.NO_CONTENT);
	}

}
