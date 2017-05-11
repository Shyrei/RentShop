package by.shyrei.rentshop.dao.impl;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import org.xml.sax.SAXException;

import by.shyrei.rentshop.dao.IRentShopDAO;
import by.shyrei.rentshop.entities.RentUnit;
import by.shyrei.rentshop.entities.Shop;
import by.shyrei.rentshop.entities.SportEquipment;
import by.shyrei.rentshop.utils.DAOException;
import by.shyrei.rentshop.utils.Messages;
import by.shyrei.rentshop.utils.parser.RentShopSAXBuilder;

/**
 * The implementation class of the DAO interface. In this class, basic methods
 * for working with DAO and the main logic of the program are implemented.
 * 
 * @author Shyrei Uladzimir
 *
 */
public class XmlDAOImpl implements IRentShopDAO {

	/**
	 * To change the store initialization file, change the constant
	 * PARSER_FILE_NAME.
	 */
	private final static String PARSER_FILE_NAME = "shop.xml";
	private SportEquipment[] inRentGoods;
	private Map<SportEquipment, Integer> inShopGoods;

	/**
	 * Method of initializing the store using a SAX parser from the XML file.
	 */
	@Override
	public Shop initShop() throws DAOException {
		Shop initGoodsList = new Shop();
		try {
			RentShopSAXBuilder init = new RentShopSAXBuilder();
			initGoodsList.setGood(init.buildGoods(PARSER_FILE_NAME));
		} catch (SAXException e) {
			throw new DAOException(Messages.SAX_EXCEPTION + e.getMessage());
		} catch (IOException e) {
			throw new DAOException(Messages.PARSER_IO_EXCEPTION + e.getMessage());
		}
		return initGoodsList;
	}

	/**
	 * The method prints out a list of all store goods.
	 * 
	 * @param goods
	 *            Object of the class Shop.
	 */
	public void showAllGoods(Shop goods) {
		inShopGoods = goods.getGood();
		if (inShopGoods.isEmpty()) {
			System.out.println(Messages.GOODS_EMPTY);
			return;
		}
		for (Map.Entry<SportEquipment, Integer> entry : inShopGoods.entrySet()) {
			System.out.print(entry.getKey());
			System.out.println(Messages.NUMBER_IN_SHOP + entry.getValue() + Messages.NUMBERS);
		}
	}

	/**
	 * The method prints out a list of all rent goods.
	 * 
	 * @param units
	 *            Object of the class RentUnit.
	 */
	public void showRentGoods(RentUnit units) {
		inRentGoods = units.getUnits();
		boolean isEmpty = false;
		for (int i = 0; i < inRentGoods.length; i++) {
			if (inRentGoods[i] != null) {
				System.out.println(inRentGoods[i]);
				isEmpty = true;
			}
		}
		if (isEmpty == false)
			System.out.println(Messages.RENT_EMPTY);
	}

	/**
	 * Method of adding goods to the customer's basket. Also it reduces the
	 * quantity of goods in the store.
	 * 
	 * @param goodName
	 *            Name of goods.
	 * @param units
	 *            List of goods in rent.
	 * @param goods
	 *            List of goods in shop.
	 */
	public void addGoodToRent(String goodName, RentUnit units, Shop goods) {
		if (checkGoodinShop(goodName, goods)) {
			SportEquipment good;
			inShopGoods = goods.getGood();
			inRentGoods = units.getUnits();
			Iterator<Map.Entry<SportEquipment, Integer>> iter = inShopGoods.entrySet().iterator();
			for (int i = 0; i < inRentGoods.length; i++) {
				if (inRentGoods[i] == null) {
					while (iter.hasNext()) {
						good = iter.next().getKey();
						if (good.getTitle().equalsIgnoreCase(goodName)) {
							inRentGoods[i] = good;
							int newValue = (inShopGoods.get(good));
							if (newValue <= 1) {
								iter.remove();
							} else {
								inShopGoods.put(good, newValue - 1);
							}
						}
					}
					System.out.println(Messages.GOOD_ADDED);
					break;
				} else if (i == inRentGoods.length - 1) {
					System.out.println(Messages.LIMIT_GOODS);
					break;
				}
			}
		}
	}

	/**
	 * The method returns the goods from the rent and adds it to the store.
	 * 
	 * @param goodName
	 *            Product name.
	 * @param units
	 *            List of goods in rent.
	 * @param goods
	 *            List of goods in shop.
	 */
	public void returnGoodToShop(String goodName, RentUnit units, Shop goods) {
		inShopGoods = goods.getGood();
		inRentGoods = units.getUnits();
		if (checkGoodinRent(goodName, units)) {
			for (int i = 0; i < inRentGoods.length; i++) {
				if (inRentGoods[i] != null && inRentGoods[i].getTitle().equalsIgnoreCase(goodName)) {
					int newValue = findValueGood(goodName, goods);
					if (newValue > 0) {
						inShopGoods.put(inRentGoods[i], newValue + 1);
					} else {
						inShopGoods.put(inRentGoods[i], 1);
					}
					inRentGoods[i] = null;
					System.out.println(Messages.GOOD_RETURN);
					break;
				}
			}
		}
	}

	/**
	 * The method takes the name of the product from the user and, if it is in
	 * the store, prints to the console.
	 * 
	 * @param goodName
	 *            Product name.
	 * @param goods
	 *            List of goods in shop.
	 */
	public void findAndPrintGood(String goodName, Shop goods) {
		inShopGoods = goods.getGood();
		for (Map.Entry<SportEquipment, Integer> entry : inShopGoods.entrySet()) {
			if (entry.getKey().getTitle().equalsIgnoreCase(goodName)) {
				System.out.print(entry.getKey());
				System.out.println(";      Кол-во в магазине: " + entry.getValue() + " шт.");
			}
		}
	}
	/**
	 * Checks if there is a product in the store.
	 * @param goodName Product name.
	 * @param goods List of goods in shop.
	 * @return Returns true if product is in shop and false if there is no good.
	 */
	private boolean checkGoodinShop(String goodName, Shop goods) {
		inShopGoods = goods.getGood();
		boolean check = false;
		for (Map.Entry<SportEquipment, Integer> entry : inShopGoods.entrySet()) {
			if (entry.getKey().getTitle().equalsIgnoreCase(goodName)) {
				check = true;
			}
		}
		if (check == false) {
			System.out.println(Messages.GOOD_IS_NOT_IN_SHOP);
		}
		return check;
	}

	/**
	 * Checks if there is a product in the rent.
	 * @param goodName
	 *            Product name
	 * @param units List of goods in rent.
	 * @return Returns true if product is in rent and false if there is no good.
	 */
	private boolean checkGoodinRent(String goodName, RentUnit units) {
		boolean check = false;
		inRentGoods = units.getUnits();
		for (int i = 0; i < inRentGoods.length; i++) {
			if (inRentGoods[i] != null && inRentGoods[i].getTitle().equalsIgnoreCase(goodName)) {
				check = true;
			}
		}
		if (check == false) {
			System.out.println(Messages.GOOD_IS_NOT_RENT);
		}
		return check;
	}

	/**
	 * The method returns the amount of the remaining good in the store.
	 * @param goodName
	 *            Product name.
	 * @param goods
	 *            List of goods in shop.
	 * @return Quantity of goods available for rent.
	 */
	private int findValueGood(String goodName, Shop goods) {
		inShopGoods = goods.getGood();
		int newValue = 0;
		for (Map.Entry<SportEquipment, Integer> entry : inShopGoods.entrySet()) {
			if (entry.getKey().getTitle().equalsIgnoreCase(goodName)) {
				newValue = entry.getValue();
			}
		}
		return newValue;
	}

}
