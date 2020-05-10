/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.entities;

import java.sql.Date;

/**
 *
 * @author Skander
 */
public class Ban {
    private int id_ban;
    private int ban_num_u;
    private Date date_ban;
    private int id_u;

    public Ban() {
    }

    public Ban(int id_ban, int ban_num_u, Date date_ban, int id_u) {
        this.id_ban = id_ban;
        this.ban_num_u = ban_num_u;
        this.date_ban = date_ban;
        this.id_u = id_u;
    }

    public int getId_ban() {
        return id_ban;
    }

    public void setId_ban(int id_ban) {
        this.id_ban = id_ban;
    }

    public int getBan_num_u() {
        return ban_num_u;
    }

    public void setBan_num_u(int ban_num_u) {
        this.ban_num_u = ban_num_u;
    }

    public Date getDate_ban() {
        return date_ban;
    }

    public void setDate_ban(Date date_ban) {
        this.date_ban = date_ban;
    }

    public int getId_u() {
        return id_u;
    }

    public void setId_u(int id_u) {
        this.id_u = id_u;
    }

    @Override
    public String toString() {
        return "Ban{" + "id_ban=" + id_ban + ", ban_num_u=" + ban_num_u + ", date_ban=" + date_ban + ", id_u=" + id_u + '}';
    }
    
}
