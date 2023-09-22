INSERT INTO bank(bank_id, name, file_path) VALUES ("3K", "기업 BC", "/images/금융아이콘_PNG_롯데.png");
INSERT INTO bank(bank_id, name, file_path) VALUES ("46", "광주은행", "/images/금융아이콘_PNG_광주.png");
INSERT INTO bank(bank_id, name, file_path) VALUES ("71", "롯데카드", "/images/금융아이콘_PNG_롯데.png");
INSERT INTO bank(bank_id, name, file_path) VALUES ("30", "KDB산업은행", "/images/금융아이콘_PNG_KDB.png");
INSERT INTO bank(bank_id, name, file_path) VALUES ("31", "BC카드", "/images/금융아이콘_PNG_BC카드.png");
INSERT INTO bank(bank_id, name, file_path) VALUES ("51", "삼성카드", "/images/금융아이콘_PNG_삼성.png");
INSERT INTO bank(bank_id, name, file_path) VALUES ("38", "새마을금고", "/images/금융아이콘_PNG_MG새마을금고.png");
INSERT INTO bank(bank_id, name, file_path) VALUES ("41", "신한은행", "/images/금융아이콘_PNG_신한.png");
INSERT INTO bank(bank_id, name, file_path) VALUES ("62", "신협", "/images/금융아이콘_PNG_신협.png");
INSERT INTO bank(bank_id, name, file_path) VALUES ("36", "씨티카드", "/images/금융아이콘_PNG_씨티.png");
INSERT INTO bank(bank_id, name, file_path) VALUES ("W1", "우리은행", "/images/금융아이콘_PNG_우리.png");
INSERT INTO bank(bank_id, name, file_path) VALUES ("37", "우체국예금보험", "/images/금융아이콘_PNG_우체국.png");
INSERT INTO bank(bank_id, name, file_path) VALUES ("39", "저축은행중앙회", "/images/금융아이콘_PNG_저축은행.png");
INSERT INTO bank(bank_id, name, file_path) VALUES ("15", "카카오뱅크", "/images/금융아이콘_PNG_카카오뱅크.png");
INSERT INTO bank(bank_id, name, file_path) VALUES ("3A", "케이뱅크", "/images/금융아이콘_PNG_케이뱅크.png");
INSERT INTO bank(bank_id, name, file_path) VALUES ("24", "토스뱅크", "/images/금융아이콘_PNG_토스.png");
INSERT INTO bank(bank_id, name, file_path) VALUES ("21", "하나은행", "/images/금융아이콘_PNG_하나.png");
INSERT INTO bank(bank_id, name, file_path) VALUES ("61", "현대카드", "/images/금융아이콘_PNG_현대카드.png");
INSERT INTO bank(bank_id, name, file_path) VALUES ("11", "KB", "/images/금융아이콘_PNG_KB.png");
INSERT INTO bank(bank_id, name, file_path) VALUES ("91", "NH농협", "/images/금융아이콘_PNG_농협.png");
INSERT INTO bank(bank_id, name, file_path) VALUES ("34", "Sh수협은행", "/images/금융아이콘_PNG_Sh수협.png");


INSERT INTO bank_account(account_number, bank_id, money, name, birth)
VALUES("000000000000", "51", 100000000, "아이돈케어", "202309011");

INSERT INTO bank_account(account_number, bank_id, money, name, birth)
VALUES("111111111111", "51", 1000000, "김출금", "200001011");

INSERT INTO bank_account(account_number, bank_id, money, name, birth)
VALUES("999999999999", "51", 1000000, "김입금", "200001011");

INSERT INTO user(name, phone_number, role)
VALUES("아이돈케어", "01012345678", "CORPORATION");

INSERT INTO user(name, phone_number, role)
VALUES("김출금", "01011111111", "INDIVIDUAL");

INSERT INTO user(name, phone_number, role)
VALUES("김입금", "01099999999", "INDIVIDUAL");

INSERT INTO fin_tech_service(fin_tech_service_id, name, login_id, login_password, client_id, client_secret, redirect_url)
VALUES("1234512345", "아이돈케어", "idontcare", "1234", "12u4hi1b245hj124", "123ijn4u123h5bkjn", "http://127.0.0.1/test");

INSERT INTO account(fintech_use_num, bank_id, user_id, account_number, fin_tech_service_id)
values("00000000001", "51", "1", "000000000000", "1234512345");

INSERT INTO account(fintech_use_num, bank_id, user_id, account_number, fin_tech_service_id)
values("00000000002", "51", "2", "111111111111", "1234512345");

INSERT INTO mobile(phone_number, name, birth, mobile_sort)
VALUES("01012345678", "김엄마", 19900101, "SK");

INSERT INTO mobile(phone_number, name, birth, mobile_sort)
VALUES("01023456789", "김자식", 20000101, "KT");

INSERT INTO mobile(phone_number, name, birth, mobile_sort)
VALUES("01011111111", "김출금", 19900101, "SK");

INSERT INTO mobile(phone_number, name, birth, mobile_sort)
VALUES("01099999999", "김입금", 20000101, "KT");