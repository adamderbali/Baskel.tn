/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.utils;

import edu.baskel.services.BanCRUD;
import java.util.Date;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 * @author Skander
 */
public class ReactiveUser implements Job{
    BanCRUD bn= new BanCRUD();

    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
         System.out.println("wa9et reactive"+ new Date());
         bn.Reactive_ban();
    }
    
    
    
}
