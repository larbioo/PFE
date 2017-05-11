/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Device;
import bean.Etudiant;
import controller.util.DeviceUtil;
import controller.util.HashageUtil;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Abed
 */
@Stateless
public class EtudiantFacade extends AbstractFacade<Etudiant> {

    @PersistenceContext(unitName = "PFEPU")
    private EntityManager em;

    @EJB
    private DeviceFacade deviceFacade;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EtudiantFacade() {
        super(Etudiant.class);
    }
    
    
    /**
    Methode : find Etudiant ( Without Exception ) <br/>
    Author : Youssef Benihoud <br/>
    Date : 10/05/2017 <br/>
     * @param id
     * @return etudiant, if it's found in DataBase <br/>
     * @return null, it it's not found in DataBase <br/>
    */
    
    @Override
    public Etudiant find(Object id) {
        try {
            Etudiant etudiant = (Etudiant) em.createQuery("select e from Etudiant e where e.cne = '" + id + "'").getSingleResult();
            if (etudiant != null) {
                return etudiant;
            }
        } catch (Exception e) {
            System.out.println("Makaynch had Enseignant");
            
        }
        return null;
    }
    
    

    /** 
    Methode Sign in Etudiant <br/>
    Author : Youssef Benihoud <br/>
    Date : 9/5/2017 <br/>
     * @param etudiant
     * @return -5 : The Param is Null <br/>
     * @return -4 : The Param is not found in DataBase <br/>
     * @return -3 : The Password is wrong <br/>
     * @return -2 : The Param is blocked <br/>
     * @return -6 : The Device's Param has Error <br/>
     * @return 1 : Connected With Success <br/>
     */
    public int signIn(Etudiant etudiant) {
        System.out.println("========= Sign In ===========");

        if (etudiant == null || etudiant.getCne() == null) {
            return -5; // You must type your login
        } else {
            
            Etudiant loadEtud = find(etudiant.getCne());
            System.out.println("loadEtud === "+loadEtud);
            
            
            if (loadEtud == null) {
                return -4; // there is no User here
            } else if (!loadEtud.getPassword().equals(HashageUtil.sha256(etudiant.getPassword()))) {
                int nbrCnx = loadEtud.getNbrCnx();

                if (nbrCnx < 3) {
                    System.out.println("loadEtud.getNbrCnx == " + loadEtud.getNbrCnx());
                    System.out.println(" This is Your Attempt number : " + nbrCnx);
                    loadEtud.setNbrCnx(nbrCnx + 1);

                } else if (nbrCnx == 3) {  // If the User try more than 3 attempts

                    System.out.println(" This is Your Attempt number == " + nbrCnx);
                    loadEtud.setBlocked(true); // Blocked User
                }
                edit(loadEtud);
                return -3; // Wrong Password
            } else if (loadEtud.isBlocked()) {
                return -2; // Etudiant blocked
            } else {
                loadEtud.setNbrCnx(0);

                System.out.println("============= START Create Device ==============");
                Device device = DeviceUtil.getDevice(); // Create Device
                int res = deviceFacade.checkDevice(loadEtud, device); // Check if Device is already Exists
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

                edit(loadEtud);
                return 1; // Succes Connection

            }
        }
    }
    /*
    Methode Sign in
    Author : Youssef 
    Date : 09/05/2017
     */

 /*
    Methode : Clonning 
    Author : Youssef Benihoud
    Date : 10/05/2017
     */
    
    public Etudiant cloneEtud(Etudiant etudiant)
{
    Etudiant clone = new Etudiant();
    clone.setBlocked(etudiant.isBlocked());
    clone.setCne(etudiant.getCne());
    clone.setDateNaissance(etudiant.getDateNaissance());
    clone.setDevices(etudiant.getDevices());
    clone.setEmail(etudiant.getEmail());
    clone.setFiliere(etudiant.getFiliere());
    clone.setGender(etudiant.getGender());
    clone.setMdpChanged(etudiant.isMdpChanged());
    clone.setNbrCnx(etudiant.getNbrCnx());
    clone.setNom(etudiant.getNom());
    clone.setPassword(etudiant.getPassword());
    clone.setPrenom(etudiant.getPrenom());
    return clone;
}
    
    
 /*
    Methode : Clonning 
    Author : Youssef Benihoud
    Date : 10/05/2017
   */
    
    
    /*TEST*/
    public void editPass(Long etudiant)
    {
        Etudiant test = find(etudiant);
        
        if ( test != null )
        {
            test.setPassword(HashageUtil.sha256("123456"));
            edit(test);
        }
    }
    /*TEST*/
    
    
}
