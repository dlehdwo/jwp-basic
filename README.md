#### 1. Tomcat 서버를 시작할 때 웹 애플리케이션이 초기화하는 과정을 설명하라.
* 먼저 Tomcat이 ServletContext을 생성한다.
* ServletContext가 ServletContextListener를 살펴보고 초기화를 진행한다.
* 우리 예제에서는 ContextLoaderListener에서 contextInitialized 메서드를 실행시킨다.
* 그럼 jwp.sql 파일을 실행해 DB가 초기화된다.
* 이후 최초 클라이언트 요청시 DispatcherServlet 인스턴스가 생성된다.
* init() 메서드를 실행해서 초기화한다.

#### 2. Tomcat 서버를 시작한 후 http://localhost:8080으로 접근시 호출 순서 및 흐름을 설명하라.
* 

#### 7. next.web.qna package의 ShowController는 멀티 쓰레드 상황에서 문제가 발생하는 이유에 대해 설명하라.
* 
