package com.worker.scheduler;

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

	public void execute(JobExecutionContext context) throws JobExecutionException {

		System.out.printf("Job %s fired at %s", context.getJobDetail().getKey().getName(), context.getFireTime());
		jobService.executeWorkersJob();
	}

}
