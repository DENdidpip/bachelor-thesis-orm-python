package entities;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.io.Serializable;

@Entity
public class Poistenie implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private Long cislo;    
    private double suma;
    @Embedded
    private Auto vozidlo;

    public Long getCislo() {
        return cislo;
    }

    public void setCislo(Long cislo) {
        this.cislo = cislo;
    }

    public double getSuma() {
        return suma;
    }

    public void setSuma(double suma) {
        this.suma = suma;
    }

    public Auto getVozidlo() {
        return vozidlo;
    }

    public void setVozidlo(Auto vozidlo) {
        this.vozidlo = vozidlo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cislo != null ? cislo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the cislo fields are not set
        if (!(object instanceof Poistenie)) {
            return false;
        }
        Poistenie other = (Poistenie) object;
        if ((this.cislo == null && other.cislo != null) || (this.cislo != null && !this.cislo.equals(other.cislo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "p4app2.Osoba[ id=" + cislo + " ]";
    }
    
}
