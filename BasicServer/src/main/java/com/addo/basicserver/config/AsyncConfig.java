package com.addo.basicserver.config;

import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class AsyncConfig implements AsyncConfigurer {
	// Defines a Spring bean named "myExecutor", so Spring can inject it where needed
    @Bean(name = "myExecutor")
    Executor myExecutor() {
        // Get the number of available CPU cores on the machine
        int cores = Runtime.getRuntime().availableProcessors();

        // Create a ThreadPoolTaskExecutor (Spring wrapper around ThreadPoolExecutor)
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        // Set the number of core threads that will always stay alive
        executor.setCorePoolSize(cores);

        // Set the maximum number of threads in the pool (core * 2 for more concurrency)
        executor.setMaxPoolSize(cores * 2);

        // Set the queue capacity (how many tasks can wait if all threads are busy)
        executor.setQueueCapacity(cores);

        // Set a prefix for thread names to help identify them in logs
        executor.setThreadNamePrefix("MyExecutor-");

        // Define what happens when the pool is full (threads + queue).
        // Instead of crashing with an exception, we log "Flood detected"
        executor.setRejectedExecutionHandler(new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
                System.out.println("Flood detected");
            }
        });

        // Initialize the executor (must be done before using it)
        executor.initialize();

        // Return the executor instance so Spring can manage it
        return executor;
    }

    @Override
    public Executor getAsyncExecutor() {
        // Tell Spring that our custom executor "myExecutor" should be used for @Async methods
        return myExecutor();
    }
	
}
