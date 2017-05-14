/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Device;
import bean.Enseignant;
import controller.util.DeviceUtil;
import controller.util.HashageUtil;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Abed
 */
@Stateless
public class EnseignantFacade extends AbstractFacade<Enseignant> {

    @PersistenceContext(unitName = "PFEPU")
    private EntityManager em;

    @EJB
    private DeviceFacade deviceFacade;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EnseignantFacade() {
        super(Enseignant.class);
    }

   
    

   
    
    /**
    Methode : find ( Without Exception ) <br/>
    Author : Youssef Benihoud <br/>
    Date : 10/05/2017 <br/>
     * @param id
     * @return Enseignant, if it's found in DataBase <br/>
     * @return null, it it's not found in DataBase <br/>
    */
    
    @Override
    public Enseignant find(Object id) {
        try {
            Enseignant enseignant = (Enseignant) em.createQuery("select e from Enseignant e where e.cin ='" + id + "'").getSingleResult();
            if (enseignant != null) {
                return enseignant;
            }
        } catch (Exception e) {
            System.out.println("Makaynch had Enseignant");
            
        }
        return null;
    }
    
    /**
    Methode : find ( Without Exception ) 
    Author : Youssef Benihoud
    Date : 10/05/2017
    */
    
   
    /** 
    Methode Sign in Enseignant <br/>
    Author : Youssef Benihoud <br/>
    Date : 9/5/2017 <br/>
     * @param enseignant
     * @return -5 : The Param is Null <br/>
     * @return -4 : The Param is not found in DataBase <br/>
     * @return -3 : The Password is wrong <br/>
     * @return -2 : The Param is blocked <br/>
     * @return -6 : The Device's Param has Error <br/>
     * @return 1 : Connected With Success <br/>
     */
    
    
    public int signIn(Enseignant enseignant) {
        System.out.println("========= Sign In ===========");
        if (enseignant == null || enseignant.getCin() == null) {
            return -5; // You must type your login
        } else {
            Enseignant loadEns = find(enseignant.getCin());
            System.out.println("loadEns === "+loadEns);
           

            if (loadEns == null) {
                return -4; // there is no User here
            } else if (!loadEns.getPassword().equals(HashageUtil.sha256(enseignant.getPassword()))) {
                int nbrCnx = loadEns.getNbrCnx();

                if (nbrCnx < 3) {
                    System.out.println("loadEns.getNbrCnx == " + loadEns.getNbrCnx());
                    System.out.println(" This is Your Attempt number : " + nbrCnx);
                    loadEns.setNbrCnx(nbrCnx + 1);

                } else if (nbrCnx == 3) {  // If the User try more than 3 attempts

                    System.out.println(" This is Your Attempt number == " + nbrCnx);
                    loadEns.setBlocked(true); // Blocked User
                }

                edit(loadEns);
                return -3; // Wrong Password
            }
           else if (loadEns.isBlocked() == true ) {
                return -2; // Enseignant blocked
            } else {
                loadEns.setNbrCnx(0);

                System.out.println("============= START Create Device ==============");
                Device device = DeviceUtil.getDevice(); // Create Device
                int res = deviceFacade.checkDevice(loadEns, device); // Check if Device is already Exists
                System.out.println("res for Device === " + res);
                if (res < 0) {
                    // sendEmailRed(user, device);
                    System.out.println("==== res = -6 === ");
                    System.out.println("Device === " + device);
                    System.out.println("Device Browser === " + device.getBrowser());
                    System.out.println("Device Category === " + device.getDeviceCategorie());
                    System.out.println("Device Operating System === " + device.getOperatingSystem());
                    return -6; // this device is not included
                }
                System.out.println("=============  END Create Device ==============");

                edit(loadEns);
                
                return 1;
            }
        }
    }

    /*
    Methode Sign in
    Author : Youssef Benihoud
    Date : 9/5/2017
     */

 /*
    Methode : Clonning Enseignant
    Author : Youssef Benihoud
    Date : 10/05/2017
     */
    public Enseignant cloneEns(Enseignant enseignant) {
        Enseignant clone = new Enseignant();
        clone.setAdmine(enseignant.isAdmine());
        clone.setBlocked(enseignant.isBlocked());
        clone.setCin(enseignant.getCin());
        clone.setDateNaissance(enseignant.getDateNaissance());
        clone.setDevices(enseignant.getDevices());
        clone.setEmail(enseignant.getEmail());
        clone.setFiliere(enseignant.getFiliere());
        clone.setGender(enseignant.getGender());
        clone.setMdpChanged(enseignant.isMdpChanged());
        clone.setModules(enseignant.getModules());
        clone.setNbrCnx(enseignant.getNbrCnx());
        clone.setNom(enseignant.getNom());
        clone.setPassword(enseignant.getPassword());
        clone.setPrenom(enseignant.getPrenom());
        clone.setTelephone(enseignant.getTelephone());
        return clone;
    }

    /*
    Methode : Clonning Enseignant
    Author : Youssef Benihoud
    Date : 10/05/2017
     */
    
    
    /*
    TesT : Edit
    */
    public void editPass(String enseignant)
    {
        Enseignant test = find(enseignant);
        
        if ( test != null )
        {
            test.setPassword(HashageUtil.sha256("123456"));
            edit(test);
        }
    }
    /*
    TesT : Edit
    */
}
