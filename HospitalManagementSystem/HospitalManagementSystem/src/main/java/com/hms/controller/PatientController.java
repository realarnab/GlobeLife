package com.hms.controller;

import com.hms.payload.PatientDto;
import com.hms.service.EmailService;
import com.hms.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200") //use to bind the api with angular
@RestController
@RequestMapping("/api/patient") //http://localhost:8080/api/patient
public class PatientController {

    private PatientService patientService;
    private EmailService emailService;

    @Autowired
    public PatientController(PatientService patientService, EmailService emailService) {
        this.patientService = patientService;
        this.emailService = emailService;
    }

    @PostMapping
    public ResponseEntity<PatientDto> createPatientRegistration(@RequestBody PatientDto patientDto){
        PatientDto reg = patientService.createReg(patientDto);
        emailService.sendMail(patientDto.getEmail(),"Patient got registered","Patient details:........  Name: "+patientDto.getName()+", Age: "+patientDto.getAge()+", Blood Group: "+patientDto.getBloodGroup()+", Fees: "+patientDto.getFees());
        return new ResponseEntity<>(reg, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PatientDto>> getAllPatients(@RequestParam(name = "pageNo",defaultValue = "0",required = false) int pageNo,@RequestParam(name = "pageSize",defaultValue = "2",required = false) int pageSize,@RequestParam(name = "sortBy",defaultValue = "id",required = false) String sortBy,@RequestParam(name = "sortDir",defaultValue = "asc",required = false) String sortDir){
        List<PatientDto> dto = patientService.getAll(pageNo, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    //http://localhost:8080/api/patients/3
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePatientDetail(@PathVariable long id){
        patientService.deletePatient(id);
        return new ResponseEntity<>("Patient detail deleted!",HttpStatus.OK);
    }
 }
