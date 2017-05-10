package by.shyrei.rentshop.utils;

/**
 * Enum for rent operation.
 * 
 * @author Shyrei Uladzimir
 * 
 */
public enum OperationsForRent {
	ADD_GOOD, RETURN_GOOD, FIND_GOOD, MY_GOOD, ALL_GOOD, RETURN;
	/**
	 * 
	 * @param userInput
	 *            User input string
	 * @return Return enum
	 * @throws UnknownOperationException
	 *             Handles invalid user input
	 */
	public static OperationsForRent rentOperation(String userInput) throws UnknownOperationException {

		switch (userInput) {
		case "1":
			return ADD_GOOD;
		case "2":
			return RETURN_GOOD;
		case "3":
			return FIND_GOOD;
		case "4":
			return MY_GOOD;
		case "5":
			return ALL_GOOD;
		case "0":
			return RETURN;
		default:
			throw new UnknownOperationException(userInput);
		}
	}
}
