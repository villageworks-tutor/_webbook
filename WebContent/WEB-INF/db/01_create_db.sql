/**
 * �y�f�[�^�x�[�X�E�f�[�^�x�[�X���[�U�̍쐬�z
 * 		�� �f�[�^�x�[�X�ڑ���� ��
 * 			JDBC�h���C�o�Forg.postgresql.Driver
 * 			URL�Fjdbc:postgreql:webbookdb
 * 					 jdbc:postgresql://localhost:5432/webbookdb
 * 			URL for unit tests
 *				 �Fjdbc:postgreql:testwebbookdb
 * 					 jdbc:postgresql://localhost:5432/testwebbookdb
 * 			USER�Flibrarian
 * 			PASSSWORD�Fhimitu
 */

-- DROP USER IF EXISTS librarian;
-- CREATE USER librarian WITH PASSWORD 'himitu';
DROP USER IF EXISTS tester;
CREATE USER tester WITH PASSWORD 'himitu';

DROP DATABASE IF EXISTS webbookdb;
CREATE DATABASE webbookdb OWNER librarian ENCODING 'utf8';
DROP DATABASE IF EXISTS testwebbookdb;
CREATE DATABASE testwebbookdb OWNER tester ENCODING 'utf8';

