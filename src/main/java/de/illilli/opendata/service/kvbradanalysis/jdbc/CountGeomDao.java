package de.illilli.opendata.service.kvbradanalysis.jdbc;

import org.postgis.PGgeometry;

public class CountGeomDao {

	private int count;
	private PGgeometry geom;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public PGgeometry getGeom() {
		return geom;
	}

	public void setGeom(PGgeometry geom) {
		this.geom = geom;
	}

	@Override
	public String toString() {
		return "CountGeomDao [count=" + count + ", geom=" + geom + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + count;
		result = prime * result + ((geom == null) ? 0 : geom.hashCode());
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
		CountGeomDao other = (CountGeomDao) obj;
		if (count != other.count)
			return false;
		if (geom == null) {
			if (other.geom != null)
				return false;
		} else if (!geom.equals(other.geom))
			return false;
		return true;
	}

}
