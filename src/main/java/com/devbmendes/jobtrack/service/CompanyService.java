package com.devbmendes.jobtrack.service;

import com.devbmendes.jobtrack.dto.CompanyRequest;
import com.devbmendes.jobtrack.dto.CompanyResponse;

import java.util.List;
import java.util.Optional;

public interface CompanyService {
    CompanyResponse save(CompanyRequest companyRequest);
    CompanyResponse findById(Long id);
    List<CompanyResponse> findAll();
    CompanyResponse update(Long id, CompanyRequest companyRequest);
    void deleteById(Long id);
    Optional<CompanyResponse> findByName(String name);
}
