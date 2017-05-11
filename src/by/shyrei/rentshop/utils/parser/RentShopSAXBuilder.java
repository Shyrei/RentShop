package by.shyrei.rentshop.utils.parser;

import java.io.IOException;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import by.shyrei.rentshop.entities.SportEquipment;

/**
 * Class is designed to launch the SAX-parser.
 * 
 * @author Shyrei Uladzimir
 * 
 */
public class RentShopSAXBuilder {

	private Map<SportEquipment, Integer> goods;

	/**
	 * Launches the SAX-parser using factory and places parsed objects of
	 * RentShop type into a collection.
	 * 
	 * @param fileName
	 *            The file from which it will be read
	 * @return Instance of class Shop
	 * @throws SAXException
	 *             Processes and passes on an SAXException
	 * @throws IOException
	 *             Processes and passes on an IOException
	 */
	public Map<SportEquipment, Integer> buildGoods(String fileName)
			throws SAXException, IOException {

		try {
			XMLReader xmlReader = XMLReaderFactory.createXMLReader();
			SAXRentShopDataParser rentShopHandler = new SAXRentShopDataParser();
			xmlReader.setContentHandler((ContentHandler) rentShopHandler);
			xmlReader.parse(fileName);
			if (rentShopHandler != null) {
				goods = rentShopHandler.getRentGoods();
			}
		} catch (SAXException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		}
		return goods;
	}

}
