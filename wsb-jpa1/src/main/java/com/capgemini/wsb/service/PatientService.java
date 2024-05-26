package com.capgemini.wsb.service;

import com.capgemini.wsb.dto.PatientTO;
import com.capgemini.wsb.dto.VisitTO;

import java.util.List;

public interface PatientService {
    public PatientTO findByLastName(String lastName); // Dodaj tę metodę
    public PatientTO findById(Long patientId);
    public List<PatientTO> findPatientsWithMoreThanXVisits(int numberOfVisits);
    public List<PatientTO> findAllPatients();
    public List<VisitTO> findAllVisitsByPatientId(Long patientId);
    public List<PatientTO> findPatientsYoungerThan(int age);
}