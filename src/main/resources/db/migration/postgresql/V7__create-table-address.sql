CREATE TABLE public.tb_address (
	id bigint NOT NULL,
	street varchar(120) NOT NULL,
	number varchar(12),
	district varchar(60) NOT NULL,
	CONSTRAINT tb_address_pk PRIMARY KEY (id)
);