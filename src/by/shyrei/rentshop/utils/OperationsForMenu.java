package by.shyrei.rentshop.utils;

public enum OperationsForMenu {
	
	INIT, RENT, EXIT, LOAD_FROM_FILE, SAVE;
	
	public static OperationsForMenu menuOperation (String s) throws MyExceptions {
		
		switch (s) {
		case "1":
			return INIT;
		case "2":
			return RENT;
		case "3":
			return LOAD_FROM_FILE;
		case "4":
			return SAVE;
		case "0":
			return EXIT;			
		default:
			throw new MyExceptions(s);
		}		
	}

}
