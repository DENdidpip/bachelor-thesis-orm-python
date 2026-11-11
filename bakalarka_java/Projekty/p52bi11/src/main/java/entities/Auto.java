package entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import java.io.Serializable;

@Entity
public class Auto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String spz;
    private String znacka;
    private int rokVyroby;

    @OneToOne(mappedBy="vozidlo")
    private Poistenie pzp;

    public Poistenie getPzp() {
        return pzp;
    }

    public void setPzp(Poistenie pzp) {
        this.pzp = pzp;
    }
    public String getSpz() {
        return spz;
    }

    public void setSpz(String spz) {
        this.spz = spz;
    }

    public String getZnacka() {
        return znacka;
    }

    public void setZnacka(String znacka) {
        this.znacka = znacka;
    }

    public int getRokVyroby() {
        return rokVyroby;
    }

    public void setRokVyroby(int rokVyroby) {
        this.rokVyroby = rokVyroby;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (spz != null ? spz.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the spz fields are not set
        if (!(object instanceof Auto)) {
            return false;
        }
        Auto other = (Auto) object;
        if ((this.spz == null && other.spz != null) || (this.spz != null && !this.spz.equals(other.spz))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "p4app2.Kniha[ id=" + spz + " ]";
    }
    
}
