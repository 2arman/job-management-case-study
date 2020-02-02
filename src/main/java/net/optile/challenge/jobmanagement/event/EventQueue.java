package net.optile.challenge.jobmanagement.event;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author Arman
 * Date: 2/2/20
 * Time: 4:31 AM
 **/
@Getter
@Component
public class EventQueue {
    private final PriorityBlockingQueue<JobEvent> jobEventsQueue;

    public EventQueue() {
        this.jobEventsQueue = new PriorityBlockingQueue<>();
    }
}
