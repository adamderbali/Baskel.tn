/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.entities;

import java.util.Date;

/**
 *
 * @author Skander
 */
public class Participation {
    
    private Evenement event;
    private Membre participant;
    private Date date_insc;

    public Participation(Evenement event, Membre participant, Date date_insc) {
        this.event = event;
        this.participant = participant;
        this.date_insc = new Date();
       
    }

    public Participation() {
    }

    public Evenement getEvent() {
        return event;
    }

    public void setEvent(Evenement event) {
        this.event = event;
    }

    public Membre getParticipant() {
        return participant;
    }

    public void setParticipant(Membre participant) {
        this.participant = participant;
    }

    public Date getDate_insc() {
        return date_insc;
    }

   
    @Override
    public String toString() {
        return "Participation{" + "event=" + event + ", participant=" + participant + ", date_insc=" + date_insc + '}';
    }
    

  
    
    
}
