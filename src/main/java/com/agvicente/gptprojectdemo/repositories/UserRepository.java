package com.agvicente.gptprojectdemo.repositories;

import com.agvicente.gptprojectdemo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
