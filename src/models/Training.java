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
	 * Constructor without categories ArrayList parameter
	 * @param id
	 * @param name
	 * @param price
	 * @param description
	 */
	public Training (int id, String name, Double price, String description) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.description = description;
		this.categories = new ArrayList<Category>();
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
		this.categories = new ArrayList<Category>(categories);
	}

	/**
	 * Function to display the training
	 * @return
	 */
	public String toString() {
		String displayTraining = "";
		String spaces = "";
		String categories = "";
		for(int i = 0; i < 5 - (" " + String.valueOf(this.getId())).length(); i++) {
			spaces += " ";
		}
		displayTraining += "| " + this.getId() + spaces;
		spaces = "";
		if((" " + this.getName()).length() < 50) {
			for(int i = 0; i < 50 - (" " + this.getName()).length(); i++) {
				spaces += " ";
			}
		}
		displayTraining += "| " + this.getName() + spaces;
		spaces = "";
			for(int i = 0; i < this.getCategories().size(); i++) {
				if(i > 0) {
					categories += "/";
				}
				categories += this.getCategories().get(i);
			}
			if((" " + categories).length() < 25) {
				for(int i = 0; i < 25 - (" " + categories).length(); i++) {
					spaces += " ";
				}
			}
		displayTraining += "| " + categories + spaces;
		spaces = "";
		if((" " + String.format("%f", this.getPrice())).length() < 10){
			spaces += " ";
		}
		displayTraining += "| " + this.getPrice() + spaces;
		spaces = "";
		if((" " + this.getDescription()).length() < 100){
			spaces += " ";
		}
		displayTraining += "| " + this.getDescription() + spaces;
		return displayTraining + "|";
	}
}
