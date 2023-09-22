INSERT INTO user(user_id, name, nick_name, phone_number, type)
VALUES(1, "성연석", "더미데이터", "01012345678", "PARENT");

INSERT INTO user(user_id, name, nick_name, phone_number, type)
VALUES(2, "이정훈", "더미데이터", "01098765432", "CHILD");

INSERT INTO virtual_account(virtual_account_id, balance, password, user_id)
VALUES("111111111111", 1000000, 1234, 1);

INSERT INTO virtual_account(virtual_account_id, balance, password, user_id)
VALUES("222222222222", 1000000, 1234, 2);

INSERT INTO transaction_history(amount, balance, content, create_at, type, user_id)
VALUES(10000, 100000, "스타벅스 결제", localtime(), "DEPOSITORY", 1);

INSERT INTO transaction_history(amount, balance, content, create_at, type, user_id)
VALUES(10000, 100000, "스타벅스 결제", localtime(), "WITHDRAWAL", 1);