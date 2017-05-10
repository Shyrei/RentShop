package by.shyrei.rentshop.utils;

/**
 * Class for error handling in the DAO layer.
 * 
 * @author Shyrei Uladzimir
 * 
 */
public class DAOException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructs an instance of exception
	 * 
	 * @param msg
	 *            Message for sending
	 */
	public DAOException(String msg) {
		super(msg);
	}
}
