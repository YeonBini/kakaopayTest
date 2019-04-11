# kakaopayTest

## 1. 개발환경
* IntelliJ
* Spring Boot 2.1.4
* Gradle
* Lombok
* h2
* jwt
* querydsl
* swagger

## 2. api 명세 

### 2-1. 회원인증 및 token 발급

#### 2-1-1. 회원가입, signUp : 회원가입 후 토큰을 생성해주는 api 
* Method : POST
* URL : http://:your_server_url/user/signUp
* Content-Type : application/json; charset=utf-8
* Parameters : UserSignInInfo
  - "username" : username
  - "password" : password
  
* 예제
```
curl -X POST \
  http://localhost:8080/user/signUp \
  -H 'Content-Type: application/json' \
  -d '{
	"username" : "yeonbn", 
	"password" : "1234"
}'
```  
* response
```
{
    "result": "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJjb3VudCI6MSwidXNlcm5hbWUiOiJ5ZW9uYm4ifQ.7V4M9N4JyLqlhcepxceTAUzdQHRmkhw1zJshRmw70Zx0zmRceaKwSubYb7fB1DiF8d6YjKThhzWi6xc2xvGk9A"
}
```
#### 2-1-2. 로그인, signIn : 로그인 후 토큰을 리턴하는 api 
* Method : GET
* URL : http://:your_server_url/user/signIn
* Content-Type : application/json; charset=utf-8
* Parameters : UserSignInInfo
  - "username" : username
  - "password" : password
* 예제 
```
curl -X GET \
  http://localhost:8080/user/signIn \
  -H 'Content-Type: application/json' \
  -d '{
	"username" : "yeonbn", 
	"password" : "1234"
}'
```
* response
```
{
    "result": "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJjb3VudCI6MSwidXNlcm5hbWUiOiJ5ZW9uYm4ifQ.7V4M9N4JyLqlhcepxceTAUzdQHRmkhw1zJshRmw70Zx0zmRceaKwSubYb7fB1DiF8d6YjKThhzWi6xc2xvGk9A"
}
```
#### 2-1-3. 토큰 재발급 : 토큰을 재발급하는 api 
* Method : GET
* URL : http://:your_server_url/user/refreshToken
* Content-Type : application/json; charset=utf-8
* header 
  - Authorization : {{token}}
* 예제
```
curl -X PUT \
  http://localhost:8080/user/refreshToken \
  -H 'Authorization: eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJjb3VudCI6MiwidXNlcm5hbWUiOiJ5ZW9uYm4ifQ.5jTo4TpPsIcoMM6xLUBAhSewTFTBg9_0LYyQdzarvDOlAVA6cqWEpnZsMOYwPxrkpPiBiWKEtoCDbXVcqbhGOw' 
```
* response 
```
{
    "result": "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJjb3VudCI6MywidXNlcm5hbWUiOiJ5ZW9uYm4ifQ.TtK_mU6T8rN4t6ZSf19KTc28Pd8J7LSjsZFM5QC1k4oebert3VgFS_d6wfjtwfD_p1lik9x99iRY8TtM8JOTxg"
}
```

### 2-2. 은행정보 api

#### 2-2-1. 초기 은행 csv 데이터 세팅하는 api
* Method : POST
* URL : http://:your_server_url/bank/init
* Content-Type : application/json; charset=utf-8
* header 
  - Authorization : {{token}}
* 예제
```
curl -X POST \
  http://localhost:8080/bank/init \
  -H 'Authorization: eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJjb3VudCI6MSwidXNlcm5hbWUiOiJ5ZW9uYm4ifQ.7V4M9N4JyLqlhcepxceTAUzdQHRmkhw1zJshRmw70Zx0zmRceaKwSubYb7fB1DiF8d6YjKThhzWi6xc2xvGk9A' 
```
* response
```
{
    "result": [
        {
            "createdDate": "2019-04-11T23:30:40.096105",
            "modifiedDate": "2019-04-11T23:30:40.096105",
            "id": 1,
            "year": 2005,
            "month": 1,
            "instituteName": "주택도시기금1",
            "instituteCode": "B000",
            "funds": 1019
        },
        {
            "createdDate": "2019-04-11T23:30:40.112006",
            "modifiedDate": "2019-04-11T23:30:40.112006",
            "id": 2,
            "year": 2005,
            "month": 1,
            "instituteName": "국민은행",
            "instituteCode": "B001",
            "funds": 846
        }, ...
	]
}
```
#### 2-2-2. 은행 리스트를 리턴하는 api
* Method : GET
* URL : http://:your_server_url/bank/list
* Content-Type : application/json; charset=utf-8
* header 
  - Authorization : {{token}}
* 예제
```
curl -X GET \
  http://localhost:8080/bank/list \
  -H 'Authorization: eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJjb3VudCI6MSwidXNlcm5hbWUiOiJ5ZW9uYm4ifQ.7V4M9N4JyLqlhcepxceTAUzdQHRmkhw1zJshRmw70Zx0zmRceaKwSubYb7fB1DiF8d6YjKThhzWi6xc2xvGk9A' 
```
* response
```
{
    "result": [
        "국민은행",
        "기타은행",
        "농협은행/수협은행",
        "신한은행",
        "외환은행",
        "우리은행",
        "주택도시기금1",
        "하나은행",
        "한국시티은행"
    ]
}
```
#### 2-2-3. 연도 별 각 금융기관 지원금액 합계
* Method : GET
* URL : http://:your_server_url/bank/info
* Content-Type : application/json; charset=utf-8
* header 
  - Authorization : {{token}}
* 예제
```
curl -X GET \
  http://localhost:8080/bank/info \
  -H 'Authorization: eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJjb3VudCI6MSwidXNlcm5hbWUiOiJ5ZW9uYm4ifQ.7V4M9N4JyLqlhcepxceTAUzdQHRmkhw1zJshRmw70Zx0zmRceaKwSubYb7fB1DiF8d6YjKThhzWi6xc2xvGk9A' 
```
* response 
```
{
    "data": [
        {
            "detail_amount": {
                "하나은행": 3122,
                "농협은행/수협은행": 1486,
                "우리은행": 2303,
                "국민은행": 13231,
                "신한은행": 1815,
                "외환은행": 1732,
                "주택도시기금1": 22247,
                "한국시티은행": 704,
                "기타은행": 1376
            },
            "year": 2005,
            "total_amount": 48016
        },
        {
            "detail_amount": {
                "하나은행": 3443,
                "농협은행/수협은행": 2299,
                "우리은행": 4134,
                "국민은행": 5811,
                "신한은행": 1198,
                "외환은행": 2187,
                "주택도시기금1": 20789,
                "한국시티은행": 288,
                "기타은행": 1061
            },
            "year": 2006,
            "total_amount": 41210
        },
        ...
        {
            "detail_amount": {
                "하나은행": 35629,
                "농협은행/수협은행": 26969,
                "우리은행": 38846,
                "국민은행": 31480,
                "신한은행": 40729,
                "외환은행": 0,
                "주택도시기금1": 85409,
                "한국시티은행": 7,
                "기타은행": 36057
            },
            "year": 2017,
            "total_amount": 295126
        }
    ],
    "name": "주택금융 공급현황"
}
```
#### 2-2-4. 연도 별 각 금융기관 지원금액 중 가장 큰 금액의 기관명 출력
* Method : GET
* URL : http://:your_server_url/bank/largest
* Content-Type : application/json; charset=utf-8
* header 
  - Authorization : {{token}}
* parameter
  - "year" : {{year}}
* 예제
```
curl -X GET \
  'http://localhost:8080/bank/largest?year=2005' \
  -H 'Authorization: eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJjb3VudCI6MSwidXNlcm5hbWUiOiJ5ZW9uYm4ifQ.7V4M9N4JyLqlhcepxceTAUzdQHRmkhw1zJshRmw70Zx0zmRceaKwSubYb7fB1DiF8d6YjKThhzWi6xc2xvGk9A' \
```
* response
```
{
    "bank": "주택도시기금1",
    "year": 2005
}
```
#### 2-2-5. 외환은행의 지원금액 평균중 가장 작은 금액과 큰 금액 출력
* Method : GET
* URL : http://:your_server_url/bank/foreignBank
* Content-Type : application/json; charset=utf-8
* header 
  - Authorization : {{token}}
* 예제
```
curl -X GET \
  http://localhost:8080/bank/foreignBank \
  -H 'Authorization: eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJjb3VudCI6MSwidXNlcm5hbWUiOiJ5ZW9uYm4ifQ.7V4M9N4JyLqlhcepxceTAUzdQHRmkhw1zJshRmw70Zx0zmRceaKwSubYb7fB1DiF8d6YjKThhzWi6xc2xvGk9A' 
```
* response
```
{
    "bank": "외환은행",
    "support_amount": [
        {
            "amount": 941,
            "year": 2008
        },
        {
            "amount": 20421,
            "year": 2015
        }
    ]
}
```
