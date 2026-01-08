package daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Initialize database connexion configuration
 */
public class DBConnexion {
	
	  private String url;
	  private String login;
	  private String pwd;
	 
	/**
	 * Constructor with no parameters
	 */
	public DBConnexion() {
		this.url = "jdbc:mariadb://localhost:3306/trainingstore";
		this.login = "traininguser";
		this.pwd = "fms2026";
	}
	
	/**
	 * Constructor with all parameters
	 * @param url URL of the database
	 * @param login Login to connect to the database
	 * @param pwd Password of the database
	 * @param db Name of the database
	 */
	public DBConnexion(String url, String login, String pwd, String db) {
		this.url = url;
		this.login = login;
		this.pwd = pwd;
	}

	/**
	 * Get the url of the database
	 * @return the url
	 */
	public String getUrl() {
		return this.url;
	}

	/**
	 * Set the url of the database
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * Get the login
	 * @return the login
	 */
	public String getLogin() {
		return this.login;
	}

	/**
	 * Set the login
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * Set the new password
	 * @param pwd the pwd to set
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	/**
	 * Get the password
	 * @return the password to connect to the database
	 */
	public String getPwd() {
		return this.pwd;
	}

	/**
	 * Establish a connexion to the database specified in the url
	 * @return connection object
	 */
	public Connection ConnectDB() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(this.url, this.login, this.pwd);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
}
