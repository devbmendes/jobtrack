package com.devbmendes.jobtrack.service;

import com.devbmendes.jobtrack.dto.CompanyRequest;
import com.devbmendes.jobtrack.dto.CompanyResponse;

import java.util.List;
import java.util.Optional;

public class CompanyServiceImpl implements CompanyService{
    @Override
    public CompanyResponse save(CompanyRequest companyRequest) {
        return null;
    }

    @Override
    public CompanyResponse findById(Long id) {
        return null;
    }

    @Override
    public List<CompanyResponse> findAll() {
        return List.of();
    }

    @Override
    public CompanyResponse update(Long id, CompanyRequest companyRequest) {
        return null;
    }

    @Override
    public Void deleteById(Long id) {
        return null;
    }

    @Override
    public Optional<CompanyResponse> findByName(String name) {
        return Optional.empty();
    }
}
