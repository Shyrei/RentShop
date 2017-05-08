package by.shyrei.rentshop.utils;

public enum OperationsForMenu {
	
	CLIENT, ACCOUNT, EXIT, LOAD_FROM_FILE, SAVE;
	
	public static OperationsForMenu menuOperation (String s) throws MyExceptions {
		
		switch (s) {
		case "1":
			return CLIENT;
		case "2":
			return ACCOUNT;
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
