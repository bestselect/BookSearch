# BookSearch

> A Book Search project
> git URL : https://github.com/bestselect/BookSearch

# Installation

* [vue 2.1](https://vuejs.org/)
* [spring boot 1.5.14](https://spring.io/guides/gs/spring-boot/)
    * [apache derby 10.13.1.1](https://db.apache.org/derby/quick_start.html)
    * [tomcat 8.5.31](http://tomcat.apache.org/)
    * [JPA 2.1](https://projects.spring.io/spring-data-jpa/)
* [webpack](https://webpack.js.org/)
* [git](https://git-scm.com/)
* [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* [Apache Maven 3.5.3](https://maven.apache.org/docs/3.5.3/release-notes.html)


* 참조
    - [vue.js 및 webpack의 자세한 가이드](http://vuejs-templates.github.io/webpack/)
    - [vue.js loader의 자세한 가이드](http://vuejs.github.io/vue-loader).
    - [spring boot & vue.js 연동 예제](https://github.com/jonashackt/spring-boot-vuejs)

# 프로젝트 구조
```$xslt
BookSearch
├─┬ backend     → backend module 및 Spring Boot 코드
│ ├── src
│ └── pom.xml
├─┬ frontend    → frontend module 및 Vue.js 코드
│ ├── src
│ └── pom.xml
└── pom.xml     → 프로젝트 Maven parent pom 

```

## 첫 실행

```
git clone https://github.com/bestselect/BookSearch.git
cd BookSearch
mvn clean && mvn install
mvn --projects backend spring-boot:run
```

이제 http://localhost:8080/ 에서 프로젝트를 만날수 있어요.

