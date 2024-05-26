package com.capgemini.wsb.service.impl;

import com.capgemini.wsb.dto.PatientTO;
import com.capgemini.wsb.dto.VisitTO;
import com.capgemini.wsb.mapper.PatientMapper;
import com.capgemini.wsb.mapper.VisitMapper;
import com.capgemini.wsb.persistence.dao.PatientDao;
import com.capgemini.wsb.persistence.entity.PatientEntity;
import com.capgemini.wsb.persistence.entity.VisitEntity;
import com.capgemini.wsb.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientDao patientDao;

    @Transactional
    @Override
    public void deletePatient(Long patientId) {
        PatientEntity patientEntity = patientDao.findOne(patientId);
        if (patientEntity != null) {
            patientDao.delete(patientId);
        }
    }

    public PatientTO save(PatientEntity patientEntity) {
        PatientEntity savedEntity = patientDao.save(patientEntity);
        return PatientMapper.mapToTO(savedEntity);
    }

    @Override
    public PatientTO findById(Long patientId) {
        PatientEntity patientEntity = patientDao.findOne(patientId);
        return PatientMapper.mapToTO(patientEntity);
    }

    @Override
    public PatientTO findByLastName(String lastName) { // Implementuj tę metodę
        PatientEntity patientEntity = patientDao.findByLastName(lastName);
        return PatientMapper.mapToTO(patientEntity);
    }

    public List<PatientTO> findPatientsWithMoreThanXVisits(int numberOfVisits) {
        List<PatientEntity> patients = patientDao.findPatientsWithMoreThanXVisits(numberOfVisits);
        return patients.stream()
                .map(PatientMapper::mapToTO)
                .collect(Collectors.toList());
    }

    public List<PatientTO> findAllPatients(){
        List<PatientEntity> patientEntity = patientDao.findAll();
        return patientEntity.stream()
                .map(PatientMapper::mapToTO)
                .collect(Collectors.toList());
    }

    public List<VisitTO> findAllVisitsByPatientId(Long patientId) {
        List<VisitEntity> visitEntities = patientDao.findAllVisitsByPatientId(patientId);
        return visitEntities.stream()
                .map(VisitMapper::mapToTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PatientTO> findPatientsYoungerThan(int age) {
        List<PatientEntity> patients = patientDao.findPatientsYoungerThan(age);
        return patients.stream()
                .map(PatientMapper::mapToTO)
                .collect(Collectors.toList());
    }
}