/*
 *
 * Das JAVA-Programm Miles-Verlag Verlagsverwaltung stellt alle notwendigen
 * Funktionen f�r die Verwaltung des Carola Hartman Miles-Verlags bereit.
 *
 * Copyright (C) 2017 EDV-Beratung und Betrieb, Entwicklung von SOftware
 *                    Dipl.Inform Thomas Zimmermann
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package milesVerlagMain;

//~--- non-JDK imports --------------------------------------------------------
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.font.PDFont;

//~--- JDK imports ------------------------------------------------------------
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.xml.transform.TransformerException;

import static milesVerlagMain.Modulhelferlein.Ausgabe;
import static milesVerlagMain.Modulhelferlein.AusgabeDB;
import static milesVerlagMain.Modulhelferlein.AusgabeRB;
import static milesVerlagMain.Modulhelferlein.Linie;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDMetadata;
import static org.apache.pdfbox.pdmodel.common.PDRectangle.A4;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.graphics.color.PDOutputIntent;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.xmpbox.XMPMetadata;
import org.apache.xmpbox.schema.DublinCoreSchema;
import org.apache.xmpbox.schema.PDFAIdentificationSchema;
import org.apache.xmpbox.type.BadFieldValueException;
import org.apache.xmpbox.xml.XmpSerializer;

/**
 *
 * @author Thomas Zimmermann
 */
public class briefVerrechnungHonorar {

    private static Connection conn = null;
    private static Statement SQLAdresse = null;
    private static Statement SQLVerrechnung = null;
    private static Statement SQLHonorar = null;
    private static ResultSet resultAdresse = null;
    private static ResultSet resultVerrechnung = null;
    private static ResultSet resultHonorar = null;
    private static Float Netto_VP_PB = 0F;
    private static Float Netto_VP_HC = 0F;
    private static Float Netto_VP_EB = 0F;
    private static Float Honorar_PB = 0F;
    private static Float Honorar_HC = 0F;
    private static Float Honorar_EB = 0F;
    private static Float Honorar = 0F;

    private static Double GesamtBetrag = 0D;
    private static Double GesamtHonorar = 0D;

    private static Integer HONORAR_ZAHLEN; // 0
    //* 0 nichts, 1 Schwelle 1, 2 Schwelle 2
    private static String HONORAR_TITEL; // 1
    private static String HONORAR_ISBN_PB; // 3
    private static Integer HONORAR_ANZAHL_PB; // 4
    private static Float HONORAR_PREIS_PB; // 5
    private static String HONORAR_ISBN_HC; // 6
    private static Integer HONORAR_ANZAHL_HC; // 7
    private static Float HONORAR_PREIS_HC; // 8
    private static String HONORAR_ISBN_EB; // 9
    private static Integer HONORAR_ANZAHL_EB; // 10
    private static Float HONORAR_PREIS_EB; // 11
    private static Integer HONORAR_ANZAHL_1; // 12
    private static Integer HONORAR_PROZENT_1; // 13
    private static Integer HONORAR_ANZAHL_2; // 14
    private static Integer HONORAR_PROZENT_2; // 15

    private static Integer Startzeile = 0;
    private static Integer Honorarzeile = 0;

    public static void briefPDF(String HONORAR_AUTOR) { // 16

        try { // Datenbank-Treiber laden
            Class.forName(Modulhelferlein.dbDriver);
        } catch (ClassNotFoundException exept) {
            Modulhelferlein.Fehlermeldung("Brief Rechnung", "ClassNotFound-Exception: Treiber nicht gefunden: ", exept.getMessage());
            System.exit(1);
        } // Datenbank-Treiber laden

        try { // Verbindung zur Datenbank ?ber die JDBC-Br?cke
            conn = DriverManager.getConnection(Modulhelferlein.dbUrl, Modulhelferlein.dbUser, Modulhelferlein.dbPassword);
        } catch (SQLException exept) {
            Modulhelferlein.Fehlermeldung("Brief Honorarverrechnung", "SQL-Exception: Verbindung nicht moeglich: ", exept.getMessage());
            System.exit(1);
        } // try Verbindung zur Datenbank ?ber die JDBC-Br?cke

        if (conn != null) {
            try {
                SQLAdresse = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                SQLHonorar = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                SQLVerrechnung = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                resultVerrechnung = SQLVerrechnung.executeQuery("SELECT * FROM TBL_VERRECHNUNG");
                resultHonorar = SQLHonorar.executeQuery("SELECT * FROM TBL_HONORAR WHERE HONORAR_AUTOR_1 = '" + HONORAR_AUTOR + "'");
                resultAdresse = SQLAdresse.executeQuery("SELECT * FROM TBL_ADRESSE WHERE ADRESSEN_ID='" + HONORAR_AUTOR + "'");
                resultAdresse.next();
                
                System.out.println("Schreibe Honorarabrechnung f�r " + HONORAR_AUTOR);
                String Titel = "";
                Integer Anzahl = 1;
                //if (HONORAR_TITEL.contains(" ")) {
                //    String[] Titelparts = HONORAR_TITEL.split(" ");
                //    Titel = Titelparts[0];
                //    while ((Anzahl < Titelparts.length) && (Anzahl < 8)) {
                //        Titel = Titel + " " + Titelparts[Anzahl];
                //        Anzahl = Anzahl + 1;
                //    }
                //} else {
                //    Titel = HONORAR_TITEL;
                //}

                // Create a document and add a page to it
                PDDocument document = new PDDocument();
                PDPage page1 = new PDPage(A4);

                document.addPage(page1);

//          PDFont fontMono = PDType1Font.COURIER;
                // Start a new content stream which will "hold" the to be created content
                PDPageContentStream cos;

                //try {
                // Create a new font object selecting one of the PDF base fonts
                // Create a new font object selecting one of the PDF base fonts
                // PDFont fontPlain = PDType1Font.HELVETICA;
                // PDFont fontBold = PDType1Font.HELVETICA_BOLD;
                // PDFont fontItalic = PDType1Font.HELVETICA_OBLIQUE;
                PDFont fontPlain = PDType0Font.load(document, new File("c:\\windows\\fonts\\arial.ttf"));
                PDFont fontBold = PDType0Font.load(document, new File("c:\\windows\\fonts\\arialbd.ttf"));
                PDFont fontItalic = PDType0Font.load(document, new File("c:\\windows\\fonts\\ariali.ttf"));
                PDFont fontBoldItalic = PDType0Font.load(document, new File("c:\\windows\\fonts\\arialbi.ttf"));

                cos = new PDPageContentStream(document, page1);

//              int line = 0;
                // Kopfzeile mit Bild
                BufferedImage awtImage = ImageIO.read(new File("header-brief.jpg"));
                //PDImageXObject  ximage = new PDPixelMap(document, awtImage);
                PDImageXObject pdImage = PDImageXObject.createFromFile("header-brief.jpg", document);
                float scaley = 0.5f; // alter this value to set the image size
                float scalex = 0.75f; // alter this value to set the image size
                cos.drawImage(pdImage, 55, 770, pdImage.getWidth() * scalex, pdImage.getHeight() * scaley);
                //cos.drawXObject(pdImage, 55, 770, pdImage.getWidth() * scalex, pdImage.getHeight() * scaley);

                // Fu?zeile
                Ausgabe(cos, fontBold, 10, Color.GRAY, 55, 35, "Carola Hartmann Miles - Verlag");
                Ausgabe(cos, fontBold, 9, Color.GRAY, 55, 25, "Dipl.Kff. Carola Hartmann");
                Ausgabe(cos, fontBold, 9, Color.GRAY, 55, 15, "Steuernr.: 19 332 6006 5");
                Ausgabe(cos, fontBold, 9, Color.GRAY, 55, 5, "USt-IDNr: DE 269 369 280");
                Ausgabe(cos, fontBold, 10, Color.GRAY, 230, 35, Modulhelferlein.CheckStr("George Caylay Stra�e 38"));
                Ausgabe(cos, fontBold, 9, Color.GRAY, 230, 25, "Telefon: +49 (0)30 36 28 86 77");
                Ausgabe(cos, fontBold, 9, Color.GRAY, 230, 15, "e-Mail: miles-verlag@t-online.de");
                Ausgabe(cos, fontBold, 9, Color.GRAY, 230, 5, "Internet: www.miles-verlag.jimdo.com");
                Ausgabe(cos, fontBold, 10, Color.GRAY, 400, 35, "14089 Berlin");
                Ausgabe(cos, fontBold, 9, Color.GRAY, 400, 25, "Volksbank Berlin");
                Ausgabe(cos, fontBold, 9, Color.GRAY, 400, 15, "IBAN: DE61 1009 0000 2233 8320 17");
                Ausgabe(cos, fontBold, 9, Color.GRAY, 400, 5, "BIC: BEV0DEBB");

// Faltmarke, Lochmarke, Faltmarke
                Linie(cos, 1, 0, 595, 15, 595);
                Linie(cos, 1, 0, 415, 25, 415);
                Linie(cos, 1, 0, 285, 15, 285);

                // Absenderzeile
                Linie(cos, 1, 50, 749, 297, 749);
                Ausgabe(cos, fontPlain, 8, Color.BLACK, 50, 751,
                        Modulhelferlein.CheckStr("C. Hartmann Miles-Verlag - George Caylay Stra�e 38 - 14089 Berlin"));

                // Datum
                Ausgabe(cos, fontPlain, 12, Color.BLACK, 354, 655,
                        "Datum: " + Modulhelferlein.printSimpleDateFormat("dd.MM.yyyy"));

                // Adresse
                String[] AdressZeile = {"", "", "", "", "", "", ""};
                String[] adresse = {"", "", "", "", "", "", ""};
                System.out.println("erzeuge Adresse aus Kundendatenbank");
                adresse[0] = resultAdresse.getString("ADRESSEN_ZUSATZ_1");
                adresse[1] = Modulhelferlein.makeAnrede(resultAdresse.getString("ADRESSEN_NAMENSZUSATZ"),
                        resultAdresse.getString("ADRESSEN_VORNAME"),
                        resultAdresse.getString("ADRESSEN_NAME"));
                adresse[2] = resultAdresse.getString("ADRESSEN_ZUSATZ_2");
                adresse[3] = resultAdresse.getString("ADRESSEN_STRASSE");
                adresse[4] = resultAdresse.getString("ADRESSEN_PLZ") + " " + resultAdresse.getString("ADRESSEN_ORT");
                adresse[5] = resultAdresse.getString("ADRESSEN_ZUSATZ_3");
                Integer ZeilenNr = 1;
                for (int i = 0; i < 6; i++) {
                    if (!adresse[i].equals("")) {
                        AdressZeile[ZeilenNr] = adresse[i];
                        ZeilenNr = ZeilenNr + 1;
                    }
                }
                Ausgabe(cos, fontPlain, 12, Color.BLACK, 55, 730, AdressZeile[1]);
                Ausgabe(cos, fontPlain, 12, Color.BLACK, 55, 715, AdressZeile[2]);
                Ausgabe(cos, fontPlain, 12, Color.BLACK, 55, 700, AdressZeile[3]);
                Ausgabe(cos, fontPlain, 12, Color.BLACK, 55, 685, AdressZeile[4]);
                Ausgabe(cos, fontPlain, 12, Color.BLACK, 55, 670, AdressZeile[5]);
                Ausgabe(cos, fontPlain, 12, Color.BLACK, 55, 655, AdressZeile[6]);

                // Betreff
                Ausgabe(cos, fontBold, 12, Color.BLACK, 55, 575, "Honorarabrechnung des Carola Hartmann Miles-Verlag");
                Ausgabe(cos, fontBold, 12, Color.BLACK, 55, 560, "hier: Verrechnung Buchk�ufe und Honorar");

                // Anrede
                Ausgabe(cos, fontPlain, 12, Color.BLACK, 55, 515, resultAdresse.getString("ADRESSEN_ANREDE")
                        + " "
                        + Modulhelferlein.makeAnrede(resultAdresse.getString("ADRESSEN_NAMENSZUSATZ"),
                                "",
                                resultAdresse.getString("ADRESSEN_NAME"))
                        + ",");

                // Text
                Ausgabe(cos, fontPlain, 12, Color.BLACK, 55, 485, "Sie baten um eine Verrechnung mit den von Ihnen get�tigten Buchk�ufe. ");
                Float Betrag = 0F;
                ZeilenNr = 1;
                while (resultVerrechnung.next()) {
                    Ausgabe(cos, fontPlain, 12, Color.BLACK, 55, 455 - 15 * ZeilenNr, "Rechnung Nr. " + resultVerrechnung.getString("VERRECHNUNG_ISBN"));
                    Ausgabe(cos, fontPlain, 12, Color.BLACK, 337, 455 - 15 * ZeilenNr, "Betrag:" );
                    AusgabeDB(cos, fontPlain, 12, Color.BLACK, 397, 455 - 15 * ZeilenNr, resultVerrechnung.getString("VERRECHNUNG_BETRAG"));
                    Ausgabe(cos, fontPlain, 12, Color.BLACK, 425, 455 - 15 * ZeilenNr, "�");
                    Betrag = Betrag + resultVerrechnung.getFloat("VERRECHNUNG_BETRAG");
                    ZeilenNr = ZeilenNr + 1;
                }
                Ausgabe(cos, fontPlain, 12, Color.BLACK, 55, 455, "Im vergangenen Jahr haben Sie B�cher im Gesamtwert von " + Float.toString(Betrag) + " Euro erworben:");

                ZeilenNr = ZeilenNr + 2;
                Honorarzeile = 470 - 15 * ZeilenNr;
                Startzeile = Honorarzeile - 20;
                ZeilenNr = 0;

                while (resultHonorar.next()) {
                    HONORAR_ANZAHL_PB = resultHonorar.getInt("HONORAR_ANZAHL_PB");
                    HONORAR_ANZAHL_EB = resultHonorar.getInt("HONORAR_ANZAHL_EB");
                    HONORAR_ANZAHL_HC = resultHonorar.getInt("HONORAR_ANZAHL_HC");
                    HONORAR_PREIS_HC = resultHonorar.getFloat("HONORAR_PREIS_HC");
                    HONORAR_PREIS_PB = resultHonorar.getFloat("HONORAR_PREIS_PB");
                    HONORAR_PREIS_EB = resultHonorar.getFloat("HONORAR_PREIS_EB");
                    HONORAR_PROZENT_1 = resultHonorar.getInt("HONORAR_PROZENT_1");
                    HONORAR_PROZENT_2 = resultHonorar.getInt("HONORAR_PROZENT_2");
                    HONORAR_ANZAHL_HC = resultHonorar.getInt("HONORAR_ANZAHL_HC");
                    HONORAR_ANZAHL_EB = resultHonorar.getInt("HONORAR_ANZAHL_EB");
                    HONORAR_ANZAHL_PB = resultHonorar.getInt("HONORAR_ANZAHL_PB");
                    HONORAR_ZAHLEN = resultHonorar.getInt("HONORAR_ZAHLEN");
                    HONORAR_ISBN_PB = resultHonorar.getString("HONORAR_ISBN_PB");
                    HONORAR_ISBN_EB = resultHonorar.getString("HONORAR_ISBN_EB");
                    HONORAR_ISBN_HC = resultHonorar.getString("HONORAR_ISBN_HC");

                    Anzahl = HONORAR_ANZAHL_PB + HONORAR_ANZAHL_HC + HONORAR_ANZAHL_EB;
                    Integer Schwelle = 0;
                    if (HONORAR_ZAHLEN == 0) { // keine Zahlung - Schwelle 1 nicht erreicht
                        GesamtHonorar = 0D;
                    } else {
                        Netto_VP_PB = HONORAR_PREIS_PB / 107 * 100;
                        Netto_VP_HC = HONORAR_PREIS_HC / 107 * 100;
                        Netto_VP_EB = HONORAR_PREIS_EB / 107 * 100;
                        if (HONORAR_ZAHLEN == 1) {
                            Honorar_PB = HONORAR_PREIS_PB / 107 * HONORAR_PROZENT_1;
                            Honorar_HC = HONORAR_PREIS_HC / 107 * HONORAR_PROZENT_1;
                            Honorar_EB = HONORAR_PREIS_EB / 107 * HONORAR_PROZENT_1;
                            Honorar = (HONORAR_ANZAHL_PB * Honorar_PB + HONORAR_ANZAHL_HC * Honorar_HC + HONORAR_ANZAHL_EB * Honorar_EB);
                        } else {
                            Honorar_PB = HONORAR_PREIS_PB / 107 * HONORAR_PROZENT_2;
                            Honorar_HC = HONORAR_PREIS_HC / 107 * HONORAR_PROZENT_2;
                            Honorar_EB = HONORAR_PREIS_EB / 107 * HONORAR_PROZENT_2;
                            Honorar = (HONORAR_ANZAHL_PB * Honorar_PB + HONORAR_ANZAHL_HC * Honorar_HC + HONORAR_ANZAHL_EB * Honorar_EB);
                        }
                        GesamtHonorar = GesamtHonorar + Honorar*1D;

                        String[] ZeilenInhalt = {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""};
                        Integer Zeile = 0;
                        if (HONORAR_ISBN_PB.length() > 0) { // PB
                            Zeile = Zeile + 1;
                            ZeilenInhalt[(Zeile - 1) * 6 + 0] = HONORAR_ISBN_PB;
                            ZeilenInhalt[(Zeile - 1) * 6 + 1] = "Paperback";
                            ZeilenInhalt[(Zeile - 1) * 6 + 2] = HONORAR_ANZAHL_PB.toString();
                            ZeilenInhalt[(Zeile - 1) * 6 + 3] = Modulhelferlein.str2dec(Netto_VP_PB * 1D);
                            ZeilenInhalt[(Zeile - 1) * 6 + 4] = Modulhelferlein.str2dec(Honorar_PB * 1D);
                            ZeilenInhalt[(Zeile - 1) * 6 + 5] = Modulhelferlein.str2dec(Honorar_PB * 1D * HONORAR_ANZAHL_PB);
                            System.out.println("Zeile " + Zeile.toString() + " - PB");
                        }
                        if (HONORAR_ISBN_HC.length() > 0) { // HC
                            Zeile = Zeile + 1;
                            ZeilenInhalt[(Zeile - 1) * 6 + 0] = HONORAR_ISBN_HC;
                            ZeilenInhalt[(Zeile - 1) * 6 + 1] = "Hardcover";
                            ZeilenInhalt[(Zeile - 1) * 6 + 2] = HONORAR_ANZAHL_HC.toString();
                            ZeilenInhalt[(Zeile - 1) * 6 + 3] = Modulhelferlein.str2dec(Netto_VP_HC * 1D);
                            ZeilenInhalt[(Zeile - 1) * 6 + 4] = Modulhelferlein.str2dec(Honorar_HC * 1D);
                            ZeilenInhalt[(Zeile - 1) * 6 + 5] = Modulhelferlein.str2dec(Honorar_HC * 1D * HONORAR_ANZAHL_HC);
                            System.out.println("Zeile " + Zeile.toString() + " - HC");
                        }
                        if (HONORAR_ISBN_EB.length() > 0) { // EB
                            Zeile = Zeile + 1;
                            ZeilenInhalt[(Zeile - 1) * 6 + 0] = HONORAR_ISBN_EB;
                            ZeilenInhalt[(Zeile - 1) * 6 + 1] = "E-Book";
                            ZeilenInhalt[(Zeile - 1) * 6 + 2] = HONORAR_ANZAHL_EB.toString();
                            ZeilenInhalt[(Zeile - 1) * 6 + 3] = Modulhelferlein.str2dec(Netto_VP_EB * 1D);
                            ZeilenInhalt[(Zeile - 1) * 6 + 4] = Modulhelferlein.str2dec(Honorar_EB * 1D);
                            ZeilenInhalt[(Zeile - 1) * 6 + 5] = Modulhelferlein.str2dec(Honorar_EB * 1D * HONORAR_ANZAHL_EB);
                            System.out.println("Zeile " + Zeile.toString() + " - EB");
                        }

                        for (int i = 1; i <= Zeile; i++) {
                            Ausgabe(cos, fontPlain, 12, Color.BLACK, 55, Startzeile - 15 * ZeilenNr - i * 15, ZeilenInhalt[(i - 1) * 6 + 0]);
                            Ausgabe(cos, fontPlain, 12, Color.BLACK, 155, Startzeile - 15 * ZeilenNr - i * 15, ZeilenInhalt[(i - 1) * 6 + 1]);
                            AusgabeRB(cos, fontPlain, 12, Color.BLACK, 260, Startzeile - 15 * ZeilenNr - i * 15, ZeilenInhalt[(i - 1) * 6 + 2]);
                            AusgabeDB(cos, fontPlain, 12, Color.BLACK, 320, Startzeile - 15 * ZeilenNr - i * 15, ZeilenInhalt[(i - 1) * 6 + 3]);
                            AusgabeDB(cos, fontPlain, 12, Color.BLACK, 410, Startzeile - 15 * ZeilenNr - i * 15, ZeilenInhalt[(i - 1) * 6 + 4]);
                            AusgabeDB(cos, fontPlain, 12, Color.BLACK, 500, Startzeile - 15 * ZeilenNr - i * 15, ZeilenInhalt[(i - 1) * 6 + 5]);

                            Ausgabe(cos, fontPlain, 12, Color.BLACK, 340, Startzeile - 15 * ZeilenNr - i * 15, "�");
                            Ausgabe(cos, fontPlain, 12, Color.BLACK, 430, Startzeile - 15 * ZeilenNr - i * 15, "�");
                            Ausgabe(cos, fontPlain, 12, Color.BLACK, 520, Startzeile - 15 * ZeilenNr - i * 15, "�");
                        }
                        ZeilenNr = ZeilenNr + Zeile;
                    } // if Schwelle erreicht

                } // while (resultHonorar.next()) {
                Ausgabe(cos, fontPlain, 12, Color.BLACK, 55, Honorarzeile, "Dem gegen�ber stehen Honoraranspr�che in H�he von " + Modulhelferlein.str2dec(GesamtHonorar) + " Euro:");
                if (HONORAR_ZAHLEN > 0) { // keine Zahlung - Schwelle 1 nicht erreicht
                    Ausgabe(cos, fontPlain, 12, Color.BLACK, 55, Startzeile , "ISBN");
                    Ausgabe(cos, fontPlain, 12, Color.BLACK, 155, Startzeile, "Typ");
                    Ausgabe(cos, fontPlain, 12, Color.BLACK, 230, Startzeile, "Anzahl");
                    Ausgabe(cos, fontPlain, 12, Color.BLACK, 300, Startzeile, "Netto-VK");
                    Ausgabe(cos, fontPlain, 12, Color.BLACK, 400, Startzeile, "Honorar");
                    Ausgabe(cos, fontPlain, 12, Color.BLACK, 480, Startzeile, "Gesamt");

                    Linie(cos, 1, 55, Startzeile - 3, 540, Startzeile - 3);
                }

                // Abrechnung
                ZeilenNr = ZeilenNr + 1;
                Startzeile = Honorarzeile - 40 - 15 * ZeilenNr;
                
                if (GesamtHonorar - Betrag < 0) {
                    GesamtBetrag = -1D * (GesamtHonorar - Betrag);  // Gesamtbetrag negativ = einzahlen
                    Ausgabe(cos, fontPlain, 12, Color.BLACK, 55, Startzeile, "Den Differenzbetrag in H�he von");
                    Ausgabe(cos, fontBold, 12, Color.RED, 235, Startzeile, Modulhelferlein.str2dec(GesamtBetrag) + " Euro ");
                    Ausgabe(cos, fontPlain, 12, Color.BLACK, 310, Startzeile, "bitten wir auf unser Konto zu �berweisen");
                    Ausgabe(cos, fontPlain, 12, Color.BLACK, 55, Startzeile - 15, "bei der     Volksbank Berlin,    IBAN: DE61 1009 0000 2233 8320 17,    BIC: BEV0DEBB");  
                    Ausgabe(cos, fontPlain, 12, Color.BLACK, 55, Startzeile - 30, "innerhalb der n�chsten 14 Tage.");
                } else {
                    GesamtBetrag = 1D * (GesamtHonorar - Betrag);  // Gesamtbetrag positiv = auszahlen  
                    Ausgabe(cos, fontPlain, 12, Color.BLACK, 55, Startzeile, "Den Differenzbetrag in H�he von");
                    Ausgabe(cos, fontBold, 12, Color.GREEN, 235, Startzeile, Modulhelferlein.str2dec(GesamtBetrag) + " Euro ");
                    Ausgabe(cos, fontPlain, 12, Color.BLACK, 310, Startzeile, "�berweisen wir auf Ihr Konto bei der");
                    Ausgabe(cos, fontPlain, 12, Color.BLACK, 55, Startzeile - 20, resultAdresse.getString("ADRESSEN_BANK") + " IBAN: " + resultAdresse.getString("ADRESSEN_IBAN"));
                }

                // Schlussformel
                Ausgabe(cos, fontPlain, 12, Color.BLACK, 55, 150, Modulhelferlein.CheckStr("Mit freundlichen Gr��en"));
                Ausgabe(cos, fontPlain, 12, Color.BLACK, 55, 85, "Carola Hartmann");
                Ausgabe(cos, fontPlain, 12, Color.BLACK, 55, 70, "Diplom Kauffrau");

                // Make sure that the content stream is closed:
                cos.close();
                System.out.println("   .. Inhalt geschrieben");

                String outputFileName = Modulhelferlein.pathBerichte + "\\Honorare\\"
                        + "Verrechnung-Honorar"
                        + "-"
                        + Modulhelferlein.printSimpleDateFormat("yyyyMMdd")
                        + "-"
                        + HONORAR_ZAHLEN.toString()
                        + "-"
                        + resultAdresse.getString("ADRESSEN_NAME")
                        + "-"
                        + Titel
                        + ".pdf";
                System.out.println(".. " + outputFileName);

// add XMP metadata
                System.out.println("   .. XMP metaddata schreiben");
                XMPMetadata xmp = XMPMetadata.createXMPMetadata();

                DublinCoreSchema dc = xmp.createAndAddDublinCoreSchema();
                dc.setTitle(outputFileName);

                PDFAIdentificationSchema id = xmp.createAndAddPFAIdentificationSchema();
                id.setPart(1);
                id.setConformance("B");

                XmpSerializer serializer = new XmpSerializer();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                serializer.serialize(xmp, baos, true);

                PDMetadata metadata = new PDMetadata(document);
                metadata.importXMPMetadata(baos.toByteArray());
                document.getDocumentCatalog().setMetadata(metadata);
                System.out.println("   -> XMP metadata geschrieben");

// sRGB output intent
                System.out.println("   .. sRGB output schreiben");
                //InputStream colorProfile = briefRechnungMahnung.class.getResourceAsStream("/org/apache/pdfbox/resources/pdfa/sRGB.icc");
                InputStream colorProfile = briefRechnungMahnung.class.getResourceAsStream("sRGB.icc");
                PDOutputIntent intent = new PDOutputIntent(document, colorProfile);
                intent.setInfo("sRGB IEC61966-2.1");
                intent.setOutputCondition("sRGB IEC61966-2.1");
                intent.setOutputConditionIdentifier("sRGB IEC61966-2.1");
                intent.setRegistryName("http://www.color.org");
                document.getDocumentCatalog().addOutputIntent(intent);
                System.out.println("   -> sRGB output geschrieben");

// Save the results and ensure that the document is properly closed:
                document.save(outputFileName);
                document.close();

                SQLAdresse.close();
                resultAdresse.close();
                System.out.println("-> gespeichert: " + outputFileName);
//                Modulhelferlein.Infomeldung("Honorarabrechnung " + args[1], "ist als PDF gespeichert unter ", outputFileName);
//                try {
//                    Runtime.getRuntime().exec("cmd.exe /c " + "\"" + outputFileName + "\"");
//                } catch (IOException exept) {
//                    Modulhelferlein.Fehlermeldung("Honorarabrechnung", "Ausgabe Brief: IO-Exception: ", exept.getMessage());
//                } // try Brief ausgeben
            } catch (IOException ex) {
                Modulhelferlein.Fehlermeldung("Honorarverrechnung: Brief erstellen", "IO-Exception: ", ex.getMessage());
            } catch (SQLException ex) {
                Modulhelferlein.Fehlermeldung("Honorarverrechnung: Brief erstellen", "SQL-Exception: ", ex.getMessage());
            } catch (BadFieldValueException e) {
                // won't happen here, as the provided value is valid
                throw new IllegalArgumentException(e);
            } catch (TransformerException ex) {
                Modulhelferlein.Fehlermeldung("PDF/A-Erstellung Honorverrechnung", "TransformerException-Exception: " + ex.getMessage());
            }
        } // if conn!= null
    }
}    // class


//~ Formatted by Jindent --- http://www.jindent.com
