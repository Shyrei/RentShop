package by.shyrei.rentshop.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import by.shyrei.rentshop.dao.impl.XmlDAOImpl;
import by.shyrei.rentshop.entities.RentUnit;
import by.shyrei.rentshop.entities.Shop;
import by.shyrei.rentshop.entities.SportEquipment;
import by.shyrei.rentshop.utils.DAOException;
import by.shyrei.rentshop.utils.Messages;
import by.shyrei.rentshop.utils.OperationsForMenu;
import by.shyrei.rentshop.utils.OperationsForRent;
import by.shyrei.rentshop.utils.UnknownOperationException;

/**
 * This class for communicating with the user using the console. User can
 * initialize the store, read / write data, rent goods, return goods, receive
 * reports.
 * 
 * @author Shyrei Uladzimir
 *
 */
public class Operator {
	/**
	 * To change the maximum number of orders, change the constant MAX_NUMBER_OF_ORDERS
	 */
	private static final int MAX_NUMBER_OF_ORDERS = 3;
	private XmlDAOImpl operator = new XmlDAOImpl();
	private SportEquipment[] inRentGoods = new SportEquipment[MAX_NUMBER_OF_ORDERS];
	private RentUnit units = new RentUnit();
	private Shop goods;
	private BufferedReader reader;
	private boolean runProgram;
	private boolean rentWork;
	private boolean initShop;

	/**
	 * Constructor an instance of Operator
	 */
	public Operator() {
		runProgram = false;
		rentWork = false;
		initShop = false;
	}

	/**
	 * The main method for the control program
	 */
	public void runingProgram() {
		runProgram = true;
		reader = new BufferedReader(new InputStreamReader(System.in));

		while (runProgram) {
			menuWork();
		}
		try {
			reader.close();
		} catch (IOException e) {
			System.out.println(Messages.IO_EXCEPTION);
		}
	}

	/**
	 * The main menu of the program. We can init shop,
	 * Serialization/Deserializationg data.
	 * 
	 * @see Operator writeConfigurationToFile()
	 * @see readConfigurationFromFile
	 * 
	 */
	protected void menuWork() {
		if (initShop) {
			menuList();
		} else {
			initList();
		}

		try {
			String userInput = reader.readLine();
			OperationsForMenu operationsMenu = OperationsForMenu.menuOperation(userInput);
			switch (operationsMenu) {
			case INIT:
				goods = operator.initShop();
				units.setUnits(inRentGoods);
				initShop = true;
				rentWork = true;
				while (rentWork)
					rentWork();
				break;
			case RENT:
				rentWork = true;
				while (rentWork)
					rentWork();
				break;
			case LOAD_FROM_FILE:
				System.out.print(Messages.ENTER_FILE_NAME);
				File fileNameLoad = new File(reader.readLine());
				readConfigurationFromFile(fileNameLoad);
				initShop = true;
				rentWork = true;
				while (rentWork)
					rentWork();
				break;
			case SAVE:
				System.out.print(Messages.ENTER_FILE_NAME);
				File fileNameSave = new File(reader.readLine());
				writeConfigurationToFile(fileNameSave);
				break;
			case EXIT:
				runProgram = false;
				rentWork = false;
			}		
		} catch (FileNotFoundException e) {
			System.out.println(Messages.FILE_NOT_FOUND);
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (UnknownOperationException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(Messages.IO_EXCEPTION);
			System.out.println(e.getMessage());
		} catch (DAOException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Rent Menu. You can rent,return or find good and show a list of goods.
	 * 
	 * @see XmlDAOImpl
	 */
	protected void rentWork() {
		rentList();
		try {
			String userInput = reader.readLine();
			OperationsForRent operationClient = OperationsForRent.rentOperation(userInput);
			switch (operationClient) {
			case ADD_GOOD:
				System.out.println(Messages.INPUT_GOOD_NAME);
				String goodName = reader.readLine();
				operator.addGoodToRent(goodName, units, goods);
				break;
			case RETURN_GOOD:
				System.out.println(Messages.INPUT_GOOD_NAME);
				String goodNameReturn = reader.readLine();
				operator.returnGoodToShop(goodNameReturn, units, goods);
				break;
			case FIND_GOOD:
				System.out.println(Messages.INPUT_GOOD_NAME);
				String goodNameFind = reader.readLine();
				operator.findAndPrintGood(goodNameFind, goods);
				break;
			case MY_GOOD:
				operator.showRentGoods(units);
				break;
			case ALL_GOOD:
				operator.showAllGoods(goods);
				break;
			case RETURN:
				rentWork = false;
			}
		} catch (UnknownOperationException ex) {
			System.out.println(ex.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(Messages.IO_EXCEPTION);
		}
	}

	/**
	 * Method for Serialization data to a file.
	 */
	private void writeConfigurationToFile(File file) throws FileNotFoundException, IOException {
		ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(file));
		stream.writeObject(goods);
		stream.writeObject(units);
		stream.close();
		System.out.println(Messages.FILE_SAVE);

	}

	/**
	 * Method for Deserialization data from a file.
	 */
	private void readConfigurationFromFile(File file)
			throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream stream = new ObjectInputStream(new FileInputStream(file));
		goods = (Shop) stream.readObject();
		units = (RentUnit) stream.readObject();
		stream.close();
		System.out.println(Messages.FILE_LOAD);

	}

	/**
	 * Instructions for displaying for main menu before initializing the shop.
	 * 
	 */
	private void initList() {
		StringBuilder builder = new StringBuilder();
		builder.append(Messages.SPACE).append(Messages.INIT_SHOP).append(Messages.LOAD).append(Messages.SAVE)
				.append(Messages.EXIT_FROM_PROGRAM).append(Messages.SPACE);
		System.out.println(builder.toString());
	}

	/**
	 * Instructions for displaying for main menu after initializing the shop.
	 * 
	 */
	private void menuList() {
		StringBuilder builder = new StringBuilder();
		builder.append(Messages.SPACE).append(Messages.LOAD).append(Messages.SAVE).append(Messages.SHOW_GOODS_IN_RENT)
				.append(Messages.EXIT_FROM_PROGRAM).append(Messages.SPACE);
		System.out.println(builder.toString());
	}

	/**
	 * Instructions for displaying for Rent menu.
	 * 
	 */
	private void rentList() {
		StringBuilder builder = new StringBuilder();
		builder.append(Messages.SPACE).append(Messages.ADD_GOOD_TO_RENT).append(Messages.RETURN_GOOD)
				.append(Messages.FIND_GOOD).append(Messages.SHOW_MY_GOODS).append(Messages.SHOW_FREE_GOODS)
				.append(Messages.RETURN_MAIN).append(Messages.SPACE);
		System.out.println(builder.toString());
	}

}
