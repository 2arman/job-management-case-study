package net.optile.challenge.jobmanagement.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "JOB_PARAMETER")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class JobParameter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_job_id"))
    private Job job;

    @Column(name = "key")
    private String key;

    @Column(name = "value")
    private String value;
}