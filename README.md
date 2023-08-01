# :pushpin: RGT 기술 과제

## 1. 사용 기술 스택
 - Spring Boot 2.7.14
 - JPA
 - MySQL 5.7
 - Java 11
 - OAuth 2.0
 - thymeleaf

## 2. 세부 사항
**1. MySQL UTF-8 설정**</br>
![image](https://github.com/kimjungwon2/rgt-delivery/assets/40010165/41446248-d8d7-4899-9956-b7e97fd7081d)
</br>DB의 한글 깨짐을 방지하기 위해 MySQL에 기본적으로 UTF-8을 설정해야 한다.

**2. 테스트 코드**</br>
테스트 코드로 어떤 메소드인지 확인할 수 있다.

## 3. RESTful API 작성
### 3.1 오류 발생
**1. 잘못된 HTTP 요청**</br>
![image](https://github.com/kimjungwon2/rgt-delivery/assets/40010165/2bc3abd0-b208-4588-a533-6effd493431f)
</br>잘못된 HTTP 요청을 하면 위의 결과가 나오게끔 처리.

**2. Bind 에러 처리**</br>
![image](https://github.com/kimjungwon2/rgt-delivery/assets/40010165/cbb66d93-8634-48bf-84dc-b74e62425946)
</br>Quantity를 음수로 하는 등. 요청의 조건이 이상하면 위의 결과가 나오게끔 만들었다.

**3. 모든 에러 처리**</br>
![image](https://github.com/kimjungwon2/rgt-delivery/assets/40010165/cf453b41-1763-4948-a288-db3c5ef16ab6)
</br>이외에 에러들은 위의 결과가 나오게끔 만들었다.

## 4. 중복 데이터 처리 및 데이터 수정
### 4.1. 중복 처리
- Order_id를 PK로 설정. 

- Pk 이외에도 유니크 키 제약조건으로 중복이 불가능하게 저장 가능하다.

### 4.2. 데이터 수정

 - '/api/modify' URI에 put 요청을 보내면 '카페테리아' -> '카페라떼'로 변경.
   
 - 저장 시에 Service 패키지의 'renameCafeteria' 메서드로 자동으로 '카페라떼' 상품명으로 변경하게 했다.

**1. 기존**</br>
![image](https://github.com/kimjungwon2/rgt-delivery/assets/40010165/3620561b-c07e-4ede-955f-6e32aff0e7a3)

**2. 수정**</br>
![image](https://github.com/kimjungwon2/rgt-delivery/assets/40010165/b5d4e533-90e9-416c-b26e-4d8169057fa7)


## 5. Google OAuth 2.0 로그인
thymeleaf로 localhost:8080에서 기본적인 HTML을 구현하여 OAuth 2.0을 확인할 수 있다. 

### 5.1. 결과 화면
**1. 초기 화면 & DB 데이터** </br>
![image](https://github.com/kimjungwon2/rgt-delivery/assets/40010165/d35194bf-824f-4ee0-8ae5-dcd46e328eff)
![image](https://github.com/kimjungwon2/rgt-delivery/assets/40010165/7c621b16-3003-4b71-b931-ef1ccbfdcb27)

**2. 로그인 화면** </br>
![image](https://github.com/kimjungwon2/rgt-delivery/assets/40010165/cb398e79-200d-4175-ad31-5f0270aab7ba)

**3. 로그인 완료** </br>
![image](https://github.com/kimjungwon2/rgt-delivery/assets/40010165/b80d18e6-059b-44bd-bd72-809eb0efb735)

**4. DB 상태** </br>
![image](https://github.com/kimjungwon2/rgt-delivery/assets/40010165/137fa9c1-ec2c-41d9-b196-298fba2322a0)

**5. 세션 상태** </br>
![image](https://github.com/kimjungwon2/rgt-delivery/assets/40010165/6b2f2e01-1ffa-4ae9-9a5c-4483b4317e4e)


### 5.2. USER 권한
**1. /move/user URI는 USER 권한만 접근 가능.** </br>
![image](https://github.com/kimjungwon2/rgt-delivery/assets/40010165/b716b84b-b7e9-4c6e-80a1-35abf6fe72fa)

**2. 기존 권한은 GUEST 권한이다.** </br>
![image](https://github.com/kimjungwon2/rgt-delivery/assets/40010165/7c07f03a-c407-4704-b3e2-585a44eba3e0)


**3. USER 권한만 들어가는 페이지(USER 권한만 들어가는 URI)에 접근할 수 없다.** </br>
![image](https://github.com/kimjungwon2/rgt-delivery/assets/40010165/1dacfd82-1c9d-40ac-9efb-160ec399aa3d)
![image](https://github.com/kimjungwon2/rgt-delivery/assets/40010165/124dd52b-57dd-4c17-9688-0f735badf242)

**4. 직접 SQL로 USER 권한으로 변경.** </br>
![image](https://github.com/kimjungwon2/rgt-delivery/assets/40010165/f28c30bb-5faf-463c-9ca1-b5c0a90fafe0)

**5. USER 권한 변경 후 접근.** </br>
![image](https://github.com/kimjungwon2/rgt-delivery/assets/40010165/d2a6aeca-4f24-4c07-9aa2-dfb5a794b4ec)

세션에는 이미 GUEST 정보로 저장이 돼서 로그아웃 후 'USER 권한 사용자만 이동' 링크에 들어가게 하면, 정상적으로 들어갔음을 확인할 수 있다.

### 5.3. 세션 저장소
세션 저장소로 아무 설정을 안 하면 내장 톰캣의 메모리에 저장되는데 이러면 아래의 문제가 발생한다.

**1. WAS 실행 시 세션 초기화** </br>
메모리에 저장돼서 배포할 때마다 톰캣이 재시작하기 때문이다.

**2. 2대 이상의 WAS가 세션 공유가 안 된다.** </br>
이 때문에 DBMS나 Redis 같은 메모리 DB를 사용한다.


이로 인해 **session 저장소로 JDBC**를 사용하였다.

![image](https://github.com/kimjungwon2/rgt-delivery/assets/40010165/0b330183-6521-4446-95a8-1687cec9f558)

DB에서 spring_session, spring_session_attributes 테이블 생성.

![image](https://github.com/kimjungwon2/rgt-delivery/assets/40010165/fd78eb3d-9b56-428f-8bae-6ed12ae06548)
로그인 시 spring_session_attributes 테이블에서 위의 데이터 생성.

![image](https://github.com/kimjungwon2/rgt-delivery/assets/40010165/dc95db9c-d7b2-4da8-8744-b518c4b0409e)

로그아웃하면 세션의 DB 데이터들이 사라진 걸 볼 수 있다.
