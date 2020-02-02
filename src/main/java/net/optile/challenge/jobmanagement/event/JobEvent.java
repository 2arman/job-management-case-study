package net.optile.challenge.jobmanagement.event;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * @author Arman
 * Date: 2/2/20
 * Time: 3:51 AM
 **/
@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class JobEvent implements Comparable<JobEvent> {
    private final long jobId;
    private final int priority;

    @Override
    public int compareTo(JobEvent jobEvent) {
        return Integer.compare(this.getPriority(), jobEvent.getPriority());
    }
}
