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

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			System.out.println("Let's inspect the beans provided by Spring Boot:");

			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for (String beanName : beanNames) {
				System.out.println(beanName);
			}
		};
	}
}