package dev.morling.quarkus.pdfextract.model;

import jakarta.json.bind.annotation.JsonbNumberFormat;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Position {

    // Attribute
    private String bezeichnung;
    @JsonbNumberFormat("#0.00")
    private double preis;
    private String einheit;
    @JsonbNumberFormat("#0.00")
    private double total;

    // leerer Konstruktor
    public Position() {
    }

    // Konstruktor
    public Position(String bezeichnung, String einheit, double preis, double total) {
        this.bezeichnung = bezeichnung;
        this.einheit = einheit;
        this.preis = preis;
        this.total = total;
    }

    public double getPreis() {
        return preis;
    }

    public void setPreis(double preis) {
        this.preis = preis;
    }

    public String getEinheit() {
        return einheit;
    }

    public void setEinheit(String einheit) {
        this.einheit = einheit;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

}