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

-- read_account_id : 111111111111 , pin_number : 123123123123
INSERT INTO real_account(read_account_id, bank_name, pin_number, user_id)
VALUES("-104 35 82 -83 -89 -127 -99 -29 120 16 -73 -19 -33 -18 -116 -124", "신한은행", "117 60 -40 -111 32 20 89 106 70 -72 -44 -105 -39 101 5 -82", 1);
