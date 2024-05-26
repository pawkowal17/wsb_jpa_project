package com.capgemini.wsb.persistence.dao.impl;

import com.capgemini.wsb.persistence.dao.PatientDao;
import com.capgemini.wsb.persistence.entity.PatientEntity;
import com.capgemini.wsb.persistence.entity.VisitEntity;
import com.capgemini.wsb.dto.PatientTO;
import com.capgemini.wsb.mapper.PatientMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.stream.Collectors;
import javax.persistence.TypedQuery;

@Repository
public class PatientDaoImpl extends AbstractDao<PatientEntity, Long> implements PatientDao {
    // Implementacja specyficznych metod dla PatientEntity, jeśli potrzebujesz

    /*@Autowired
    private PatientDao patientDao;*/
    public PatientEntity findByLastName(String lastName) {
        return entityManager.createQuery("SELECT p FROM PatientEntity p WHERE p.lastName = :lastName", PatientEntity.class)
                .setParameter("lastName", lastName)
                .getSingleResult();
    }

    public List<PatientEntity> findPatientsWithMoreThanXVisits(int numberOfVisits) {
        TypedQuery<PatientEntity> query = entityManager.createQuery(
                "SELECT p FROM PatientEntity p WHERE SIZE(p.visits) > :numberOfVisits",
                PatientEntity.class);
        query.setParameter("numberOfVisits", numberOfVisits);
        return query.getResultList();
    }

    public List<VisitEntity> findAllVisitsByPatientId(Long patientId) {
        TypedQuery<VisitEntity> query = entityManager.createQuery(
                "SELECT v FROM PatientEntity p JOIN p.visits v WHERE p.id = :patientId",
                VisitEntity.class);
        query.setParameter("patientId", patientId);
        return query.getResultList();
    }
}