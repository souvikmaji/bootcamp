package com.worker.scheduler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.worker.service.WorkerJobService;

@Component
public class WorkerJob implements Job {
	@Autowired
	private WorkerJobService jobService;
	private static final Logger logger = LogManager.getLogger(WorkerJob.class);

	public void execute(JobExecutionContext context) throws JobExecutionException {
		logger.info("Job " + context.getJobDetail().getKey().getName() + " fired at " + context.getFireTime());
		jobService.executeWorkersJob();
	}

}
