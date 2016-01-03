package de.illilli.opendata.service.kvbradanalysis;

import java.sql.Timestamp;

/**
 * <p>
 * Format from kvbradrouting:
 * </p>
 * 
 * <pre>
 * {
 *   "number":21489,
 *   "timeinmillis":334574,
 *   "distance":1584.136,
 *   "modtime":"Dec 29, 2015 7:13:55 AM",
 *   "type":2,
 *   "typeString":"LINESTRING",
 *   "value":"(6.95764 50.905,6.95707 50.90436,6.95722 50.90433)",
 *   "srid":4326
 * }
 * </pre>
 *
 */
public class Kvbradrouting {

	private int number;
	private long timeinmillis;
	private double distance;
	private Timestamp timestamp;
	private int type;
	private String typeString;
	private String value;
	private int srid;

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public long getTimeinmillis() {
		return timeinmillis;
	}

	public void setTimeinmillis(long timeinmillis) {
		this.timeinmillis = timeinmillis;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getTypeString() {
		return typeString;
	}

	public void setTypeString(String typeString) {
		this.typeString = typeString;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getSrid() {
		return srid;
	}

	public void setSrid(int srid) {
		this.srid = srid;
	}

	@Override
	public String toString() {
		return "Kvbradrouting [number=" + number + ", timeinmillis="
				+ timeinmillis + ", distance=" + distance + ", timestamp="
				+ timestamp + ", type=" + type + ", typeString=" + typeString
				+ ", value=" + value + ", srid=" + srid + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(distance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + number;
		result = prime * result + srid;
		result = prime * result + (int) (timeinmillis ^ (timeinmillis >>> 32));
		result = prime * result
				+ ((timestamp == null) ? 0 : timestamp.hashCode());
		result = prime * result + type;
		result = prime * result
				+ ((typeString == null) ? 0 : typeString.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		Kvbradrouting other = (Kvbradrouting) obj;
		if (Double.doubleToLongBits(distance) != Double
				.doubleToLongBits(other.distance))
			return false;
		if (number != other.number)
			return false;
		if (srid != other.srid)
			return false;
		if (timeinmillis != other.timeinmillis)
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		if (type != other.type)
			return false;
		if (typeString == null) {
			if (other.typeString != null)
				return false;
		} else if (!typeString.equals(other.typeString))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

}
