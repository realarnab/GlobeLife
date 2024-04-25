package com.hms.doclogin.service;

import com.hms.doclogin.payload.MedicineDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MedicineService {
    MedicineDto createMedicine(MedicineDto medicineDto);

    List<MedicineDto> getAll(int pageNo, int pageSize, String sortBy, String sortDir);
}
