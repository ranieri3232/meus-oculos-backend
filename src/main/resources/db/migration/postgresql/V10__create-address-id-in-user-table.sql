ALTER TABLE tb_users
ADD COLUMN address_id bigint;

ALTER TABLE tb_users
ADD CONSTRAINT fk_user_address
FOREIGN KEY (address_id)
REFERENCES tb_address(id);