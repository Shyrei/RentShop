package by.shyrei.rentshop.utils.parser;

import java.util.HashMap;
import java.util.Map;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import by.shyrei.rentshop.entities.Category;
import by.shyrei.rentshop.entities.SportEquipment;

public class SAXRentShopDataParser extends DefaultHandler {
	
	

	private Category category;
	private SportEquipment sportEquipment;
	private Integer numberSportEquipment;
	public Map<SportEquipment, Integer> goods = new HashMap<>();
	private boolean inSportEquipment;
	private boolean inCategory;
	private boolean inTitle;
	private boolean inPrice;
	private boolean inRent;
	private boolean inSum;	
	

	public void buildListVouchers(String fileName) {}

	@Override
	public void characters(char[] ch, int start, int end) {
		String s = new String(ch, start, end);
		if (inSportEquipment) {
			inSportEquipment = false;
		} else if (inCategory) {
			category.setName(s);
			sportEquipment.setCategory(category);
			inCategory = false;
		} else if (inTitle) {
			sportEquipment.setTitle(s);
			inTitle = false;
		} else if (inPrice) {
			sportEquipment.setPrice(Integer.parseInt(s));
			inPrice = false;
		} else if (inRent) {
			sportEquipment.setInRent(Boolean.parseBoolean(s));
			inRent = false;
		} else if (inSum) {
			numberSportEquipment = Integer.parseInt(s);
			inSum = false;
		}
	}

	/**
	 * Takes action when the parser finishes processing the element. Adds the
	 * initialized voucher object with info obtained from the parsed document
	 * into a collection
	 * 
	 * @param uri
	 * @param localName
	 * @param qName
	 */

	@Override
	public void endElement(String uri, String localName, String qName) {
		if (qName.equals("sportEquipment")) {
			goods.put(sportEquipment, numberSportEquipment);	
			sportEquipment = null;
		}
	}

	public Map<SportEquipment, Integer> getRentGoods() {
		return goods;
	}

	/**
	 * Takes action when the parser comes across the new element
	 * 
	 * @param uri
	 * @param localName
	 * @param qName
	 * @param atts
	 * @throws SAXException
	 */

	public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {

		switch (qName) {
		case "sportEquipment":
			sportEquipment = new SportEquipment();			
			inSportEquipment = true;
			break;
		case "category":
			category = new Category();			
			inCategory = true;
			break;
		case "title":
			inTitle = true;
			break;
		case "price":
			inPrice = true;
			break;
		case "inRent":
			inRent = true;
			break;
		case "sum":
			inSum = true;
			break;
		}
	}

	@Override
	public void warning(SAXParseException e) throws SAXException {
		// TODO Auto-generated method stub
		super.warning(e);
	}

	@Override
	public void error(SAXParseException e) throws SAXException {
		// TODO Auto-generated method stub
		super.error(e);
	}

	@Override
	public void fatalError(SAXParseException e) throws SAXException {
		// TODO Auto-generated method stub
		super.fatalError(e);
	}

	
	

}
