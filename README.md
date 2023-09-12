## 금융 결제원 OPEN BANK API 문서를 활용한 가상 계좌 입출금 서버

 참고 자료1 : https://developers.kftc.or.kr/dev/openapi/open-banking/oauth
 참고 자료2 : https://developers.openbanking.or.kr/openapi/oauth


### 1.OAuth 인증
---
내 계좌 등록 시 SMS 본인인증을 통해 token을 발급 받고 ARS인증을 통해 핀번호를 발급받음. 
플랫폼은 이것을 이용해 이용자의 핀테크번호(계좌 번호 내장)로 API 사용

### 2.잔액 조회
---
Access Token과 사용자의 핀테크번호(계좌 번호 내장)로 잔액 조회

### 3.계좌 실명 조회
---
AccessToken과 사용자의 핀테크번호(계좌 번호 내장)로 잔액 조회

### 4.거래 내역 조회
---
AccessToken과 사용자의 핀테크번호(계좌 번호 내장)로 잔 거래내역 조회.

### 5.입금 이체
---
플랫폼의 지급 계좌 핀테크 이용번호 혹은 계좌번호를 이용해 원하는 곳으로 출금. 

### 6.출금 이체
---
사용자가 등록하고 출금 동의한 계좌로부터 자금을 출금해 플랫폼 수납 계좌로 입금.
AccessToken과 핀테크 이용번호, 거래 금액을 이용