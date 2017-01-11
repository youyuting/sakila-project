package isep.web.sakila.webapi.model;

import isep.web.sakila.jpa.entities.Category;

public class CategoryWO extends WebObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected int categoryId;
	protected String name;

	public CategoryWO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CategoryWO(final Category cat) {
		super();
		this.categoryId = cat.getCategoryId();
		this.name = cat.getName();
	}

	public CategoryWO(int categoryId, String name) {
		super();
		this.categoryId = categoryId;
		this.name = name;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
