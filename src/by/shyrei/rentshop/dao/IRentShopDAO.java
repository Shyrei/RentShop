package by.shyrei.rentshop.dao;

import by.shyrei.rentshop.entities.RentUnit;
import by.shyrei.rentshop.entities.Shop;


public interface IRentShopDAO {
	
	Shop readGoods();
	
	void addGoodToRent(String goodName, RentUnit units, Shop goods);
	
	void returnGoodToShop(String goodName, RentUnit units, Shop goods);
	
	void showAllGoods(Shop goods);
		

}
