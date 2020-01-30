package net.optile.challenge.jobmanagement.service;

import net.optile.challenge.jobmanagement.service.dto.JobType;

import java.util.List;

/**
 * @author Arman
 * Date: 1/30/20
 * Time: 1:02 AM
 **/
public interface JobTypeService {
    List<JobType> getAll();
}
