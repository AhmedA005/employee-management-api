package org.example.efinance.services.impl;

import org.example.efinance.entities.Job;
import org.example.efinance.repositories.JobRepository;
import org.example.efinance.services.JobService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public Job createJob(Job job) {
        return jobRepository.save(job);
    }

    @Override
    public Optional<Job> getJobById(String id) {
        return jobRepository.findById(id);
    }

    @Override
    public Job updateJob(String id, Job job) {
        return jobRepository.findById(id)
                .map(
                  existingJob -> {
                      existingJob.setId(id);
                      existingJob.setJobTitle(job.getJobTitle());
                      existingJob.setMaxsalary(job.getMaxsalary());
                      existingJob.setMinsalary(job.getMinsalary());
                      return jobRepository.save(existingJob);
                  })
                .orElseThrow(() -> new RuntimeException("Job not found with id: " + id));
    }

    @Override
    public void deleteJobById(String id) {
        jobRepository.deleteById(id);
    }

    @Override
    public Iterable<Job> getAllJobs() {
        return jobRepository.findAll();
    }
}
