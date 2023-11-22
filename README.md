
# Project [bs-api] 
- Blog Search API

## 기능 요구사항
1. 블로그 검색
- 키워드를 통해 블로그를 검색
- 검색 결과에서 Sorting(정확도순, 최신순) 기능을 지원
- 검색 결과는 Pagination 형태로 제공
- 검색 소스는 카카오 API의 키워드로 블로그 검색(https://developers.kakao.com/docs/latest/ko/daum-search/dev-guide#search-blog)을 활용
- 추후 카카오 API 이외에 새로운 검색 소스가 추가될 수 있음을 고려

2. 인기 검색어 목록
- 사용자들이 많이 검색한 순서대로, 최대 10개의 검색 키워드를 제공
- 검색어 별로 검색된 횟수도 함께 표기

## 실행주의사항
외부호출 (Kakao, Naver) key는 실제 구동시에 환경변수로 넣어주어야 구동됩니다.

## 시스템 디자인
현재 기준으로 프로젝트가 구성된 모습
<img width="1034" alt="AS-IS" src="https://github.com/jaymarystella/bs-api/assets/151337670/2fa8634f-8e3b-481a-b9ac-359000e14f89">


## 작업 Task 목록
- BS-0
  - 에픽
- BS-1
    - 모듈 구조 나누기 (external, api)
- BS-2
    - external - kakao dev 연동 및 테스트
- BS-3
    - api서버 명세 설계
    - external 서버에서 불필요한 응답 필터
      - 불필요한 응답 태그 필터
    - 블로그 검색 API 구현
- BS-4
    - 데이터베이스 연결
    - 엔티티 설계
- BS-5
    - 통계 기능 구현 및 블로그 검색 API에 저장기능 연동
    - 인기 검색어 목록 API 구현
- BS-6
  - 네이버 블로그 API Fallback 연동 및 circuit breaker 설정
- BS-7
  - 한글 명사 키워드 추출 기능
- BS-8
  - 고가용성을 위한 설정 및 설계

## 외부라이브러리
- Feign Client
  - 통신 라이브러리
- Spock
  - 테스트 프레임워크
- commons-io
  - feign에서 body 읽을때 사용 
- springfox
  - swagger에 사용
- resilience4j
  - 서킷브레이커 적용
- KOMORAN
  - 한글 분석에 사용

## 시스템 디자인 (TO-BE)
추후 개선부분 까지 합쳐서 고려된 모습
<img width="1328" alt="TO-BE" src="https://github.com/jaymarystella/bs-api/assets/151337670/c5dd00ee-2e2f-49a7-b832-54f8116cbe89">
개선포인트
- Event발행 별도 메세지 서버 구성
- RDS에 정해진 처리량만큼 write하는 worker 서버 구성
- External Server 별도 구성
- 일 호출량 (10만) 제한을 대비한 다수 키 매니징 기능 구현
- 데이터베이스 일간 파티셔닝
- 빠른 검색 서빙을 위한 캐시레이어추가 (DDB, ES 등)
