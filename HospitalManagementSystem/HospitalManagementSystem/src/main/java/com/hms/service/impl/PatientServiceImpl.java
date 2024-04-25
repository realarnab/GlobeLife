package com.hms.service.impl;

import com.hms.entity.Patient;
import com.hms.payload.PatientDto;
import com.hms.repository.PatientRepository;
import com.hms.service.PatientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {

    private PatientRepository patientRepository;
    private ModelMapper modelMapper;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository, ModelMapper modelMapper) {
        this.patientRepository = patientRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PatientDto createReg(PatientDto patientDto) {
        Patient patient = mapToEntity(patientDto);
        Patient save = patientRepository.save(patient);
        PatientDto dto = mapToDto(save);
        return dto;
    }

    @Override
    public List<PatientDto> getAll(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageable =PageRequest.of(pageNo,pageSize,sort);
        Page<Patient> all = patientRepository.findAll(pageable);
        List<Patient> content = all.getContent();
        List<PatientDto> dto = content.stream().map(e -> mapToDto(e)).collect(Collectors.toList());
        return dto;
    }

    @Override
    public void deletePatient(long id) {
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new RuntimeException("Resource Not Found"));
        patientRepository.deleteById(id);
    }

    public Patient mapToEntity(PatientDto patientDto){
        Patient patient = modelMapper.map(patientDto, Patient.class);
        return patient;
    }

    public PatientDto mapToDto(Patient patient){
        PatientDto dto = modelMapper.map(patient, PatientDto.class);
        return dto;
    }
}
