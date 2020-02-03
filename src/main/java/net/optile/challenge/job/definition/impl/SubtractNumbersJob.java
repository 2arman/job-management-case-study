package net.optile.challenge.job.definition.impl;

import com.google.common.collect.ImmutableSet;
import lombok.extern.slf4j.Slf4j;
import net.optile.challenge.job.definition.JobTypeDefinition;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Map;
import java.util.Set;

/**
 * @author Arman
 * Date: 1/31/20
 * Time: 8:00 PM
 **/
@Slf4j
public class SubtractNumbersJob implements JobTypeDefinition {

    public static final String JOB_TYPE = "subtract";
    public static final String OPERAND_1 = "operand1";
    public static final String OPERAND_2 = "operand2";

    @Override
    public String getJobTypeName() {
        return JOB_TYPE;
    }

    @Override
    public Set<String> getJobParameter() {
        return ImmutableSet.of(OPERAND_1, OPERAND_2);
    }

    @Override
    public boolean validate(Map<String, String> parameter) {
        return parameter != null &&
                parameter.containsKey(OPERAND_1) &&
                parameter.containsKey(OPERAND_2) &&
                NumberUtils.isParsable(parameter.get(OPERAND_1)) &&
                NumberUtils.isParsable(parameter.get(OPERAND_2));
    }

    @Override
    public String run(Map<String, String> parameter) {
        log.info("the {} is running", JOB_TYPE);
        return String.valueOf((Integer.parseInt(parameter.get(OPERAND_1))
                - Integer.parseInt(parameter.get(OPERAND_2))));
    }
}
