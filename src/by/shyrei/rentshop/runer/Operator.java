//package by.shyrei.rentshop.runer;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
//import java.util.List;
//
//import by.shyrei.rentshop.services.Test;
//import by.shyrei.rentshop.utils.Messages;
//import by.shyrei.rentshop.utils.MyExceptions;
//import by.shyrei.rentshop.utils.OperationsForMenu;
//
//
//public class Operator implements Messages {
//	
//	/**
//	 * @param args
//	 * @throws IOException
//	 */
//	private boolean runProgram;
//	private boolean clientWork;
//	private boolean accountWork;
//	private BufferedReader reader;
//
//	Test vova = new Test();
//	
//
//	public Operator() {
//		runProgram = false;
//		clientWork = false;
//		accountWork = false;
//
//	}
//
//	/**
//	 * The main method for the control program
//	 */
//	public void runingProgram() {
//		runProgram = true;
//		reader = new BufferedReader(new InputStreamReader(System.in));
//		do {
//			while (runProgram) {
//				menuWork();
//			}
//		} while (runProgram);
//		try {
//			reader.close();
//		} catch (IOException e) {
//			System.out.println(IO_EXCEPTION);
//		}
//	}
//
//	/**
//	 * The main menu of the program. Recording / reading, the transition to the
//	 * customers or accounts.
//	 */
//	protected void menuWork() {
//		menuList();
//		try {
//			String userInput = reader.readLine();
//			OperationsForMenu operationsMenu = OperationsForMenu.menuOperation(userInput);
//			switch (operationsMenu) {
//			case CLIENT:
//				clientWork = true;
//				while (clientWork)
//					clientWork();
//				break;
//			case ACCOUNT:
//				accountWork = true;
//				while (accountWork)
//					accountWork();
//				break;
//			case LOAD_FROM_FILE:
//				System.out.print(ENTER_FILE_NAME);
//				File fileNameLoad = new File(reader.readLine());
//				readConfigurationFromFile(fileNameLoad);
//				clientWork = true;
//				break;
//			case SAVE:
//				System.out.print(ENTER_FILE_NAME);
//				File fileNameSave = new File(reader.readLine());
//				writeConfigurationToFile(fileNameSave);
//				break;
//			case EXIT:
//				runProgram = false;
//				clientWork = false;
//				accountWork = false;
//			}
//		} catch (MyExceptions e) {
//			System.out.println(e.getMessage());
//		} catch (FileNotFoundException e) {
//			System.out.println(FILE_NOT_FOUND);
//		} catch (ClassNotFoundException e) {
//			System.out.println(e.getMessage());
//		}catch (IOException e) {
//			e.printStackTrace();
//			System.out.println(IO_EXCEPTION);
//		}
//	}
//
//	/**
//	 * Method for writing data to a file.
//	 */
//	private void writeConfigurationToFile(File file) throws FileNotFoundException, IOException {
//		ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(file));
//		stream.writeObject(ClientWorker.listClient);
//		stream.close();
//		System.out.println(FILE_SAVE);
//
//	}
//
//	/**
//	 * A method for reading data from a file.
//	 */
//	private void readConfigurationFromFile(File file)
//			throws FileNotFoundException, IOException, ClassNotFoundException {
//		ObjectInputStream stream = new ObjectInputStream(new FileInputStream(file));
//		ClientWorker.listClient = (List<Client>) stream.readObject();
//		stream.close();
//		System.out.println(FILE_LOAD);
//
//	}
//
//	/**
//	 * Client Menu. You can create, delete, find customers and display a list of
//	 * clients.
//	 * 
//	 * @see Client
//	 */
//	protected void clientWork() {
//		clientList();
//		try {
//			String userInput = reader.readLine();
//			OperationsForClient operationClient = OperationsForClient.menuClient(userInput);
//			switch (operationClient) {
//			case CREATE:
//				vova.create();
//				break;
//			case VIEW_LIST:
//				vova.print();
//				break;
//			case REMOVE_CLIENT:
//				vova.remove();
//				break;
//			case FIND_CLIENT:
//				vova.find();
//				break;
//			case RETURN:
//				clientWork = false;
//			}
//		} catch (UnknownMyException ex) {
//			System.out.println(ex.getMessage());
//		} catch (IOException e) {
//			e.printStackTrace();
//			System.out.println(IO_EXCEPTION);
//		}
//	}
//
//	/**
//	 * Accounts menu. You can find the account, delete it, add new accounts.
//	 * View the positive and negative account balance.
//	 * 
//	 * @see Account
//	 */
//	protected void accountWork() {
//		accountList();
//		try {
//			String userInput = reader.readLine();
//			OperationsForAccount operationAccount = OperationsForAccount.menuClient(userInput);
//			switch (operationAccount) {
//			case ADD_ACCOUNT:
//				olya.addAccount();
//				;
//				break;
//			case VIEW_LIST_OF_ACCOUNT:
//				olya.print();
//				break;
//			case VIEW_LIST_OF_CLIENT:
//				vova.print();
//				break;
//			case FIND_ACCOUNT:
//				olya.find();
//				break;
//			case DELETE_ACCOUNT:
//				olya.remove();
//				break;
//			case BALANCE_PLUS:
//				olya.BalancePlus();
//				break;
//			case BALANCE_MINUS:
//				olya.BalanceMinus();
//				break;
//			case RETURN:
//				accountWork = false;
//			}
//		} catch (UnknownMyException ex) {
//			System.out.println(ex.getMessage());
//		} catch (IOException e) {
//			e.printStackTrace();
//			System.out.println(IO_EXCEPTION);
//		}
//
//	}
//
//	/**
//	 * Instructions for displaying for main menu.
//	 * 
//	 */
//	protected void menuList() {
//		StringBuilder builder = new StringBuilder();
//		builder.append(SELECT_USER).append(SPACE).append(WORK_WITH_CLIENT).append(WORK_WITH_ACCOUNT).append(LOAD)
//				.append(SAVE).append(EXIT_FROM_PROGRAM).append(SPACE);
//		System.out.println(builder.toString());
//	}
//
//	/**
//	 * Instructions for displaying for Client menu.
//	 * 
//	 */
//	protected void clientList() {
//		StringBuilder builder = new StringBuilder();
//		builder.append(SPACE).append(CREATE_CLIENT).append(VIEW_LIST_OF_CLIENT).append(DELETE_CLIENT)
//				.append(FIND_CLIENT).append(RETURN_MAIN).append(SPACE);
//		System.out.println(builder.toString());
//	}
//
//	/**
//	 * Instructions for displaying for Account menu.
//	 * 
//	 */
//	protected void accountList() {
//		StringBuilder builder = new StringBuilder();
//		builder.append(SPACE).append(ADD_ACCOUNT).append(VIEW_LIST_OF_ACCOUNT).append(VIEW_LIST_CLIENT)
//				.append(FIND_ACCOUNT).append(DELETE_ACCOUNT).append(BALANCE_PLUS).append(BALANCE_MINUS)
//				.append(RETURN_MAIN).append(SPACE);
//		System.out.println(builder.toString());
//	}
//
//}
