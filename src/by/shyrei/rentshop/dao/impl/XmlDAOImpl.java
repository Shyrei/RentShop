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

	@Override
	public Shop readGoods() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void showAllGoods(Shop goods) {
		Map<SportEquipment, Integer> allGoods = goods.getGood();
		if (allGoods.isEmpty()) {
			System.out.println(Messages.GOODS_EMPTY);
			return;
		}
		for (Map.Entry<SportEquipment, Integer> entry : allGoods.entrySet()) {
			System.out.print(entry.getKey());
			System.out.println("   Кол-во в магазине: " + entry.getValue() + " шт.");
		}
	}

	// TODO доделать проверку на нулевой массив!
	public void showRentGoods(RentUnit units) {
		SportEquipment[] inRentGoods = units.getUnits();
		for (int i = 0; i < inRentGoods.length; i++) {
			if (inRentGoods[i] != null) {
				System.out.println(inRentGoods[i]);
			}
		}
	}

	@Override
	public void addGoodToRent(String goodName, RentUnit units, Shop goods) {
		SportEquipment good;
		Map<SportEquipment, Integer> inShopGoods = goods.getGood();
		SportEquipment[] inRentGoods = units.getUnits();
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
			} else {
				if (i == (inRentGoods.length - 1)) {
					System.out.println(Messages.LIMIT_GOODS);
					break;
				}
			}
		}
	}

	@Override
	public void returnGoodToShop(String goodName, RentUnit units, Shop goods) {
		SportEquipment good;
		Map<SportEquipment, Integer> inShopGoods = goods.getGood();
		SportEquipment[] inRentGoods = units.getUnits();
		for (int i = 0; i < inRentGoods.length; i++) {
			for (Iterator<Map.Entry<SportEquipment, Integer>> iter = inShopGoods.entrySet().iterator(); iter
					.hasNext();) {
				good = iter.next().getKey();
				if (good.getTitle().equals(goodName) && good.equals(inRentGoods[i])) {
					inRentGoods[i] = null;
					int newValue = (inShopGoods.get(good));
					inShopGoods.put(good, newValue + 1);
					return;
				} else if (inRentGoods[i] != null && inRentGoods[i].getTitle().equals(goodName)) {
					inShopGoods.put(inRentGoods[i], 1);
					inRentGoods[i] = null;
					return;
				}
			}
		}
	}

	public Shop initShop() throws SAXException, IOException, ParserConfigurationException {
		Shop goodsList = new Shop();
		RentShopSAXBuilder init = new RentShopSAXBuilder();
		goodsList.setGood(init.buildGoods("shop.xml"));
		return goodsList;
	}

	public void findGood(String goodName, Shop goods) {
		//SportEquipment good = null;
		Map<SportEquipment, Integer> inShopGoods = goods.getGood();
		for (Map.Entry<SportEquipment, Integer> entry : inShopGoods.entrySet()) {
			if (entry.getKey().getTitle().equals(goodName)) {
				System.out.print(entry.getKey());
				System.out.println(";      Кол-во в магазине: " + entry.getValue() + " шт.");
			}
		}
	}

}
