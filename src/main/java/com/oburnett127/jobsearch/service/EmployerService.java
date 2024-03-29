package com.oburnett127.jobsearch.service;

import com.oburnett127.jobsearch.model.Employer;
import com.oburnett127.jobsearch.model.request.EmployerUpdateRequest;
import com.oburnett127.jobsearch.repository.EmployerRepository;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployerService {
    private final EmployerRepository employerRepository;

    public EmployerService(EmployerRepository employerRepository) {
        this.employerRepository = employerRepository;
    }

    @SneakyThrows
    public List<Employer> listAll() {
        return this.employerRepository.findAll();
    }

    @SneakyThrows
    public Employer getEmployerById(int id) {
        return this.employerRepository.getReferenceById(id);
    }

    @SneakyThrows
    public Optional<Employer> getEmployerByName(String employerName) {
        return employerRepository.findByName(employerName);
    }

    @SneakyThrows
    public void createEmployer(Employer employer) {
        this.employerRepository.save(employer);
    }

    @SneakyThrows
    public Employer updateEmployer(EmployerUpdateRequest employerUpdateRequest) {
        final var id = employerUpdateRequest.getId();
        final var name = employerUpdateRequest.getName();

        final var employer = this.employerRepository.getReferenceById(id);

        if(name.isBlank() || name == null || !name.matches("^[a-zA-Z0-9 ]*$")) {
            throw new RuntimeException("The employer name cannot be blank nor contain non-alphanumeric characters.");
        }

        employer.setName(name);
        this.employerRepository.save(employer);
        return employer;
    }

    @SneakyThrows
    public void deleteEmployer(int id) { this.employerRepository.deleteById(id); }

    @SneakyThrows
    public int getMaxEmployerId() {
       return employerRepository.getMaxEmployerId();
    }
}