/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.Enseignant;
import bean.Etudiant;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import service.EnseignantFacade;
import service.EtudiantFacade;

/**
 *
 * @author BENIHOUD Youssef
 */
@Named(value = "connectionController")
@SessionScoped
public class ConnectionController implements Serializable {

    private Enseignant selectedEns;
    private Etudiant selectedEtud;
    private String messageConnection;
    @EJB
    private EnseignantFacade enseignantFacade;
    @EJB
    private EtudiantFacade etudiantFacade;

    /**
     * Creates a new instance of ConnectionController
     */
    public ConnectionController() {
    }

    /**
     * Methode Controller : Sign In Etudiant ; Sign In Enseignant Author :
     * Youssef Benihoud Date : 09/05/2017
     */
    public void signInEtud() {

        System.out.println("====  Start Sign In Etudiant Controller  === ");

        Etudiant testEtud = new Etudiant();
        Etudiant test = new Etudiant();

        test = etudiantFacade.cloneEtud(selectedEtud);
        System.out.println(" testEtud  === " + test);
        selectedEtud = new Etudiant();
        int res = etudiantFacade.signIn(test);
        System.out.println(" Res  === " + res);

        if (res == 1) {

            testEtud = etudiantFacade.find(test.getCne());
            if (testEtud.isMdpChanged()) {
                // To Do Session
                // To Do Path To Change Password Interface For Etudiant	
                messageConnection = "You have to Change Your Password";
            }

            // To Do Session
            // To Do Path : Interface Etudiant
            messageConnection = "You are Etudiant";

        } else if (res == -5) {
            messageConnection = "You didn't write anything";
        } else if (res == -4) {
            messageConnection = " This User doesn't exist ";
        } else if (res == -3) {
            messageConnection = "Wrong Password";
        } else if (res == -6) {
            messageConnection = " Device Problem";
        } else if (res == -2) {
            messageConnection = " Blocked User";
        }

        // To Do Path : To Connection Page
        System.out.println("====  End Sign In Etudiant Controller  === ");

    }

    public void signInEns() {
        System.out.println("====  Start Sign In Enseignant Controller  === ");

        Enseignant test = new Enseignant();
        Enseignant testEns = new Enseignant();

        test = enseignantFacade.cloneEns(selectedEns);
        selectedEns = new Enseignant();
        System.out.println(" test  === " + test);
        int res = enseignantFacade.signIn(test);

        if (res == 1) {
            testEns = enseignantFacade.find(test.getCin());

            if (testEns.isMdpChanged()) {
                // To Do Session
                // To DO Path : Change Password Interface For Enseignant
                messageConnection = " You have to change your password ";
            }

            if (testEns.isAdmine()) {
                // To Do Session
                // To Do Path : Interface Admin
                messageConnection = " You are Admin ";
            }

            // To Do Session
            // To Do Path : Interface Enseignant
            messageConnection = " You are Enseignant";
        } else if (res == -5) {
            messageConnection = "You didn't write anything";
        } else if (res == -4) {
            messageConnection = " This User doesn't exist ";
        } else if (res == -3) {
            messageConnection = "Wrong Password";
        } else if (res == -6) {
            messageConnection = " Device Problem";
        } else if (res == -2) {
            messageConnection = " Blocked User";
        }

        // To Do Path : To Connection Page
        System.out.println("====  Start Sign In Enseignant Controller  === ");

    }

    /*
    Test Controller 
    Edit
    
    
    public void editEns()
    {
        //enseignantFacade.editPass(selectedEns.getCin());
        etudiantFacade.editPass(selectedEtud.getCne());
        messageConnection = "Password Changed from EditEns";
    }
    
    
    Test Controller 
    Edit
     */
    public Enseignant getSelectedEns() {
        if (selectedEns == null) {
            return selectedEns = new Enseignant();
        }
        return selectedEns;
    }

    public void setSelectedEns(Enseignant selectedEns) {
        this.selectedEns = selectedEns;
    }

    public Etudiant getSelectedEtud() {
        if (selectedEtud == null) {
            return selectedEtud = new Etudiant();
        }
        return selectedEtud;
    }

    public void setSelectedEtud(Etudiant selectedEtud) {
        this.selectedEtud = selectedEtud;
    }

    public String getMessageConnection() {
        if (messageConnection == null) {
            return messageConnection = "";
        }
        return messageConnection;
    }

    public void setMessageConnection(String messageConnection) {
        this.messageConnection = messageConnection;
    }

}
