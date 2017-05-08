package by.shyrei.rentshop.utils;

public class MyExceptions extends Exception {
	private static final long serialVersionUID = 1L;

	/**
	 * @author Shyrei Uladzimir
	 * Constructs an instance of exception
	 * @param userInput - User String type input
	 */
	public MyExceptions(String userInput) {
		super(userInput + " - неизвестная команда, повторите ввод...");
	}

}
