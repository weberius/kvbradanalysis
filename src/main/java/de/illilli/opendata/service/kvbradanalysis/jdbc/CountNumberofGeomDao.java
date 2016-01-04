package de.illilli.opendata.service.kvbradanalysis.jdbc;

import org.postgis.PGgeometry;

public class CountNumberofGeomDao {

	private int numberof;
	private PGgeometry geom;

	public int getNumberof() {
		return numberof;
	}

	public void setNumberof(int numberof) {
		this.numberof = numberof;
	}

	public PGgeometry getGeom() {
		return geom;
	}

	public void setGeom(PGgeometry geom) {
		this.geom = geom;
	}

	@Override
	public String toString() {
		return "CountNumberofGeomDao [numberof=" + numberof + ", geom=" + geom
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((geom == null) ? 0 : geom.hashCode());
		result = prime * result + numberof;
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
		CountNumberofGeomDao other = (CountNumberofGeomDao) obj;
		if (geom == null) {
			if (other.geom != null)
				return false;
		} else if (!geom.equals(other.geom))
			return false;
		if (numberof != other.numberof)
			return false;
		return true;
	}

}
