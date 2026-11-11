/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author vsa
 */
@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Knihkupectvo implements Serializable {

    @Id
    private String nazov;
    private String adresa;
    // Struktura prepojovacej tabulky podla prednasky REST3
    @JoinTable(name = "KNIHKUPECTVO_KNIHA",
             joinColumns = @JoinColumn(name = "OBCHOD_NAZOV"),
             inverseJoinColumns = @JoinColumn(name = "KNIHA_ISBN"))
    @ManyToMany
//    @XmlElementWrapper(name = "ponuka")
//    @XmlElement(name = "kniha")
//    @XmlTransient
    private List<Kniha> ponuka;
        

    public Knihkupectvo() {
    }

    public Knihkupectvo(String nazov, String adresa) {
        this.nazov = nazov;
        this.adresa = adresa;
        ponuka = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Knihkupectvo{" + "nazov=" + nazov + '}';
    }

    public String getNazov() {
        return nazov;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    @XmlTransient
    public List<Kniha> getPonuka() {
        return ponuka;
    }
    
    @XmlElementWrapper(name = "ponuka")
    @XmlElement(name = "isbn")
    public List<String> getIsbnList() {
        List<String> isbnList = new ArrayList<>();
        for(Kniha k: ponuka) {
            isbnList.add(k.getIsbn());
        }
        return isbnList;
    }

    public void setPonuka(List<Kniha> ponuka) {
        this.ponuka = ponuka;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.nazov);
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
        final Knihkupectvo other = (Knihkupectvo) obj;
        if (!Objects.equals(this.nazov, other.nazov)) {
            return false;
        }
        return true;
    }

}
