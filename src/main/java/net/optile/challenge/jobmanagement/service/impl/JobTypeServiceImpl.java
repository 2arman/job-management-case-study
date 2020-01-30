package net.optile.challenge.jobmanagement.service.impl;

import net.optile.challenge.jobmanagement.service.JobTypeService;
import net.optile.challenge.jobmanagement.service.dto.JobType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Arman
 * Date: 1/30/20
 * Time: 1:06 AM
 **/
@Service
public class JobTypeServiceImpl implements JobTypeService {
    @Override
    public List<JobType> getAll() {
        return new ArrayList<>();
    }
}
