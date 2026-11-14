ALTER TABLE tb_ubs
ADD COLUMN address_id bigint;

ALTER TABLE tb_ubs
ADD CONSTRAINT fk_ubs_address
FOREIGN KEY (address_id)
REFERENCES tb_address(id);