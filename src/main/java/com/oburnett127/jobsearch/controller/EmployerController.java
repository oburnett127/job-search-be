package com.oburnett127.jobsearch.controller;

import com.oburnett127.jobsearch.model.Employer;
import com.oburnett127.jobsearch.model.request.EmployerCreateRequest;
import com.oburnett127.jobsearch.model.request.EmployerUpdateRequest;
import com.oburnett127.jobsearch.service.EmployerService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/employer")
public class EmployerController {
    private final EmployerService service;

    public EmployerController(EmployerService service) {
        this.service = service;
    }

    @GetMapping("/list")
    public ResponseEntity<List<Employer>> view() {
        final var result = service.listAll();
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/getbyid/{id}")
    public ResponseEntity<Employer> getEmployerById(@Validated @PathVariable String id) {
        System.out.println("inside getEmployerById() $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ --------------------");
        final var employer = service.getEmployerById(Integer.parseInt(id));
        return ResponseEntity.ok().body(employer);
    }

    @GetMapping("/getbyname/{name}")
    public ResponseEntity<Employer> getEmployerByName(@Validated @PathVariable String name) {
        System.out.println("inside getEmployerByName() $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ --------------------");
        final Optional<Employer> employer = service.getEmployerByName(name);
        if(employer.isPresent()) return ResponseEntity.ok().body(employer.get());
        else return ResponseEntity.ok().body(null);
    }

    @PostMapping("/create")
    public ResponseEntity<Employer> createEmployer(@Validated @RequestBody EmployerCreateRequest employerCreateRequest) throws IOException {
        System.out.println("inside createEmployer() $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ --------------------");
        final var employer = Employer.builder()
                .name(employerCreateRequest.getName())
                .build();
        service.createEmployer(employer);
        return ResponseEntity.ok(employer);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Employer> updateEmployer(@Validated @PathVariable int id, @RequestBody EmployerUpdateRequest employerUpdateRequest) throws IOException {
        System.out.println("inside updateEmployer() $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ --------------------");
        employerUpdateRequest.setId(id);
        final var result = service.updateEmployer(employerUpdateRequest);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<Employer> deleteEmployer(@Validated @PathVariable int id) throws IOException {
        System.out.println("inside deleteEmployer() $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ --------------------");
        service.deleteEmployer(id);
        return ResponseEntity.ok().body(null);
    }
}