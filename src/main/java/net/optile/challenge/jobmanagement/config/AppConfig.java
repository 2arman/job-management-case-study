package net.optile.challenge.jobmanagement.config;

import com.google.common.eventbus.EventBus;
import net.optile.challenge.jobmanagement.event.subscriber.EventListener;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class AppConfig {

	@SuppressWarnings("UnstableApiUsage")
	@Bean
	public EventBus eventBus(EventListener eventListener) {
		EventBus jobEventBus = new EventBus("jobEventBus");
		jobEventBus.register(eventListener);
		return jobEventBus;
	}
}