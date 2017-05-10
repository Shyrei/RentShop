package by.shyrei.rentshop.entities;

import java.io.Serializable;

/**
 * Contains information about category of sport equipment. It is classic Java
 * bean class that has only fields, getters and setters methods. Implements
 * serializable.
 * 
 * @author Shyrei Uladzimir
 * 
 */
public class Category implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;

	public Category(String name) {
		super();
		this.name = name;
	}

	public Category() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Category other = (Category) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
