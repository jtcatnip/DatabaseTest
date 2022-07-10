package com.jeff.testdb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Priority;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
//import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Priority(50)
@Service
public class ServiceWorker {

	private static final Logger log = LogManager.getLogger(ServiceWorker.class);
	private String threadName = null;

	@Value("${serviceworker.sleep.interval}")
	private long sleepInterval;

	@Autowired
	private DBService dbService;

	@PostConstruct
	private void init() {
		log.info("@PostConstruct Starting up...Thread name: " + Thread.currentThread().getName() + " Group: " + Thread.currentThread().getThreadGroup().getName());
		log.info("Sleep interval set to: " + sleepInterval + " (ms)");

	}

	@Async("threadPoolExecutor")
	public void run() {
		
		log.info("Thread name: " + Thread.currentThread().getName() + " Group: " + Thread.currentThread().getThreadGroup().getName());
		log.info("Sleep interval set to: " + sleepInterval + " (ms)");

		try {
			while (true) {
				List<Row> retData = dbService.getData();
				for (Row r1 : retData) {
					log.info("Row: " + r1.toString());
				}

				try {
					log.info("Sleeping in ms: " + sleepInterval);
					Thread.sleep(sleepInterval);
				} catch (InterruptedException iEx) {
					log.error("Sleep exception caught: " + iEx.getMessage());

				}
			}
		} catch (Exception ex) {
			log.error("Error during while(true)" + ex.getMessage());
			log.error("Performing System.exit");
			System.exit(-1);

		}
	}

	@PreDestroy
	public void destroy() {
		log.info("@PreDestroy:Shutting down...Thread name: " + Thread.currentThread().getName() + " Group: " + Thread.currentThread().getThreadGroup().getName());

	}

}
