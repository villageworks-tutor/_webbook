/** �R�[�h�}�X�^�̃f�[�^�o�^ */
-- �����}�X�^�̃R�[�h�̓o�^
INSERT INTO privilege (code, name) VALUES (1, '�V�X�e���Ǘ���');
INSERT INTO privilege (code, name) VALUES (2, '��ʗ��p��');

-- ���ރ}�X�^�̃R�[�h�̓o�^
INSERT INTO category (code, name) VALUES (0, '���L');
INSERT INTO category (code, name) VALUES (1, '�N�w');
INSERT INTO category (code, name) VALUES (2, '���j');
INSERT INTO category (code, name) VALUES (3, '�Љ�Ȋw');
INSERT INTO category (code, name) VALUES (4, '���R�Ȋw');
INSERT INTO category (code, name) VALUES (5, '�Z�p');
INSERT INTO category (code, name) VALUES (6, '�Y��');
INSERT INTO category (code, name) VALUES (7, '�|�p');
INSERT INTO category (code, name) VALUES (8, '����');
INSERT INTO category (code, name) VALUES (9, '���w');

-- �o�ŎЃ}�X�^�̃R�[�h�̓o�^
INSERT INTO publisher (code, name) VALUES ('01', '��g���X');

/** �}�X�^�e�[�u���̃f�[�^�o�^ */
INSERT INTO member VALUES (1, '12056692', '�~�c �r��', '232-0016', '�_�ސ쌧���l�s���{����2-16-18', '080-4293-2703', 'mizuho2311@mhwuymgwsr.rp.rql', '1985-07-05',  1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO member VALUES (2, '12050662', '���c ����', '277-0851', '��t�����s������1-17-13', '080-3440-9925', 'yuzuki086@udtmsizh.nbl.jrml', '2001-02-01',  2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO member VALUES (3, '12058021', '�l�� �H�Y', '259-0201', '�_�ސ쌧�������S�^�ߒ��^��3-20-8', '080-4751-9498', 'fujio_tsuchiya@yfqkvmrmfr.geq.bbl', '1987-11-15',  2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO member VALUES (4, '12057327', '�Ë� ����', '121-0807', '�����s������ɋ��{��1-16�ɋ��{���A�p�[�g406', '090-4613-0336', 'yoshiko_shimada@ztvzw.frig.fhbl', '1970-10-08',  1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO member VALUES (5, '12052425', '�g�V ����', '241-0005', '�_�ސ쌧���l�s���攒��4-6�����t�H���X�g216', '080-1284-7148', 'chuuzou30475@kwjs.qxl', '1980-01-27',  2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO member VALUES (6, '12056107', '�k �ǕF', '231-0862', '�_�ސ쌧���l�s����R�蒬2-5-9�R�蒬�^���[418', '080-6431-9202', 'momokatezuka@wjhpjivinw.hgl', '1997-04-02',  2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO member VALUES (7, '12058439', '��c �O', '350-0236', '��ʌ���ˎs�ԉe��4-2-6', '090-1133-3877', 'ikosugi@xsrehtx.uis.ayl', '1978-04-07',  2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO auth VALUES (1, '12056692', '31c191d73919df6a7928748abb781800efed72c335fd574b548bd5c83320960d'); -- usr1
INSERT INTO auth VALUES (2, '12050662', 'dc6d1427d39c787ca546abeccddc0ca01c3411a82b4a4493acdb251ac4dd1931'); -- usr2
INSERT INTO auth VALUES (3, '12058021', '583c1da8f27d6202a6ba4afed44ee6ceca416ed2a35ffb23de424d60632d2706'); -- usr3
INSERT INTO auth VALUES (4, '12057327', '37fd8167e5d090ed401696d0fc5abbbf16b66888b1a0a52321e48a2100507bdc'); -- usr4
INSERT INTO auth VALUES (5, '12052425', 'd09ce0948322de958f9c3e943e1e33892430ea48c5f36d1c972ef74c7219d2c7'); -- usr5
INSERT INTO auth VALUES (6, '12056107', 'ebe1a73a2ee3db071055e7d3c790b7cf8e8becaf2ef1713152f4b8202fc2f2b2'); -- usr6
INSERT INTO auth VALUES (7, '12058439', '223d74bca6107ec1508771622068bb3b3b7bbc103da7014aa40ae642c57ccab9'); -- usr7
