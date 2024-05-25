insert into address (id, address_line1, address_line2, city, postal_code)
            values (1, 'xx', 'yy', 'city', '62-030');

INSERT INTO doctor (id, first_name, last_name, telephone_number, email, doctor_number, specialization)
VALUES (1, 'John', 'Smith', '123456789', 'john.smith@example.com', 'DOC123', 'SURGEON');

INSERT INTO patient (id, first_name, last_name, telephone_number, email, patient_number, date_of_birth)
VALUES (1, 'Jane', 'Doe', '987654321', 'jane.doe@example.com', 'PAT456', '1990-01-01');

INSERT INTO visit (id, description, time, doctor_id, patient_id)
VALUES (1, 'Regular checkup', '2024-05-24 10:00:00', 1, 1);

INSERT INTO medical_treatment (id, description, type, visit_id)
VALUES (1, 'Prescribed medication', 'EKG', 1);

INSERT INTO patient_address (patient_id, address_id) VALUES (1, 1);

INSERT INTO doctor_address (doctor_id, address_id) VALUES (1, 1);
