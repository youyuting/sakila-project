package isep.web.sakila.dao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import isep.web.sakila.jpa.entities.Film;
import isep.web.sakila.jpa.entities.FilmCategory;

public interface FilmCategoryRepository extends CrudRepository<FilmCategory, Integer> {

	@Query("select fc from FilmCategory fc where fc.film=?1")
	List<FilmCategory> findAllFilmCategoryByIdFilm(Film film);
}
