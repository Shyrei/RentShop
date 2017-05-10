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
import by.shyrei.rentshop.utils.Messages;
import by.shyrei.rentshop.utils.parser.RentShopSAXBuilder;

public class XmlDAOImpl implements IRentShopDAO {

	private SportEquipment[] inRentGoods;
	private Map<SportEquipment, Integer> inShopGoods;

	@Override
	public Shop readGoods() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
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

	@Override
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

	@Override
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

	public boolean checkGoodinShop(String goodName, Shop goods) {
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

	public boolean checkGoodinRent(String goodName, RentUnit units) {
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

	public Shop initShop() throws SAXException, IOException, ParserConfigurationException {
		Shop goodsList = new Shop();
		RentShopSAXBuilder init = new RentShopSAXBuilder();
		goodsList.setGood(init.buildGoods("shop.xml"));
		return goodsList;
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

	public int findValueGood(String goodName, Shop goods) {
		inShopGoods = goods.getGood();
		int newValue = 0;
		for (Map.Entry<SportEquipment, Integer> entry : inShopGoods.entrySet()) {
			if (entry.getKey().getTitle().equals(goodName)) {
				newValue = entry.getValue();
			}
		}
		return newValue;
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

	// public Map<SportEquipment, Integer> checkGoodinShop(String goodName, Shop
	// goods) {
	// inShopGoods = goods.getGood();
	// Map<SportEquipment, Integer> goodIn = new HashMap();
	// for (Map.Entry<SportEquipment, Integer> entry : inShopGoods.entrySet()) {
	// if (entry.getKey().getTitle().equals(goodName)) {
	// goodIn.put(entry.getKey(), entry.getValue());
	// }
	// }
	// return goodIn;
	// }

}
