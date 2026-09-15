package com.devbmendes.jobtrack.repository;

import com.devbmendes.jobtrack.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company,Long> {
    Optional<Company> findByNameIgnoreCase(String name);
}
