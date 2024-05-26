package com.capgemini.wsb.persistance.dao;

import com.capgemini.wsb.persistence.dao.PatientDao;
import com.capgemini.wsb.persistence.entity.PatientEntity;
import com.capgemini.wsb.persistence.entity.VisitEntity;
import com.capgemini.wsb.persistence.enums.Sex;
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
public class PatientDaoTest
{
    @Autowired
    private PatientDao patientDao;

    @Autowired
    private EntityManager entityManager;

    @Transactional
    @Test
    public void testFindPatientByLastName() {
        // given
        String lastName = "Doe";
        // when
        PatientEntity patientEntity = patientDao.findByLastName(lastName);
        // then
        assertThat(patientEntity).isNotNull();
        assertThat(patientEntity.getLastName()).isEqualTo(lastName);
    }

    @Transactional
    @Test
    public void testFindPatientsYoungerThan() {
        // given
        PatientEntity patient1 = new PatientEntity();
        patient1.setFirstName("John");
        patient1.setLastName("Doe");
        patient1.setTelephoneNumber("123456789");
        patient1.setEmail("john.doe@example.com");
        patient1.setPatientNumber("P12345");
        patient1.setDateOfBirth(LocalDate.of(2000, 5, 15)); // 23 years old
        patient1.setAge(23);
        patient1.setSex(Sex.MALE);

        PatientEntity patient2 = new PatientEntity();
        patient2.setFirstName("Jane");
        patient2.setLastName("Smith");
        patient2.setTelephoneNumber("987654321");
        patient2.setEmail("jane.smith@example.com");
        patient2.setPatientNumber("P67890");
        patient2.setDateOfBirth(LocalDate.of(1990, 8, 20)); // 33 years old
        patient2.setAge(33);
        patient2.setSex(Sex.FEMALE);

        PatientEntity patient3 = new PatientEntity();
        patient3.setFirstName("Emily");
        patient3.setLastName("Brown");
        patient3.setTelephoneNumber("555555555");
        patient3.setEmail("emily.brown@example.com");
        patient3.setPatientNumber("P11223");
        patient3.setDateOfBirth(LocalDate.of(2010, 1, 10)); // 14 years old
        patient3.setAge(14);
        patient3.setSex(Sex.FEMALE);

        entityManager.persist(patient1);
        entityManager.persist(patient2);
        entityManager.persist(patient3);
        entityManager.flush();

        // when
        int age = 30;
        List<PatientEntity> patientsYoungerThan30 = patientDao.findPatientsYoungerThan(age);

        // then
        assertThat(patientsYoungerThan30).hasSize(3); // 1+2 data.sql(1) + test(2)
        assertThat(patientsYoungerThan30).extracting("firstName")
                .containsExactlyInAnyOrder("Jesse", "John", "Emily");
    }

    @Transactional
    @Test
    public void testFindPatientsWithMoreThanXVisits() {
        // given
        int numberOfVisits = 1;

        PatientEntity patient1 = new PatientEntity();
        patient1.setFirstName("John");
        patient1.setLastName("Doe");
        patient1.setTelephoneNumber("123456789");
        patient1.setEmail("john.doe@example.com");
        patient1.setPatientNumber("P12345");
        patient1.setDateOfBirth(LocalDate.of(1985, 5, 15));
        patient1.setAge(35);
        patient1.setSex(Sex.MALE);

        VisitEntity visit1 = new VisitEntity();
        visit1.setDescription("Regular checkup");
        visit1.setTime(LocalDateTime.of(2023, 5, 15, 10, 0));
        visit1.setPatient(patient1);

        VisitEntity visit2 = new VisitEntity();
        visit2.setDescription("Follow-up appointment");
        visit2.setTime(LocalDateTime.of(2023, 6, 20, 11, 0));
        visit2.setPatient(patient1);

        patient1.setVisits(List.of(visit1, visit2));

        PatientEntity patient2 = new PatientEntity();
        patient2.setFirstName("Jane");
        patient2.setLastName("Smith");
        patient2.setTelephoneNumber("987654321");
        patient2.setEmail("jane.smith@example.com");
        patient2.setPatientNumber("P67890");
        patient2.setDateOfBirth(LocalDate.of(1990, 8, 20));
        patient2.setAge(30);
        patient2.setSex(Sex.FEMALE);

        VisitEntity visit3 = new VisitEntity();
        visit3.setDescription("Dental checkup");
        visit3.setTime(LocalDateTime.of(2023, 7, 10, 14, 0));
        visit3.setPatient(patient2);

        patient2.setVisits(List.of(visit3));

        entityManager.persist(patient1);
        entityManager.persist(patient2);
        entityManager.flush();

        // when
        List<PatientEntity> patients = patientDao.findPatientsWithMoreThanXVisits(numberOfVisits);

        // then
        assertThat(patients).hasSize(2); // data.sql(1) + test(1)
        assertThat(patients.get(0).getFirstName()).isEqualTo("Karen");
        assertThat(patients.get(0).getLastName()).isEqualTo("Goldman"); // from data.sql
        assertThat(patients.get(1).getFirstName()).isEqualTo("John");
        assertThat(patients.get(1).getLastName()).isEqualTo("Doe"); // from this test
    }
}