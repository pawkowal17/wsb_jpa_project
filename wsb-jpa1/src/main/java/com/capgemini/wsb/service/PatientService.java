package com.capgemini.wsb.service;

import com.capgemini.wsb.dto.PatientTO;
import com.capgemini.wsb.dto.VisitTO;

import java.util.List;

public interface PatientService {
    public PatientTO findById(Long patientId);
    public PatientTO findByLastName(String lastName);
    public List<VisitTO> findAllVisitsByPatientId(Long patientId);
    public List<PatientTO> findPatientsWithMoreThanXVisits(int numberOfVisits);
    public List<PatientTO> findPatientsYoungerThan(int age);
    public List<PatientTO> findAllPatients();
}