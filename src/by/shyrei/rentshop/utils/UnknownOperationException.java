package by.shyrei.rentshop.utils;

/**
 * Class for handling exception in case of incorrect user input.
 * 
 * @author Shyrei Uladzimir
 * 
 */
public class UnknownOperationException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructs an instance of exception
	 * 
	 * @param userInput
	 *            - User String type input
	 */
	public UnknownOperationException(String userInput) {
		super(userInput + " - неизвестная команда, повторите ввод...");
	}

}
