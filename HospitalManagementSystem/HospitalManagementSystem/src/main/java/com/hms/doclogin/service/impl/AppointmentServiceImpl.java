package com.hms.doclogin.service.impl;

import com.hms.doclogin.entity.Appointment;
import com.hms.doclogin.payload.AppointmentDto;
import com.hms.doclogin.repository.AppointmentRepository;
import com.hms.doclogin.service.AppointmentService;
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
public class AppointmentServiceImpl implements AppointmentService {
    private AppointmentRepository appointmentRepository;
    private ModelMapper modelMapper;

    @Autowired
    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, ModelMapper modelMapper) {
        this.appointmentRepository = appointmentRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public AppointmentDto createAppointment(AppointmentDto appointmentDto) {
        Appointment appointment = mapToEntity(appointmentDto);
        Appointment save = appointmentRepository.save(appointment);
        AppointmentDto dto = mapToDto(save);
        return dto;
    }

    @Override
    public List<AppointmentDto> getAll(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageable =PageRequest.of(pageNo,pageSize,sort);
        Page<Appointment> pages = appointmentRepository.findAll(pageable);
        List<Appointment> contents = pages.getContent();
        List<AppointmentDto> dtos = contents.stream().map(e -> this.mapToDto(e)).collect(Collectors.toList());
        return dtos;
    }

    @Override
    public void deleteAppointment(long id) {
        Appointment appointment = appointmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Appointment Not Found!!"));
        appointmentRepository.delete(appointment);
    }

    public Appointment mapToEntity(AppointmentDto appointmentDto){
        Appointment appointment = modelMapper.map(appointmentDto, Appointment.class);
        return appointment;
    }

    public AppointmentDto mapToDto(Appointment appointment){
        AppointmentDto dto = modelMapper.map(appointment, AppointmentDto.class);
        return dto;
    }
}
