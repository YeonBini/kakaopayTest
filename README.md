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
* Method : POST
* URL : http://:your_server_url/signUp
* Content-Type : application/json; charset=utf-8
* Parameters : UserSignInInfo
  - "username" : username
  - "password" : password
  
* 예제
  curl -X POST \
  http://localhost:8080/user/signUp \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: cec88ddc-6c75-4e85-8eea-44cda2295612' \
  -H 'cache-control: no-cache' \
  -d '{
	"username" : "yeonbn", 
	"password" : "1234"
}'
  
  

