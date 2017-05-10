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

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import by.shyrei.rentshop.dao.impl.XmlDAOImpl;
import by.shyrei.rentshop.entities.RentUnit;
import by.shyrei.rentshop.entities.Shop;
import by.shyrei.rentshop.entities.SportEquipment;
import by.shyrei.rentshop.utils.Messages;
import by.shyrei.rentshop.utils.MyExceptions;
import by.shyrei.rentshop.utils.OperationsForMenu;
import by.shyrei.rentshop.utils.OperationsForRent;

public class Operator {

	private boolean runProgram;
	private boolean rentWork;
	private boolean initShop;
	private BufferedReader reader;
	XmlDAOImpl operator = new XmlDAOImpl();

	RentUnit units = new RentUnit();
	SportEquipment[] inRentGoods = new SportEquipment[3];
	Shop goods;

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
		do {
			while (runProgram) {
				menuWork();
			}
		} while (runProgram);

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
		} catch (MyExceptions e) {
			System.out.println(e.getMessage());
		} catch (FileNotFoundException e) {
			System.out.println(Messages.FILE_NOT_FOUND);
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(Messages.IO_EXCEPTION);
			System.out.println(e.getMessage());
		} catch (SAXException e) {
			System.out.println(Messages.SAX_EXCEPTION);
			System.out.println(e.getMessage());
		} catch (ParserConfigurationException e) {
			System.out.println(Messages.PARSER_CONFIG_EXCEPTION);
			System.out.println(e.getMessage());
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
				System.out.println("Введите имя товара:");
				String goodName = reader.readLine();
				operator.addGoodToRent(goodName, units, goods);
				break;
			case RETURN_GOOD:
				System.out.println("Введите имя товара:");
				String goodNameReturn = reader.readLine();
				operator.returnGoodToShop(goodNameReturn, units, goods);
				break;
			case FIND_GOOD:
				System.out.println("Введите имя товара:");
				String goodNameFind = reader.readLine();
				operator.findGood(goodNameFind, goods);
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
		} catch (MyExceptions ex) {
			System.out.println(ex.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(Messages.IO_EXCEPTION);
		}
	}

	/**
	 * Instructions for displaying for main menu before initializing the shop.
	 * 
	 */
	protected void initList() {
		StringBuilder builder = new StringBuilder();
		builder.append(Messages.SPACE).append(Messages.INIT_SHOP).append(Messages.LOAD).append(Messages.SAVE)
				.append(Messages.EXIT_FROM_PROGRAM).append(Messages.SPACE);
		System.out.println(builder.toString());
	}

	/**
	 * Instructions for displaying for main menu after initializing the shop.
	 * 
	 */
	protected void menuList() {
		StringBuilder builder = new StringBuilder();
		builder.append(Messages.SPACE).append(Messages.SHOW_GOODS_IN_RENT).append(Messages.LOAD).append(Messages.SAVE)
				.append(Messages.EXIT_FROM_PROGRAM).append(Messages.SPACE);
		System.out.println(builder.toString());
	}

	/**
	 * Instructions for displaying for Rent menu.
	 * 
	 */
	protected void rentList() {
		StringBuilder builder = new StringBuilder();
		builder.append(Messages.SPACE).append(Messages.ADD_GOOD_TO_RENT).append(Messages.RETURN_GOOD)
				.append(Messages.FIND_GOOD).append(Messages.SHOW_MY_GOODS).append(Messages.SHOW_FREE_GOODS)
				.append(Messages.RETURN_MAIN).append(Messages.SPACE);
		System.out.println(builder.toString());
	}

}
