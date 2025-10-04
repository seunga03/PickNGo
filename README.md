# ✈️ PickNGo

**콘솔 기반 여행지 정보 조회 프로그램**  
회원 관리 · 즐겨찾기 · 댓글 기능을 통해 여행 데이터를 손쉽게 탐색할 수 있는 Java 콘솔 애플리케이션입니다.  
도메인 모델링, RDB 설계, JDBC 연동, 레이어드 아키텍처 등 백엔드 핵심 개념을 실습했습니다.

>  멀티캠퍼스 백엔드 개발자 부트캠프  
>  1조 _가족같은_ : 김용원 · 김광호 · 김영관 · 이승아

---

## 🧩 프로젝트 개요

### 목표
- CSV 기반 여행지 데이터를 RDB(MySQL)로 마이그레이션  
- JDBC로 연동하여 CRUD 기능 구현  
- 콘솔 기반 MVC 구조로 빠른 탐색 및 관리 기능 제공  
- **GUI 없이도 데이터 탐색 흐름 완성** (CLI 환경 최적화)

### 배경
- GUI 부담 없이 **도메인 중심 설계 + 데이터 처리 로직**에 집중  
- 제한된 기간 내 핵심 기능 완주: 검색, 페이징, 즐겨찾기, 댓글  
- 실무 수준의 **RDB 스키마·쿼리·서비스 계층 분리** 연습

---

## 🎨 유스케이스

<img width="3704" height="2924" alt="image" src="https://github.com/user-attachments/assets/44345168-3a12-4109-b06a-ac244175d94a" />

---

## 🚀 주요 기능

|        구분        | 주요 기능                            | 상세 설명                              |
| :--------------- | :------------------------------- | :--------------------------------- |
|    **여행지 조회**  | 전체 보기 · 권역별 필터 · 키워드 검색 · 인기순 정렬 | 페이지네이션과 정렬 기능을 통해 다양한 기준으로 여행지를 탐색 |
|     **즐겨찾기**   | 등록 · 조회 · 인기순 통계                 | `(user_id, no)` 중복 방지로 데이터 정확성 유지  |
|      **댓글**    | 등록 · 조회                          | 각 여행지별 사용자 댓글 관리 기능 제공             |
|    **회원 관리**   | 회원가입 · 로그인 · 로그아웃 · 마이페이지 수정     | 세션 유지 및 DB 재조회로 최신 사용자 정보 반영       |
|  **관리자 기능** | CSV 대량 등록 · 여행지 CRUD · 에러 처리     | CSV 파싱 및 벌크 INSERT로 효율적인 데이터 관리 지원 |


---

## 🧱 시스템 구조

**MVC Layered Architecture**

```
업로드 예정
```

- **JDBC + PreparedStatement** 로 SQL 인젝션 방어  
- **query.properties** 파일을 통한 SQL 분리  
- **UserSession** 클래스로 로그인 상태 유지  

---

## 🗄 DB 설계 (ERD)

<img width="1272" height="878" alt="image" src="https://github.com/user-attachments/assets/7ad6c1b8-d46c-4b89-ac4b-bee5149dbb76" />


> ERD 상 USERS ↔ LIKES ↔ TRAVELS ↔ COMMENTS 관계를 통해 사용자 행동 데이터 추적 가능

---

## 💬 개인 코드 리뷰 요약

### 🧑🏻‍💻 김용원
- 전체·권역별·검색·인기순 조회 기능 구현  
- **페이지네이션 재사용 구조** 설계 (하나의 로직으로 4개 기능 통합)  

### 🧑🏻‍💻 김광호
- 관리자 기능 (CSV 대량 등록, CRUD, 입력 검증, 예외 처리)  
- 예외 발생 시 프로그램 종료 없이 **안내 후 복귀** 로직 개선  

### 🧑🏻‍💻 김영관
- 댓글 등록/조회, 즐겨찾기 등록 기능 구현  
- UserSession을 활용한 사용자–여행지 key 전달  

### 👩🏻‍💻 이승아
- 인트로 메뉴 분기, 회원가입, 로그인, 마이페이지(조회·수정·로그아웃)
- **세션 갱신 로직** 강화, **생성자 주입**으로 순환 참조 방지

---

## ▶️ 실행 방법

1. **환경 준비**
   - JDK 17 이상
   - MySQL 8.x
   - IDE: IntelliJ IDEA or Eclipse

2. **DB 설정**
   ```sql
   CREATE DATABASE pickngo DEFAULT CHARACTER SET utf8mb4;
   USE pickngo;
   ```

3. **SQL 매핑 확인**
   - `resources/query.properties` 파일의 SQL 키 확인
   - DB 접속 정보(`JDBCConnect.java`) 수정

4. **실행**
   - `App.java` 실행  
   - 콘솔 메뉴를 통해 로그인/탐색/등록 기능 사용 가능

---

## 📜 라이선스
본 프로젝트는 [MIT License](./LICENSE) 하에 배포됩니다.

---

> “**PickNGo는 콘솔만으로도 탐색 → 기록 → 여정까지 연결되는 사용자 경험을 실현한 프로젝트입니다.**  
> 단순한 JDBC 학습을 넘어, 팀 협업과 객체 설계의 중요성을 체득했습니다.”

![Java](https://img.shields.io/badge/Java-17+-orange)
![Build](https://img.shields.io/badge/Build-JDBC%20Project-blue)
![DB](https://img.shields.io/badge/DB-MySQL-blue)
![License](https://img.shields.io/badge/License-MIT-green)

