package com.devbmendes.jobtrack.service;

import com.devbmendes.jobtrack.dto.JobAppRequest;
import com.devbmendes.jobtrack.entity.Company;
import com.devbmendes.jobtrack.entity.JobApplication;
import com.devbmendes.jobtrack.exceptions.ResourceNotFoundException;
import com.devbmendes.jobtrack.repository.CompanyRepository;
import com.devbmendes.jobtrack.repository.JobApplicationRepository;
import org.springframework.stereotype.Service;

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

    public JobApplication findById(Long jobApplicationId) {

        return jobApplicationRepository.findById(jobApplicationId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "JobApplication with this ID: "
                                        + jobApplicationId
                                        + " not found"
                        ));
    }
    public Company findCompanyById(Long id){
        Optional<Company> company = companyRepository.findById(id);
        if (company.isEmpty()){
            throw new ResourceNotFoundException("Company with this ID : "+id+" not found");
        }
        return company.get();
    }

    @Override
    public JobApplication saveJobApplication(JobAppRequest userJobAppRequest) {
        JobApplication jobApplication = new JobApplication();
        Company companyJobApplication = findCompanyById(userJobAppRequest.getCompanyId());
        jobApplication.setCompany(companyJobApplication);
        jobApplication.setLocation(userJobAppRequest.getLocation());
        jobApplication.setPosition(userJobAppRequest.getPosition());
        jobApplication.setNotes(userJobAppRequest.getNotes());

        return jobApplicationRepository.save(jobApplication);
    }

    @Override
    public List<JobApplication> findAll() {
        return jobApplicationRepository.findAll();
    }

}
