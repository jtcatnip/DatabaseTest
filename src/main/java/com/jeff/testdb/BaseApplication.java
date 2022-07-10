package com.jeff.testdb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@SpringBootApplication
@Configuration
@EnableAsync

public class BaseApplication implements CommandLineRunner {
	
	private static final Logger log = LogManager.getLogger(BaseApplication.class);
	
//	private static ConfigurableApplicationContext context = null;
	
	@Autowired 
	private ServiceWorker serviceWorker;
	
	@Autowired
	private TimeWorker timeWorker;
	
	@Bean("threadPoolExecutor")
	public TaskExecutor getAsyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(4);
		executor.setMaxPoolSize(4);
		executor.setWaitForTasksToCompleteOnShutdown(true);
		executor.setThreadNamePrefix("Async-BaseProducer-");
		executor.setThreadGroupName("DB-Tester");
		executor.initialize();
		log.info("threadPoolExecutor initialized");
		return executor;		
	} 
	

	@Override
	public void run(String... args) throws Exception {
		log.info("In run() before start of threads");
		serviceWorker.run();
		timeWorker.run();
		log.info("In run() after threads started");	
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		log.info("In main() in Step1. Starting SpringApplication.run()");
		
		SpringApplication app = new SpringApplication(BaseApplication.class);
		app.run(args);
	
		log.info("In main(). Completed SpringApplication.run()");
		

	}

}
