package net.optile.challenge.jobmanagement.domain;

import io.swagger.annotations.ApiModelProperty;
import net.optile.challenge.jobmanagement.service.dto.JobExecutionTypeDto;
import net.optile.challenge.jobmanagement.service.dto.JobStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author Arman
 * Date: 1/30/20
 * Time: 3:51 AM
 **/
@Entity
@Table(name = "JOB")
public class Job implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    private final JobStatus jobStatus;
//
//    @ApiModelProperty(value = "job status", position = 2)
//    private final String jobType;
//
//    @ApiModelProperty(value = "job status", position = 3)
//    private final Integer priority;
//
//    @ApiModelProperty(value = "job execution method", position = 4)
//    private final JobExecutionTypeDto jobExecutionType;
//
//    @ApiModelProperty(value = "job parameter", position = 5)
//    private final Map<String,String> parameters;
//
//    @ApiModelProperty(value = "job created date", position = 6)
//    private final LocalDateTime createdDate;
