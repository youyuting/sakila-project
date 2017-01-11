package isep.web.sakila.webapi.service;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isep.web.sakila.dao.repositories.LanguageRepository;
import isep.web.sakila.jpa.entities.Language;
import isep.web.sakila.webapi.model.LanguageWO;

@Service("languageService")
@Transactional
public class LanguageServiceImpl implements LanguageService {
	@Autowired
	private LanguageRepository languageRepository;

	private static final Log log = LogFactory.getLog(LanguageServiceImpl.class);

	@Override
	public List<LanguageWO> findAllLanguage() {
		List<LanguageWO> languages = new LinkedList<LanguageWO>();

		for (Language language : languageRepository.findAll()) {
			languages.add(new LanguageWO(language));
			System.out.println("Adding " + language.getLanguageId());
		}

		return languages;
	}

	@Override
	public LanguageWO findById(int id) {
		log.debug(String.format("Looking for language by Id %s", id));
		Language language = languageRepository.findOne(id);

		if (language != null) {
			return new LanguageWO(language);
		}
		return null;
	}

	@Override
	public void saveLanguage(LanguageWO languageWO) {

		System.out.println("save language");
		Language language = new Language();
		language.setName(languageWO.getName());
		language.setLastUpdate(new Timestamp(System.currentTimeMillis()));
		languageRepository.save(language);
		System.out.println("fin save language");
	}

	@Override
	public void updateLanguage(LanguageWO languageWO) {

		Language language = languageRepository.findOne(languageWO.getLanguageId());

		language.setName(languageWO.getName());

		language.setLastUpdate(new Timestamp(System.currentTimeMillis()));
		languageRepository.save(language);
	}

	@Override
	public void deleteLanguageById(int id) {
		languageRepository.delete(id);
	}

}