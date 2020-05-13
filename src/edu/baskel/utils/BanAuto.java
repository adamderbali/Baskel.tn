/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.utils;

import edu.baskel.services.ReclamationCRUD;
import java.util.Date;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 * @author Skander
 */
public class BanAuto implements Job{
        ReclamationCRUD rcd = new ReclamationCRUD();
        
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        System.out.println("Lennaaa ");
        System.out.println("wa9et   "+ new Date());
        try {
            rcd.Banner_user();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
}
