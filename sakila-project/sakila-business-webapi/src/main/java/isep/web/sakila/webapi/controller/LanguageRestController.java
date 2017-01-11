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

import isep.web.sakila.webapi.model.LanguageWO;
import isep.web.sakila.webapi.service.LanguageService;

@RestController
public class LanguageRestController {

	@Autowired
	LanguageService languageService;

	// -----------------------------------------------------

	@RequestMapping(value = "/getLanguages/", method = RequestMethod.GET)
	public ResponseEntity<List<LanguageWO>> listAllCities() {
		List<LanguageWO> countries = languageService.findAllLanguage();
		if (countries.isEmpty()) {
			System.out.println("vide");
			return new ResponseEntity<List<LanguageWO>>(HttpStatus.NO_CONTENT);
		}
		System.out.println(countries.size());
		return new ResponseEntity<List<LanguageWO>>(countries, HttpStatus.OK);
	}

	@RequestMapping(value = "/language/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LanguageWO> getLanguage(@PathVariable("id") int id) {
		System.out.println("Fetching Language with id " + id);
		LanguageWO languageWO = languageService.findById(id);
		if (languageWO == null) {
			System.out.println("Film with id " + id + " not found");
			return new ResponseEntity<LanguageWO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<LanguageWO>(languageWO, HttpStatus.OK);
	}

	// ---------------------------------------------------------------------------------------------

	@RequestMapping(value = "/createLanguage/", method = RequestMethod.POST)
	public ResponseEntity<Void> createLanguage(@RequestBody LanguageWO languageWO, UriComponentsBuilder ucBuilder) {
		System.out.println("Creating Language " + languageWO.getName());
		languageService.saveLanguage(languageWO);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/language/{id}").buildAndExpand(languageWO.getLanguageId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/updateLanguage/", method = RequestMethod.POST)
	public ResponseEntity<Void> updateCity(@RequestBody LanguageWO languageWO, UriComponentsBuilder ucBuilder) {
		System.out.println(String.format("Updating Language %s ", languageWO.getName()));
		System.out.println(String.format("Updating Language %s ", languageWO.getLanguageId()));

		LanguageWO currentLanguage = languageService.findById(languageWO.getLanguageId());

		if (currentLanguage == null) {
			System.out.println("Language with id " + languageWO.getName() + " not found");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}

		languageService.updateLanguage(languageWO);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/language/{id}").buildAndExpand(languageWO.getLanguageId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/deleteLanguage/{id}", method = RequestMethod.GET)
	public ResponseEntity<LanguageWO> deleteCity(@PathVariable("id") int id) {

		System.out.println("Fetching & Deleting City with id " + id);

		LanguageWO currentLanguage = languageService.findById(id);
		if (currentLanguage == null) {
			System.out.println("Unable to delete. City with id " + id + " not found");
			return new ResponseEntity<LanguageWO>(HttpStatus.NOT_FOUND);
		}
		languageService.deleteLanguageById(id);
		return new ResponseEntity<LanguageWO>(HttpStatus.NO_CONTENT);
	}

}