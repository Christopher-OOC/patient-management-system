package org.javalord.patientservice.service;

import org.javalord.patientservice.dto.PatientResponseDto;
import org.javalord.patientservice.mapper.PatientMapper;
import org.javalord.patientservice.model.Patient;
import org.javalord.patientservice.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService {

    private PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<PatientResponseDto> getPatients() {
        List<Patient> patients = patientRepository.findAll();

        return patients
                .stream()
                .map(PatientMapper::toDto)
                .toList();
    }

}
