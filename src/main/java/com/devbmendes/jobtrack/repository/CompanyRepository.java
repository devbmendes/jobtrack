package com.devbmendes.jobtrack.repository;

import com.devbmendes.jobtrack.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,Long> {
}
