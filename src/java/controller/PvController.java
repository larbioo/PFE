/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.NoteModulaire;
import bean.Semestre;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import service.EtudiantFacade;
import service.NoteModulaireFacade;

/**
 *
 * @author Abed
 */
@Named(value = "pvController")
@SessionScoped
public class PvController implements Serializable {

    @EJB
    private EtudiantFacade etudiantFacade;
    @EJB
    private NoteModulaireFacade noteModulaireFacade;
    private List<NoteModulaire> items;
    private Semestre semestre;

    public void update(Semestre item) {
        items = noteModulaireFacade.findNoteModulaireBySemestre(item);
    }

    public List<NoteModulaire> getItems() {
        if (items == null) {
            items = new ArrayList();
        }
        return items;
    }

    public void setItems(List<NoteModulaire> items) {
        this.items = items;
    }

    public EtudiantFacade getEtudiantFacade() {
        return etudiantFacade;
    }

    public void setEtudiantFacade(EtudiantFacade etudiantFacade) {
        this.etudiantFacade = etudiantFacade;
    }

    public Semestre getSemestre() {
        return semestre;
    }

    public void setSemestre(Semestre semestre) {
        this.semestre = semestre;
    }

    /**
     * Creates a new instance of PvController
     */
    public PvController() {
    }

}
