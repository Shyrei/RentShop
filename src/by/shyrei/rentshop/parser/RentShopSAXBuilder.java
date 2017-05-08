package by.shyrei.rentshop.parser;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import by.shyrei.rentshop.entities.SportEquipment;

public class RentShopSAXBuilder {

	public Map<SportEquipment, Integer> goods;
	public Map<SportEquipment, Integer> buildGoods(String fileName) {

		try {

			XMLReader xmlReader = XMLReaderFactory.createXMLReader();
			SAXRentShopDataParser rentShopHandler = new SAXRentShopDataParser();
			xmlReader.setContentHandler((ContentHandler) rentShopHandler);
			SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
			SAXParser saxParser = saxParserFactory.newSAXParser();
			File f = new File(fileName);
			saxParser.parse(f, rentShopHandler);
			if (rentShopHandler != null) {
				goods = rentShopHandler.getRentGoods();
			}
		} catch (SAXException e) {
			System.err.println("Problem of parser");

		} catch (IOException e) {
			System.err.println("Problem of reading file");

		} catch (ParserConfigurationException e) {
			System.err.println("Error configuration parser: " + e);
		}

		return goods;
	}

}
