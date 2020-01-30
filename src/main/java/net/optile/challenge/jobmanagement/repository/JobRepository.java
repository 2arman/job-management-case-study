package net.optile.challenge.jobmanagement.repository;

import net.optile.challenge.jobmanagement.domain.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Arman
 * Date: 1/30/20
 * Time: 3:50 AM
 **/
@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
}
