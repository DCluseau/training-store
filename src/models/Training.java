package models;

import java.util.ArrayList;

public class Training {
	private int id;
	private String name;
	private Double price;
	private String description;
	private ArrayList<Category> categories;
	
	/**
	 * Constructor without parameters
	 */
	public Training() {
		this.id = 0;
		this.name = "";
		this.price = 0.0;
		this.description = "";
		this.categories = new ArrayList<Category>();
	}
	
	/**
	 * Constructor with all parameters
	 * @param id
	 * @param name
	 * @param price
	 * @param description
	 * @param categories
	 */
	public Training (int id, String name, Double price, String description, ArrayList<Category> categories) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.description = description;
		this.categories = new ArrayList<Category>(categories);
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the categories
	 */
	public ArrayList<Category> getCategories() {
		return categories;
	}

	/**
	 * @param categories the categories to set
	 */
	public void setCategories(ArrayList<Category> categories) {
		this.categories = categories;
	}

	/**
	 * Function to display the training
	 * @param category The category to display
	 * @return
	 */
	public String toString(String category) {
		String displayTraining = "";
		displayTraining += "| " + this.getName() + " | " + category + " | " + this.getPrice() + " € | " + this.getDescription();
		return displayTraining;
	}
}
