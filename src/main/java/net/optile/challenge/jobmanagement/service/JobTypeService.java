package net.optile.challenge.jobmanagement.service;

import net.optile.challenge.jobmanagement.job.JobDefinition;
import net.optile.challenge.jobmanagement.service.dto.JobType;

import java.util.List;
import java.util.Map;

/**
 * @author Arman
 * Date: 1/30/20
 * Time: 1:02 AM
 **/
public interface JobTypeService {
    List<JobType> getAllJobTypes();

    List<JobDefinition> getAllJobDefinitions();

    void validateTypeAndParameters(String jobType, Map<String, String> parameters);
}
