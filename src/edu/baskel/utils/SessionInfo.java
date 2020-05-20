/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.utils;

import edu.baskel.entities.Membre;
import edu.baskel.entities.Reparateur;

/**
 *
 * @author dell
 */
public final class SessionInfo {

    public static Membre loggedM;
    public static Reparateur loggedR;//
    public static int iduser;
    private static SessionInfo instance;

    public SessionInfo() {
    }

    public SessionInfo(int iduser) {
        this.iduser = iduser;

    }

    public static Membre getLoggedM() {
        return loggedM;
    }

    public static void setLoggedM(Membre loggedM) {
        SessionInfo.loggedM = loggedM;
    }

    public static SessionInfo getInstance(int iduser) {
        if (instance == null) {
            instance = new SessionInfo(iduser);
        }
        return instance;
    }
//
    public static Reparateur getLoggedR() {
        return loggedR;
    }
//
    public static void setLoggedR(Reparateur loggedR) {
        SessionInfo.loggedR = loggedR;
    }

    public static SessionInfo getInstance() {
        return instance;
    }

    public static void setInstance(SessionInfo instance) {
        SessionInfo.instance = instance;
    }

    public static void setIduser(int iduser) {
        SessionInfo.iduser = iduser;
    }

    public int getiduser() {
        return iduser;
    }

    @Override
    public String toString() {
        return "SessionInfo{" + "id_u=" + iduser + '}';
    }

}
