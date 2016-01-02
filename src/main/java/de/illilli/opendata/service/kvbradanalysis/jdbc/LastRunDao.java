package de.illilli.opendata.service.kvbradanalysis.jdbc;

import java.sql.Timestamp;

public class LastRunDao {

	private Timestamp modtime;
	private String comment;

	public Timestamp getModtime() {
		return modtime;
	}

	public void setModtime(Timestamp modtime) {
		this.modtime = modtime;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comment == null) ? 0 : comment.hashCode());
		result = prime * result + ((modtime == null) ? 0 : modtime.hashCode());
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
		LastRunDao other = (LastRunDao) obj;
		if (comment == null) {
			if (other.comment != null)
				return false;
		} else if (!comment.equals(other.comment))
			return false;
		if (modtime == null) {
			if (other.modtime != null)
				return false;
		} else if (!modtime.equals(other.modtime))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "KvbradroutingDb [modtime=" + modtime + ", comment=" + comment
				+ "]";
	}

}
