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
 * @author sabri
 */
public class CjEvent {
    
    public static void main(String args[]){
        
       
        try {
            JobDetail job1 = JobBuilder.newJob(EventRappel.class)
                    .withIdentity("job1","group1").build();
            
            Trigger trigger1 = TriggerBuilder.newTrigger()
                    .withIdentity("cronTrigger1","group1")
                    .withSchedule(CronScheduleBuilder.cronSchedule(""))
                    .build();
            
            Scheduler scheduler1 = new StdSchedulerFactory().getScheduler();
            scheduler1.start();
            scheduler1.scheduleJob(job1,trigger1);
           
                Thread.sleep(100000);
            
            scheduler1.shutdown();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        
        }
                
    }
    
/*
  try {
                Thread.sleep(100000);
            } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
            }*/