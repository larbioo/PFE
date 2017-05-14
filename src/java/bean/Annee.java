/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Abed
 */
@Entity
public class Annee implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int libelle;
    
    @OneToMany(mappedBy = "annee")
    private List<Semestre> semestres;
    
    @OneToMany(mappedBy = "annee")
    private List<NoteAnnuelle> noteAnnuelles;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Semestre> getSemestres() {
        return semestres;
    }

    public void setSemestres(List<Semestre> semestres) {
        this.semestres = semestres;
    }

    public List<NoteAnnuelle> getNoteAnnuelles() {
        return noteAnnuelles;
    }

    public void setNoteAnnuelles(List<NoteAnnuelle> noteAnnuelles) {
        this.noteAnnuelles = noteAnnuelles;
    }

    public int getLibelle() {
        return libelle;
    }

    public void setLibelle(int libelle) {
        this.libelle = libelle;
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
        if (!(object instanceof Annee)) {
            return false;
        }
        Annee other = (Annee) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Annee "+libelle;
    }
    
}
