/** コードマスタのデータ登録 */
-- 権限マスタのコードの登録
INSERT INTO priviledge (code, name) VALUES (0, 'システム管理者');
INSERT INTO priviledge (code, name) VALUES (1, '一般利用者');

-- 分類マスタのコードの登録
INSERT INTO category (code, name) VALUES (0, '総記');
INSERT INTO category (code, name) VALUES (1, '哲学');
INSERT INTO category (code, name) VALUES (2, '歴史');
INSERT INTO category (code, name) VALUES (3, '社会科学');
INSERT INTO category (code, name) VALUES (4, '自然科学');
INSERT INTO category (code, name) VALUES (5, '技術');
INSERT INTO category (code, name) VALUES (6, '産業');
INSERT INTO category (code, name) VALUES (7, '芸術');
INSERT INTO category (code, name) VALUES (8, '言語');
INSERT INTO category (code, name) VALUES (9, '文学');

-- 出版社マスタのコードの登録
INSERT INTO publisher (code, name) VALUES ('01', '岩波書店');

/** マスタテーブルのデータ登録 */
INSERT INTO member VALUES (1, '12056692', '梅田 俊章', '232-0016', '神奈川県横浜市南区宮元町2-16-18', '080-4293-2703', 'mizuho2311@mhwuymgwsr.rp.rql', '1985-07-05',  0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO member VALUES (2, '12050662', '清田 健蔵', '277-0851', '千葉県柏市向原町1-17-13', '080-3440-9925', 'yuzuki086@udtmsizh.nbl.jrml', '2001-02-01',  1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO member VALUES (3, '12058021', '浜口 秋雄', '259-0201', '神奈川県足柄下郡真鶴町真鶴3-20-8', '080-4751-9498', 'fujio_tsuchiya@yfqkvmrmfr.geq.bbl', '1987-11-15',  1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO member VALUES (4, '12057327', '古橋 正美', '121-0807', '東京都足立区伊興本町1-16伊興本町アパート406', '090-4613-0336', 'yoshiko_shimada@ztvzw.frig.fhbl', '1970-10-08',  0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO member VALUES (5, '12052425', '吉澤 正徳', '241-0005', '神奈川県横浜市旭区白根4-6白根フォレスト216', '080-1284-7148', 'chuuzou30475@kwjs.qxl', '1980-01-27',  1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO member VALUES (6, '12056107', '橘 良彦', '231-0862', '神奈川県横浜市中区山手町2-5-9山手町タワー418', '080-6431-9202', 'momokatezuka@wjhpjivinw.hgl', '1997-04-02',  1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO member VALUES (7, '12058439', '浅田 薫', '350-0236', '埼玉県坂戸市花影町4-2-6', '090-1133-3877', 'ikosugi@xsrehtx.uis.ayl', '1978-04-07',  1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO auth VALUES (1, '12056692',	'7ccb68e74ce41f7db7943a342890bbd43712cae0cf690705e706bd0eb56779c2', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO auth VALUES (2, '12050662', '7e8f1f5171586a5b86bd654a858e89050f8c38460c9b16529c55c7dc1f99a4e2', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO auth VALUES (3, '12058021',	'1ad7acd335f782b4b331e465b0f00797dcf36a80785df3dbff806a1286255d9c', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO auth VALUES (4, '12057327',	'7e02576ebea9ff2fc9089afc4439910d3c202e859911ede551990904f3b53682', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO auth VALUES (5, '12052425',	'ed316da9eb5fdf699f5979dcd6df0f907d01968cb21007fea6587248b990f350', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO auth VALUES (6, '12056107',	'9f11b239554e5f7aa45bc586007070c1b76a82ce3f157f91b9692a973366c631', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO auth VALUES (7, '12058439',	'30d42ed0a461c661761ab6b87338e9b89486a24ceb7b5c486170daf7cc169d07', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
