### 각 모듈 요약 설명

1. httpClient: Spring Webclient 를 조금 더 편하게 쓰기 위한 Wrapper
2. testContainer: mysql, redis, localstack을 테스트 컨테이너를 활요하여 테스트를 용이하게 하기 위함
3. openapi-diff: 두 개의 openapi json,yml 비교하여 md와 html로 차이점을 추출해줌
4. holiday: 영업일 관련 대체휴무일, 휴무일 등 날짜 관련 연산 서비스
5. image: 미디어센터를 통해 등록되는 이미지를 편하게 등록,조회하는 서비스
6. querydsl-util: querydsl을 koltin infix를 이용하여 예쁘게 만들어줌
7. src/main: 항상 하지만 까먹기 쉬운 샘플들
8. restdoc-openapi: spring restdoc의 결과물인 아스키독을 openapi spec으로 변환해준다
9. address: 주소 검색 후 우편번호 및 상세 정보를 반환하는 서비스
10. video: 비디오 관련 스트리밍, 업로드 후 저장 서비스 
11. stringTemplate: 문자열 템플릿을 이용하여 문자열을 생성하는 서비스