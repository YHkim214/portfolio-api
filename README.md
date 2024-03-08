# 포트폴리오 프로젝트: HoloBoard

## 기획의도
- **Youtube Data API v3**를 활용하여 실시간 방송목록을 수집, 제공
- **JWT인증**을 구현한 회원제 시스템
- 실시간 방송 별 게시판 생성 및 커뮤니티 기능 제공

## 작업인원 및 기간
- 작업인원 : 1명
- 작업기간 : 2023-12-18 ~ 2024-02-16

## 사용기술

### Back-End
- JAVA(Open Jdk 21)
- Spring Boot 3.2
- Mybatis(*JPA로 변경 작업 중)
- Spring Security
- MY-SQL

### Front-End
- React.js

### TOOLS
- IntelliJ
- DBeaver
- Postman

### 프로젝트 구성도

![KakaoTalk_Image_2024-02-26-14-04-41](https://github.com/YHkim214/portfolio-api/assets/41041713/9efd3527-2ba8-4204-b759-eacfacf7aa4b)

### 데이터 베이스 설계
![스크린샷 2024-03-08 오전 11 21 51](https://github.com/YHkim214/portfolio-api/assets/41041713/fce36ee3-a26c-4c49-88bb-3c0b694a74e3)

### 주요 화면 구성

#### 홈 화면
당일 날짜를 기준으로 진행 예정 or 진행 중인 실시간 스트리밍 정보를 카드 형식으로 보여주는 페이지

![스크린샷 2024-02-22 오전 8 27 14](https://github.com/YHkim214/portfolio-cli/assets/41041713/237d15f0-c968-4374-ac68-0e752c5298fe)

#### 실시간 방송 
해당 방송을 볼 수 있도록 유튜브 Iframe을 제공. 더불어 방송의 통계정보(최대 시청자, 평균시청자, 좋아요 수)를 제공. 우측에는 방송 별 게시판기능 제공

![스크린샷 2024-02-22 오전 8 28 37](https://github.com/YHkim214/portfolio-cli/assets/41041713/5d269dd3-f49d-4e54-bc9c-1ff046bd8c67)

#### 회원가입 및 로그인 화면
![스크린샷 2024-02-22 오전 8 27 48](https://github.com/YHkim214/portfolio-cli/assets/41041713/e0660494-61d4-414b-a6ae-3fc8f037fba4)
![스크린샷 2024-02-22 오전 8 27 59](https://github.com/YHkim214/portfolio-cli/assets/41041713/c4ade443-58f4-43f0-990b-c73d828dfe38)

