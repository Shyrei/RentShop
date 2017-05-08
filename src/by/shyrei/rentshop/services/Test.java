package by.shyrei.rentshop.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import by.shyrei.rentshop.dao.impl.XmlDAOImpl;
import by.shyrei.rentshop.entities.Category;
import by.shyrei.rentshop.entities.RentUnit;
import by.shyrei.rentshop.entities.Shop;
import by.shyrei.rentshop.entities.SportEquipment;
import by.shyrei.rentshop.parser.RentShopSAXBuilder;

public class Test {

//	InitShop init = new InitShop();
//	Map<SportEquipment, Integer> allgoods = init.initShop();
//	Map<SportEquipment, Integer> inShopGoods = allgoods;
//	SportEquipment[] inRentGoods = new SportEquipment[3];
//
//	public void showAllGoods() {
//		for (Map.Entry entry : allgoods.entrySet()) {
//			System.out.print(entry.getKey());
//			System.out.println("   Кол-во: " + entry.getValue() + " шт.");
//		}
//	}
//
//	public void showAllGoodsinShop() {
//		for (Map.Entry entry : inShopGoods.entrySet()) {
//			System.out.print(entry.getKey());
//			System.out.println("   Кол-во: " + entry.getValue() + " шт.");
//		}
//
//	}
//
//	public void showAllUserGood() {
//		for (int i = 0; i < inRentGoods.length; i++) {
//			if (inRentGoods[i] != null)
//				System.out.println(inRentGoods[i]);
//		}
//	}
//
//	public void addGoodToRent(String goodName) {
//		SportEquipment good;
//		Iterator<Map.Entry<SportEquipment, Integer>> iter = inShopGoods.entrySet().iterator();
//		for (int i = 0; i < inRentGoods.length; i++) {
//			if (inRentGoods[i] == null) {
//				while (iter.hasNext()) {
//					good = iter.next().getKey();
//					if (good.getTitle().equals(goodName)) {
//						inRentGoods[i] = good;
//						int newValue = (inShopGoods.get(good));
//						if (newValue <= 1) {
//							iter.remove();
//						} else {
//							inShopGoods.put(good, newValue - 1);
//						}
//					}
//				}
//			} else {
//				if (i == (inRentGoods.length - 1)) {
//					System.out.println("Вы можете взять в прокат только 3-и товара.");
//					break;
//				}
//			}
//		}
//
//	}
//
//	public void returnGoodToShop(String goodName) {
//		SportEquipment good;
//		for (int i = 0; i < inRentGoods.length; i++) {
//			for (Iterator<Map.Entry<SportEquipment, Integer>> iter = inShopGoods.entrySet().iterator(); iter
//					.hasNext();) {
//				good = iter.next().getKey();
//				if (good.getTitle().equals(goodName) && good.equals(inRentGoods[i])) {
//					inRentGoods[i] = null;
//					int newValue = (inShopGoods.get(good));
//					inShopGoods.put(good, newValue + 1);
//					return;
//				}
//			}
//		}
//
//	}

	public static void main(String[] args) {
		XmlDAOImpl run = new XmlDAOImpl();
		RentUnit units = new RentUnit();
		SportEquipment[] inRentGoods = new SportEquipment[3];
		units.setUnits(inRentGoods);				
		Shop goods = run.readGoods();
		System.out.println("Все товары:");
		run.showAllGoods(goods);
		run.addGoodToRent("Лыжи", units, goods);
		run.addGoodToRent("Лыжи", units, goods);
		//run.addGoodToRent("Зонт", units, goods);
		run.addGoodToRent("Лыжи", units, goods);
		System.out.println("В аренде товары:");
		run.showRentGoods(units);
		System.out.println("Все товары:");
		run.showAllGoods(goods);
		System.out.println("");
		run.returnGoodToShop("Лыжи", units, goods);
		run.returnGoodToShop("Лыжи", units, goods);
		run.returnGoodToShop("Лыжи", units, goods);
		//run.returnGoodToShop("Лыжи", units, goods);
		System.out.println("В аренде товары:");
		run.showRentGoods(units);
		System.out.println("Все товары:");
		run.showAllGoods(goods);
	
		

	}

}