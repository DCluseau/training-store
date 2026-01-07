/**
 * 
 */
package business;

import java.util.ArrayList;
import java.util.Scanner;

import daos.CategoryDao;
import daos.TrainingDao;
import models.Category;
import models.Training;

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
			this.categoriesMenu.add(category.getId() + " - " + category.getName());
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
	public void displayMainMenu() {
		String menu = "Menu principal : \n";
		for(String option : this.getMainMenu()) {
			menu += option + "\n";
		}
		System.out.println(menu);
	}
	
	/**
	 * 
	 * @return
	 */
	public void displayCategoriesMenu() {
		String menu = "Quelles catégories voulez-vous afficher ? : \n";
		for(String option : this.getCategoriesMenu()) {
			menu += option + "\n";
		}
		System.out.println(menu);
	}
	
	public void displayTrainingsByCategories(int id) {
		Scanner sc = new Scanner(System.in);
		int numOption = 0;
		try {
			numOption = sc.nextInt();
			TrainingDao trainingDao = new TrainingDao();
			trainingDao.readTrainingsByCategory(numOption);
		}catch (Exception e) {
			e.printStackTrace();
		}
		sc.close();
		
	}
	
	/**
	 * 
	 * @param trainings
	 */
	public void displayTrainingList(ArrayList<Training> trainings) {
		String columns = "| Id  ";
		String spaces = "";
		String line = "";
		for(int i = 0; i < 187; i++) {
			line += "-";
		}
		columns += "| Name";
		for(int i = 0; i < 50 - " Name".length() ; i++) {
			spaces += " ";
		}
		columns += spaces + "| Category";
		spaces = "";
		for(int i = 0; i < 25 - " Category".length() ; i++) {
			spaces += " ";
		}
		columns += spaces + "| Price";
		spaces = "";
		for(int i = 0; i < 10 - " Price".length() ; i++) {
			spaces += " ";
		}
		columns += spaces + "| Description";
		spaces = "";
		for(int i = 0; i < 100 - " Description".length() ; i++) {
			spaces += " ";
		}
		
		System.out.println(columns);
		System.out.println(line);
		for(Training training: trainings) {
			System.out.println(training);
		}
		System.out.println(line);
		
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<Training> getAllTrainings(){
		ArrayList<Training> allTrainings = new ArrayList<Training>();
		TrainingDao trainingDao = new TrainingDao();
		allTrainings = new ArrayList<Training>(trainingDao.readAll());
		return allTrainings;
	}
	
	public void searchTrainings(String tags) {
		ArrayList<Training> trainingsFound = new ArrayList<Training>();
		TrainingDao trainingDao = new TrainingDao();
		trainingsFound = new ArrayList<Training>(trainingDao.search(tags));
		displayTrainingList(trainingsFound);
	}
	
	/**
	 * 
	 * @param menu
	 */
	public void selectOption(String menu) {
		Scanner scan = new Scanner(System.in);
		int select = 0;
		if(menu.equals("main")) {
			try {
				while(select != 4) {
					System.out.println("Veuillez saisir une option (entrez le numéro de l'option) : ");
					select = scan.nextInt();
					switch(select) {
					case 1:
						this.displayTrainingList(this.getAllTrainings());
						break;
					case 2:
						this.displayCategoriesMenu();
						break;
					case 3:
						String tags = "";
						System.out.println("Veuillez saisir vos mots-clés en remplaçant les espaces par un +");
						tags = scan.next();
						this.searchTrainings(tags);
						break;
					case 4:
						System.exit(1);
						break;
					default:
						break;
					}
					select = 0;
					this.displayMainMenu();
				}
			}catch (Exception e) {
				scan.close();
				e.printStackTrace();
			}
		}else if(menu.equals("categories")) {
			
		}else {
			this.displayMainMenu();
		}
		scan.close();
	}
}
