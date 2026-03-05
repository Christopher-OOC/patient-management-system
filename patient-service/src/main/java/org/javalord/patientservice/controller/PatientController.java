package org.javalord.patientservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.groups.Default;
import org.javalord.patientservice.dto.PatientRequestDto;
import org.javalord.patientservice.dto.PatientResponseDto;
import org.javalord.patientservice.dto.validators.CreatePatientValidationGroup;
import org.javalord.patientservice.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/patients")
@Tag(name = "Patient", description = "API for managing Patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @Operation(summary = "Get Patients")
    @GetMapping
    public ResponseEntity<List<PatientResponseDto>> getPatients() {
        List<PatientResponseDto> patients = patientService.getPatients();

        return ResponseEntity.ok(patients);
    }

    @Operation(summary = "Create new Patients")
    @PostMapping
    public ResponseEntity<PatientResponseDto> createPatient(@Validated({Default.class, CreatePatientValidationGroup.class}) @RequestBody PatientRequestDto patientRequestDto) {
        PatientResponseDto patientResponseDto = patientService.createPatient(patientRequestDto);

        return ResponseEntity.ok(patientResponseDto);
    }

    @Operation(summary = "Update a Patients")
    @PutMapping(value = "/{id}")
    public ResponseEntity<PatientResponseDto> updatePatient(@PathVariable UUID id, @RequestBody @Validated({Default.class}) PatientRequestDto patientRequestDto) {
        PatientResponseDto patientResponseDto = patientService.updatePatient(id, patientRequestDto);
        return ResponseEntity.ok(patientResponseDto);
    }

    @Operation(summary = "Delete Patients")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable UUID id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }


}
