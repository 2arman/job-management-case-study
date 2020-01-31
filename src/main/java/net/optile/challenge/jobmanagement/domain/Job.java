package net.optile.challenge.jobmanagement.domain;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Arman
 * Date: 1/30/20
 * Time: 3:51 AM
 **/
@Entity
@Table(name = "JOB")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Job implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private JobStatus jobStatus;

    @Column
    private String jobType;

    @Column
    private Integer priority;

    @Column
    @Enumerated(EnumType.STRING)
    private ExecutionMethod executionMethod;

    @Column
    private LocalDateTime executionTime;

    @Column
    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<JobParameter> parameters;

    @Column
    private LocalDateTime createdDate;

    @Column
    private String result;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Job{");
        sb.append("id=").append(id);
        sb.append(", jobStatus=").append(jobStatus);
        sb.append(", jobType='").append(jobType).append('\'');
        sb.append(", priority=").append(priority);
        sb.append(", executionMethod=").append(executionMethod);
        sb.append(", executionTime=").append(executionTime);
        sb.append(", createdDate=").append(createdDate);
        sb.append('}');
        return sb.toString();
    }
}
