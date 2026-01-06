package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.Category;
import models.Training;

/**
 * Class to acces database for training table and class
 */
public class TrainingDao implements IDao {
	private Training training;
	
	/**
	 * Constructor without parameters
	 */
	public TrainingDao() {
		this.training = new Training();
	}
	
	/**
	 * Constructor with all parameters
	 * @param trainings ArrayList of Training objects
	 */
	public TrainingDao(Training training) {
		this.training = training;
	}

	/**
	 * Get training attribute
	 * @return the training
	 */
	public Training getTraining() {
		return training;
	}

	/**
	 * Set training attribute
	 * @param training the training to set
	 */
	public void setTrainings(Training training) {
		this.training = training;
	}
	
	/**
	 * INSERT one record query
	 * @return whether the query is successful or not
	 */
	@Override
	public boolean create() {
		String sql = "INSERT INTO training ('id', 'name', 'price', 'description') VALUES(NULL, ?, ?, ?)";
		DBConnexion dbConnection = new DBConnexion();
		try{
			Connection  connection = dbConnection.ConnectDB();
			PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, this.getTraining().getName());
			ps.setDouble(2, this.getTraining().getPrice());
			ps.setString(3, this.getTraining().getDescription());
			if(ps.executeUpdate() == 1) {
				ResultSet rs = ps.getGeneratedKeys();
				if(rs.next()) {
					this.getTraining().setId(rs.getInt("id"));
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
	public Training read() {
		ResultSet result = null;
		DBConnexion dbConnection = new DBConnexion();
		try {
			Connection  connection = dbConnection.ConnectDB();
			Statement stmt = connection.createStatement();
			result = stmt.executeQuery("SELECT * FROM training WHERE training.id = " + this.getTraining().getId());
			stmt.close();
			while(result.next()) {
				this.training = new Training(result.getInt("id"), result.getString("name"), result.getDouble("price"), result.getString("description"));
				this.training.setCategories(this.readCategories());
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return this.getTraining();
	}
	
	public ArrayList<Category> readCategories(){
		ArrayList<Category> categories = new ArrayList<Category>();
		ResultSet result = null;
		Category category = new Category();
		DBConnexion dbConnection = new DBConnexion();
		String sql = "SELECT id_training FROM is_category WHERE id_training = ?";
		try {
			Connection  connection = dbConnection.ConnectDB();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, this.getTraining().getId());
			result = ps.executeQuery(sql);
			while(result.next()) {
				// Creating a new object to avoid reference problems
				category.setId(result.getInt("id"));
				CategoryDao categoryDao = new CategoryDao(category);
				categories.add(categoryDao.read());
			}
			ps.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categories;
	}

	/**
	 * SELECT all records query
	 * @return ArrayList of found records
	 */
	public ArrayList<Training> readAll() {
		ArrayList<Training> trainings = new ArrayList<Training>();
		ResultSet result = null;
		DBConnexion dbConnection = new DBConnexion();
		String sql = "SELECT * FROM training";
		try {
			Connection  connection = dbConnection.ConnectDB();
			PreparedStatement ps = connection.prepareStatement(sql);
			result = ps.executeQuery(sql);
			while(result.next()) {
				// Creating a new object to avoid reference problems
				this.training = new Training(result.getInt("id"), result.getString("name"), result.getDouble("price"), result.getString("description"));
				this.training.setCategories(this.readCategories());
				trainings.add(this.training);
			}
			ps.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return trainings;
	}

	/**
	 * UPDATE one record query
	 * @return whether the query is successful or not
	 */
	@Override
	public boolean update() {
		String sql = "UPDATE training SET name = ?, price = ?, description = ? WHERE id = ?";
		DBConnexion dbConnection = new DBConnexion();
		try{
			Connection  connection = dbConnection.ConnectDB();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, this.getTraining().getName());
			ps.setDouble(2, this.getTraining().getPrice());
			ps.setString(3, this.getTraining().getDescription());
			ps.setInt(4, this.getTraining().getId());
			if(ps.executeUpdate() == 1) {
				this.updateCategories();
				return true;
			}
			ps.close();
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * UPDATE one record query
	 * @return whether the query is successful or not
	 */
	public boolean updateCategories() {
		CategoryDao categoryDao = new CategoryDao();
		String sql = "SELECT * FROM is_category WHERE id_training = ? and id_category = ?";
		DBConnexion dbConnection = new DBConnexion();
		if(this.getTraining().getCategories().size() == 1) {
			// Verify if there are more categories in database than in object
			// If not, just update the record
			// Else, delete all records which don't have id_training and id_category from the object
		}else if(this.getTraining().getCategories().size() > 1) {
			for(Category category : this.getTraining().getCategories()) {
				try{
					Connection  connection = dbConnection.ConnectDB();
					PreparedStatement ps = connection.prepareStatement(sql);
					ps.setInt(1, this.getTraining().getId());
					ps.setInt(1, category.getId());
					ResultSet result = ps.executeQuery(sql);
					if(!result.next()) {
						String sqlInsert = "INSERT INTO is_category (id_training, id_category) VALUES (?, ?)";
						ps = connection.prepareStatement(sqlInsert);
						ps.setInt(1, this.getTraining().getId());
						ps.setInt(2, category.getId());
						if(!(ps.executeUpdate() == 1)) {
							return false;
						}
					}
					ps.close();
				}catch (SQLException e) {
					e.printStackTrace();
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * DELETE one record query (training and is_category tables)
	 * @return whether the query is successful or not
	 */
	@Override
	public boolean delete() {
		String sql = "DELETE FROM training WHERE id = ?";
		DBConnexion dbConnection = new DBConnexion();
		try{
			Connection  connection = dbConnection.ConnectDB();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, this.getTraining().getId());
			if(ps.executeUpdate() == 1) {
				sql = "DELETE FROM is_category WHERE id_training = ?";
				ps.setInt(1, this.getTraining().getId());
				if(ps.executeUpdate() == 1) {
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
}
