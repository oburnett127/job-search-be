package com.oburnett127.jobsearch.service;

import com.oburnett127.jobsearch.model.UserInfo;
import com.oburnett127.jobsearch.model.request.AuthenticationRequest;
import com.oburnett127.jobsearch.model.request.RegisterRequest;
import com.oburnett127.jobsearch.model.response.AuthenticationResponse;
import com.oburnett127.jobsearch.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserInfoService {
  private final UserInfoRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  @SneakyThrows
  public UserInfo register(RegisterRequest request, int empId) {
    var user = new UserInfo();

    if(request.getIsEmployer() == false) {
      System.out.println("role is: USER");
      user = UserInfo.builder()
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .roles("USER")
        .build();
    } else {
      System.out.println("role is: EMPLOYER");
      user = UserInfo.builder()
          .email(request.getEmail())
          .password(passwordEncoder.encode(request.getPassword()))
          .employerId(empId)
          .roles("EMPLOYER")
          .build();
    }

    userRepository.save(user);
    return user;
  }

  @SneakyThrows
  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    try {
      authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
          request.getEmail(),
          request.getPassword()
        )
      );
    } catch (AuthenticationException e) {
      return AuthenticationResponse.builder()
        .token(null)
        .message("Authentication failed")
        .user(null)
        .build();
    }
    Optional<UserInfo> user = userRepository.findByEmail(request.getEmail());
    if(user.isPresent()) {
      return AuthenticationResponse.builder()
        .token(jwtService.generateToken(request.getEmail()))
        .message("Authentication successful")
        .user(user.get())
        .build();
    } else {
      return AuthenticationResponse.builder()
        .token(jwtService.generateToken(request.getEmail()))
        .message("Authentication failed")
        .user(null)
        .build();
    }
  }

  @SneakyThrows
  public String getRoleByUserId(int userId) {
    Optional<UserInfo> user = userRepository.findById(userId);
    UserInfo account = user.get();
    return account.getRoles();
  }

  @SneakyThrows
  public int getUserIdByEmail(String emailAddress) {
    Optional<UserInfo> user = userRepository.findByEmail(emailAddress);
    int userId = user.get().getId();
    return userId;
  }

  @SneakyThrows
  public Optional<UserInfo> getUserByEmail(String emailAddress) {
    return userRepository.findByEmail(emailAddress);
  }
}