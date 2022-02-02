/**
 * 【データベース・データベースユーザの作成】
 * 		★ データベース接続情報 ★
 * 			JDBCドライバ：org.postgresql.Driver
 * 			URL：jdbc:postgreql:webbookdb
 * 					 jdbc:postgresql://localhost:5432/webbookdb
 * 			URL for unit tests
 *				 ：jdbc:postgreql:testwebbookdb
 * 					 jdbc:postgresql://localhost:5432/testwebbookdb
 * 			USER：librarian
 * 			PASSSWORD：himitu
 */

-- DROP USER IF EXISTS librarian;
-- CREATE USER librarian WITH PASSWORD 'himitu';
DROP USER IF EXISTS tester;
CREATE USER tester WITH PASSWORD 'himitu';

DROP DATABASE IF EXISTS webbookdb;
CREATE DATABASE webbookdb OWNER librarian ENCODING 'utf8';
DROP DATABASE IF EXISTS testwebbookdb;
CREATE DATABASE testwebbookdb OWNER tester ENCODING 'utf8';

