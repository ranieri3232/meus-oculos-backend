CREATE TABLE public.tb_roles (
	id bigint NOT NULL,
	authority varchar NOT NULL,
	CONSTRAINT tb_roles_pk PRIMARY KEY (id),
	CONSTRAINT tb_roles_unique UNIQUE (authority)
);