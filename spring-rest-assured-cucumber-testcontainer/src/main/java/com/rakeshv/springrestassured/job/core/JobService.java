package com.rakeshv.springrestassured.job.core;


import com.rakeshv.springrestassured.common.core.PagedContent;

public interface JobService {
    JobResponse addNewJob(JobRequest jobRequest);

    JobResponse findJobById(Long jobId);

    JobResponse updateJob(Long jobId, JobRequest updateJobRequest);

    void deleteJob(Long jobId);

    PagedContent<JobResponse> findAllJobs(Integer pageNo, Integer pageSize);
}
