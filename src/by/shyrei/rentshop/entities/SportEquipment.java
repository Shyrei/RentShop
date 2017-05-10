package by.shyrei.rentshop.entities;

import java.io.Serializable;

/**
 * This is an abstract class of sports equipment. Heirs of this class winter and
 * summer equipment. Implements serializable.
 * 
 * @author Shyrei Uladzimir
 *
 */
public abstract class SportEquipment implements Serializable {

	private static final long serialVersionUID = 1L;
	private Category category;
	private String title;
	private int price;

	public SportEquipment() {
		super();
	}

	public SportEquipment(Category category, String title, int price) {
		super();
		this.category = category;
		this.title = title;
		this.price = price;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + price;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SportEquipment other = (SportEquipment) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (price != other.price)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SportEquipment [category=" + category + ", title=" + title + ", price=" + price + "]";
	}
}
