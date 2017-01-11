package isep.web.sakila.dao.repositories;

import org.springframework.data.repository.CrudRepository;

import isep.web.sakila.jpa.entities.Country;

public interface CountryRepository extends CrudRepository<Country,Integer> {

}
