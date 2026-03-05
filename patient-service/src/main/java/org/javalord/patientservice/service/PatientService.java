package org.javalord.patientservice.service;

import org.javalord.patientservice.dto.PatientRequestDto;
import org.javalord.patientservice.dto.PatientResponseDto;
import org.javalord.patientservice.exception.EmailAlreadyExistsException;
import org.javalord.patientservice.exception.PatientNotFoundException;
import org.javalord.patientservice.mapper.PatientMapper;
import org.javalord.patientservice.model.Patient;
import org.javalord.patientservice.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

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

    public PatientResponseDto createPatient(PatientRequestDto patient) {
        if (patientRepository.existsByEmail(patient.getEmail())) {
            throw new EmailAlreadyExistsException("A patient with email already exists " + patient.getEmail());
        }

        Patient newPatient = patientRepository.save(PatientMapper.toModel(patient));

        return PatientMapper.toDto(newPatient);
    }

    public PatientResponseDto updatePatient(UUID id, PatientRequestDto requestDto) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found with ID: " + id ));

        if (patientRepository.existsByEmailAndIdNot(requestDto.getEmail(), id)) {
            throw new EmailAlreadyExistsException("A patient with email already exists " + requestDto.getEmail());
        }

        patient.setName(requestDto.getName());
        patient.setEmail(requestDto.getEmail());
        patient.setAddress(requestDto.getAddress());
        patient.setDateOfBirth(LocalDate.parse(requestDto.getDateOfBirth()));

        Patient saved = patientRepository.save(patient);
        return PatientMapper.toDto(saved);
    }

    public void deletePatient(UUID id) {
        patientRepository.deleteById(id);
    }

}
