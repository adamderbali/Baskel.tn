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
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/**
 *
 * @author Skander
 */
public class CronJobAdmin {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  {
        
    try{
         JobDetail jobAdmin = JobBuilder.newJob(BanAuto.class).withIdentity("Cron", "group1").build();
            Trigger trigger1 = TriggerBuilder.newTrigger()
                    .withIdentity("CronTrigger", "group1")
                    .withSchedule(CronScheduleBuilder.cronSchedule("0 * * ? * *"))//chaque minute  0 * * ? * *	// chaue heure 0 0 * ? * *	
                    .build(); // chaue jr a 12pm 0 0 12 * * ?	//0,4,5 0 0 ? * * *//
            
            Scheduler scheduler1 = new StdSchedulerFactory().getScheduler();
            scheduler1.start();
            scheduler1.scheduleJob(jobAdmin, trigger1);
            
            JobDetail jobAdmin2 = JobBuilder.newJob(ReactiveUser.class).withIdentity("Cron2", "group12").build();
            Trigger trigger2 = TriggerBuilder.newTrigger()
                    .withIdentity("CronTrigger2", "group12")
                    .withSchedule(CronScheduleBuilder.cronSchedule("0 * * ? * *"))//chaque minute  0 * * ? * *	// chaue heure 0 0 * ? * *	
                    .build(); // chaue jr a 12pm 0 0 12 * * ?	//0 0 0 ? * * *
            
            Scheduler scheduler2 = new StdSchedulerFactory().getScheduler();
            scheduler1.start();
            scheduler1.scheduleJob(jobAdmin2, trigger2);
            
            
            Thread.sleep(100000);
            
    
    }catch(Exception e){
            e.printStackTrace();
    }
    }}

    

