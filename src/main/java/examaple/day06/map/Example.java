package examaple.day06.map;

import java.util.*;

public class Example {
    public static void main(String[] args) {

        //1. map 컬렉션 생성
        Map<String , Integer> map = new HashMap<>(); // 제네릭 타입은 기본타입 불가능 따라서 래핑클래스 이용

        //2. 컬렉션 객체에 객체(엔트리{키:값}쌍) 넣기

        map.put("신용권",85);
        map.put("홍길동",90);
        map.put("동장군",80);
        map.put("홍길동",95); //key의 값이 중복일경우 value가 업데이트된다

        System.out.println(map);
        
        //3. 키로 값 얻기
        System.out.println(map.get("홍길동"));
        
        //4. 키로 엔트리 삭제 
        map.remove("홍길동");
        System.out.println(map);
        
        // 순회: 인덱스없음
        // keySet() : 모든 키를 셋 컬렉션으로 반환
        Set<String > keySet = map.keySet();
        for(String key : keySet){
            System.out.println("key = " + key);
            System.out.println("map.get(key) = " + map.get(key));
        }

        Set<Map.Entry<String , Integer>> entrySet = map.entrySet();
        Iterator<Map.Entry<String ,Integer>> entryIterator = entrySet.iterator();
        while (entryIterator.hasNext()){
            Map.Entry<String ,Integer> entry = entryIterator.next();
            System.out.println(entry);
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }

        for(Map.Entry<String,Integer> entry : map.entrySet()){
            System.out.println(entry);
        }

        for(Integer value : map.values()){
            System.out.println(value);
        }
        System.out.println("dd");
        map.entrySet().forEach(key -> System.out.println(key.getValue()+""+key.getKey()));
        System.out.println("총 엔트리 개수");
        System.out.println(map.size());

        // p.663=======================================//
        //1.properties 객체 : 키값 쌍 둘다 string인 해시테이블
        Properties properties = new Properties();
        properties.setProperty("driver","com.mysql.cj.jdbc.Driver");
        System.out.println(properties.getProperty("driver"));
        properties.setProperty("url","jdbc:mysql://localhost:3306/springweb");
        properties.setProperty("admin","root");
        properties.setProperty("password","1234");

        System.out.println(properties.getProperty("driver"));
        System.out.println(properties.getProperty("url"));
        System.out.println(properties.getProperty("admin"));
        System.out.println(properties.getProperty("password"));

    }
}
