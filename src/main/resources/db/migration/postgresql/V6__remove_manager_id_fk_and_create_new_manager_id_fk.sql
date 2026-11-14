ALTER TABLE tb_ubs
DROP CONSTRAINT fk_ubs_user;

ALTER TABLE tb_ubs
ADD CONSTRAINT fk_ubs_user
FOREIGN KEY (manager_id)
REFERENCES tb_users(id);