# holiday

영업일 관련 대체휴무일, 휴무일 등 날짜 관련 연산 서비스 ( 예정 )

## sample

test 코드 쪽에 사용방법이 쓰여 있습니다.

## 주의사항

1. json 파싱을 위한 라이브러리는 제공되지 않습니다.

기본적으로 `jackson-module-kotlin` 라이브러리가 등록되어 있다면 잘 동작합니다.

<br>

2. 기본적 dataProvider로 공공데이터 API를 사용하고 있습니다. 

https://www.data.go.kr/tcs/dss/selectApiDataDetailView.do?publicDataPk=15012690

이 중 공휴일 조회 API를 사용하고 있으니 encoding되지 않은 키를 입력하여 사용해주시기 바랍니다.

**API의 기한은 요청일로부터 2년입니다.**

<br>

3. 공공데이터 API의 기본 하루 요청허용량은 10000건 입니다. 

여러 서버가 사용하시는 경우 cacheProvider를 구현하여 미들웨어에 넣어 쓰시길 바랍니다. 

기본 프로바이더는 in Memory를 사용하고 있습니다.