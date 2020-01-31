package net.optile.challenge.jobmanagement.event.subscriber;

import com.google.common.eventbus.Subscribe;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.optile.challenge.jobmanagement.domain.Job;
import net.optile.challenge.jobmanagement.domain.JobStatus;
import net.optile.challenge.jobmanagement.job.JobExecutor;
import net.optile.challenge.jobmanagement.repository.JobRepository;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@SuppressWarnings("UnstableApiUsage")
@Slf4j
@Component
@AllArgsConstructor
public class EventListener {
    private static AtomicInteger eventsHandled = new AtomicInteger(0);
    private final JobExecutor jobExecutor;
    private final JobRepository jobRepository;

    public static int getEventsHandled() {
        return eventsHandled.get();
    }

    @Subscribe
    public void handleJob(Job event) {
        log.info("------------------------------- \n " +
                "listener get the job event {}", event);
        Job job = jobRepository.getOne(event.getId());
        job.setJobStatus(JobStatus.RUNNING);
        jobRepository.save(job);
        try {
            String executionResult = jobExecutor.execute(job);
            job.setJobStatus(JobStatus.SUCCESS);
            job.setResult(executionResult);
            jobRepository.save(job);
            log.info("job {} finished successfully!", job.getId());
        } catch (Throwable t) {
            log.info("job {} failed! ", job.getId(), t);
            job.setJobStatus(JobStatus.FAILED);
            jobRepository.save(job);
        } finally {
            eventsHandled.getAndIncrement();
        }
    }
}