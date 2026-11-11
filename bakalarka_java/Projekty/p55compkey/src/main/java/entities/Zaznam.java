package entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Zaznam implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    private ZaznamPK pk;

    private double pulz;

    public double getPulz() {
        return pulz;
    }

    public void setPulz(double pulz) {
        this.pulz = pulz;
    }
    
    private double teplota;

    public double getTeplota() {
        return teplota;
    }

    public void setTeplota(double teplota) {
        this.teplota = teplota;
    }


    public ZaznamPK getPk() {
        return pk;
    }

    public void setPk(ZaznamPK pk) {
        this.pk = pk;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Zaznam other = (Zaznam) obj;
        if (!Objects.equals(this.pk, other.pk)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Zaznam{" + "pk=" + pk + ", pulz=" + pulz + ", teplota=" + teplota + '}';
    }
    


}
