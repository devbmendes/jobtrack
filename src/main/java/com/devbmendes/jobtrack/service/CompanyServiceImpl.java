package com.devbmendes.jobtrack.service;

import com.devbmendes.jobtrack.dto.CompanyRequest;
import com.devbmendes.jobtrack.dto.CompanyResponse;
import com.devbmendes.jobtrack.entity.Company;
import com.devbmendes.jobtrack.exceptions.CompanyAlreadyExistsException;
import com.devbmendes.jobtrack.exceptions.ResourceNotFoundException;
import com.devbmendes.jobtrack.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService{
    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }
    CompanyResponse convertCompany(Company company){
        return new CompanyResponse(company.getName(),company.getWebsiteUrl(),
                company.getLocation(),company.getDescription(),company.getCreatedAt());
    }

    @Override
    public CompanyResponse save(CompanyRequest companyRequest) {
        Optional<Company> findCompany = companyRepository.findByNameIgnoreCase(companyRequest.getName());
        if (findCompany.isPresent()){
            throw new ResourceNotFoundException("Company with name : "+companyRequest.getName()+" already exists");

        }
        Company company = new Company(
                companyRequest.getName(),companyRequest.getWebsiteUrl(),
                companyRequest.getLocation(),companyRequest.getDescription());


        return convertCompany(companyRepository.save(company));
    }

    @Override
    public CompanyResponse findById(Long id) {
        Optional<Company> companyOptional = companyRepository.findById(id);
        if (companyOptional.isEmpty()){
            throw new ResourceNotFoundException("Company whit this id : "+id+" not found");
        }
        return convertCompany(companyOptional.get());
    }

    @Override
    public List<CompanyResponse> findAll() {
        List<Company> companyList = companyRepository.findAll();
        List<CompanyResponse> responseList = new ArrayList<CompanyResponse>();
        for (Company company : companyList){
            CompanyResponse companyResponse = new CompanyResponse(
                    company.getName(),
                    company.getWebsiteUrl(),
                    company.getLocation(),
                    company.getDescription(),
                    company.getCreatedAt()
            );
            responseList.add(companyResponse);
        }

        return responseList;
    }

    @Override
    public CompanyResponse update(Long id, CompanyRequest companyRequest) {
        Optional<Company> companyOptional = companyRepository.findById(id);
        Optional<Company> companyByName = companyRepository.findByNameIgnoreCase(companyRequest.getName());
        if (companyByName.isPresent() && !companyByName.get().getId().equals(id)){
            throw new CompanyAlreadyExistsException("Company with name : "+companyRequest.getName()+"" +
                    " already exists");
        }
        companyOptional.get().setName(companyRequest.getName());
        companyOptional.get().setLocation(companyRequest.getLocation());
        companyOptional.get().setDescription(companyRequest.getDescription());
        companyOptional.get().setWebsiteUrl(companyRequest.getWebsiteUrl());
        Company updated = companyRepository.save(companyOptional.get());
        return new CompanyResponse(updated.getName(),updated.getWebsiteUrl(),updated.getLocation(),
                updated.getDescription(), updated.getCreatedAt());
    }

    @Override
    public void deleteById(Long id) {
        CompanyResponse companyResponse = findById(id);
        if (companyResponse!= null){
            companyRepository.deleteById(id);
        }

    }

    @Override
    public Optional<CompanyResponse> findByName(String name) {
        return Optional.empty();
    }
}
