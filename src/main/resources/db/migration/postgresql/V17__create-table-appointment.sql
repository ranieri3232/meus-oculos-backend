CREATE TABLE public.tb_appointment(
    id BIGINT GENERATED  BY DEFAULT AS IDENTITY PRIMARY KEY,
    eye_exam_id BIGINT NOT NULL,
    event_id BIGINT NOT NULL,
    patient_id BIGINT NOT NULL,
    service_date DATE,
    arrived_date DATE,
    received_date DATE,

    CONSTRAINT fk_appointment_patient
        FOREIGN KEY (patient_id) REFERENCES tb_patients (id),

    CONSTRAINT fk_appointment_eye_exam
        FOREIGN KEY (eye_exam_id) REFERENCES tb_eye_exams (id),

    CONSTRAINT fk_appointment_event
        FOREIGN KEY (event_id) REFERENCES tb_events (id)
);

CREATE UNIQUE INDEX uk_eye_exam_when_attended
    ON tb_appointment (eye_exam_id)
    WHERE service_date IS NOT NULL;