package com.spring.sqa_fullstack.repository;

import com.spring.sqa_fullstack.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
