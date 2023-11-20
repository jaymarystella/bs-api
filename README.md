
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

## 외부라이브러리
- Feign Client
  - 통신 라이브러리
- Spock
  - 테스트 프레임워크
- commons-io
  - feign에서 body 읽을때 사용 
- resilience4j
  - 서킷브레이커 적용

    
