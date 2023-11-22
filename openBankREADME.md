## 금융 결제원 OPEN BANK API 문서를 활용한 가상 계좌 입출금 서버
 참고 자료0  : https://blog.naver.com/PostView.nhn?blogId=mofin0418&logNo=222169284087&parentCategoryNo=&categoryNo=6&viewDate=&isShowPopularPosts=true&from=search
 참고 자료1 : https://developers.kftc.or.kr/dev/openapi/open-banking/oauth
 참고 자료2 : https://developers.openbanking.or.kr/openapi/oauth
 참고 자료3 : https://nankisu.tistory.com/44
 참고 자료4 : https://data-make.tistory.com/432
 참고 자료5 : https://to-moneyking.tistory.com/105
 참고 자료6 : https://not-robot.tistory.com/7

### 1.OAuth 인증
@PostMapping("/oauth/2.0/token1")
---
내 계좌 등록 시 SMS 본인인증을 통해 token을 발급 받고 ARS인증을 통해 핀번호를 발급받음. 
플랫폼은 이것을 이용해 이용자의 핀테크번호(계좌 번호 내장)로 API 사용

### 2.잔액 조회
@GetMapping("/account/balance/fin_num")
---
Access Token과 사용자의 핀테크번호(계좌 번호 내장)로 잔액 조회
요청
Headder : accessToken
fintech_use_num : 핀테크이용번호
bank_tran_id : 은행거래고유번호
tran_dtime : 요청일시
응답
bank_name : 은행 이름
balance_amt : 계좌 잔액


### 3.계좌 실명 조회
@PostMapping("/inquiry/real_name")
---
AccessToken과 사용자의 계좌번호, 주민등록번호 앞 7자리로 계좌 실명 조회
요청
Headder : accessToken
bank_code_std : 개설기관 표준코드
account_num : 계좌번호
bank_tran_id : 은행거래고유번호
account_holder_info : 예금주 인증정보(주민등록번호 앞 7자리)
tran_dtime : 요청일시
응답
account_holder_name : 예금주 성명


### 4.거래 내역 조회
@GetMapping("/account/transaction_list/fin_num")
---
AccessToken과 사용자의 핀테크번호(계좌 번호 내장)로 거래내역 조회.
Headder : accessToken
Headder : accessToken
bank_tran_id : 은행거래고유번호
fintech_use_num : 핀테크이용번호
inquiry_type : 조회구분코드(A: ALL, I : 입금, O : 출금)
from_date : 조회시작일자
to_date : 조회종료일자
tran_dtime : 요청 일시

응답
rsp_code : 응답코드(API)
res_list : 조회된 거래내역
tran_date : 거래일자("2016031")
tran_time : 거래시간("113000")
inout_type : 입금 출금 구분
print_content : 통장 인자 내용
tran_amt : 거래금액
after_balance_amt : 거래 후 잔액


### 5.입금 이체
@PostMapping("/transfer/deposit/fin_num")
---
플랫폼의 지급 계좌 핀테크 이용번호 혹은 계좌번호를 이용해 원하는 곳으로 출금. 
요청
Headder : accessToken
fintech_use_num : 출금계좌핀테크이용번호
bank_tran_id : 은행거래고유번호
wd_print_content : 출금 계좌에 남길 내역(내 계좌에 남길 내역)
cntr_account_num : 입금하고자 하는 계좌 번호
cntr_account_bank_code_std : 입금하고자 하는 은행 코드
dps_print_content : 입금 계좌에 남길 내역(보낼 계좌에 남길 내역)
tran_amt : 요청 금액
tran_dtime : 요청 일시
req_client_name : 요청 고객 성명
응답
rsp_code : 응답코드(API)

### 6.출금 이체
@PostMapping("/transfer/withdraw/fin_num")
---
사용자가 등록하고 출금 동의한 계좌로부터 자금을 출금해 플랫폼 수납 계좌로 입금.
요청
Headder : accessToken
fintech_use_num : 출금계좌핀테크이용번호
bank_tran_id : 은행거래고유번호
wd_print_content : 출금 계좌에 남길 내역(내 계좌에 남길 내역)
cntr_account_num : 입금하고자 하는 계좌 번호
cntr_account_bank_code_std : 입금하고자 하는 은행 코드
dps_print_content : 입금 계좌에 남길 내역(보낼 계좌에 남길 내역)
tran_amt : 금액
tran_dtime : 요청 일시
req_client_name : 요청 고객 성명	
응답
rsp_code : 응답코드(API)	


