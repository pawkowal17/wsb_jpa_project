package com.capgemini.wsb.service;

import com.capgemini.wsb.dto.MedicalTreatmentTO;

public interface MedicalTreatmentService {
    MedicalTreatmentTO findById(Long id);
}