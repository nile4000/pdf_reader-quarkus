package dev.morling.quarkus.pdfextract.model;

import java.time.LocalDate;
import java.util.List;

public class Beleg {

    public LocalDate dateReceipt;

    public List<Position> positions;

    public Beleg(LocalDate dateReceipt, List<Position> positions) {
        this.dateReceipt = dateReceipt;
        this.positions = positions;
    }

    public Beleg() {
    }

}
