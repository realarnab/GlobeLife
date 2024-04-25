package com.hms.doclogin.service.impl;

import com.hms.doclogin.entity.Medicine;
import com.hms.doclogin.payload.MedicineDto;
import com.hms.doclogin.repository.MedicineRepository;
import com.hms.doclogin.service.MedicineService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicineServiceImpl implements MedicineService {

    private MedicineRepository medicineRepository;
    private ModelMapper modelMapper;

    public MedicineServiceImpl(MedicineRepository medicineRepository, ModelMapper modelMapper) {
        this.medicineRepository = medicineRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public MedicineDto createMedicine(MedicineDto medicineDto) {
        Medicine medicine = mapToEntity(medicineDto);
        Medicine save = medicineRepository.save(medicine);
        MedicineDto dto = mapToDto(save);
        return dto;
    }

    @Override
    public List<MedicineDto> getAll(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Medicine> page = medicineRepository.findAll(pageable);
        List<Medicine> content = page.getContent();
        List<MedicineDto> dto = content.stream().map(e -> mapToDto(e)).collect(Collectors.toList());
        return dto;
    }

    public MedicineDto mapToDto(Medicine medicine){
        MedicineDto dto = modelMapper.map(medicine, MedicineDto.class);
        return dto;
    }

    public Medicine  mapToEntity(MedicineDto medicineDto){
        Medicine medicine = modelMapper.map(medicineDto, Medicine.class);
        return medicine;
    }
}
