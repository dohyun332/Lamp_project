package com.lamp.app;
/**
 1) pom.xml 파일 설정
 - MVN repo 에서 MyBatis 검색  >  'MyBatis' 랑 MyBatis Spring' 모듈 두개 필요함 > pom 파일에 넣고 업데이트 하기
 2) web.xml 파일 한글 필터 설정
 - 이거 없으면 게시판에서 한글 이상하게 나옴
 3) root-context.xml 파일 설정 (아래 두개 bean 등록)
 - <bean id="sqlSessionFactory"></bean>
 - <bean id="sqlSession"></bean>
 4) servlet-context.xml 설정
 - 맨 아래 <view-controller path="/" view-name="index"/> 넣기
 - 이거 넣으면 HomeController 랑 Home.jsp 필요없음.
 5) "mybatis-config.xml" 생성
 - main > resources 폴더에 "mybatis-config.xml" 만들기
 => 여기는 별칭 정하는곳
 - main > resources 폴더에 mapper 만들고 boardMapper.xml 파일 만들기
 => 여기에 모든 sql 문들을 다 넣을거임

 --------------------------------------------------------------------------------------------------
 1) 각 view 파일들 (index, loginForm, registerForm, registerInfo, board) ch2꺼 복사해오기
 2) css 파일들도 다 복사해오기
 3) com.bitstudy.app 밑에 'controller', 'dao', 'domain' 패키지 생성
 4) 'controller' 패키지에 LoginController 만들기
 5) userDao 등록 (dao)
 - LoginController 에서 UserDao 빨간색 뜨는거 선택 후 'Create class UserDao' 선택
 - 창 뜨면 'Destination Package' 에 'com.bitstudy.app.dao' 입력하면 폴더랑 파일 생성될거임.
 6) User 클래스 만들기 (domain)
 - UserDao 만들면 또 User 없다고 빨간색 뜰거임
 - 3번때처럼 'Create class 어쩌고 선택'
 - 창 뜨면 'Destination Package' 에 'com.bitstudy.app.domain' 입력하면 폴더랑 파일 생성될거임.
 7) dao 패키지에 UserDaoImpl 만들기
 8) 각 view 파일들 (index, loginForm, registerForm, registerInfo, board) 만들기
 9) 톰캣 연결하기
 10) 게시판용 데이터베이스 만들기
 create table board
 (
 bno         int auto_increment primary key,
 title       varchar(50)                        not null,
 content     text                               not null,
 writer      varchar(50)                        not null,
 view_cnt    int      default 0                 null,
 comment_cnt int      default 0                 null,
 reg_date    datetime default CURRENT_TIMESTAMP null,    // now() 로 입력하기
 `update`    datetime default CURRENT_TIMESTAMP null     // now() 로 입력하기
 );
 11) Mapper.xml 안에 내용 채우기(해야 하는데 DTO 개념 알아야 해서 나중에 채울거임)

 *최종적으로
 controller 패키지 안에는 LoginController , RegisterController 있으면 되고
 dao 패키지 않에는 BoardDao, userDao, UserDaoImple 있으면 되고
 domain 패키지 않에는 BoardDto, User, UserLogin 있으면 됨

 
 12) DB에 가짜 데이터들 몇개 넣어놓기
 
 13) dao 폴더에 BoardDao 만들기
 14) domain 폴더에 BoardDto 만들기
 */