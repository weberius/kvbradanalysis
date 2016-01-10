package de.illilli.opendata.service.kvbradanalysis.jdbc;

/**
 * This class takes a single Integer value from Database. It is used for example
 * in case of SelectMaxCountFromDb and SelectCeilAvgCountFromAnalysisresult
 *
 */
public class NumberDao {

	private int number;

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "NumberDao [number=" + number + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + number;
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
		NumberDao other = (NumberDao) obj;
		if (number != other.number)
			return false;
		return true;
	}

}
