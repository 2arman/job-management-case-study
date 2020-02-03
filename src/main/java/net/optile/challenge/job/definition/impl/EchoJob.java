package net.optile.challenge.job.definition.impl;

import lombok.extern.slf4j.Slf4j;
import net.optile.challenge.job.definition.JobTypeDefinition;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

/**
 * @author Arman
 * Date: 1/31/20
 * Time: 8:00 PM
 **/
@Slf4j
public class EchoJob implements JobTypeDefinition {

    public static final String JOB_TYPE = "echo";
    public static final String PARAM = "param";

    @Override
    public String getJobTypeName() {
        return JOB_TYPE;
    }

    @Override
    public Set<String> getJobParameter() {
        return Collections.singleton(PARAM);
    }

    @Override
    public boolean validate(Map<String, String> parameter) {
        return parameter != null &&
                parameter.containsKey(PARAM);
    }

    @Override
    public String run(Map<String, String> parameter) {
        log.info("the {} is running ", JOB_TYPE);
        return parameter.get(PARAM);
    }
}
