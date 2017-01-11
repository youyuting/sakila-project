package isep.web.sakila.webapi.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import isep.web.sakila.dao.repositories.ActorRepository;
import isep.web.sakila.dao.repositories.FilmActorRepository;
import isep.web.sakila.dao.repositories.FilmCategoryRepository;
import isep.web.sakila.dao.repositories.FilmRepository;
import isep.web.sakila.dao.repositories.LanguageRepository;
import isep.web.sakila.jpa.entities.Actor;
import isep.web.sakila.jpa.entities.Film;
import isep.web.sakila.jpa.entities.FilmActor;
import isep.web.sakila.jpa.entities.FilmActorPK;
import isep.web.sakila.jpa.entities.FilmCategory;
import isep.web.sakila.jpa.entities.FilmCategoryPK;
import isep.web.sakila.jpa.entities.Language;
import isep.web.sakila.webapi.model.FilmWO;

@Service("filmService")
@Transactional
public class FilmServiceImpl implements FilmService {

	@Autowired
	private FilmRepository filmRepository;
	@Autowired
	private LanguageRepository languageRepository;
	@Autowired
	private ActorRepository actorRepository;
	@Autowired
	private FilmActorRepository filmActorRepository;
	@Autowired
	private FilmCategoryRepository filmCategoryRepository;

	private static final Log log = LogFactory.getLog(FilmServiceImpl.class);

	SimpleDateFormat formatter = new SimpleDateFormat("yyyy");

	@Override
	public List<FilmWO> findAllFilms() {
		List<FilmWO> films = new LinkedList<FilmWO>();

		for (Film film : filmRepository.findAll()) {
			films.add(new FilmWO(film));
			System.out.println("Adding " + film.getTitle());
		}

		return films;
	}

	@Override
	public FilmWO findById(int id) {
		log.debug(String.format("Looking for film by Id %s", id));
		Film film = filmRepository.findOne(id);

		if (film != null) {
			return new FilmWO(film);
		}
		return null;
	}

	@Override
	public void saveFilm(FilmWO filmWO) {
		Film film = new Film();
		film.setTitle(filmWO.getTitle());
		film.setDescription(filmWO.getDescription());

		Language language1 = languageRepository.findOne(filmWO.getLanguage_id_1());
		film.setLanguage1(language1);
		film.setRentalDuration(filmWO.getRentalDuration());
		film.setRentalRate(filmWO.getRentalRate());
		film.setLength(filmWO.getLength());
		film.setReplacementCost(filmWO.getReplacementCost());
		film.setRating(filmWO.getRating());
		film.setSpecialFeatures(filmWO.getSpecialFeatures());
		film.setLastUpdate(new Timestamp(System.currentTimeMillis()));

		filmRepository.save(film);

		System.out.println(film.getFilmId());

		// save actors
		saveFilmActor(filmWO, film);
		// save categories
		saveFilmCategory(filmWO, film);

	}

	@Override
	public void updateFilm(FilmWO filmWO) {

		Film film = filmRepository.findOne(filmWO.getFilmId());
		film.setTitle(filmWO.getTitle());
		film.setDescription(filmWO.getDescription());

		Language language1 = languageRepository.findOne(filmWO.getLanguage_id_1());

		film.setLanguage1(language1);
		film.setRentalDuration(filmWO.getRentalDuration());
		film.setRentalRate(filmWO.getRentalRate());
		film.setLength(filmWO.getLength());
		film.setReplacementCost(filmWO.getReplacementCost());
		film.setRating(filmWO.getRating());
		film.setSpecialFeatures(filmWO.getSpecialFeatures());
		film.setLastUpdate(new Timestamp(System.currentTimeMillis()));
		filmRepository.save(film);

		deleteFilmActorByIdFilm(film);
		deleteFilmCategoryByIdFilm(film);

		// save actors
		saveFilmActor(filmWO, film);
		// save categories
		saveFilmCategory(filmWO, film);

	}

	@Override
	public void deleteFilmById(int id) {
		Film film = filmRepository.findOne(id);
		deleteFilmActorByIdFilm(film);
		deleteFilmCategoryByIdFilm(film);
		filmRepository.delete(id);
	}

	// ---------------------------------------------------------------------------------
	// ----------------------------------------------------------------------------------

	public void saveFilmActor(FilmWO filmWO, Film film) {
		// save categories
		for (Integer listIdActor : filmWO.getListIdActor()) {
			Actor actor = actorRepository.findOne(listIdActor);

			FilmActorPK pk = new FilmActorPK();
			pk.setActorId(listIdActor);
			pk.setFilmId(film.getFilmId());

			FilmActor filmActor = new FilmActor();
			filmActor.setId(pk);
			filmActor.setLastUpdate(new Timestamp(System.currentTimeMillis()));
			filmActorRepository.save(filmActor);
		}
	}

	public void saveFilmCategory(FilmWO filmWO, Film film) {
		for (Integer listIdCategory : filmWO.getListIdCategory()) {

			System.out.println(listIdCategory);
			FilmCategoryPK pk = new FilmCategoryPK();

			pk.setCategoryId(Byte.valueOf(Integer.toString(listIdCategory)));
			pk.setFilmId(film.getFilmId());

			FilmCategory filmCategory = new FilmCategory();
			filmCategory.setId(pk);
			filmCategory.setLastUpdate(new Timestamp(System.currentTimeMillis()));
			filmCategoryRepository.save(filmCategory);
		}
	}

	public void deleteFilmActorByIdFilm(Film film) {
		List<FilmActor> listFilmActor = filmActorRepository.findAllFilmActorByIdFilm(film);
		for (FilmActor filmActor : listFilmActor) {
			filmActorRepository.delete(filmActor);
		}
	}

	public void deleteFilmCategoryByIdFilm(Film film) {
		List<FilmCategory> listFilmCategory = filmCategoryRepository.findAllFilmCategoryByIdFilm(film);
		for (FilmCategory filmCategory : listFilmCategory) {
			filmCategoryRepository.delete(filmCategory);
		}
	}

}
