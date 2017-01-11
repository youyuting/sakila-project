package isep.web.sakila.webapi.service;

import java.util.List;

import isep.web.sakila.webapi.model.FilmWO;

public interface FilmService {
	FilmWO findById(int id);
	List<FilmWO> findAllFilms();

	void saveFilm(FilmWO filmWO);

	void updateFilm(FilmWO filmWO);

	void deleteFilmById(int id);


}