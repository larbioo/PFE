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
public class Etudiant implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private Long cne;
    private String nom;
    private String prenom;
    @Email
    private String email;
    private String gender;
    private String password;
    /* Wrote By Youssef */
    private boolean mdpChanged;
    private boolean blocked;
    private int nbrCnx;
    /* Wrote By Youssef */
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateNaissance;

    @OneToMany(mappedBy = "etudiant")
    private List<Device> devices;

    @OneToMany(mappedBy = "etudiant")
    private List<Demande> demandes;
    
    @OneToMany(mappedBy = "etudiant")
    private List<NoteAnnuelle> noteAnnuelles;
    
    @OneToMany(mappedBy = "etudiant")
    private List<NoteSemestre> noteSemestres;
    
    @OneToMany(mappedBy = "etudiant")
    private List<NoteModulaire> noteModulaires;
    
    @ManyToOne
    private Filiere filiere;

    public Long getCne() {
        return cne;
    }

    public void setCne(Long cne) {
        this.cne = cne;
    }

    public List<NoteSemestre> getNoteSemestres() {
        return noteSemestres;
    }

    public void setNoteSemestres(List<NoteSemestre> noteSemestres) {
        this.noteSemestres = noteSemestres;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<NoteModulaire> getNoteModulaires() {
        return noteModulaires;
    }

    public void setNoteModulaires(List<NoteModulaire> noteModulaires) {
        this.noteModulaires = noteModulaires;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public List<Demande> getDemandes() {
        return demandes;
    }

    public void setDemandes(List<Demande> demandes) {
        this.demandes = demandes;
    }

    public List<NoteAnnuelle> getNoteAnnuelles() {
        return noteAnnuelles;
    }

    public void setNoteAnnuelles(List<NoteAnnuelle> noteAnnuelles) {
        this.noteAnnuelles = noteAnnuelles;
    }

    public Filiere getFiliere() {
        return filiere;
    }

    public void setFiliere(Filiere filiere) {
        this.filiere = filiere;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    public boolean isMdpChanged() {
        return mdpChanged;
    }

    public void setMdpChanged(boolean mdpChanged) {
        this.mdpChanged = mdpChanged;
    }

    public int getNbrCnx() {
        return nbrCnx;
    }

    public void setNbrCnx(int nbrCnx) {
        this.nbrCnx = nbrCnx;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cne != null ? cne.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the cne fields are not set
        if (!(object instanceof Etudiant)) {
            return false;
        }
        Etudiant other = (Etudiant) object;
        if ((this.cne == null && other.cne != null) || (this.cne != null && !this.cne.equals(other.cne))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.Etudiant[ id=" + cne + " ]";
    }

}
