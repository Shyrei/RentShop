package by.shyrei.rentshop.utils;

/**
 * Enum for menu operation.
 * 
 * @author Shyrei Uladzimir
 * 
 */
public enum OperationsForMenu {

	INIT, RENT, EXIT, LOAD_FROM_FILE, SAVE;
	/**
	 * 
	 * @param userInput
	 *            User input string
	 * @return Return enum
	 * @throws UnknownOperationException
	 *             Handles invalid user input
	 */
	public static OperationsForMenu menuOperation(String userInput) throws UnknownOperationException {

		switch (userInput) {
		case "1":
			return INIT;
		case "2":
			return LOAD_FROM_FILE;
		case "3":
			return SAVE;
		case "4":
			return RENT;
		case "0":
			return EXIT;
		default:
			throw new UnknownOperationException(userInput);
		}
	}

}
