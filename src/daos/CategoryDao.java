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
public class CategoryDao implements IDao<Category> {
	private Category category;

	/**
	 * Constructor without parameters
	 */
	public CategoryDao() {
		this.category = new Category(); 
	}

	/**
	 * Constructor with all parameters
	 * @param category Category to initialize
	 */
	public CategoryDao(Category category) {
		this.category = category; 
	}

	/**
	 * Get category
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * Set category
	 * @param category the category to set
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

	/**
	 * INSERT one record query
	 * @return whether the query is successful or not
	 */
	@Override
	public boolean create() {
		String sql = "INSERT INTO category ('id', 'name') VALUES(NULL, ?)";
		DBConnexion dbConnection = new DBConnexion();
		try{
			Connection  connection = dbConnection.ConnectDB();
			PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, this.getCategory().getName());
			if(ps.executeUpdate() == 1) {
				ResultSet rs = ps.getGeneratedKeys();
				if(rs.next()) {
					this.getCategory().setId(rs.getInt("id"));
					return true;
				}
			}
			ps.close();
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	/**
	 * SELECT one record query
	 * @return Select query result
	 */
	@Override
	public Category read() {
		ResultSet result = null;
		DBConnexion dbConnection = new DBConnexion();
		String sql = "SELECT * FROM category WHERE category.id = ?";
		try {
			Connection  connection = dbConnection.ConnectDB();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, this.getCategory().getId());
			result = ps.executeQuery();
			while(result.next()) {
				this.category = new Category(result.getInt(("id")), result.getString(("name")));
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return this.getCategory();
	}

	/**
	 * SELECT all records query
	 * @return ArrayList of found records
	 */
	public ArrayList<Category> readAll() {
		ArrayList<Category> categories = new ArrayList<Category>();
		ResultSet result = null;
		DBConnexion dbConnection = new DBConnexion();
		String sql = "SELECT * FROM category ORDER BY id ASC";
		try {
			Connection  connection = dbConnection.ConnectDB();
			PreparedStatement ps = connection.prepareStatement(sql);
			result = ps.executeQuery();
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
	
	/**
	 * Alternative method to be tested : use read method to read all
	 * SELECT all records query
	 * @return ArrayList of found records
	 */
//	public ArrayList<Category> readAll() {
//		ArrayList<Category> categories = new ArrayList<Category>();
//		ResultSet result = null;
//		DBConnexion dbConnection = new DBConnexion();
//		String sql = "SELECT id FROM category";
//		try {
//			Connection  connection = dbConnection.ConnectDB();
//			PreparedStatement ps = connection.prepareStatement(sql);
//			result = ps.executeQuery();
//			while(result.next()) {
//				// Creating a new Category to avoid reference problems
//				this.category = new Category();
//				this.category.setId(result.getInt(("id")));
//				categories.add(this.read());
//			}
//			ps.close();
//			connection.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return categories;
//	}

	/**
	 * UPDATE one record query
	 * @return whether the query is successful or not
	 */
	@Override
	public boolean update() {
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

	/**
	 * DELETE one record query
	 * @return whether the query is successful or not
	 */
	@Override
	public boolean delete() {
		String sql = "DELETE FROM category WHERE id = ?";
		DBConnexion dbConnection = new DBConnexion();
		try{
			Connection  connection = dbConnection.ConnectDB();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, this.getCategory().getId());
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
