# Media Gallery

Im Rahmen der LV "Software Engineering 2" soll eine JavaFX-Anwendung realisiert werden, welche Bilder aus einem Verzeichnis
lädt und deren Informationen aus einer Datenbank liest. Des weiteren soll über eine Liste aller Bilde die Navigation zwischen
den Bildern möglich sein. Darüber hinaus soll es dem Nutzer möglich gemacht werden die Metadaten der Bilder zur manipulieren.
Weitere Details zur Aufgabenstellung sind dem Markdown `aufgabenstellung.md` zu entnehmen. 

## MVVM Struktur
In JavaFX sind die Controller, die standardsgemäß erstellt werden die `Views`. 
`Views` machen nichts anderes, als dass die Werte der Bedienelemente auf `Property`-Objekte 
der entsprechenenden `ViewModel` gebunden werden. Das passiert, indem beim Binden Methoden des `ViewModel` übergeben werden,
welche `Property`-Objekte zurückgeben. 
Dadurch ist es möglich, dass wenn sich die Werte im  `ViewModel` verändern, automatisch der Wert in der 
`View` auch verändert und angezeigt wird. Eine `View`enthält deswegen immer ein privates Objekt des 
jeweiligen `ViewModels`, um die Daten abzufagen.

`ViewModels` stellen die Logik der Anwendung zur Verfügung. Zum Beispiel kann hier in der Methode, auf die
die `View` gebunden ist, Abfagen stattfinden, zu welchen Bedinungen welcher Wert zurückgegeben werden soll.
Manchmal muss es auch möglich sein, dass einzelne `ViewModel` auf die Werte anderer `ViewModel` zugreifen.

Bei dieser Kommunikation werden `Models` benutzt. Also Klassen die den Austausch-Objekten eine Form geben. 

## Nutzung des Programms:
* Das Programm lädt alle Bilder (.jpg, .png, .bmp, .gif) aus dem aktuellen Verzeichnis und zeigt sie in einer scrollbaren Liste an.
* Wenn keine Bilder gefunden werden, wird die Information preis gegeben, dass keine Bilder zu finden waren.
* Wenn Bilder gefunden wurden, wird das erste Bild in einer Großansicht angezeigt.
* Für jedes Bild werden in einer Übersicht Daten über IPTC, EXIF und Fotograf_innen angezeigt.
* Die IPTC, EXIF und Fotograf_innen können bearbeitet werden. Dabei darf jedoch nur gespeichert werden, wenn eine Validierung es zulässt.
* Über eine Suchleiste ist es möglich nach diesen Daten zu suchen.
* Nach gefilterten Ergebnisse werden dann in der scrollbaren Liste angezeigt.

## Ansicht (View)
* `SearchView`: `Textfield` zu eintippen des Textes und ein `Button` zum Bestätigen der Suche
* `DataView`: Beinhaltet drei Tabs...
    * EXIF (Basis Metadaten): `Textfield` für Kameramodell, Datum und Uhrzeit, ISO, Blende und ein `Button` zum Speichern von Änderungen.
    * IPTC (Erweiterte Daten): `Textfield` für Bildbeschreibung, Schlüsselwörter (Tags) und Bewertung und ein `Button` zum Speichern von Änderungen
    * Fotograf_innen: `Textfield` für Vornamen, Nachnamen, Geburtstag, Notizen und ein `Button` zum Speichern von Änderungen
* `PictureView`: Ein `ImageView` für das Anzeigen des ausgewählten Bildes.
* `PictureListView`: Eine `ListView` für das Anzeigen aller Bilder.

## Funktionsweise (ViewModel-Klassen)
Alle `ViewModel` sind in einer umfassenden Klasse `MainViewModel` vereint, sodass eine Kommunikation zwischen den `ViewModel` möglich ist.

* `DataViewModel` -> `PictureViewModel`
    * enthält Daten des ausgewählten Bilds
    * beinhaltet drei weitere `ViewModel`
    * `EXIFViewModel`
        * erhält EXIF Daten des ausgewählten Bildes
        * verändert EXIF Daten des ausgewählten Bildes
        * validiert Eingaben in Textfeldern für Bestätigung
    * `IPTCViewModel`
        * erhält ITPC Daten des ausgewählten Bildes
        * verändert ITPC Daten des ausgewählten Bildes
        * validiert Eingaben in Textfeldern für Bestätigung
    * `PhotographerViewModel`
        * erhält Photographer Daten des ausgewählten Bildes
        * verändert Photographer Daten des ausgewählten Bildes
        * validiert Eingaben in Textfeldern für Bestätigung

* `SearchViewModel` -> `PictureService`, `PictureListView`
    * sendet bei Bestätigung und leerem Suchtext keine Anfrage
    * sendet nach Bestätigung mit Suchtext Anfrage
    * erhält eine Liste an `Picture`-Objekten aus Anfrage
    * manipuliert Liste in ListView von `PictureListView`

* `PictureListViewModel` -> `PictureService`
    * lädt alle Bilder aus `PictureService`
    * hält Liste bereit, was manipuliert werden kann. 
    
* `PictureViewModel` -> `PictureListViewModel`
    * erhält von `PictureList` das erste Element der Liste und zeigt es an
    * zeigt von `PictureList` das gewählte Element an

## Modelle (Model-Klassen)
* `PictureModel`: 
    * filename (String)
    * type (FileType)
    * exif (EXIFModel)
    * iptc (IPTCModel)
    * photographer (PhotographerModel)

* `EXIFModel`:  
    * camera (String)
    * time (Date)
    * iso (Integer)
    * aperture (Float)

* `IPTCModel`: 
    * description (String)
    * rating (Int)
    * tags (String[])

* `PhotographerModel`: 
    * forename (String)
    * name (String)
    * birthday (Date)
    * note (String)

## Helper Klassen
* `PictureService` -> `PictureFactory`, `DBService`
    * kann nach Texten suchen in den IPTC, EXIF und Fotograf_innen suchen
    * kann alle Bilder zurückgeben
    * kann Bilder löschen
    * kann Bilder manipulieren
    * gibt entweder eine Liste oder einzelne Objekte von `PictureModel` zurück

* `PictureFactory` -> `EXIFFactory`, `IPTCFactory`, `PhotographerFactory`
    * erstellt aus Rohdaten `PictureModel`-Objekte
    
* `EXIFFactory`
    * erstellt aus Rohdaten `EXIFModel`-Objekte
    
* `IPTCFactory`
    * erstellt aus Rohdaten `IPTCModel`-Objekte

* `PhotographerFactory`
    * erstellt aus Rohdaten `PhotographerModel`-Objekte

* `DBService`
    * Prepared Statements
    * sendet SELECTS an Datenbank und gibt ResultSets zurück
    * sendet INSERTS an Datenbank
    * sendet ALTER an Datenbank
    
## Helper Enumerations
* `FileType`: .jpg, .png, .bmp, .gif
 
## Datenbank
* Picture: 
    * picture_id (Int)
    * filename (String)
    * type (String)

* EXIF: 
    * exif_id (Int)
    * camera (String)
    * time (Date)
    * iso (Integer)
    * aperture (Float)
    
* IPTC: 
    * id (Int)
    * description (String)
    * rating (Int)
    
* Photographer: 
    * id (Int)
    * forename (String)
    * name (String)
    * birthday (Date)
    * note (String)
    
* Tag: 
    * id (Int)
    * description (String)
    
* IPTC_Tags: 
    * id_ipct (Int)
    * id_tag (Int)

## Fragen und Antwort:
* Was sind IPTC und EXIF?
https://lernen.zoner.de/exif-iptc-metadaten-und-wie-man-sie-nutzt/

* Wie funktioniert das Anzeigen von Tabs in JavaFX?
http://tutorials.jenkov.com/javafx/tabpane.html

* Wie funktioniert das Anzeigen von Bildern in JavaFX?
https://www.tutorialspoint.com/javafx/javafx_images.htm

* Wie funktioniert die ListView in JavaFX?
http://www.java2s.com/Tutorials/Java/JavaFX/0640__JavaFX_ListView.htm
https://stackoverflow.com/questions/33592308/javafx-how-to-put-imageview-inside-listview