# fhswe-java-app-mediaGallery
 A Media Library / Database based on JavaFX

# Allgemein
## Ziele
Ziele dieses Übungsbeispiels ist es:
* *GUI*: Implementierung einer grafischen Oberfläche mit JavaFX.
* *UI-Komponente*: Implementierung einer eigenen grafischen Komponente.
* *Presentation-/Viewmodelle*: Kapseln eines Models, einer Entität in eine ViewModel zur optimalen Aufbereitung für die UI. Anwenden von objektorientierten Grundsätzen.
*  *Layer *: Kapselung von Aufgaben in Layer (BL, DAL, VM)

## Ziele II
* *Design Patterns*: Anwenden mind. eines Design Patterns
* *Logging*: Anwenden einer Logging-Komponente (log4j)
* *Reporting*: Erstellen von Berichten mit Hilfe einer geeigneten Bibliothek. Dazu müssen Sie eine geeignete Bibliothek auswählen und anwenden.
* *Codemetriken*: Einhaltung von Codemetriken mit Hilfe von Continious Integration.

## Aufgabenstellung
Implementieren Sie eine einfache Bilddatenbank "PicDB"
* Verwalten von Bildern aus *einem* Verzeichnis.
* Auslesen und Ändern der IPTC & EXIF Informationen des Bildes.
* Speichern der IPTC & EXIF Informationen in einer Datenbank.
* Verwaltung von Fotografen_innen in einer Datenbank.
* Zuordnung der Fotografen_innen zu Bildern.
* Suche nach Bildern anhand der IPTC, EXIF und Fotogafen_innen Daten.

# Konkretes
## GUI
* Die grafische Oberfläche muss nach den Vorgaben gestaltet sein.
* Die Liste aller Bilder im unteren Bereich muss als wiederverwendbare Komponente implementiert werden.

![GUI Konzept](https://www.bilder-upload.eu/bild-8574e5-1582565485.png.html)

## UML
![UML](https://www.bilder-upload.eu/bild-5828c3-1582565496.png.html)

## Fotografen_innen
Fotografen_innen können über ein Menü aufgelistet und bearbeitet / neu angelegt werden.

| Name        | Typ         | Validierung | Anmerkungen |
| ----------- |:-----------|:-----------| :-----------|
| Vorname | String(100) | - | Enthält auch zweiten Vornamen |
| Nachname | String(50) | Pflichtfeld | - |
| Geburtstag | Datum | < Today() | - |
| Notizen | Text | nein | Kann beliebig lang werden | 

## Berichte
| Bericht | Inhalt |
| ------ | :-------|
| Einzelbild | Ein einzelnes Bild wird gedruckt. Es werden die IPTC sowie EXIF Informationen gedruckt. Der oder die Fotograf_in wird ebenfalls gedruckt. |
| Tags | Ein Bericht der eine Liste aller Tags mit der Anzahl der Fotos beinhaltet. |

## IPTC & EXIF Informationen
Diese Funktionalität ist als Mockup zu implementieren. 
Wenn Sie eine geeignete Bibliothek finden, steht es Ihnen frei, diese auch zu nutzen.

Unabhängig davon *müssen* diese Informationen in der Datenbank gespeichert werden (Cache für die Suche).
Diese Bibliothek würde also eine Synchronisierung durchführen.

## Nicht dokumentierte Anforderungen
Anforderungen, welche nicht explizit dokumentiert oder durch Unit-Test festgekegt sind, dürfen Sie selbst bestimmen, wie sie diese umsetzen.
Beispiele wären: Das Aussehen der Berichte

Versehen Sie alle public-Klassen/Methoden/Eigenschaften mit Kommentaren, aus denen mittels Tools eine API
Dokumentation erstellt werden kann. Führen Sie das Tool Ihrer Wahl aus und zeigen Sie uns das Ergebnis.

Wenn Sie unsicher sind bzgl. des Tools fragen Sie uns.
Mögliche Tools:
* Doxygen
* Sandcastle
* Javadoc

Falls Sie ein anderes Tool nutzen möchten, können Sie dies gerne tun. Eventuell sprecgeb Sie sich mit uns ab.

Sie erhalten keine Punkte für diese Aufgabe. Es gehört aber zum guten Ton, seinen Code ausreichend zu dokumentieren.
Ihre Kollegen_innen in großen Projekten werden es Ihnen danken.

## Dokumentation

Ausgedruckt ca. 1 A4 Seite lang.
1. Benutzerhandbuch - Wie wird die Applikation verwendet.
2. Lösungsbeschreibung - Wie wurde die Aufgabe gelöst.
3. Worauf bin ich stolz.
4. Was würde ich das nächste Mal anders machen.

## Technologien

UI-Technologie: JavaFX
Datenbank: JDBC (H2Database)
Logging: log4j
Reporting: ...