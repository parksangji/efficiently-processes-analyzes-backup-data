package com.example.jpabatchrepository.repository;

import com.example.jpabatchrepository.entity.ProcessedLoginActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessedLoginActivityRepository extends JpaRepository<ProcessedLoginActivity, Long> {
}
