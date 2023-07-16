package com.oburnett127.jobsearch.controller;

import com.oburnett127.jobsearch.model.Application;
import com.oburnett127.jobsearch.model.dto.ApplicationDto;
import com.oburnett127.jobsearch.model.request.ApplicationCreateRequest;
import com.oburnett127.jobsearch.service.ApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/application")
public class ApplicationController {
    private final ApplicationService service;

    public ApplicationController(ApplicationService service) {
        this.service = service;
    }

    @GetMapping("/getoneapplication/{jobId}")
    public ResponseEntity<ApplicationDto> getOneApplication(@Validated @PathVariable String jobId, 
                                                    @Validated @PathVariable String applicantId) {
        System.out.println("inside getApplicationByJobId() $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ --------------------");
        final var application = service.getOneApplication(Integer.parseInt(jobId), Integer.parseInt(applicantId));

        var result = new ApplicationDto(application.getId(), application.getJobId(), application.getApplicantId());

        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/getbyjobid/{jobId}")
    public ResponseEntity<ApplicationDto[]> getApplicationsByJobId(@Validated @PathVariable String jobId) {
        System.out.println("inside getApplicationByJobId() $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ --------------------");
        List<Application> applications = service.getApplicationsByJobId(Integer.parseInt(jobId));

        var result = applications.stream()
                    .map(application -> new ApplicationDto(application.getId(), application.getJobId(), 
                            application.getApplicantId()))
                    .collect(Collectors.toList());

        ApplicationDto[] applicationArray = result.toArray(new ApplicationDto[0]);                    

        return new ResponseEntity<>(applicationArray, HttpStatus.OK);
    }

    @GetMapping("/getbyapplicantid/{applicantId}")
    public ResponseEntity<ApplicationDto[]> getApplicationsByApplicantId(@Validated @PathVariable int applicantId) {
        System.out.println("inside getApplicationByApplicantId() $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ --------------------");
        List<Application> applications = service.getApplicationsByApplicantId(applicantId);

        var result = applications.stream()
                    .map(application -> new ApplicationDto(application.getId(), application.getJobId(), 
                            application.getApplicantId()))
                    .collect(Collectors.toList());

        ApplicationDto[] applicationArray = result.toArray(new ApplicationDto[0]);                    

        return new ResponseEntity<>(applicationArray, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Application> createApplication(@Validated @RequestBody ApplicationCreateRequest applicationCreateRequest ) throws IOException {
        System.out.println("inside createApplication() $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ --------------------");
        service.createApplication(applicationCreateRequest);
        return ResponseEntity.ok().body(null);
    }
}