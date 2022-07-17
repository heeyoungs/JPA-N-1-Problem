# JPA N+1 문제

배경
team와 member 엔티티가 있다. 둘은 ManyToOne 관계이고 Member의 정보를 findAll()로 가져오려고 한다.
그런데 testCode에서는 쿼리문 한번으로 정보를 가져오는 반면 Controller에서는 쿼리문 세개가 동작한다.

-> 여기서 N+1 문제란? 
1번 조회해야 할 것을 N개 종류의 데이터 각각을 추가로 조회하게 되서 총 N+1번 조회하게 되는 문제이다.

문제
1. 왜 testCode 에서는 쿼리문이 한개 실행되는데 controller에서는 쿼리문이 세개 실행되는 가?
2. Member 정보만 쿼리한번으로 가져오는 방법은?
3. Member-Team 정보를 쿼리 한번으로 가져오는 방법은?

답
1. 트랜잭션
testcode 에서는 Team,Member 데이터를 save하는 과정에서 hibernate 1차 캐싱이 발생한다.
즉 testCode에서의 findAll()은 같은 트랜잭션안에 있기 때문에 team정보를 알고있고 그래서 team정보를 select하지 않는다.
그러나 controller에서의 트랜잭션 범위는 findAll() 메소드 하나. 즉 team정보를 모르기 때문에 member 정보를 조회하는 과정에 team 정보도 같이 select해온다.(N+1)
2. fetch 전략
ManyToOne, OneToOne 의 기본 fetch 전략은 Eager
OneToMany, ManyToMany 의 기본 fetch 전략은 Lazy
Eager은 데이터베이스에서 정보를 한 번에 가져옴
Lazy는 자바에서 데이터베이스 내의 그 값에 접근할 때 값을 가져옴
즉 fetch 전략을 Lazy로 바꾸고 MemberDto로 값을 매핑한다.
3. EntityGraph/ Fetch Join
   1. EntityGraph 
      @NamedEntityGraph, @EntityGraph라는 어노테이션을 사용한다. 이 경우 조회시 필요한 값들을 left outer join으로 한번에 가져오게 된다.
   2. Join Fetch
      @Query 어노테이션에 쿼리를 직접 작성해 조회시 바로 가져오고 싶은 Entity 필드를 지정하는 방법이다. inner join이 사용된다.
      