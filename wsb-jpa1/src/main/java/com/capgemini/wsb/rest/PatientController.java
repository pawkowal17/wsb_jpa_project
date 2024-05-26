package com.capgemini.wsb.rest;

import com.capgemini.wsb.dto.PatientTO;
import com.capgemini.wsb.dto.VisitTO;
import com.capgemini.wsb.rest.exception.EntityNotFoundException;
import com.capgemini.wsb.service.PatientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/patient/{id}")
    public PatientTO findById(@PathVariable Long id) {
        final PatientTO patient = patientService.findById(id);
        if (patient != null) {
            return patient;
        }
        throw new EntityNotFoundException(id);
    }
    @GetMapping("/patient/lastname/{lastName}") // Endpoint dla wyszukiwania po nazwisku
    public PatientTO findByLastName(@PathVariable String lastName) {
        PatientTO patient = patientService.findByLastName(lastName);
        if (patient != null) {
            return patient;
        }
        /*throw new EntityNotFoundException(lastName);*/
        return null;
    }
    @GetMapping("/patient/more-than-visits/{numberOfVisits}")
    public List<PatientTO> findPatientsWithMoreThanXVisits(@PathVariable int numberOfVisits) {
        List<PatientTO> patients = patientService.findPatientsWithMoreThanXVisits(numberOfVisits);
        if (patients != null && !patients.isEmpty()) {
            return patients;
        }
        /*throw new EntityNotFoundException("No patients found with more than " + numberOfVisits + " visits.");*/
        return null;
    }
    @GetMapping("/patient/all-patients")
    public List<PatientTO> findAllPatients() {
        List<PatientTO> patients = patientService.findAllPatients();
        if (patients != null && !patients.isEmpty()) {
            return patients;
        }
        /*throw new EntityNotFoundException("No patients found with more than " + numberOfVisits + " visits.");*/
        return null;
    }

    @GetMapping("/patient/visits/{patientId}")
    public List<VisitTO> findAllVisitsByPatientId(@PathVariable Long patientId) {
        List<VisitTO> visits = patientService.findAllVisitsByPatientId(patientId);
        if (visits != null && !visits.isEmpty()) {
            return visits;
        }
        throw new EntityNotFoundException(patientId);
    }
}