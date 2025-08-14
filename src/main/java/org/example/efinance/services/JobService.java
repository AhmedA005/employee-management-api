package org.example.efinance.services;

import org.example.efinance.entities.Job;

import java.util.Optional;

public interface JobService {
    Job createJob(Job job);

    Optional<Job> getJobById(String id);

    Job updateJob(String id, Job job);

    void deleteJobById(String id);

    Iterable<Job> getAllJobs();
}
