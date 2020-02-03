package net.optile.challenge.jobmanagement.event.subscriber;

import com.google.common.eventbus.Subscribe;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.optile.challenge.jobmanagement.event.EventQueue;
import net.optile.challenge.jobmanagement.event.JobEvent;
import net.optile.challenge.job.executor.JobExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@SuppressWarnings("UnstableApiUsage")
@Slf4j
@Component
@AllArgsConstructor
public class EventListener {
    private static AtomicInteger eventsHandled = new AtomicInteger(0);
    private final JobExecutor jobExecutor;
    private final EventQueue eventQueue;

    public static int getEventsHandled() {
        return eventsHandled.get();
    }

    @Subscribe
    public void handleJob(JobEvent jobEvent) {
        log.info("listener get the job event {}", jobEvent);
        JobEvent priorJobEvent = eventQueue.getJobEventsQueue().poll();
        log.info("This job event select for execution {}", priorJobEvent);
        jobExecutor.execute(jobEvent.getJobId());
        eventsHandled.getAndIncrement();
    }
}