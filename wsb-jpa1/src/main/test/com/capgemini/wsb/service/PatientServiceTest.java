/*package com.capgemini.wsb.service;

import com.capgemini.wsb.dto.DoctorTO;
import com.capgemini.wsb.dto.PatientTO;
import com.capgemini.wsb.dto.VisitTO;
import com.capgemini.wsb.mapper.DoctorMapper;
import com.capgemini.wsb.mapper.PatientMapper;
import com.capgemini.wsb.mapper.VisitMapper;
import com.capgemini.wsb.persistence.dao.DoctorDao;
import com.capgemini.wsb.persistence.dao.PatientDao;
import com.capgemini.wsb.persistence.dao.VisitDao;
import com.capgemini.wsb.persistence.entity.DoctorEntity;
import com.capgemini.wsb.persistence.entity.PatientEntity;
import com.capgemini.wsb.persistence.entity.VisitEntity;
import com.capgemini.wsb.service.PatientService;
import com.capgemini.wsb.service.impl.PatientServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PatientServiceTest {

    @Mock
    private PatientDao patientDao;

    @Mock
    private DoctorDao doctorDao;

    @Mock
    private VisitDao visitDao;

    @InjectMocks
    private PatientService patientService = new PatientServiceImpl(patientDao);

    @Test
    public void testShouldDeletePatientWithCascade() {
        // given
        Long patientId = 1L;
        PatientEntity patientEntity = createPatientEntity(patientId);
        DoctorEntity doctorEntity = createDoctorEntity();
        VisitEntity visitEntity = createVisitEntity(patientEntity, doctorEntity);

        // when
        when(patientDao.findOne(patientId)).thenReturn(patientEntity);
        when(visitDao.findAllByPatientId(patientId)).thenReturn(Collections.singletonList(visitEntity));

        patientService.deletePatient(patientId);

        // then
        verify(patientDao, times(1)).delete(patientEntity);
        verify(visitDao, times(1)).delete(visitEntity);
        verify(doctorDao, never()).delete(any());
    }

    private PatientEntity createPatientEntity(Long id) {
        PatientEntity patientEntity = new PatientEntity();
        patientEntity.setId(id);
        // Set other properties if needed
        return patientEntity;
    }

    private DoctorEntity createDoctorEntity() {
        DoctorEntity doctorEntity = new DoctorEntity();
        // Set properties for doctor if needed
        return doctorEntity;
    }

    private VisitEntity createVisitEntity(PatientEntity patientEntity, DoctorEntity doctorEntity) {
        VisitEntity visitEntity = new VisitEntity();
        visitEntity.setPatient(patientEntity);
        visitEntity.setDoctor(doctorEntity);
        // Set other properties for visit if needed
        return visitEntity;
    }
}*/