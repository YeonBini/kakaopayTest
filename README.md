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
* URL : http://:your_server_url/signUp
* Content-Type : application/json; charset=utf-8
* Parameters : UserSignInInfo
  - "username" : username
  - "password" : password
  
* 예제
```
curl -X POST \
  http://localhost:8080/user/signUp \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: cec88ddc-6c75-4e85-8eea-44cda2295612' \
  -H 'cache-control: no-cache' \
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
* URL : http://:your_server_url/signIn
* Content-Type : application/json; charset=utf-8
* Parameters : UserSignInInfo
  - "username" : username
  - "password" : password
* 예제 
```
curl -X GET \
  http://localhost:8080/user/signIn \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 27f2249a-08a1-4eb5-9bba-9d004c14031a' \
  -H 'cache-control: no-cache' \
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
* URL : http://:your_server_url/signIn
* Content-Type : application/json; charset=utf-8
* header 
  - Authorization : {{token}}
* 예제
```
curl -X PUT \
  http://localhost:8080/user/refreshToken \
  -H 'Authorization: eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJjb3VudCI6MiwidXNlcm5hbWUiOiJ5ZW9uYm4ifQ.5jTo4TpPsIcoMM6xLUBAhSewTFTBg9_0LYyQdzarvDOlAVA6cqWEpnZsMOYwPxrkpPiBiWKEtoCDbXVcqbhGOw' \
  -H 'Postman-Token: 9eb902b0-45a7-4880-9675-f68fd935198b' \
  -H 'cache-control: no-cache'
```
* response 
```
{
    "result": "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJjb3VudCI6MywidXNlcm5hbWUiOiJ5ZW9uYm4ifQ.TtK_mU6T8rN4t6ZSf19KTc28Pd8J7LSjsZFM5QC1k4oebert3VgFS_d6wfjtwfD_p1lik9x99iRY8TtM8JOTxg"
}
```
