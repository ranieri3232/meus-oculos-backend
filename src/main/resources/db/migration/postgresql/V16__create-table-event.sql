CREATE TABLE public.tb_events(
    id bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    event_date DATE NOT NULL,
    location VARCHAR(90) NOT NULL,
    address_id BIGINT,
    description VARCHAR(250),
    event_time TIME(0) NOT NULL,
    CONSTRAINT fk_event_address FOREIGN KEY (address_id) REFERENCES tb_address(id)
);