package isep.web.sakila.dao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import isep.web.sakila.jpa.entities.Film;
import isep.web.sakila.jpa.entities.FilmActor;

public interface FilmActorRepository extends JpaRepository<FilmActor, Integer> {

	@Query("select f from FilmActor f where f.film=?1")
	List<FilmActor> findAllFilmActorByIdFilm(Film film);
}