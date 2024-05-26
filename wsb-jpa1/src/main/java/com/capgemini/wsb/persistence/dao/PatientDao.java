package com.capgemini.wsb.persistence.dao;

import com.capgemini.wsb.persistence.entity.PatientEntity;
import com.capgemini.wsb.persistence.entity.VisitEntity;

import java.util.List;

public interface PatientDao extends Dao<PatientEntity, Long> {
    PatientEntity findByLastName(String lastName);
    List<PatientEntity> findPatientsWithMoreThanXVisits(int numberOfVisits);
    List<VisitEntity> findAllVisitsByPatientId(Long patientId);
    List<PatientEntity> findPatientsYoungerThan(int age);
}
