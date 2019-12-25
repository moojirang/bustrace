# bustrace

## 요구사항

요구사항을 여기에 남겨 놓아요

## 개발환경 관련

### MongoDB 관련

#### MongoDB 설치

- mongoDB 도커 설치 및 실행하기
    - docker run -p 27017:27017 --name mongo_boot -d mongo
- mongoDB 도커 구동 및 접근
    - docker start mongo_boot
    - docker stop mongo_boot
    - docker attach mongo_boot
    - docker exec -i -t mongo_boot bash
    - root@d26475076d57:/# mongo

#### MongoDB 기본 사용법

- mongodb 실행
    - mongod --dbpath "DATA=PATH"
    - mongo
- 현재 사용중인 데이터베이스 확인
    - db
- 신규 데이터베이스 생성
    - use DATABASE_NAME
- 내가 만든 데이터베이스들 확인
    - show dbs
    - 참고) 데이터베이스에 최소 1건의 데이터가 들어 있어야 show dbs 실행 시 목록에 노출 됨
- 아래 사이트 참조
    - https://nicewoong.github.io/development/2018/02/10/mongodb-tutorial/
    
## 서버배포 

- Maven > bustrace > Lifecycle > package 실행
- ftp로 war copy & past
- ssh 아이디@IP <-- 접속
- docroot로 이동
- 기존 프로세스 kill -9 pid
- 쉘 실행

## 참조URL

- [메뉴얼] http://www.gbis.go.kr/gbis2014/publicService.action?cmd=mBusRoute
- [WSDL] http://openapi.gbis.go.kr/ws/busrouteservice?wsdl=BusRouteInterface.wsdl
