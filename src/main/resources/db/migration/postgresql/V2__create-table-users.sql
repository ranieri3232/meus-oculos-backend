CREATE TABLE public.tb_users (
	id bigint NOT NULL,
	name varchar(120) NOT NULL,
	email varchar(80) NOT NULL,
	password varchar(80) NOT NULL,
	cpf varchar(11) NOT NULL,
	CONSTRAINT tb_users_pk PRIMARY KEY (id),
	CONSTRAINT tb_users_email_unique UNIQUE (email),
	CONSTRAINT tb_users_cpf_unique UNIQUE (cpf)
);