package by.shyrei.rentshop.utils;

public enum OperationsForRent {
ADD_GOOD, RETURN_GOOD, FIND_GOOD, MY_GOOD, ALL_GOOD, RETURN;
	
	public static OperationsForRent rentOperation (String s) throws MyExceptions {
		
		switch (s) {
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
			throw new MyExceptions(s);
		}		
	}
}
