package org.javalord.patientservice.mapper;

import org.javalord.patientservice.dto.PatientRequestDto;
import org.javalord.patientservice.dto.PatientResponseDto;
import org.javalord.patientservice.model.Patient;

import java.time.LocalDate;

public class PatientMapper {

    public static PatientResponseDto toDto(Patient patient) {
        PatientResponseDto dto = new PatientResponseDto();
        dto.setId(patient.getId().toString());
        dto.setName(patient.getName());
        dto.setAddress(patient.getAddress());
        dto.setEmail(patient.getEmail());
        dto.setDateOfBirth(patient.getDateOfBirth().toString());

        return dto;
    }

    public static Patient toModel(PatientRequestDto patient) {
        Patient newPatient = new Patient();
        newPatient.setName(patient.getName());
        newPatient.setAddress(patient.getAddress());
        newPatient.setEmail(patient.getEmail());
        newPatient.setDateOfBirth(LocalDate.parse(patient.getDateOfBirth()));
        newPatient.setRegisteredDate(LocalDate.parse(patient.getRegisteredDate()));

        return newPatient;
    }

}
