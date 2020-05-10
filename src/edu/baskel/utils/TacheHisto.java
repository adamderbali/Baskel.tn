/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.utils;

import edu.baskel.services.HistoriqueCRUD;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 * @author dell
 */
public class TacheHisto implements Job{
    
    HistoriqueCRUD hh = new HistoriqueCRUD();

    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        System.out.println("adam");
        System.out.println("adam"+ new Date());
        try {
            hh.LastCnx();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
}
