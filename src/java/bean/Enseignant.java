/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import org.hibernate.validator.constraints.Email;

/**
 *
 * @author Abed
 */
@Entity
public class Enseignant implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String cin;
    private String nom;
    private String prenom;
    @Email
    private String email;
    private String password;
    private String telephone;
    private String gender;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateNaissance;
    private boolean blocked;
    private int nbrCnx;
    private boolean mdpChanged;
    private boolean admine;
    private int tentativeRest;
    
    @OneToMany(mappedBy = "enseignant")
    private List<Module> modules;

    @OneToMany(mappedBy = "user")
    private List<Device> devices;
    
    @ManyToOne
    private Filiere filiere; // si il est admin
    
    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }

    public Filiere getFiliere() {
        return filiere;
    }

    public void setFiliere(Filiere filiere) {
        this.filiere = filiere;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public int getNbrCnx() {
        return nbrCnx;
    }

    public void setNbrCnx(int nbrCnx) {
        this.nbrCnx = nbrCnx;
    }

    public boolean isMdpChanged() {
        return mdpChanged;
    }

    public void setMdpChanged(boolean mdpChanged) {
        this.mdpChanged = mdpChanged;
    }

    public boolean isAdmine() {
        return admine;
    }

    public void setAdmine(boolean admine) {
        this.admine = admine;
    }

    public int getTentativeRest() {
        return tentativeRest;
    }

    public void setTentativeRest(int tentativeRest) {
        this.tentativeRest = tentativeRest;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cin != null ? cin.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the cin fields are not set
        if (!(object instanceof Enseignant)) {
            return false;
        }
        Enseignant other = (Enseignant) object;
        if ((this.cin == null && other.cin != null) || (this.cin != null && !this.cin.equals(other.cin))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.Enseignant[ id=" + cin + " ]";
    }
    
}
