package com.worker.scheduler;

import javax.annotation.PostConstruct;

import org.quartz.JobDetail;
import org.quartz.SimpleTrigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

import com.worker.config.AutoWiringSpringBeanJobFactory;

@Configuration
@ConditionalOnExpression("'${using.spring.schedulerFactory}'=='true'")
public class SpringQrtzScheduler {
	@Autowired
	private ApplicationContext applicationContext;

	@PostConstruct
	public void init() {
		System.out.println("Hello world from Spring...");
	}
	
	@Bean
    public SpringBeanJobFactory springBeanJobFactory() {
        AutoWiringSpringBeanJobFactory jobFactory = new AutoWiringSpringBeanJobFactory();
        System.out.println("Configuring Job factory");

        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }
	
	@Bean
    public SchedulerFactoryBean scheduler(org.quartz.Trigger[] trigger, JobDetail job) {
        SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
        schedulerFactory.setConfigLocation(new ClassPathResource("quartz.properties"));

        System.out.println("Setting the Scheduler up");
        schedulerFactory.setJobFactory(springBeanJobFactory());
        schedulerFactory.setJobDetails(job);
        schedulerFactory.setTriggers(trigger);

        return schedulerFactory;
    }
	
	@Bean
    public JobDetailFactoryBean jobDetail() {
        JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();
        jobDetailFactory.setJobClass(WorkerJob.class);
        jobDetailFactory.setName("worker");
        jobDetailFactory.setDescription("Invoke Worker Job service...");
        jobDetailFactory.setDurability(true);
        return jobDetailFactory;
    }
	
	@Bean
    public SimpleTriggerFactoryBean trigger(JobDetail job) {

        SimpleTriggerFactoryBean trigger = new SimpleTriggerFactoryBean();
        trigger.setJobDetail(job);

        int frequencyInSec = 10;
        System.out.printf("Configuring trigger to fire every %d seconds\n", frequencyInSec);

        trigger.setRepeatInterval(frequencyInSec * 1000);
        trigger.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
        trigger.setName("Job Trigger");
        return trigger;
    }

}
