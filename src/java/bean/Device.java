/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author hp
 */
@Entity
public class Device implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String browser;
    private String operatingSystem;
    private String deviceCategorie;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateCreation;
    private String adresseMac;
    @ManyToOne
    private Enseignant user;

    @ManyToOne
    private Etudiant etudiant;

    public String getAdresseMac() {
        return adresseMac;
    }

    public void setAdresseMac(String adresseMac) {
        this.adresseMac = adresseMac;
    }

    public Enseignant getEnseignant() {
        if (user == null) {
            user = new Enseignant();
        }
        return user;
    }

    public void setEnseignant(Enseignant user) {
        this.user = user;
    }

    public Long getId() {
        return id;

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateCreation() {
        if ( dateCreation == null )
        {
            return dateCreation = new Date();
        }
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Enseignant getUser() {
        return user;
    }

    public void setUser(Enseignant user) {
        this.user = user;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
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
        if (!(object instanceof Device)) {
            return false;
        }
        Device other = (Device) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public String getDeviceCategorie() {
        return deviceCategorie;
    }

    public void setDeviceCategorie(String deviceCategorie) {
        this.deviceCategorie = deviceCategorie;
    }
    

    @Override
    public String toString() {
        return "bean.Device[ id=" + id + " ]";
    }

}
