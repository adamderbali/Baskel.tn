/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.utils;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/**
 *
 * @author dell
 */
public class CronJob {
    public static void jobs(){
        
        try{
            JobDetail job1 = JobBuilder.newJob(TacheHisto.class)
                    .withIdentity("Cron", "group1").build();
            
            Trigger trigger1 = TriggerBuilder.newTrigger()
                    .withIdentity("CronTrigger", "group1")
                    .withSchedule(CronScheduleBuilder.cronSchedule("0 0 12 * * ?"))//chaque minute  0 * * ? * *	// chaue heure 0 0 * ? * *	
                    .build(); // chaue jr a 12pm 0 0 12 * * ?	//
            
            Scheduler scheduler1 = new StdSchedulerFactory().getScheduler();
            scheduler1.start();
            scheduler1.scheduleJob(job1, trigger1);
            
            Thread.sleep(100000);
            
            scheduler1.shutdown();
            
        }catch(Exception e){
            e.printStackTrace();
    }
    }
    
   // public static void main(String[] args) {
        //jobs();
    //}
}
