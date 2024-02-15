package examaple.day05.set;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Example {
    public static void main(String[] args) {
        /*// set컬렉션
            //- 저장순서 / 인덱스가 유지되지 않는다.
            //- 중복 저장할수 없다 , null 하나만 가능하다
        // set 인터페이스
            // 1. 구현클래스
                hashset
                2.사용방법/메소드
                    .add
                    .contains
            선언
                E:컬렉션에 저장할 객체의 타입
                set<E> 컬렉션명 = new 구현클래스<>();
        */
        Set<String> set = new HashSet<>();
        set.add("java");
        System.out.println("set =" +set);
        set.add("jdbc");
        System.out.println("set =" +set);
        set.add("jsp");
        System.out.println("set =" +set);
        set.add("java");
        System.out.println("set =" +set);
        set.add("aaa");
        System.out.println("set =" +set);
        // 4. 순회
            //인덱스가 없으므로 일반 for문 사용 불가능
        for(String i : set){
            System.out.println(i);
        }
        System.out.println();
        Iterator<String > rs = set.iterator();
        while(rs.hasNext()){
            System.out.println(rs.next());
        }
        System.out.println();
        // 3. 컬렉션 객체.forEach함수(반복변수명 -> 실행문)
            // iterator 지원하는 객체만 사용 가능
        set.forEach( s -> System.out.println(s));

        Set<Member> set2 = new HashSet<>();
        set2.add(new Member("홍길동",30));
        set2.add(new Member("홍길동",30));
        set2.add(new Member("홍길동",302));
        System.out.println(set2);
    }
}
