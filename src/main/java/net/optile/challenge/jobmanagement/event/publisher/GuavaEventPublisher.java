package net.optile.challenge.jobmanagement.event.publisher;

import com.google.common.eventbus.EventBus;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.optile.challenge.jobmanagement.domain.ExecutionMethod;
import net.optile.challenge.jobmanagement.domain.Job;
import net.optile.challenge.jobmanagement.event.EventQueue;
import net.optile.challenge.jobmanagement.event.JobEvent;
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
public class GuavaEventPublisher implements EventPublisher {
    private final EventBus eventBus;
    private final ThreadPoolTaskScheduler taskScheduler;
    private final EventQueue eventQueue;

    @Override
    public void publishEvent(Job job) {
        taskScheduler.schedule(() -> {
                    log.info("post the job event to event bus with scheduled {}", job);
                    JobEvent jobEvent = new JobEvent(job.getId(), job.getPriority());
                    eventQueue.getJobEventsQueue().put(jobEvent);
                    eventBus.post(jobEvent);
                },
                Date.from(job.getExecutionTime().atZone(ZoneId.systemDefault()).toInstant()));
        log.info("job event {} with type {} scheduled for {}", job.getId(), job.getJobType(), job.getExecutionTime());
        log.info("Number of events handled {}", EventListener.getEventsHandled());
    }
}
