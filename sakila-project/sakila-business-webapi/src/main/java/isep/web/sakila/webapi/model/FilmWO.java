package isep.web.sakila.webapi.model;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import isep.web.sakila.jpa.entities.Film;

public class FilmWO extends WebObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected int filmId;
	protected String title;
	protected String description;
	protected int language_id_1;
	protected int language_id_2;
	protected byte rentalDuration;
	protected BigDecimal rentalRate;
	protected int length;
	protected BigDecimal replacementCost;
	protected String rating;
	protected String specialFeatures;
	protected ArrayList<Integer> listIdActor;
	protected ArrayList<Integer> listIdCategory;

	SimpleDateFormat formatter = new SimpleDateFormat("yyyy");

	public FilmWO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FilmWO(int filmId, String title, String description,  int language_id_1,
			int language_id_2, byte rentalDuration, BigDecimal rentalRate, int length, BigDecimal replacementCost,
			String rating, String specialFeatures, ArrayList<Integer> listIdActor, ArrayList<Integer> listIdCategory) {
		super();
		this.filmId = filmId;
		this.title = title;
		this.description = description;
		this.language_id_1 = language_id_1;
		this.language_id_2 = language_id_2;
		this.rentalDuration = rentalDuration;
		this.rentalRate = rentalRate;
		this.length = length;
		this.replacementCost = replacementCost;
		this.rating = rating;
		this.specialFeatures = specialFeatures;
		this.listIdActor = listIdActor;
		this.listIdCategory = listIdCategory;
	}

	public FilmWO(final Film film) {
		super();
		this.filmId = film.getFilmId();
		this.title = film.getTitle();
		this.description = film.getDescription();
		this.language_id_1 = film.getLanguage1().getLanguageId();
		this.rentalDuration = film.getRentalDuration();
		this.rentalRate = film.getRentalRate();
		this.length = film.getLength();
		this.replacementCost = film.getReplacementCost();
		this.rating = film.getRating();
		this.specialFeatures = film.getSpecialFeatures();
	}

	public int getFilmId() {
		return filmId;
	}

	public void setFilmId(int filmId) {
		this.filmId = filmId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public int getLanguage_id_1() {
		return language_id_1;
	}

	public void setLanguage_id_1(int language_id_1) {
		this.language_id_1 = language_id_1;
	}

	public int getLanguage_id_2() {
		return language_id_2;
	}

	public void setLanguage_id_2(int language_id_2) {
		this.language_id_2 = language_id_2;
	}

	public byte getRentalDuration() {
		return rentalDuration;
	}

	public void setRentalDuration(byte rentalDuration) {
		this.rentalDuration = rentalDuration;
	}

	public BigDecimal getRentalRate() {
		return rentalRate;
	}

	public void setRentalRate(BigDecimal rentalRate) {
		this.rentalRate = rentalRate;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public BigDecimal getReplacementCost() {
		return replacementCost;
	}

	public void setReplacementCost(BigDecimal replacementCost) {
		this.replacementCost = replacementCost;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getSpecialFeatures() {
		return specialFeatures;
	}

	public void setSpecialFeatures(String specialFeatures) {
		this.specialFeatures = specialFeatures;
	}

	public ArrayList<Integer> getListIdActor() {
		return listIdActor;
	}

	public void setListIdActor(ArrayList<Integer> listIdActor) {
		this.listIdActor = listIdActor;
	}

	public ArrayList<Integer> getListIdCategory() {
		return listIdCategory;
	}

	public void setListIdCategory(ArrayList<Integer> listIdCategory) {
		this.listIdCategory = listIdCategory;
	}

}