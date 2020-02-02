package net.optile.challenge.jobmanagement.service;

import net.optile.challenge.jobmanagement.service.dto.*;
import net.optile.challenge.jobmanagement.service.exception.BadRequestException;
import net.optile.challenge.jobmanagement.service.exception.JobTypeNotFoundException;

/**
 * @author Arman
 * Date: 1/29/20
 * Time: 7:47 AM
 **/
public interface JobRegistrationService {

    RegisterJobResponse registerJob(RegisterJobRequest registerJobRequest) throws JobTypeNotFoundException, BadRequestException;
}
