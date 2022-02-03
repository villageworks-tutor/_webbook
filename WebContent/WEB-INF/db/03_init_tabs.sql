/** コードマスタのデータ登録 */
-- 権限マスタのコードの登録
INSERT INTO privilege (code, name) VALUES (1, 'システム管理者');
INSERT INTO privilege (code, name) VALUES (2, '一般利用者');

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
INSERT INTO member VALUES (1, '12056692', '梅田 俊章', '232-0016', '神奈川県横浜市南区宮元町2-16-18', '080-4293-2703', 'mizuho2311@mhwuymgwsr.rp.rql', '1985-07-05',  1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO member VALUES (2, '12050662', '清田 健蔵', '277-0851', '千葉県柏市向原町1-17-13', '080-3440-9925', 'yuzuki086@udtmsizh.nbl.jrml', '2001-02-01',  2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO member VALUES (3, '12058021', '浜口 秋雄', '259-0201', '神奈川県足柄下郡真鶴町真鶴3-20-8', '080-4751-9498', 'fujio_tsuchiya@yfqkvmrmfr.geq.bbl', '1987-11-15',  2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO member VALUES (4, '12057327', '古橋 正美', '121-0807', '東京都足立区伊興本町1-16伊興本町アパート406', '090-4613-0336', 'yoshiko_shimada@ztvzw.frig.fhbl', '1970-10-08',  1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO member VALUES (5, '12052425', '吉澤 正徳', '241-0005', '神奈川県横浜市旭区白根4-6白根フォレスト216', '080-1284-7148', 'chuuzou30475@kwjs.qxl', '1980-01-27',  2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO member VALUES (6, '12056107', '橘 良彦', '231-0862', '神奈川県横浜市中区山手町2-5-9山手町タワー418', '080-6431-9202', 'momokatezuka@wjhpjivinw.hgl', '1997-04-02',  2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO member VALUES (7, '12058439', '浅田 薫', '350-0236', '埼玉県坂戸市花影町4-2-6', '090-1133-3877', 'ikosugi@xsrehtx.uis.ayl', '1978-04-07',  2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO auth VALUES (1, '12056692', '31c191d73919df6a7928748abb781800efed72c335fd574b548bd5c83320960d'); -- usr1
INSERT INTO auth VALUES (2, '12050662', 'dc6d1427d39c787ca546abeccddc0ca01c3411a82b4a4493acdb251ac4dd1931'); -- usr2
INSERT INTO auth VALUES (3, '12058021', '583c1da8f27d6202a6ba4afed44ee6ceca416ed2a35ffb23de424d60632d2706'); -- usr3
INSERT INTO auth VALUES (4, '12057327', '37fd8167e5d090ed401696d0fc5abbbf16b66888b1a0a52321e48a2100507bdc'); -- usr4
INSERT INTO auth VALUES (5, '12052425', 'd09ce0948322de958f9c3e943e1e33892430ea48c5f36d1c972ef74c7219d2c7'); -- usr5
INSERT INTO auth VALUES (6, '12056107', 'ebe1a73a2ee3db071055e7d3c790b7cf8e8becaf2ef1713152f4b8202fc2f2b2'); -- usr6
INSERT INTO auth VALUES (7, '12058439', '223d74bca6107ec1508771622068bb3b3b7bbc103da7014aa40ae642c57ccab9'); -- usr7
