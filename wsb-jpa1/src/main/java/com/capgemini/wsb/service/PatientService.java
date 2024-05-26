package com.capgemini.wsb.service;

import com.capgemini.wsb.dto.PatientTO;
import com.capgemini.wsb.dto.VisitTO;
import com.capgemini.wsb.persistence.entity.PatientEntity;

import java.util.List;

public interface PatientService {
    void deletePatient(Long patientId);
    PatientTO save(PatientEntity patientEntity);

    PatientTO findByLastName(String lastName); // Dodaj tę metodę
    PatientTO findById(Long patientId);
    List<PatientTO> findPatientsWithMoreThanXVisits(int numberOfVisits);
    List<PatientTO> findAllPatients();
    List<VisitTO> findAllVisitsByPatientId(Long patientId);
    List<PatientTO> findPatientsYoungerThan(int age);
}