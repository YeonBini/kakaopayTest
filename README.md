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

#### 2-1-1. 회원가입, signUp
##### 회원가입 후 토큰을 생성해주는 api 
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

