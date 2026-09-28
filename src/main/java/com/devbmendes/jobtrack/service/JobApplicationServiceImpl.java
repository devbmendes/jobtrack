package com.devbmendes.jobtrack.service;

import com.devbmendes.jobtrack.dto.JobAppRequest;
import com.devbmendes.jobtrack.entity.Company;
import com.devbmendes.jobtrack.entity.JobApplication;
import com.devbmendes.jobtrack.exceptions.ResourceNotFoundException;
import com.devbmendes.jobtrack.repository.CompanyRepository;
import com.devbmendes.jobtrack.repository.JobApplicationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JobApplicationServiceImpl implements JobApplicationService{

    private final JobApplicationRepository jobApplicationRepository;
    private final CompanyRepository companyRepository;

    public JobApplicationServiceImpl(JobApplicationRepository jobApplicationRepository, CompanyRepository companyRepository) {
        this.jobApplicationRepository = jobApplicationRepository;
        this.companyRepository = companyRepository;
    }
    @Override
    public JobApplication findById(Long jobApplicationId) {

        return jobApplicationRepository.findById(jobApplicationId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "JobApplication with this ID: "
                                        + jobApplicationId
                                        + " not found"
                        ));
    }

    @Override
    public void deleteById(Long id) {
        Optional<JobApplication> jobApplication = jobApplicationRepository.findById(id);
        if (jobApplication.isEmpty()){
            throw new ResourceNotFoundException("JobApplication with this ID :"+id+" not found");
        }
        jobApplicationRepository.deleteById(id);
    }

    public Company findCompanyById(Long id){
        Optional<Company> company = companyRepository.findById(id);
        if (company.isEmpty()){
            throw new ResourceNotFoundException("Company with this ID : "+id+" not found");
        }
        return company.get();
    }

    @Override
    public JobApplication saveJobApplication(JobAppRequest jobAppRequest) {
        JobApplication jobApplication = new JobApplication();
        Company companyJobApplication = findCompanyById(jobAppRequest.getCompanyId());
        jobApplication.setCompany(companyJobApplication);
        jobApplication.setLocation(jobAppRequest.getLocation());
        jobApplication.setRequirements(jobAppRequest.getRequirements());
        jobApplication.setPosition(jobAppRequest.getPosition());
        jobApplication.setCreatedAt(LocalDateTime.now());
        jobApplication.setNotes(jobAppRequest.getNotes());

        return jobApplicationRepository.save(jobApplication);
    }

    @Override
    public List<JobApplication> findAll() {
        return jobApplicationRepository.findAll();
    }

}
