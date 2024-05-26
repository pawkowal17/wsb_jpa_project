package com.capgemini.wsb.service;

import com.capgemini.wsb.persistence.entity.PatientEntity;
import com.capgemini.wsb.persistence.entity.VisitEntity;
import com.capgemini.wsb.persistence.enums.Sex;
import com.capgemini.wsb.dto.VisitTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PatientServiceTest {

    @Autowired
    private PatientService patientService;

    @Autowired
    private EntityManager entityManager;

    @Transactional
    @Test
    public void testFindAllVisitsByPatientId() {
        // given
        PatientEntity patient = new PatientEntity();
        patient.setFirstName("John");
        patient.setLastName("Doe");
        patient.setTelephoneNumber("123456789");
        patient.setEmail("john.doe@example.com");
        patient.setPatientNumber("P12345");
        patient.setDateOfBirth(LocalDate.of(1985, 5, 15));
        patient.setAge(35);
        patient.setSex(Sex.MALE);

        VisitEntity visit1 = new VisitEntity();
        visit1.setDescription("Regular checkup");
        visit1.setTime(LocalDateTime.of(2023, 5, 15, 10, 0));
        visit1.setPatient(patient);

        VisitEntity visit2 = new VisitEntity();
        visit2.setDescription("Follow-up appointment");
        visit2.setTime(LocalDateTime.of(2023, 6, 20, 11, 0));
        visit2.setPatient(patient);

        patient.setVisits(List.of(visit1, visit2));

        entityManager.persist(patient);
        entityManager.flush();

        // when
        List<VisitTO> visits = patientService.findAllVisitsByPatientId(patient.getId());

        // then
        assertThat(visits).hasSize(2);
        assertThat(visits.get(0).getDescription()).isEqualTo("Regular checkup");
        assertThat(visits.get(1).getDescription()).isEqualTo("Follow-up appointment");
    }
}