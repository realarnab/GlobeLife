package com.hms.service;

import com.hms.payload.PatientDto;

import java.util.List;

public interface PatientService {
    PatientDto createReg(PatientDto patientDto);
    List<PatientDto> getAll(int pageNo, int pageSize, String sortBy, String sortDir);
    void deletePatient(long id);
}
