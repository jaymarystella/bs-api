
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

    
