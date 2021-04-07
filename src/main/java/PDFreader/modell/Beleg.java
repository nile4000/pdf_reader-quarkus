package PDFreader.modell;

import java.time.LocalDate;
import java.util.List;

public class Beleg {

    // Attribute
    private LocalDate dateReceipt;
    private String location;

    // Position angeben
    private List<Position> positionen;

    // Konstruktor
    public Beleg(LocalDate dateReceipt, String location, List<Position> positionen) {
        this.dateReceipt = dateReceipt;
        this.location = location;
        this.positionen = positionen;
    }

    // Getter & Setter
    public LocalDate getDateReceipt() {
        return dateReceipt;
    }

    public void setDateReceipt(LocalDate dateReceipt) {
        this.dateReceipt = dateReceipt;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Position> getPositionen() {
        return positionen;
    }

    public void setPositionen(List<Position> positionen) {
        this.positionen = positionen;
    }

}
