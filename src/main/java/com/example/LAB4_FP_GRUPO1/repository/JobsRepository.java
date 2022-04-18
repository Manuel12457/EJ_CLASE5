package com.example.LAB4_FP_GRUPO1.repository;

import com.example.LAB4_FP_GRUPO1.entity.Jobs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobsRepository extends JpaRepository<Jobs, String> {
}
