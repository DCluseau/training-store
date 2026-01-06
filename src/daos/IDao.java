package daos;

/**
 * Interface IDao
 */
public interface IDao<T> {
	
	/**
	 * Create record in database
	 * @return whether the query is successful or not
	 */
	public boolean create();
	
	/**
	 * Read one record
	 * @return whether the query is successful or not
	 */
	public T read();
	
	/**
	 * Update one record
	 * @return whether the query is successful or not
	 */
	public boolean update();
	
	/**
	 * Delete one record
	 * @return whether the query is successful or not
	 */
	public boolean delete();
}
