# KVB Rad Analysis

Dieser Service analysiert die durch [kvbradrouting](https://github.com/codeforcologne/kvbradrouting) ermittelten Fahrradrouten durch Köln. Er holt sich die dort hinterlegten Routen, teilt sie in Unterabschnitte auf und persistiert diese in der Datenbank. Danach können diese durchgezählt werden. 

## Services

### /kvbradanalysis/service/put

Der Service /kvbradanalysis/service/put löst die Analyse aus. Hier wird folgendermaßen vorgegangen:

1. Zeitpunkt des letzten Laufes ermitteln
2. Routing Ergebnisse von kvbradrouting erfragen
3. Ergebnisse in die Datenbank schreiben
4. delete analysis-values
5. write analysis-values
6. vermerken, dass Daten geschrieben wurde

Der Service kann mit curl aufgerufen werden, und sollte einmal täglich laufen. Er kann durch einen CRON ausgelöst werden:

    curl -X PUT http://localhost:8080/kvbradanalysis/service/put
    
Dafür kann das Skript `kvbradanalysis.sh` verwendet werden. Diese wird mit `sudo EDITOR=vim.tiny crontab -e` in die cron-Liste eingtragen (Beispiel wird jede Nacht um 03:03 Uhr ausgeführt):

    3 3 * * * /home/pi/kvbradanalysis.sh


### /kvbradanalysis/service/data?geojson

Über den REST-EndPoint /kvbradanalysis/service/data?geojson lassen sich alle routing-Informationen abfragen.

## Datenbank

### Datentabelle

	CREATE TABLE analysis (
    	number       integer NOT NULL,
    	modtime      timestamp DEFAULT current_timestamp
	);
	SELECT AddGeometryColumn ('public','analysis','geom',4326,'LINESTRING',2);

### Ergebnistabelle

In der Ergebnistabelle werden die aggregierten Ergebnisse zur schnellen Abfrage vorgehalten. Es werden stets zunächst die vorhandenen Ergebnisse gelöscht um dann die aktuellen Ergebnisse hinzuzufügen. 

	CREATE TABLE analysisresult (
    	count       integer NOT NULL
	);
	SELECT AddGeometryColumn ('public','analysisresult','geom',4326,'LINESTRING',2);

Mit folgendem insert werden die Daten aggregiert:

    insert into analysisresult ( 
      select count(number) numberof, geom 
      from analysis 
      group by geom
    )

### Steuerungstabelle

	CREATE TABLE kvbradanalysis (
	    numberofinsert      integer,
	    modtime      timestamp DEFAULT current_timestamp
	);


## Test

Da zur Zeit keine Integration Test Stage zur Verfügung steht, sind alle Tests, die eineDatenbank voraussetzt als main codiert. Um eine Datenbankverbindung hierfür zur Verfügung stellen zu können, muss die Datei src/test/resources/jndi.properties.template in src/test/resources/jndi.properties kopiert und die entsprechenden Parameter für die Datenbank-Verbindung gesetzt werden.

Weiterhin muss im Test die Verbindung für die Verwendung im Test-Betrieb konfiguriert werden. Dies erfolgt durch Aufruf von 		

	JndiProperties.setUpConnectionForJndi();

Dadurch werden die connection Parameter zum Auslesen per JNDI gesetzt. Das Anfordern der Datenbankverbindung erfolgt dann innerhalb der ausführenden Klasse.