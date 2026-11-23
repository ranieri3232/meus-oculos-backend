CREATE TABLE public.tb_eye_exams (
	id bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,

	exam_date DATE NOT NULL,
	lens_color VARCHAR(60),
	lens_type VARCHAR(60),
	sphere_right NUMERIC(3, 2),
	cylindrical_right NUMERIC(3, 2),
	axis_right NUMERIC(3, 2),
	sphere_left NUMERIC(3, 2),
    cylindrical_left NUMERIC(3, 2),
    axis_left NUMERIC(3, 2),
    additional NUMERIC(3, 2),
    observations VARCHAR(500),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    is_chosen BOOLEAN NOT NULL DEFAULT TRUE,
    patient_id BIGINT NOT NULL,

    CONSTRAINT fk_patient
    FOREIGN KEY (patient_id)
    REFERENCES tb_patients(id)
    ON DELETE CASCADE
);