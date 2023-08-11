package com.oburnett127.jobsearch.model.response;

import com.oburnett127.jobsearch.model.UserInfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
  private String token;
  private String message;
  private UserInfo user;
}
