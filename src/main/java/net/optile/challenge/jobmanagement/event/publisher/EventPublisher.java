package net.optile.challenge.jobmanagement.event.publisher;

import net.optile.challenge.jobmanagement.domain.Job;

/**
 * @author Arman
 * Date: 1/31/20
 * Time: 11:28 PM
 **/
public interface EventPublisher {
    void publishEvent(Job jobEvent);
}
