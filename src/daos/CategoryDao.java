package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.Category;

/**
 * Class to make operations on DB for Category class
 */
public class CategoryDao implements IDao {
	private Category category;

	/**
	 * Constructor without parameters
	 */
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
		String sql = "SELECT * FROM category";
		try {
			Connection  connection = dbConnection.ConnectDB();
			PreparedStatement ps = connection.prepareStatement(sql);
			result = ps.executeQuery(sql);
			while(result.next()) {
				// Creating a new Category to avoid reference problems
				this.category = new Category(result.getInt(("id")), result.getString(("name")));
				categories.add(this.category);
			}
			ps.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categories;
	}

	@Override
	public boolean update() {
		// Requête UPDATE
		String sql = "UPDATE category SET name = ? WHERE id = ?";
		DBConnexion dbConnection = new DBConnexion();
		try{
			Connection  connection = dbConnection.ConnectDB();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, this.getCategory().getName());
			ps.setInt(2, this.getCategory().getId());
			if(ps.executeUpdate() == 1) {
				return true;
			}
			ps.close();
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	@Override
	public boolean delete() {
		// Requête DELETE
		String sql = "DELETE FROM category WHERE id = ?";
		DBConnexion dbConnection = new DBConnexion();
		try{
			Connection  connection = dbConnection.ConnectDB();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(2, this.getCategory().getId());
			if(ps.executeUpdate() == 1) {
				return true;
			}
			ps.close();
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

}
