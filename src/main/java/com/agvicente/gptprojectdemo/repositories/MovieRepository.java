package com.agvicente.gptprojectdemo.repositories;

import com.agvicente.gptprojectdemo.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
