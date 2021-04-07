package PDFreader.modell;

import java.util.Date;

public class Position {

    // Attribute
    private Date dateReceipt;
    private String bezeichnung;
    private String einheit;
    private double preis;
    private double aktion;
    private double total;

    // leerer Konstruktor
    public Position() {
    }

    // Konstruktor
    public Position(Date dateReceipt, String bezeichnung, String einheit, double preis, double aktion, double total) {
        this.dateReceipt = dateReceipt;
        this.bezeichnung = bezeichnung;
        this.einheit = einheit;
        this.preis = preis;
        this.aktion = aktion;
        this.total = total;
    }

    // Getter & Setter
    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public String getEinheit() {
        return einheit;
    }

    public void setEinheit(String einheit) {
        this.einheit = einheit;
    }

    public Date getdateReceipt() {
        return dateReceipt;
    }

    public void setdateReceipt(Date dateReceipt) {
        this.dateReceipt = dateReceipt;
    }

    public double getPreis() {
        return preis;
    }

    public void setPreis(double preis) {
        this.preis = preis;
    }

    public double getAktion() {
        return aktion;
    }

    public void setAktion(double aktion) {
        this.aktion = aktion;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

}