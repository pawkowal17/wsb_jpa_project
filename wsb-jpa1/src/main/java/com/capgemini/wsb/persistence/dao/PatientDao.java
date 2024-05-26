package com.capgemini.wsb.persistence.dao;

import com.capgemini.wsb.persistence.entity.PatientEntity;
import com.capgemini.wsb.persistence.entity.VisitEntity;

import java.util.List;

public interface PatientDao extends Dao<PatientEntity, Long> {
    // Możesz dodać specyficzne metody dla PatientEntity, jeśli potrzebujesz
    PatientEntity findByLastName(String lastName); // Dodaj tę metodę
    List<PatientEntity> findPatientsWithMoreThanXVisits(int numberOfVisits);
    List<VisitEntity> findAllVisitsByPatientId(Long patientId);
}