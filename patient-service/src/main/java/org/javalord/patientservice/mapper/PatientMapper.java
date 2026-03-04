package org.javalord.patientservice.mapper;

import org.javalord.patientservice.dto.PatientResponseDto;
import org.javalord.patientservice.model.Patient;

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

}
