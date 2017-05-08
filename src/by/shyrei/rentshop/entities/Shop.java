package by.shyrei.rentshop.entities;

import java.util.Map;

public class Shop {
	
	private Map<SportEquipment, Integer> good;

	public Map<SportEquipment, Integer> getGood() {
		return good;
	}

	public void setGood(Map<SportEquipment, Integer> good) {
		this.good = good;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((good == null) ? 0 : good.hashCode());
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
		Shop other = (Shop) obj;
		if (good == null) {
			if (other.good != null)
				return false;
		} else if (!good.equals(other.good))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "" + good + "\n";
	}

	public Shop(Map<SportEquipment, Integer> good) {
		super();
		this.good = good;
	}

	public Shop() {
		super();
	}
	
	

}
