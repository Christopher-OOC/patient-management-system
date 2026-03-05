package org.javalord.patientservice.controller;

import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import org.javalord.patientservice.dto.PatientRequestDto;
import org.javalord.patientservice.dto.PatientResponseDto;
import org.javalord.patientservice.dto.validators.CreatePatientValidationGroup;
import org.javalord.patientservice.model.Patient;
import org.javalord.patientservice.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public ResponseEntity<List<PatientResponseDto>> getPatients() {
        List<PatientResponseDto> patients = patientService.getPatients();

        return ResponseEntity.ok(patients);
    }

    @PostMapping
    public ResponseEntity<PatientResponseDto> createPatient(@Validated({Default.class, CreatePatientValidationGroup.class}) @RequestBody PatientRequestDto patientRequestDto) {
        PatientResponseDto patientResponseDto = patientService.createPatient(patientRequestDto);

        return ResponseEntity.ok(patientResponseDto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PatientResponseDto> updatePatient(@PathVariable UUID id, @RequestBody @Validated({Default.class}) PatientRequestDto patientRequestDto) {
        PatientResponseDto patientResponseDto = patientService.updatePatient(id, patientRequestDto);
        return ResponseEntity.ok(patientResponseDto);
    }


}
