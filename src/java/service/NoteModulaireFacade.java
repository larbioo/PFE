/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Etudiant;
import bean.NoteModulaire;
import bean.Semestre;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Abed
 */
@Stateless
public class NoteModulaireFacade extends AbstractFacade<NoteModulaire> {

    @PersistenceContext(unitName = "PFEPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NoteModulaireFacade() {
        super(NoteModulaire.class);
    }
    
     public List<NoteModulaire> findNoteModulaireBySemestre(Semestre semestre){
        return em.createQuery("SELECT nm FROM NoteModulaire nm WHERE nm.module.semestre="+semestre.getId()).getResultList();
    }
}
