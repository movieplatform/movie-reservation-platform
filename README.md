# 🎬 Movie Reservation Platform

영화 예매부터 리뷰까지 한 번에!
회원 관리, 포인트, 결제, 좌석 예약, 리뷰 기능을 모두 갖춘 영화 예매 통합 플랫폼입니다.
[🔗 배포 링크](https://app.moviescore.shop)

---

## 📆 프로젝트 개요

| 항목 | 내용 |
| ---- | ---- |
| 🧪 프로젝트명 | Movie Reservation Platform |
| ⏰ 개발 기간 | 2025.8.21 ~ 2025.10.31 |

---

<br>

## 🛠️ 기술 스택

| 구분 | 기술 |
| :---: | :--- |
| **Back-End** | <img src="https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=java&logoColor=white"> <img src="https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white"> <img src="https://img.shields.io/badge/Spring_Security-6DB33F?style=for-the-badge&logo=spring-security&logoColor=white"> <img src="https://img.shields.io/badge/JPA-59666C?style=for-the-badge&logo=hibernate&logoColor=white"> <img src="https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white"> <img src="https://img.shields.io/badge/Session_Login-FFCC00?style=for-the-badge&logo=databricks&logoColor=black"> |
| **Front-End** | <img src="https://img.shields.io/badge/React-61DAFB?style=for-the-badge&logo=react&logoColor=black"> |
| **Infra/DevOps** | <img src="https://img.shields.io/badge/AWS_EC2-FF9900?style=for-the-badge&logo=amazon-ec2&logoColor=white"> <img src="https://img.shields.io/badge/Nginx-009639?style=for-the-badge&logo=nginx&logoColor=white"> <img src="https://img.shields.io/badge/AWS_S3-569A31?style=for-the-badge&logo=amazon-s3&logoColor=white"> <img src="https://img.shields.io/badge/CloudFront-232F3E?style=for-the-badge&logo=amazonaws&logoColor=white"> <img src="https://img.shields.io/badge/AWS_RDS-527FFF?style=for-the-badge&logo=amazon-rds&logoColor=white"> <img src="https://img.shields.io/badge/GitHub_Actions-2088FF?style=for-the-badge&logo=github-actions&logoColor=white"> <img src="https://img.shields.io/badge/Route53-FF9900?style=for-the-badge&logo=amazon-route-53&logoColor=white"> <img src="https://img.shields.io/badge/SSL-003366?style=for-the-badge&logo=letsencrypt&logoColor=white"> |
| **Database** | <img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white"> |

<br>


---

## 🧩 시스템 아키텍처 (구상도)


---

🔑 주요 기능

👥 회원관리

- 회원가입: 이름, 연락처, 이메일, 생일 등 기본 정보 입력
- 로그인/로그아웃 기능 제공
- 구글 로그인 가능
- 회원 탈퇴 기능 (마이페이지에서 가능)
   * 탈퇴 시 로그아웃 처리
   * 탈퇴한 아이디는 재사용 불가 (DB 설계 및 로직 구현 필요)

🎁 포인트 시스템

- 회원가입 시 5,000 포인트 적립
- 예약 결제 시 기본 적립률(예: 결제금액의 5%)에 따른 포인트 적립

🎬 영화정보

- 장르 버튼 제공 (코미디, 공포, 액션, 드라마 등)
- 버튼 클릭 시 해당 장르의 영화 목록만 필터링해서 출력
- 영화 검색기능
- 영화 상세페이지

🪑 예약 시스템

- 상영 일정 선택
- 상영관 좌석 배치 관리 및 점유 현황

💳 결제 시스템

- 포인트 사용 후 결제
- 결제 5분 안에 미결제 시 예약 자동 취소

✍️ 리뷰 기능

- 별점 1~5점 평가
- 리뷰 최신순 / 평점순 정렬
- 비로그인 시 리뷰 작성 불가
- 홈 화면에서 리뷰 평점순으로 영화 정렬

🛠 관리자 페이지

- 회원관리: 회원 탈퇴처리
- 장르별 영화 등록
- 극장, 상영관, 좌석 생성
- 상영일정 생성
- 사용자가 문의한 내용 답변

📄 마이 페이지

- 회원정보
- 포인트 내역
- 예약 내역
- 리뷰 내역
- 문의 내역

---

## 🧱 ERD

<details open>
<summary>ERD 보기</summary>

![ERD](https://github.com/movieplatform/movie-reservation-platform/blob/main/erd.png)

🔗 [ERDCloud에서 보기](https://www.erdcloud.com/d/QbGLM37ZgdvvYHAye)

</details>

## 🚀 배포 환경

| 구성요소 | 기술 스택 | 설명 |
|-----------|------------|------|
| **서버** | AWS EC2 | Spring Boot 애플리케이션 구동 |
| **웹 서버** | Nginx | SSL 인증 및 Reverse Proxy 설정 |
| **정적 리소스** | AWS S3 + CloudFront | 프론트엔드 배포 및 CDN 캐싱 |
| **데이터베이스** | AWS RDS (MySQL 8.0) | 영화, 회원, 예약, 리뷰 등 데이터 저장 |
| **CI/CD** | GitHub Actions | dev 브랜치 푸시 시 자동 빌드 및 배포 |
| **도메인/보안** | Route53 + SSL 인증서 | HTTPS 통신 보안 |

## ⚙️ 트러블슈팅

| 주제 | 문제 상황 | 해결 방법 | 개선 결과 |
|------|------------|------------|------------|
| **동시성 제어** | 여러 사용자가 동시에 같은 좌석을 예약 | `@Transactional` + 비관적 락(`PESSIMISTIC_WRITE`) 적용 | 중복 예약 문제 해결 |
| **삭제 전략** | 데이터 참조 제약으로 인해 영화 삭제 불가 | Soft Delete 적용 및 `is_deleted` 플래그 추가 | 무결성 유지 및 안전한 삭제 가능 |
| **검색 성능 개선** | LIKE 검색 시 속도 저하 | Full-Text Index / B-Tree 비교 및 검증 | 검색 속도 최대 3배 개선 |

## 🎥 시연 영상
[🔗 시연 영상 바로가기](https://youtu.be/xxxxxxxx)
