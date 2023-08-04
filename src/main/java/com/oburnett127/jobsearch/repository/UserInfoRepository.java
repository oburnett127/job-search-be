package com.oburnett127.jobsearch.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.oburnett127.jobsearch.model.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {
  Optional<UserInfo> findByEmail(String email);
}
