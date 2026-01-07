/**
 * 
 */
package business;

import java.util.ArrayList;

import daos.CategoryDao;
import models.Category;

/**
 * Class to use all class objects
 */
public class TrainingStore {
	
	private ArrayList<String> mainMenu = new ArrayList<String>(){{
				add("1 - Afficher toutes les formations");
				add("2 - Sélectionner les catégories à afficher");
				add("3 - Rechercher une formation par mots-clés");
				add("4 - Quitter");
			}};
	private ArrayList<String> categoriesMenu = new ArrayList<String>();
	
	/**
	 * Constructor without parameters
	 */
	public TrainingStore() {
		CategoryDao categoryDao = new CategoryDao();
		categoryDao.readAll();
		for(Category category : categoryDao.readAll()) {
			this.categoriesMenu.add(category.getName());
		}
	}
	
	/**
	 * Constructor with Category sub menu
	 * @param categoriesMenu ArrayList with all categories from database
	 */
	public TrainingStore(ArrayList<String> categoriesMenu) {
		this.categoriesMenu = new ArrayList<String>(categoriesMenu);
	}

	/**
	 * @return the mainMenu
	 */
	public ArrayList<String> getMainMenu() {
		return mainMenu;
	}

	/**
	 * @param mainMenu the mainMenu to set
	 */
	public void setMainMenu(ArrayList<String> mainMenu) {
		this.mainMenu = mainMenu;
	}

	/**
	 * @return the categoriesMenu
	 */
	public ArrayList<String> getCategoriesMenu() {
		return categoriesMenu;
	}

	/**
	 * @param categoriesMenu the categoriesMenu to set
	 */
	public void setCategoriesMenu(ArrayList<String> categoriesMenu) {
		this.categoriesMenu = categoriesMenu;
	}
	
	/**
	 * 
	 * @return
	 */
	public String displayMainMenu() {
		String menu = "Menu principal : \n";
		for(String option : this.getMainMenu()) {
			menu += option + "\n";
		}
		return menu;
	}
	
	/**
	 * 
	 * @return
	 */
	public String displayCategoriesMenu() {
		String menu = "Quelles catégories voulez-vous afficher ? : \n";
		for(String option : this.getMainMenu()) {
			menu += option + "\n";
		}
		return menu;
	}
}
