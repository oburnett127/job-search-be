package com.oburnett127.jobsearch.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oburnett127.jobsearch.model.Employer;
import com.oburnett127.jobsearch.user.User;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {

  private final UserService service;

  @PostMapping("/signup")
  public ResponseEntity<AuthenticationResponse> register(
      @RequestBody RegisterRequest request) {
    AuthenticationResponse response = service.register(request);
    if(response == null) return ResponseEntity.status(409).body(new AuthenticationResponse(null));
    else return ResponseEntity.status(200).body(response);
  }

  @PostMapping("/login")
  public ResponseEntity<AuthenticationResponse> authenticate(
      @RequestBody AuthenticationRequest request) {
    return ResponseEntity.ok(service.authenticate(request));
  }

  @GetMapping("/get/{email}")
  public ResponseEntity<Employer> getEmployerByEmail(@Validated @PathVariable String email) {
      System.out.println("$$$$$$$$$$$$ inside getEmployerByEmail");
      final var employer = service.getEmployerByEmail(email);
      return ResponseEntity.ok().body(employer);
  }
}