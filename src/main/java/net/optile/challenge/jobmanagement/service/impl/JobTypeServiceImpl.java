package net.optile.challenge.jobmanagement.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.optile.challenge.job.definition.JobTypeDefinition;
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
                .map(jobTypeDefinition -> JobType.builder()
                        .name(jobTypeDefinition.getJobTypeName())
                        .parameters(jobTypeDefinition.getJobParameter())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<JobTypeDefinition> getAllJobDefinitions() {
        List<JobTypeDefinition> jobTypeDefinitions = new ArrayList<>();
        Set<Class<? extends JobTypeDefinition>> jobsImplemented = new Reflections(
                "net.optile", new SubTypesScanner()).getSubTypesOf(JobTypeDefinition.class);
        for (Class<? extends JobTypeDefinition> jobClass : jobsImplemented) {
            try {
                jobTypeDefinitions.add(jobClass.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                log.warn("could not load job jobDefinition {}", jobClass.getName(), e);
            }
        }
        return jobTypeDefinitions;
    }

    @Override
    public void validateTypeAndParameters(@NotNull String jobType, Map<String, String> parameters) {
        boolean validParam = getAllJobDefinitions()
                .stream()
                .filter(jobTypeDefinition -> jobTypeDefinition.getJobTypeName().equals(jobType))
                .findFirst()
                .orElseThrow(() -> new JobTypeNotFoundException(jobType))
                .validate(parameters);
        if (!validParam) {
            throw new BadRequestException(jobType);
        }
    }
}
