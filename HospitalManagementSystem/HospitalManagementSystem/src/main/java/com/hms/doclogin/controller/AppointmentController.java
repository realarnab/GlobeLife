package com.hms.doclogin.controller;

import com.hms.doclogin.payload.AppointmentDto;
import com.hms.doclogin.service.AppointmentService;
import org.eclipse.angus.mail.imap.AppendUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/appointment")
public class AppointmentController {

private AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping
    public ResponseEntity<AppointmentDto> createAppointmentDetail(@RequestBody AppointmentDto appointmentDto){
        AppointmentDto dto = appointmentService.createAppointment(appointmentDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AppointmentDto>> getAllAppointment(@RequestParam(name = "pageNo",defaultValue = "0",required = false) int  pageNo,@RequestParam(name = "pageSize",defaultValue = "3",required = false)  int pageSize,@RequestParam(name = "sortBy",defaultValue = "id",required = false) String sortBy,@RequestParam(name = "sortDir",defaultValue = "asc",required = false)String sortDir)
    {
        List<AppointmentDto> all = appointmentService.getAll(pageNo, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(all,HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteAppointment(@PathVariable long id){
        appointmentService.deleteAppointment(id);
        return new ResponseEntity<>("Appointment Deleted",HttpStatus.OK);
    }

}
