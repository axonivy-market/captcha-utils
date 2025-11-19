# CAPTCHA-UTILS

Ein umfassendes Dienstprogramm zum Generieren und Überprüfen von Bild- und
Audio-CAPTCHAs, um Ihre Anwendung vor Bot- und automatisierten Angriffen zu
schützen. Dieses Tool stellt sicher, dass Benutzerinteraktionen authentisch
sind, und bietet einen robusten Schutz für Formulare, Anmeldungen und andere
kritische Bereiche Ihrer Anwendung.

### Wichtigste Funktionen:
* **Bild-CAPTCHA generieren:** Erstellen Sie anpassbare bildbasierte
  Herausforderungen.
* **Audio-CAPTCHA generieren:** Bieten Sie barrierefreie Audio-Herausforderungen
  für sehbehinderte Benutzer.
* **CAPTCHA überprüfen:** Eine einfache und zuverlässige Methode, um
  Benutzereingaben anhand des generierten CAPTCHA-Werts zu überprüfen.

### Funktionsdetails:<br><br>
Bild-CAPTCHA generieren – diese Funktion erstellt ein einzigartiges
bildbasiertes CAPTCHA mit mehreren anpassbaren Optionen:
* **Abmessungen:** Geben Sie die genaue Breite und Höhe des CAPTCHA-Bildes an,
  damit es zu Ihrer Benutzeroberfläche passt.
* **Inhaltstyp:** Definieren Sie die Zeichen, die im CAPTCHA verwendet werden
  sollen. Sie können zwischen Zahlen, lateinischen Buchstaben usw. wählen.
* **CAPTCHA-Länge:** Steuert die Anzahl der Zeichen in der CAPTCHA-Zeichenfolge.
* **Rauschen:** Fügen Sie dem Bild verschiedene Arten von visuellem Rauschen
  hinzu, z. B. gerade oder gekrümmte Linien, um das Lesen für Bots zu
  erschweren.
* **Hintergrund:** Passen Sie den Hintergrundtyp an und legen Sie die Farbe des
  CAPTCHA-Bildes fest.

Audio-CAPTCHA generieren – diese Funktion erstellt eine Audiodatei, die die
CAPTCHA-Zeichen vorliest und eine barrierefreie Alternative **** zum
Bild-CAPTCHA bietet:
* **Inhaltstyp:** Der Inhalt des Audio-CAPTCHAs basiert auf dem
  Sprachproduzenten. Sie können die Sprache auswählen, es stehen viele
  Ländersprachen zur Auswahl.
* **Optionales Hintergrundgeräusch:** Fügen Sie der Audiodatei eine Ebene mit
  Hintergrundgeräuschen hinzu, um eine automatische Transkription zu verhindern.
  Diese Funktion erhöht die Sicherheit für das Audio-CAPTCHA.

## Demo

1. Starten Sie den Demo-Prozess „Captcha überprüfen”.

<img width="1713" height="812" alt="image" src="https://github.com/user-attachments/assets/babd0f62-949e-4db8-9c56-a5f1f2238824" />

2. Sie können zwischen Bild- und Sprachverifizierung wählen.

<img width="916" height="566" alt="image" src="https://github.com/user-attachments/assets/df4979e1-6aa3-479f-b2c6-b415cfa0218d" />

Informationen für Entwickler: Die Funktion „ `generateImageCaptcha` ” wird im
Hintergrund verwendet, um das Captcha-Bild basierend auf den Eingaben und
Einstellungen des Benutzers zu generieren.

### Bildüberprüfung

![image-captcha](images/image-captcha.png)

### Audioüberprüfung

![audio-captcha](images/audio-captcha.png)
