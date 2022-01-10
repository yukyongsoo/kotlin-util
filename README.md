# Vertical Service Lab 백엔드 공통 모듈

---
### 신규 버전 배포 방법

1. 각 모듈에 들어있는 build.gradle.kts 의 version 필드의 값을 올립니다.
   1. 안 올리시면 덮어써서 다른분들이 많이 화낼 수 있습니다. 
2. gradle :moduleName:clean :moduleName:publish 를 수행합니다. 

배포가 S3에서 이루어 지므로 반드시 aws credential 이 필요합니다 

---
### 각 모듈 요약 설명

1. httpClient: Spring Webclient 를 조금 더 편하게 쓰기 위한 Wrapper
2. testContainer: mysql, redis, localstack을 테스트 컨테이너를 활요하여 테스트를 용이하게 하기 위함
3. openapi-diff: 두 개의 openapi json,yml 비교하여 md와 html로 차이점을 추출해줌
4. testObject: 테스트 시에 객체를 직접 생성하지 않고 랜덤한 값이 입력된 객체 생성
5. holiday: 영업일 관련 대체휴무일, 휴무일 등 날짜 관련 연산 서비스
6. image: 미디어센터를 통해 등록되는 이미지를 편하게 등록,조회하는 서비스