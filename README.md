# RxJava 세미나 + 그 외

### 1. 그 외 먼저

- Lombok 을 알아보자

    - [Project Lombok](https://projectlombok.org/)
    - [Gradle 정보](http://search.maven.org/#artifactdetails%7Corg.projectlombok%7Clombok%7C1.16.14%7Cjar)

- Lambda 를 알아보자

    - Java 8 이전 버전에서 Lambda를 쓰는 방법
    - [retrolambda](https://github.com/evant/gradle-retrolambda)
    - [Gradle 정보](http://search.maven.org/#artifactdetails%7Cme.tatarka%7Cgradle-retrolambda%7C3.5.0%7Cjar)


### 2. 본 편

- Reactive Extension

    - [ReactiveX](http://reactivex.io/)

- RxJava (v1.x)

    - [RxJava](https://github.com/ReactiveX/RxJava)
    - a library for composing **asynchronous** and **event-based** programs using
      **observable sequences** for the Java VM
    - [Gragle 정보 - RxJava 1.x](http://search.maven.org/#artifactdetails%7Cio.reactivex%7Crxjava%7C1.2.7%7Cjar)

- Android + RxJava

    - [RxAndroid](https://github.com/ReactiveX/RxAndroid)

- Android + RxJava + RxBinding

    - [RxBinding](https://github.com/JakeWharton/RxBinding)

- RxJava 2.x

    - [Gradle 정보 - RxJava 2.x](http://search.maven.org/#artifactdetails%7Cio.reactivex.rxjava2%7Crxjava%7C2.0.6%7Cjar)

----

# Reference

- Links

    - [[TD 2015] 반응형(Reactive) 응용프로그램 아키텍처](https://channel9.msdn.com/Events/TechDays/TDK2015/T3-6)
    - [RxJava 1.x Javadoc](http://reactivex.io/RxJava/1.x/javadoc/)
    - [RxJava 2.x Javadoc](http://reactivex.io/RxJava/2.x/javadoc/)
    - [RxMarbles](http://rxmarbles.com/)

- RxJava 2.x

    - [What's different in 2.0](https://github.com/ReactiveX/RxJava/wiki/What's-different-in-2.0)
  ( [번역본](http://realignist.me/code/2017/01/25/rxjava2-changelog.html) )
    - package : io.reactivex
    - Flowable, Single, Completable, Maybe 등 추가 => 용도에 맞는 스트림을 사용할 것
    - Java8 네이밍 센스에 맞췄음
    - 기타등등
