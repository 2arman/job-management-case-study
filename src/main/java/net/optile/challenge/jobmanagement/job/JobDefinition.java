package net.optile.challenge.jobmanagement.job;

import java.lang.reflect.Parameter;
import java.util.Map;
import java.util.Set;

/**
 * @author Arman
 * Date: 1/31/20
 * Time: 7:50 PM
 **/
public interface JobDefinition {

    /**
     * @return job type name
     */
    String getJobType();

    /**
     * @return optional and mandatory job parameter
     */
     Set<String> getJobParameter();

    /**
     * @param parameter the job parameters
     * @return true when parameter(s) is valid and false when parameter(s) is invalid
     */
     boolean validate(Map<String,String> parameter);

    /**
     * the main action in each job
     * @param parameter the parameter that required for this job
     * @return the specific result of job
     */
    String run(Map<String,String> parameter);
}
