package isep.web.sakila.webapi.model;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isep.web.sakila.dao.repositories.CategoryRepository;
import isep.web.sakila.jpa.entities.Category;
import isep.web.sakila.webapi.service.CategoryService;

@Service("categoryService")
@Transactional
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;

	private static final Log log = LogFactory.getLog(CategoryServiceImpl.class);

	@Override
	public List<CategoryWO> findAllCategory() {
		List<CategoryWO> categories = new LinkedList<CategoryWO>();

		for (Category category : categoryRepository.findAll()) {
			categories.add(new CategoryWO(category));
		}

		return categories;
	}

	@Override
	public CategoryWO findById(int id) {
		log.debug(String.format("Looking for category by Id %s", id));
		Category category = categoryRepository.findOne(id);

		if (category != null) {
			return new CategoryWO(category);
		}
		return null;
	}

	@Override
	public void saveCategory(CategoryWO categoryWO) {

		Category category = new Category();
		category.setCategoryId(categoryWO.getCategoryId());
		category.setName(categoryWO.getName());
		category.setLastUpdate(new Timestamp(System.currentTimeMillis()));
		categoryRepository.save(category);
	}

	@Override
	public void updateCategory(CategoryWO categoryWO) {

		Category category = categoryRepository.findOne(categoryWO.getCategoryId());

		category.setCategoryId(categoryWO.getCategoryId());
		category.setName(categoryWO.getName());
		category.setLastUpdate(new Timestamp(System.currentTimeMillis()));
		categoryRepository.save(category);
	}

	@Override
	public void deleteCategoryById(int id) {
		categoryRepository.delete(id);
	}

}
