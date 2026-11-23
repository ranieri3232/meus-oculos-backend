CREATE TABLE public.tb_patients (
	id bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	name VARCHAR(120) NOT NULL,
	nickname VARCHAR(60),
	cpf VARCHAR(11) NOT NULL,
	cns VARCHAR(15),
	birthdate DATE NOT NULL,
	profession VARCHAR(40),
	phone_number VARCHAR(11),
	monthly_income INT,
	created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
	CONSTRAINT uk_patient_cpf UNIQUE (cpf)
);