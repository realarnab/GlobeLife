package com.hms.doclogin.service;

import com.hms.doclogin.payload.AppointmentDto;

import java.util.List;

public interface AppointmentService {
    AppointmentDto createAppointment(AppointmentDto appointmentDto);

    List<AppointmentDto> getAll(int pageNo, int pageSize, String sortBy, String sortDir);
    void deleteAppointment(long id);
}
