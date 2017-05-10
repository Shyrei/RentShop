package by.shyrei.rentshop.dao;

import by.shyrei.rentshop.entities.Shop;
import by.shyrei.rentshop.utils.DAOException;

/**
 * Interface DAO.
 * 
 * @author Shyrei Uladzimir
 * 
 */
public interface IRentShopDAO {

	Shop initShop() throws DAOException;

}
