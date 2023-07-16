package com.oburnett127.jobsearch.controller;

import com.oburnett127.jobsearch.model.Job;
import com.oburnett127.jobsearch.model.dto.JobDto;
import com.oburnett127.jobsearch.model.request.JobCreateRequest;
import com.oburnett127.jobsearch.model.request.JobUpdateRequest;
import com.oburnett127.jobsearch.service.EmployerService;
import com.oburnett127.jobsearch.service.JobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/job")
public class JobController {
    private final JobService jobService;
    private final EmployerService employerService;

    public JobController(JobService jobService, EmployerService employerService) {
        this.jobService = jobService;
        this.employerService = employerService;
    }

    @GetMapping("/list")
    public ResponseEntity<JobDto[]> view() {
        List<Job> jobs = jobService.listAll();

        var result = jobs.stream()
                .map(job -> new JobDto(job.getId(), job.getTitle(), job.getDescription(),
                        job.getPostDate(), job.getEmployerId(), employerService.getEmployerById(job.getEmployerId()).getName()))
                .collect(Collectors.toList());

        JobDto[] jobArray = result.toArray(new JobDto[0]);

        return new ResponseEntity<>(jobArray, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<JobDto> getJob(@Validated @PathVariable String id) {
        System.out.println("inside getJob() $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ --------------------");
        final var job = jobService.getJob(Integer.parseInt(id));

        var result = new JobDto(job.getId(), job.getTitle(), job.getDescription(),
                        job.getPostDate(), job.getEmployerId(), employerService.getEmployerById(job.getEmployerId()).getName());

        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/create")
    public ResponseEntity<Job> createJob(@Validated @RequestBody JobCreateRequest jobCreateRequest) throws IOException {
        System.out.println("inside createJob() $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ --------------------");
        
        int empId = jobCreateRequest.getEmployerId();
        System.out.println("employer ID is: " + empId);
        
        final var job = new Job(jobCreateRequest.getTitle(), empId, jobCreateRequest.getDescription(), LocalDate.now().toString()); 
        System.out.println("after building job");
        jobService.createJob(job);
        System.out.println("after calling createJob");
        return ResponseEntity.ok(job);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Job> updateJob(@Validated @PathVariable int id, @RequestBody JobUpdateRequest jobUpdateRequest) throws IOException {
        System.out.println("inside updateJob() $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ --------------------");
        jobUpdateRequest.setId(id);
        final var result = jobService.updateJob(jobUpdateRequest);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<Job> deleteJob(@Validated @PathVariable int id) throws IOException {
        System.out.println("inside deleteJob() $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ --------------------");
        jobService.deleteJob(id);
        return ResponseEntity.ok().body(null);
    }
}