/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Device;
import bean.Enseignant;
import bean.Etudiant;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Abed
 */
@Stateless
public class DeviceFacade extends AbstractFacade<Device> {

    @PersistenceContext(unitName = "PFEPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DeviceFacade() {
        super(Device.class);
    }

    /*
    Methode : CheckDevice  ; FindUser ; FindDeviceByEnseignant ; FindDeviceByEtudiant
    Author : Youssef Benihoud
     */
    public List<Device> findUser(Object user) {
        System.out.println("===== Start Find User ======");
        List<Device> list = new ArrayList();

        if (user instanceof Enseignant) {
            list = findDeviceByEnseignant((Enseignant) user);
        } else if (user instanceof Etudiant) {
            list = findDeviceByEtudiant((Etudiant) user);
        }
        System.out.println("===== End Find User ======");
        return list;

    }

    public int checkDevice(Object user, Device connectedDevice) {

        System.out.println("======== Start Check Device =======");
        System.out.println(" User is === " + user);
        List<Device> list = findUser(user);

        if (list.isEmpty()) {
            save(connectedDevice, user);
            return 1; // it's a new device
        } else {
            for (int i = 0; i < list.size(); i++) {
                Device device = list.get(i);
                if (device.getDeviceCategorie().equals(connectedDevice.getDeviceCategorie())
                        && device.getBrowser().equals(connectedDevice.getBrowser())
                        && device.getOperatingSystem().equals(connectedDevice.getOperatingSystem())) {
                    return 2; // this device is already here 
                }
            }
        }
        System.out.println("======== End Check Device =======");

        return -1; // this is not the device
    }

    public void save(Device device, Object user) {
        System.out.println("======== Save Device =======");

        if (user instanceof Enseignant) {
            device.setEnseignant((Enseignant) user);
        } else if (user instanceof Etudiant) {
            device.setEtudiant((Etudiant) user);
        }
        create(device);

        System.out.println("======== End Save Device =======");

    }

    /*
    Methode : CheckDevice 
    Author : Youssef Benihoud
     */
    
 /**
    Methode : find DEVICE By Enseignant <br/>
    Author : Youssef Benihoud <br/>
     * @param enseignant <br/>
     * @return List Device <br/>
     * @return Devices <br/>
     * @return Null <br/>
     */
    public List<Device> findDeviceByEnseignant(Enseignant enseignant) {
        if (enseignant == null || enseignant.getCin().equals("")) {
            return new ArrayList<>();
        } else if (enseignant.getDevices() != null) {
            String req = "SELECT d FROM Device d WHERE d.user.cin='" + enseignant.getCin() + "'";
            return em.createQuery(req).getResultList();
        } else {
            return new ArrayList<>();
        }
    }

    /*
    Methode : find DEVICE By Enseignant
    Author : Youssef Benihoud
     */

 /**
    Methode : find DEVICE By Etudiant <br/>
    Author : Youssef Benihoud  <br/>
     * @param etudiant
     * @return List of Device <br/>
     * @return Devices <br/>
     * @return Null <br/>
     */
    public List<Device> findDeviceByEtudiant(Etudiant etudiant) {
        if (etudiant == null || etudiant.getCne().equals("")) {
            return new ArrayList<>();
        } else if (etudiant.getDevices() != null) {
            String req = "SELECT d FROM Device d WHERE d.etudiant.cne='" + etudiant.getCne() + "'";
            return em.createQuery(req).getResultList();
        } else {
            return new ArrayList<>();
        }
    }

    /*
    Methode : find DEVICE By Etudiant
    Author : Youssef Benihoud
     */
}
