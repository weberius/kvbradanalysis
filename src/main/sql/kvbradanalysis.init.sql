CREATE TABLE analysis (
	number       integer NOT NULL,
	modtime      timestamp DEFAULT current_timestamp
);
SELECT AddGeometryColumn ('public','analysis','geom',4326,'LINESTRING',2);

CREATE TABLE analysisresult (
	count       integer NOT NULL
);
SELECT AddGeometryColumn ('public','analysisresult','geom',4326,'LINESTRING',2);

CREATE TABLE kvbradanalysis (
    numberofinsert      integer,
    modtime      timestamp DEFAULT current_timestamp
);
