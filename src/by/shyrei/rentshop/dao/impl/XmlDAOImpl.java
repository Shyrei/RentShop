package by.shyrei.rentshop.dao.impl;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

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
 * @author Uladzimir
 *
 */
public class XmlDAOImpl implements IRentShopDAO {

	private SportEquipment[] inRentGoods;
	private Map<SportEquipment, Integer> inShopGoods;

	@Override
	public Shop initShop() throws DAOException {
		Shop goodsList = new Shop();
		try {
			RentShopSAXBuilder init = new RentShopSAXBuilder();
			goodsList.setGood(init.buildGoods("shop.xml"));
		} catch (SAXException e) {
			throw new DAOException(Messages.SAX_EXCEPTION + e.getMessage());
		} catch (IOException e) {
			throw new DAOException(Messages.PARSER_IO_EXCEPTION + e.getMessage());
		} catch (ParserConfigurationException e) {
			throw new DAOException(Messages.PARSER_CONFIG_EXCEPTION + e.getMessage());
		}
		return goodsList;
	}

	public void showAllGoods(Shop goods) {
		inShopGoods = goods.getGood();
		if (inShopGoods.isEmpty()) {
			System.out.println(Messages.GOODS_EMPTY);
			return;
		}
		for (Map.Entry<SportEquipment, Integer> entry : inShopGoods.entrySet()) {
			System.out.print(entry.getKey());
			System.out.println("   Кол-во в магазине: " + entry.getValue() + " шт.");
		}
	}

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
						if (good.getTitle().equals(goodName)) {
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

	public void returnGoodToShop(String goodName, RentUnit units, Shop goods) {
		inShopGoods = goods.getGood();
		inRentGoods = units.getUnits();
		if (checkGoodinRent(goodName, units)) {
			for (int i = 0; i < inRentGoods.length; i++) {
				if (inRentGoods[i] != null && inRentGoods[i].getTitle().equals(goodName)) {
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

	public void findGood(String goodName, Shop goods) {
		inShopGoods = goods.getGood();
		for (Map.Entry<SportEquipment, Integer> entry : inShopGoods.entrySet()) {
			if (entry.getKey().getTitle().equals(goodName)) {
				System.out.print(entry.getKey());
				System.out.println(";      Кол-во в магазине: " + entry.getValue() + " шт.");
			}
		}
	}

	private boolean checkGoodinShop(String goodName, Shop goods) {
		inShopGoods = goods.getGood();
		boolean check = false;
		for (Map.Entry<SportEquipment, Integer> entry : inShopGoods.entrySet()) {
			if (entry.getKey().getTitle().equals(goodName)) {
				check = true;
			}
		}
		if (check == false) {
			System.out.println(Messages.GOOD_IS_NOT_IN_SHOP);
		}
		return check;
	}

	private boolean checkGoodinRent(String goodName, RentUnit units) {
		boolean check = false;
		inRentGoods = units.getUnits();
		for (int i = 0; i < inRentGoods.length; i++) {
			if (inRentGoods[i] != null && inRentGoods[i].getTitle().equals(goodName)) {
				check = true;
			}
		}
		if (check == false) {
			System.out.println(Messages.GOOD_IS_NOT_RENT);
		}
		return check;
	}

	private int findValueGood(String goodName, Shop goods) {
		inShopGoods = goods.getGood();
		int newValue = 0;
		for (Map.Entry<SportEquipment, Integer> entry : inShopGoods.entrySet()) {
			if (entry.getKey().getTitle().equals(goodName)) {
				newValue = entry.getValue();
			}
		}
		return newValue;
	}

}
