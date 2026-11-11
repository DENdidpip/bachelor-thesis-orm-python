package entities;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import java.io.Serializable;
import java.util.List;

@Entity
public class Osoba implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;    
    private String meno;
    
    @ManyToMany(mappedBy="autor", fetch = FetchType.EAGER)
//    @ManyToMany(mappedBy="autor")
    private List<Kniha> dielo;

    public List<Kniha> getDielo() {
        return dielo;
    }

    public void setDielo(List<Kniha> dielo) {
        this.dielo = dielo;
    }


    public String getMeno() {
        return meno;
    }

    public void setMeno(String meno) {
        this.meno = meno;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Osoba)) {
            return false;
        }
        Osoba other = (Osoba) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "p4app2.Osoba[ id=" + id + " ]";
    }
    
}
