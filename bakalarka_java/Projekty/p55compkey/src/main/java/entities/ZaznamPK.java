package entities;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable
public class ZaznamPK implements Serializable {

    private static final long serialVersionUID = 1L;

    private String pacient;
    private LocalDate datum;

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }
    
    public String getPacient() {
        return pacient;
    }

    public void setPacient(String pacient) {
        this.pacient = pacient;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.pacient);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ZaznamPK other = (ZaznamPK) obj;
        if (!Objects.equals(this.pacient, other.pacient)) {
            return false;
        }
        if (!Objects.equals(this.datum, other.datum)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ZaznamPK{" + "pacient=" + pacient + ", datum=" + datum + '}';
    }
    
}
