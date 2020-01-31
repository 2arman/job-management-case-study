package net.optile.challenge.jobmanagement.event.publisher;

import com.google.common.eventbus.EventBus;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import net.optile.challenge.jobmanagement.domain.ExecutionMethod;
import net.optile.challenge.jobmanagement.domain.Job;
import net.optile.challenge.jobmanagement.event.subscriber.EventListener;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.util.Date;

/**
 * @author Arman
 * Date: 1/31/20
 * Time: 12:04 PM
 **/
@SuppressWarnings("UnstableApiUsage")
@Component
@AllArgsConstructor
@Slf4j
public class GuavaEventPublisher implements EventPublisher{
    private final EventBus eventBus;
    private final ThreadPoolTaskScheduler taskScheduler;

    @Override
    public void publishEvent(Job jobEvent) {
        if (jobEvent.getExecutionMethod() == ExecutionMethod.IMMEDIATE) {
            log.info("job event {} with type {} publish immediately", jobEvent.getId(), jobEvent.getJobType());
            eventBus.post(jobEvent);
        } else {
            taskScheduler.schedule(() -> {
                        log.info("post the job event with scheduled {}", jobEvent);
                        eventBus.post(jobEvent);
                    },
                    Date.from(jobEvent.getExecutionTime().atZone(ZoneId.systemDefault()).toInstant()));
            log.info("job event {} with type {} scheduled for {}", jobEvent.getId(), jobEvent.getJobType(), jobEvent.getExecutionTime());
        }
        log.info("Number of events handled {}", EventListener.getEventsHandled());
    }
}
