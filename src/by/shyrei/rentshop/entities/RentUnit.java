package by.shyrei.rentshop.entities;

import java.util.Arrays;

public class RentUnit {

	private SportEquipment[] units;

	public SportEquipment[] getUnits() {
		return units;
	}

	public void setUnits(SportEquipment[] units) {
		this.units = units;
	}

	public RentUnit(SportEquipment[] units) {
		super();
		this.units = units;
	}

	public RentUnit() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(units);
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
		RentUnit other = (RentUnit) obj;
		if (!Arrays.equals(units, other.units))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RentUnit [units=" + Arrays.toString(units) + "]";
	}
}
