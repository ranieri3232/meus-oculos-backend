ALTER TABLE tb_ubs
ADD COLUMN manager_id bigint;

ALTER TABLE tb_ubs
ADD CONSTRAINT fk_ubs_user
FOREIGN KEY (manager_id)
REFERENCES tb_ubs(id);