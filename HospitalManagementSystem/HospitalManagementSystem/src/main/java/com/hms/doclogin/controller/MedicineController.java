package com.hms.doclogin.controller;

import com.hms.doclogin.payload.MedicineDto;
import com.hms.doclogin.service.MedicineService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicine")
@CrossOrigin(origins = "http://localhost:4200")
public class MedicineController {

    private MedicineService medicineService;

    public MedicineController(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    @PostMapping
    public ResponseEntity<MedicineDto> createMed(@RequestBody MedicineDto medicineDto){
        MedicineDto dto = medicineService.createMedicine(medicineDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }


    @GetMapping()
    public ResponseEntity<List<MedicineDto>> getAllMedicine(@RequestParam(name = "pageNo",defaultValue = "0",required = false) int pageNo,@RequestParam(name = "pageSize",defaultValue = "2",required = false) int pageSize,@RequestParam(name = "s ortBy",defaultValue = "id",required = false) String sortBy,@RequestParam(name = "sortDir",defaultValue = "asc",required = false) String sortDir){
        List<MedicineDto> all = medicineService.getAll(pageNo, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(all,HttpStatus.OK);
    }
}
