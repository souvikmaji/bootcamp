package com.worker.service;

import org.springframework.stereotype.Service;

@Service
public class WorkerJobService {
	public void executeWorkersJob() {
		try {
			System.out.println("Starting job");
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			System.out.println("Error while executing sample job");
		} finally {
			System.out.println("Worker job has finished...");
		}
	}
}
