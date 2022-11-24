# 게시판 만들기

---
### 프로젝트 설정
 - gradle
 - h2 DB 1.4.200
 - spring boot 2.7.5
 - java 11
---
### 자세한 내용
https://magicmk.tistory.com/category/Java/spring%20%EA%B2%8C%EC%8B%9C%ED%8C%90
<br/>
위 블로그에 기술

---
### 진행 상황
 - 전체
   - url security 적용 (완료)
   - authorize 적용 (완료)
   - thymeleaf layout 적용 (완료)
   - bootstrap 적용 (완료)
 - 회원
   - 간단한 회원가입 (email, username, password)
     - 패스워드 암호화 (완료)
     - 이메일 중복체크, 유효성 검사 (완료)
   - 조회
     - 회원 목록 (완료)
     - 회원 조회 (완료)
   - 로그인 / 로그아웃
     - spring security login (완료)
   - 회원 정보
     - 정보 수정 (username 완료, password 완료)
     - 회원 탈퇴 (완료)
 - 게시물
   - 게시물 조회
   - 게시물 작성
   - 게시물 정보
   