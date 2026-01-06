package daos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.Category;


public class CategoryDao implements IDao {
	private Category category;

	public CategoryDao() {
		this.category = new Category(); 
	}
	
	public CategoryDao(Category category) {
		this.category = category; 
	}
	

	/**
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public boolean create() {
		
		return false;
	}
	
	@Override
	public ResultSet read() {
		ResultSet result = null;
		DBConnexion dbConnection = new DBConnexion();
		try {
			Connection  connection = dbConnection.ConnectDB();
			Statement stmt = connection.createStatement();
			result = stmt.executeQuery("SELECT * FROM category WHERE category.id = " + this.getCategory());
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * Read all categories from database
	 * @return
	 */
	public ArrayList<Category> readAll() {
		ArrayList<Category> categories = new ArrayList<Category>();
		ResultSet result = null;
		DBConnexion dbConnection = new DBConnexion();
		try {
			Connection  connection = dbConnection.ConnectDB();
			Statement stmt = connection.createStatement();
			result = stmt.executeQuery("SELECT * FROM category");
			while(result.next()) {
				// Creating a new Category to avoid reference problems
				this.category = new Category(result.getInt(("id")), result.getString(("name")));
				categories.add(this.category);
			}
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categories;
	}

	@Override
	public boolean update() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete() {
		// TODO Auto-generated method stub
		return false;
	}

}
