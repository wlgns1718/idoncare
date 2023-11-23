# 아이돈케어 (IDonCare)

<div align="center">
<img width="329" alt="image" src="https://github.com/wlgns1718/idoncare/assets/113763592/0681f879-0864-481d-8c88-2df24c8cc3bc">
 
</div>
<div align="center">

<!-- [![Hits](https://hits.seeyoufarm.com/api/count/incr/badge.svg?url=https%3A%2F%2Fgithub.com%2Fwlgns1718%2Fidoncare&count_bg=%2353E384&title_bg=%23555555&icon=&icon_color=%23E7E7E7&title=hits&edge_flat=false)](https://hits.seeyoufarm.com) -->
</div>

# 아이돈케어 (IDonCare)

> **삼성청년SW아카데미(SSAFY)** <br/> **개발기간: 2023.09.04 ~ 2023.10.06**

## 배포 주소

> **프론트 서버** : ~~[https://i9d111.p.ssafy.io](https://i9d111.p.ssafy.io)~~ <br> **백엔드 서버** : ~~[https://i9d111.p.ssafy.io/tab](https://i9d111.p.ssafy.io/tab)~~<br> **실시간 키오스크** : ~~[https://i9d111.p.ssafy.io/kiosk/auth](https://i9d111.p.ssafy.io/kiosk/auth)~~<br> 


## 개발팀 소개

| 신지훈  | 성연석  | 이우철  |
| :--------: | :--------: | :--------: |
|[@wlgns1718](www.naver.com)|[@dbcd9351](www.naver.com)|[@lwc421](www.naver.com)|
|BackEnd| BackEnd| BackEnd|

| 이정훈  | 이제성  | 김슬기  |
| :--------: | :--------: | :--------: |
|[@wlgns1718](www.naver.com)|[@dbcd9351](www.naver.com)|[@lwc421](www.naver.com)|
|FrontEnd| FrontEnd| FrontEnd|

----
                                               

## 프로젝트 소개

아이돈케어는 부모가 자녀의 용돈을 손쉽게 관리해주는 서비스를 제공합니다. 뿐만 아니라 미션과 리워드를 통해 자녀의 금융 교육을 보조하고 활동보고서를 통해 올바른 경제 관념을 기를 수 있도록 기능을 제공하고 있습니다.

#### 자녀의 용돈관리와 경제 활동을 위해 다양한 기능을 제공합니다.

- 정기용돈 및 용돈 지급 기능
  - 부모는 서로 관계를 수락한 자녀의 정기 용돈을 등록할 수 있고, 정기 용돈 목록에서 확인할 수 있습니다.
  - 용돈을 바로 지급하거나 자녀의 용돈 요청에 의해 금액을 재설정하여 용돈을 지급할 수 있습니다.
- 미션 기능
  - 미션 카테고리를 통해 간편하게 미션을 등록할 수 있습니다.
  - 자녀가 요청한 미션에 대해 내용을 수정하거나 금액을 재설정하여 등록할 수 있습니다.
  - 미션을 제대로 수행했는지에 대한 내용을 확인할 수 있습니다.
- 활동 보고서 기능
  - 부모는 등록된 자녀의 입출금 내역을 확인할 수 있습니다.
  - 요일별, 월별 자녀의 입출금 상태를 확인할 수 있습니다.
  - 자녀가 가장 많이 소비한 활동과 지난 기간에 비해 소비 활동이 어떻게 변했는지 확인할 수 있습니다.
- 간편결제 기능
  - 자녀는 QR코드를 생성하거나 QR코드를 조회하여 간편하게 결제를 진행할 수 있습니다.

## 시작 가이드

### Requirements

For building and running the application you need:

- [Node.js 18.16.0](https://nodejs.org/ca/blog/release/v18.16.0)
- [SpringBoot 2.7.13](https://spring.io/blog/2023/06/22/spring-boot-2-7-13-available-now)
- [Java 1.8](https://www.oracle.com/kr/java/technologies/javase/javase8-archive-downloads.html)
- [MySql](https://dev.mysql.com/downloads/installer/)

### Installation

<!-- ```bash
$ git clone https://lab.ssafy.com/s09-webmobile3-sub2/S09P12D111.git
``` -->

#### Backend

```
$ cd Backend/idoncare
$ [update] build.gradle
$ cd..
$ cd openBank
$ [update] build.gradle
$ [Run TabApplication]
```

#### Frontend

```
$ cd Frontend
$ npm install
$ npm run start
```
---

## 기술스택 🛠

[![stackticon](https://firebasestorage.googleapis.com/v0/b/stackticon-81399.appspot.com/o/images%2F1700651852355?alt=media&token=58682014-4ab0-4954-b92f-802b506d8a71)](https://github.com/msdio/stackticon)

### FrontEnd
<!-- <img src="https://img.shields.io/badge/표시할이름-색상?style=for-the-badge&logo=기술스택아이콘&logoColor=white"> -->
- Node JS
- React
- TypeSCript
- Tailwind

### BackEnd
- Java
- Spring Boot
- Spring Data JPA
- Swagger-ui
- JWT
- Gradle
- JUnit5

### Deploy
- AWS EC2
- Docker
- Docker-compose
- Nginx
- jenkins

### Communication
- Git
- GitLab
- Jira
- Notion
- Mattermost
- Figma

### API 키 발급
#### Kakao developers 카카오 로그인 API 설정
- 내 애플리케이션 → 애플리케이션 추가
- 카카오 로그인 동의항목 설정, 활성화 설정
- REST API 키, Redirect URL, Client Secret 키 생성

---
## 화면 구성

## 시연 영상

---

## 아키텍쳐
![Alt text](image.png)

## 디렉토리 구조

- 프론트엔드

```bash
.
├── App.test.tsx
├── App.tsx
├── apis
│   ├── axios
│   └── url
├── assets
│   ├── fonts
│   ├── icons
│   └── imgs
├── components
│   ├── active
│   ├── common
│   ├── connect
│   ├── login
│   ├── main
│   ├── mission
│   ├── newAccount
│   ├── password
│   ├── pocketmoney
│   ├── purchase
│   ├── signup
│   └── wallet
├── constants
│   └── dummy
├── hooks
│   ├── useAccessTokenState.tsx
│   └── useComma.ts
├── index.css
├── layouts
│   └── AppLayout.tsx
├── main.tsx
├── pages
│   ├── CameraPurchase.tsx
│   ├── DemandMoneyCheck.tsx
│   ├── DonePage.tsx
│   ├── Home.tsx
│   ├── KidDemandMoney.tsx
│   ├── KidDemandMoneyList.tsx
│   ├── KidDemanedMoneyList.tsx
│   ├── KidRegist.tsx
│   ├── KidSetting.tsx
│   ├── Login.tsx
│   ├── MissionCreatPage.tsx
│   ├── MissionCreateMoney.tsx
│   ├── MissionDetailPage.tsx
│   ├── MissonPage.tsx
│   ├── MyPage.tsx
│   ├── NewAccount.tsx
│   ├── NotFound.tsx
│   ├── ParentSetting.tsx
│   ├── PocketMoney.tsx
│   ├── PocketMoneySend.tsx
│   ├── Preparing.tsx
│   ├── Purchase.tsx
│   ├── QRcodePurchase.tsx
│   ├── RegularMoneySend.tsx
│   ├── Report.tsx
│   ├── Signup.tsx
│   ├── Transfer.tsx
│   ├── TransferConfirm.tsx
│   ├── TransferSelect.tsx
│   ├── Wallet.tsx
│   ├── WalletRecharge.tsx
│   └── WalletSearch.tsx
├── routes
│   └── privateRoute.tsx
├── store
│   ├── common
│   ├── mission
│   ├── newAccount
│   ├── signup
│   └── wallet
├── types
│   ├── LayoutChildrenProps.ts
│   ├── MissionTypes.ts
│   ├── NewAccountCreateProps.ts
│   ├── PostLogin.ts
│   ├── SignupUserInfo.ts
│   ├── UserData.ts
│   └── WalletTypes.ts
└── vite-env.d.ts

```
- 백엔드

```bash
.
├── main
│   ├── java
│   │   └── d209
│   │       └── Idontcare
│   │           ├── IdontcareApplication.java
│   │           ├── account
│   │           │   ├── controller
│   │           │   ├── dto
│   │           │   ├── entity
│   │           │   ├── exception
│   │           │   ├── repository
│   │           │   └── service
│   │           ├── common
│   │           │   ├── APIBuilder.java
│   │           │   ├── ErrorHandler.java
│   │           │   ├── MutableHttpServletRequest.java
│   │           │   ├── ObjectMapper.java
│   │           │   ├── annotation
│   │           │   ├── aspect
│   │           │   ├── dto
│   │           │   ├── entity
│   │           │   ├── exception
│   │           │   └── service
│   │           ├── config
│   │           │   ├── RedisConfig.java
│   │           │   ├── SecurityConfig.java
│   │           │   ├── SwaggerConfig.java
│   │           │   └── WebConfig.java
│   │           ├── jwt
│   │           │   ├── AccessRefreshTokenDto.java
│   │           │   ├── AuthInfo.java
│   │           │   ├── JwtFilter.java
│   │           │   ├── JwtSecurityConfig.java
│   │           │   └── JwtTokenProvider.java
│   │           ├── mission
│   │           │   ├── controller
│   │           │   ├── dto
│   │           │   ├── entity
│   │           │   ├── repository
│   │           │   └── service
│   │           ├── pocketmoney
│   │           │   ├── controller
│   │           │   ├── dto
│   │           │   ├── entity
│   │           │   ├── repository
│   │           │   └── service
│   │           ├── relationship
│   │           │   ├── controller
│   │           │   ├── dto
│   │           │   ├── entity
│   │           │   ├── repository
│   │           │   └── service
│   │           └── user
│   │               ├── controller
│   │               ├── dto
│   │               ├── entity
│   │               ├── repository
│   │               └── service
│   └── resources
│       ├── application.properties
│       └── application.properties.template
└── test
    └── java
        └── d209
            └── Idontcare
                ├── IdontcareApplicationTests.java
                ├── RegularPocketTests.java
                ├── RestTests.java
                ├── UserTests.java
                └── dto
                    ├── TestBody.java
                    └── TestHeader.java

```

- 오픈뱅킹

```
.
├── main
│   ├── java
│   │   └── KFTC
│   │       └── openBank
│   │           ├── OpenBankApplication.java
│   │           ├── config
│   │           │   ├── DataLoader.java
│   │           │   ├── OpenApiConfig.java
│   │           │   ├── PostConstructor.java
│   │           │   └── WebConfig.java
│   │           ├── controller
│   │           │   └── AccountController.java
│   │           ├── domain
│   │           │   ├── Account.java
│   │           │   ├── Bank.java
│   │           │   ├── BankAccount.java
│   │           │   ├── FinTechService.java
│   │           │   ├── Mobile.java
│   │           │   ├── MobileSort.java
│   │           │   ├── PinNumber.java
│   │           │   ├── Role.java
│   │           │   ├── TransactionHistory.java
│   │           │   ├── Type.java
│   │           │   └── User.java
│   │           ├── dto
│   │           │   ├── AccountDeleteRequestDto.java
│   │           │   ├── AccountRegistRequesDto.java
│   │           │   ├── AccountVerifiRequesDto.java
│   │           │   ├── AuthRequestDto.java
│   │           │   ├── AuthResponseDto.java
│   │           │   ├── BalanceRequestDto.java
│   │           │   ├── BalanceResponseDto.java
│   │           │   ├── BankRequestDto.java
│   │           │   ├── DepositRequestDto.java
│   │           │   ├── DepositResponseDto.java
│   │           │   ├── InquiryRequestDto.java
│   │           │   ├── InquiryResponseDto.java
│   │           │   ├── MobileRequestDto.java
│   │           │   ├── PaymentDto.java
│   │           │   ├── ReceiveRequestDto.java
│   │           │   ├── ReceiveResponseDto.java
│   │           │   ├── ResList.java
│   │           │   ├── ResponseDto.java
│   │           │   ├── TransactionRequestDto.java
│   │           │   ├── TransactionResponseDto.java
│   │           │   ├── WithdrawReponseDto.java
│   │           │   └── WithdrawRequestDto.java
│   │           ├── exception
│   │           │   ├── AccountException.java
│   │           │   ├── BankAccountException.java
│   │           │   ├── MobileException.java
│   │           │   ├── TransactionHistoryException.java
│   │           │   └── UserException.java
│   │           ├── repository
│   │           │   ├── AccountRepository.java
│   │           │   ├── BankAccountRepository.java
│   │           │   ├── BankRepository.java
│   │           │   ├── FinTechServiceRepository.java
│   │           │   ├── MobileRepository.java
│   │           │   ├── TransactionHistoryRepository.java
│   │           │   └── UserRepository.java
│   │           └── service
│   │               ├── AccountService.java
│   │               ├── InitService.java
│   │               ├── MobileService.java
│   │               └── UserService.java
│   └── resources
│       ├── Data.sql
│       ├── application.properties
│       └── static
│           └── images
│               ├── BC카드.png
│               ├── KB은행.png
│               ├── KDB산업은행.png
│               ├── NH농협.png
│               ├── Sh수협은행.png
│               ├── 광주은행.png
│               ├── 금융아이콘_PNG_ABL.png
│               ├── 금융아이콘_PNG_AIA.png
│               ├── 금융아이콘_PNG_AIG.png
│               ├── 금융아이콘_PNG_AXA.png
│               ├── 금융아이콘_PNG_BNK.png
│               ├── 금융아이콘_PNG_BNP파리바.png
│               ├── 금융아이콘_PNG_BOA은행.png
│               ├── 금융아이콘_PNG_CHUBB.png
│               ├── 금융아이콘_PNG_DB.png
│               ├── 금융아이콘_PNG_HSBC.png
│               ├── 금융아이콘_PNG_IBK.png
│               ├── 금융아이콘_PNG_JP모건체이스.png
│               ├── 금융아이콘_PNG_KTB투자증권.png
│               ├── 금융아이콘_PNG_RGA.png
│               ├── 금융아이콘_PNG_SBI.png
│               ├── 금융아이콘_PNG_SC제일.png
│               ├── 금융아이콘_PNG_SK.png
│               ├── 금융아이콘_PNG_교보.png
│               ├── 금융아이콘_PNG_교보라이프플래닛.png
│               ├── 금융아이콘_PNG_대신증권.png
│               ├── 금융아이콘_PNG_도이치뱅크.png
│               ├── 금융아이콘_PNG_라이나생명.png
│               ├── 금융아이콘_PNG_메리츠.png
│               ├── 금융아이콘_PNG_메트라이프.png
│               ├── 금융아이콘_PNG_미래에셋.png
│               ├── 금융아이콘_PNG_부국증권.png
│               ├── 금융아이콘_PNG_산림조합.png
│               ├── 금융아이콘_PNG_수호천사동양생명.png
│               ├── 금융아이콘_PNG_신영증권.png
│               ├── 금융아이콘_PNG_유안타.png
│               ├── 금융아이콘_PNG_유진.png
│               ├── 금융아이콘_PNG_이베스트.png
│               ├── 금융아이콘_PNG_중국건설은행.png
│               ├── 금융아이콘_PNG_중국공상은행.png
│               ├── 금융아이콘_PNG_중국은행.png
│               ├── 금융아이콘_PNG_코리아리재.png
│               ├── 금융아이콘_PNG_키움.png
│               ├── 금융아이콘_PNG_푸르덴셜.png
│               ├── 금융아이콘_PNG_한국투자.png
│               ├── 금융아이콘_PNG_한국포스.png
│               ├── 금융아이콘_PNG_한화.png
│               ├── 금융아이콘_PNG_흥국.png
│               ├── 대구은행.png
│               ├── 롯데카드.png
│               ├── 삼성카드.png
│               ├── 새마을금고.png
│               ├── 신한은행.png
│               ├── 신협.png
│               ├── 씨티카드.png
│               ├── 우리은행.png
│               ├── 우체국예금보험.png
│               ├── 저축은행중앙회.png
│               ├── 카카오뱅크.png
│               ├── 케이뱅크.png
│               ├── 토스뱅크.png
│               ├── 하나은행.png
│               └── 현대카드.png
└── test
    └── java
        └── KFTC
            └── openBank
                └── OpenBankApplicationTests.java

```
