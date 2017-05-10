package by.shyrei.rentshop.utils.parser;

import java.util.HashMap;
import java.util.Map;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import by.shyrei.rentshop.entities.Category;
import by.shyrei.rentshop.entities.SportEquipment;
import by.shyrei.rentshop.entities.SummerSportEquipment;
import by.shyrei.rentshop.entities.WinterSportEquipment;

public class SAXRentShopDataParser extends DefaultHandler {

	private Category category;
	private WinterSportEquipment winterSportEquipment;
	private SummerSportEquipment summerSportEquipment;
	private Integer numberSportEquipment;
	private Map<SportEquipment, Integer> goods = new HashMap<>();
	private boolean inWinterSportEquipment;
	private boolean inSummerSportEquipment;
	private boolean inCategory;
	private boolean inTitle;
	private boolean inPrice;
	private boolean inSum;

	public void buildRentShops (String fileName) {
	}

	@Override
	public void characters(char[] ch, int start, int end) {
		String s = new String(ch, start, end);
		if (inWinterSportEquipment) {
			if (inCategory) {
				category.setName(s);
				winterSportEquipment.setCategory(category);
				inCategory = false;
			} else if (inTitle) {
				winterSportEquipment.setTitle(s);
				inTitle = false;
			} else if (inPrice) {
				winterSportEquipment.setPrice(Integer.parseInt(s));
				inPrice = false;
			} else if (inSum) {
				numberSportEquipment = Integer.parseInt(s);
				inSum = false;
			}
		}

		if (inSummerSportEquipment) {
			if (inCategory) {
				category.setName(s);
				summerSportEquipment.setCategory(category);
				inCategory = false;
			} else if (inTitle) {
				summerSportEquipment.setTitle(s);
				inTitle = false;
			} else if (inPrice) {
				summerSportEquipment.setPrice(Integer.parseInt(s));
				inPrice = false;
			} else if (inSum) {
				numberSportEquipment = Integer.parseInt(s);
				inSum = false;
			}
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
		if (qName.equals("winterSportEquipment")) {
			goods.put(winterSportEquipment, numberSportEquipment);
			winterSportEquipment = null;
			inWinterSportEquipment = false;
		} else if (qName.equals("summerSportEquipment")) {
			goods.put(summerSportEquipment, numberSportEquipment);
			summerSportEquipment = null;
			inSummerSportEquipment = false;
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
		case "winterSportEquipment":
			winterSportEquipment = new WinterSportEquipment();
			inWinterSportEquipment = true;
			break;
		case "summerSportEquipment":
			summerSportEquipment = new SummerSportEquipment();
			inSummerSportEquipment = true;
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