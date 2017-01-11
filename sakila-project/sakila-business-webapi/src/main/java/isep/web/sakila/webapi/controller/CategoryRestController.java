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

import isep.web.sakila.webapi.model.CategoryWO;
import isep.web.sakila.webapi.service.CategoryService;

@RestController
public class CategoryRestController {

	@Autowired
	CategoryService categoryService;

	// -----------------------------------------------------

	@RequestMapping(value = "/getCategories/", method = RequestMethod.GET)
	public ResponseEntity<List<CategoryWO>> listAllCategory() {
		List<CategoryWO> categories = categoryService.findAllCategory();
		if (categories.isEmpty()) {
			System.out.println("vide");
			return new ResponseEntity<List<CategoryWO>>(HttpStatus.NO_CONTENT);
		}
		System.out.println(categories.size());
		return new ResponseEntity<List<CategoryWO>>(categories, HttpStatus.OK);
	}

	@RequestMapping(value = "/category/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CategoryWO> getCategory(@PathVariable("id") int id) {
		System.out.println("Fetching Category with id " + id);
		CategoryWO categoryWO = categoryService.findById(id);
		if (categoryWO == null) {
			System.out.println("Film with id " + id + " not found");
			return new ResponseEntity<CategoryWO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<CategoryWO>(categoryWO, HttpStatus.OK);
	}

	// ---------------------------------------------------------------------------------------------

	@RequestMapping(value = "/createCategory/", method = RequestMethod.POST)
	public ResponseEntity<Void> createCategory(@RequestBody CategoryWO categoryWO, UriComponentsBuilder ucBuilder) {
		System.out.println("Creating Category " + categoryWO.getName());
		categoryService.saveCategory(categoryWO);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/category/{id}").buildAndExpand(categoryWO.getCategoryId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/updateCategory/", method = RequestMethod.POST)
	public ResponseEntity<Void> updateCategory(@RequestBody CategoryWO categoryWO, UriComponentsBuilder ucBuilder) {
		System.out.println(String.format("Updating Category %s ", categoryWO.getName()));

		CategoryWO currentCategory = categoryService.findById(categoryWO.getCategoryId());

		if (currentCategory == null) {
			System.out.println("Category with id " + categoryWO.getName() + " not found");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}

		categoryService.saveCategory(categoryWO);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/category/{id}").buildAndExpand(categoryWO.getCategoryId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/deleteCategory/{id}", method = RequestMethod.GET)
	public ResponseEntity<CategoryWO> deleteCategory(@PathVariable("id") int id) {

		System.out.println("Fetching & Deleting City with id " + id);

		CategoryWO currentCategory = categoryService.findById(id);
		if (currentCategory == null) {
			System.out.println("Unable to delete. City with id " + id + " not found");
			return new ResponseEntity<CategoryWO>(HttpStatus.NOT_FOUND);
		}
		categoryService.deleteCategoryById(id);
		return new ResponseEntity<CategoryWO>(HttpStatus.NO_CONTENT);
	}

}