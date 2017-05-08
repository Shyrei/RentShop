package by.shyrei.rentshop.runer;

import by.shyrei.rentshop.services.Operator;
import by.shyrei.rentshop.utils.Messages;

public class RentShopRuner {

	public static void main(String[] args) {

		Operator operator = new Operator();
		operator.runingProgram();
		System.out.println(Messages.EXIT_PROGRAM);
	}
}
