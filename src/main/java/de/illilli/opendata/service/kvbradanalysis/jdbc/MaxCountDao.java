package de.illilli.opendata.service.kvbradanalysis.jdbc;

public class MaxCountDao {

	private int maxcount;

	public int getMaxcount() {
		return maxcount;
	}

	public void setMaxcount(int maxcount) {
		this.maxcount = maxcount;
	}

	@Override
	public String toString() {
		return "MaxCountDao [maxcount=" + maxcount + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + maxcount;
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
		MaxCountDao other = (MaxCountDao) obj;
		if (maxcount != other.maxcount)
			return false;
		return true;
	}

}
