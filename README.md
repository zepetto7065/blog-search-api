# blog-search-api

-  Hexagonal Architecture

### build & deploy
```
cd bootstrap
../gradlew clean web-application:build
java -jar web-application/build/libs/web-application-1.0.0-SNAPSHOT.jar
```
### module dependency
![](http://www.plantuml.com/plantuml/png/RP1DRiOW34Jtd88Bv0fLRX9DZ5KX4cE1MnPLxzxG_BJvb9KVUcCnC5P3iOzrSKv12U_iAHVsNyvli6eM5lDGNIB1OXQFweFx_eiZG8tBoqRZUOB6dcxRl3dbKcCr5kBkeJTBIzp69FZaygj9ddn2GjvzSl_xp3bRjG9zQdH_HqDVRF33yBRNF0_X1EX591VsAtl-o49F8Bae7UH6bj0_-0S0)
- bootstrap
    - web_application : web api 관련 의존성 취합 및 bootJar 빌드 모듈
- adapter
    - web_api : spring web controller 등 사용자 요청 처리
    - persistence : 데이터 저장소 처리 
    - client_kakao : kakao open api 리소스 
    - client_naver : naver open api 리소스
- core
    - application : 핵심 비지니스 로직

### download link
- https://github.com/zepetto7065/blog-search-api/raw/main/web-application-1.0.0-SNAPSHOT.jar

### library
- lombok - Java 소스내 반복되는 라이브러리 어노테이션으로 간소화
- mapstruct - 분리된 모듈간 VO 객체 Mapping 간소화
- zalando - application/problem+json 오류 응답 형식 사용하기 위함 