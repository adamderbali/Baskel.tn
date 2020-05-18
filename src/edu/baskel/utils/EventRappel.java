/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.baskel.utils;

import edu.baskel.services.ParticipationCrud;
import java.util.Date;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 * @author sabri
 */
public class EventRappel implements Job{
    ParticipationCrud pc = new ParticipationCrud();
    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        
        try {
            System.out.println("job1 -----> Time is"+new Date());
            pc.rappelEvent();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        
    }
    
}
