package net.optile.challenge.jobmanagement.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.optile.challenge.jobmanagement.job.JobDefinition;
import net.optile.challenge.jobmanagement.service.JobTypeService;
import net.optile.challenge.jobmanagement.service.dto.JobType;
import net.optile.challenge.jobmanagement.service.exception.BadRequestException;
import net.optile.challenge.jobmanagement.service.exception.JobTypeNotFoundException;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Arman
 * Date: 1/30/20
 * Time: 1:06 AM
 **/
@Service
@Slf4j
public class JobTypeServiceImpl implements JobTypeService {

    @Override
    public List<JobType> getAllJobTypes() {
        return getAllJobDefinitions().stream()
                .map(jobDefinition -> JobType.builder()
                        .name(jobDefinition.getJobType())
                        .parameters(jobDefinition.getJobParameter())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<JobDefinition> getAllJobDefinitions() {
        List<JobDefinition> jobDefinitions = new ArrayList<>();
        Set<Class<? extends JobDefinition>> jobsImplemented = new Reflections(
                "net.optile", new SubTypesScanner()).getSubTypesOf(JobDefinition.class);
        for (Class<? extends JobDefinition> jobClass : jobsImplemented) {
            try {
                jobDefinitions.add(jobClass.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                log.warn("could not load job jobDefinition {}", jobClass.getName(), e);
            }
        }
        return jobDefinitions;
    }

    @Override
    public void validateTypeAndParameters(@NotNull String jobType, Map<String, String> parameters) {
        boolean validParam = getAllJobDefinitions()
                .stream()
                .filter(jobDefinition -> jobDefinition.getJobType().equals(jobType))
                .findFirst()
                .orElseThrow(() -> new JobTypeNotFoundException(jobType))
                .validate(parameters);
        if (!validParam) {
            throw new BadRequestException(jobType);
        }
    }
}
