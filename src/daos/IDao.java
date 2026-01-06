package daos;

import java.sql.ResultSet;

/**
 * Interface IDao
 */
public interface IDao {
	
	/**
	 * Create record in database
	 * @return
	 */
	public boolean create();
	
	/**
	 * Read one record
	 * @return
	 */
	public ResultSet read();
	
	/**
	 * Update one record
	 * @return
	 */
	public boolean update();
	
	/**
	 * Delete one record
	 * @return
	 */
	public boolean delete();
}
