INSERT INTO bank(bank_id, name) VALUES ("3K", "기업 BC");
INSERT INTO bank(bank_id, name) VALUES ("46", "광주은행");
INSERT INTO bank(bank_id, name) VALUES ("71", "롯데카드");
INSERT INTO bank(bank_id, name) VALUES ("30", "KDB산업은행");
INSERT INTO bank(bank_id, name) VALUES ("31", "BC카드");
INSERT INTO bank(bank_id, name) VALUES ("51", "삼성카드");
INSERT INTO bank(bank_id, name) VALUES ("38", "새마을금고");
INSERT INTO bank(bank_id, name) VALUES ("41", "신한카드");
INSERT INTO bank(bank_id, name) VALUES ("62", "신협");
INSERT INTO bank(bank_id, name) VALUES ("36", "씨티카드");
INSERT INTO bank(bank_id, name) VALUES ("33", "우리BC카드(BC 매입)");
INSERT INTO bank(bank_id, name) VALUES ("W1", "우리카드(우리 매입)");
INSERT INTO bank(bank_id, name) VALUES ("37", "우체국예금보험");
INSERT INTO bank(bank_id, name) VALUES ("39", "저축은행중앙회");
INSERT INTO bank(bank_id, name) VALUES ("35", "전북은행");
INSERT INTO bank(bank_id, name) VALUES ("42", "제주은행");
INSERT INTO bank(bank_id, name) VALUES ("15", "카카오뱅크");
INSERT INTO bank(bank_id, name) VALUES ("3A", "케이뱅크");
INSERT INTO bank(bank_id, name) VALUES ("24", "토스뱅크");
INSERT INTO bank(bank_id, name) VALUES ("21", "하나카드");
INSERT INTO bank(bank_id, name) VALUES ("61", "현대카드");
INSERT INTO bank(bank_id, name) VALUES ("11", "KB국민카드");
INSERT INTO bank(bank_id, name) VALUES ("91", "NH농협카드");
INSERT INTO bank(bank_id, name) VALUES ("34", "Sh수협은행");


INSERT INTO bank_account(account_number, bank_id, money, name, birth)
VALUES("111111111111", "51", 100000000, "아이돈케어", "202309011");

INSERT INTO bank_account(account_number, bank_id, money, name, birth)
VALUES("111111111112", "51", 1000000, "김싸피", "200001011");

INSERT INTO user(name, phone_number, role)
VALUES("아이돈케어", "01012345678", "CORPORATION");

INSERT INTO user(name, phone_number, role)
VALUES("김싸피", "01099999999", "INDIVIDUAL");

INSERT INTO fin_tech_service(fin_tech_service_id, name, login_id, login_password, client_id, client_secret, redirect_url)
VALUES("1234512345", "아이돈케어", "idontcare", "1234", "12u4hi1b245hj124", "123ijn4u123h5bkjn", "http://127.0.0.1/test");

INSERT INTO account(fintech_use_num, bank_id, user_id, account_number, fin_tech_service_id)
values("00000000001", "51", "1", "111111111111", "1234512345");

INSERT INTO account(fintech_use_num, bank_id, user_id, account_number, fin_tech_service_id)
values("00000000002", "51", "2", "111111111112", "1234512345");

INSERT INTO mobile(phone_number, name, birth, mobile_sort)
VALUES("01012345678", "김엄마", 19900101, "SK");

INSERT INTO mobile(phone_number, name, birth, mobile_sort)
VALUES("01023456789", "김자식", 20000101, "KT");


