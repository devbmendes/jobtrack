package com.devbmendes.jobtrack.controller;

import com.devbmendes.jobtrack.dto.ApiResponse;
import com.devbmendes.jobtrack.dto.CompanyRequest;
import com.devbmendes.jobtrack.dto.CompanyResponse;
import com.devbmendes.jobtrack.service.CompanyService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }
    @PostMapping("/")
    public ResponseEntity<ApiResponse<CompanyResponse>> save(@Valid @RequestBody CompanyRequest request){
       CompanyResponse companyResponse =  companyService.save(request);
       return ResponseEntity.status(HttpStatus.CREATED)
               .body(new ApiResponse<>("Company created",companyResponse));
    }
    @GetMapping("/")
    public ResponseEntity<ApiResponse<List<CompanyResponse>>> getAll(){
        List<CompanyResponse> list = companyService.findAll();
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>("All companies",list));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CompanyResponse>> findById(@PathVariable Long id){
        CompanyResponse companyResponse = companyService.findById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>("Company founded",companyResponse));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteById(@PathVariable Long id){
        companyService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>("Company deleted",null));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CompanyResponse>> updateById(@PathVariable Long id,
    @RequestBody CompanyRequest companyRequest){
        CompanyResponse companyResponse = companyService.update(id,companyRequest);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>("Company updated",companyResponse));

    }
}
