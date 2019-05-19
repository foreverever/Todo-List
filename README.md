# TODO list 만들기

## 요구사항
### 기능 요구사항

    ○ 새로운 TODO(제목과 내용)를 작성할 수 있다.
    ○ TODO 목록을 볼 수 있다.
    ○ TODO 항목의 제목과 내용을 수정할 수 있다.
    ○ TODO 항목을 삭제할 수 있다.
    ○ 사용자의 선택에 의해 TODO에는 마감 기한을 넣을 수 있다.
    ○ TODO 항목의 우선순위를 설정 및 조절할 수 있다.
    ○ TODO 항목에 대한 완료 처리를 할 수 있다.
    ○ 마감기한이 지난 TODO에 대해 알림을 노출할 수 있다.

### 성능 요구사항

    ○ TODO 이용 시 발생하는 오류 사항을 최소화한다.
    ○ 오류 발생 시 사용자가 이해하기 쉽게 표시한다.
    ○ 다른 사람이 읽기 쉬운 코드를 작성한다.
    ○ HTML/CSS에서 사용할 수 있는 최신 구조와 기술을 사용한다.

### 인터페이스 요구사항

    ○ 직관적이고 의미 전달이 명확한 화면을 사용자에게 제공한다

### Entitiy 설계

- Todo
~~~
title : String
contents : String
priority : String
completed : boolean
expired : boolean
deadline : LocalDateTime

update()
complete()
updateExpired()

AbstractEntity에게 상속 받음
~~~

- AbstractEntity
~~~
id : Long
createdDate : LocalDateTime
modifiedDate : LocalDateTime

FormattedDate()
~~~

### Step
1. Todo 엔티티 생성

2. Todo CURD 기능 생성 (unitTest 진행)

3. Controller & Restcontroller & Service 구성

4. Ajax 기능 구현

5. 예외처리 구현

### Environment
- java 8 (Intellij)
- Gradle
- Junit
- SpringBoot 2.1.4 RELEASE
- JPA (Hibernate)
- JQuery
- H2 Database


### AWS 웹 서버 설치 및 빌드 (Ubuntu)

1. 아파치 설치
~~~
sudo apt-get update sudo
apt-get install apache2
~~~

2. 자바 설치
~~~
sudo apt-get install openjdk-8-jdk
~~~

3. github 프로젝트 clone
~~~
git clone -b master --single-branch https://github.com/foreverever/Todo-List
~~~

4. bulid 실행 (test패키지 제외)
~~~
/Todo-List$ ./gradlew build -x test

(gradlew-permission-denied 뜰 경우 : chmod +x gradlew 입력 후 실행)
~~~

5. jar 실행

~~~
/Todo-List/build/libs$ java -jar Todo-List-0.0.1-SNAPSHOT.jar
~~~

### URL

<http://ec2-54-180-2-193.ap-northeast-2.compute.amazonaws.com:7080>



