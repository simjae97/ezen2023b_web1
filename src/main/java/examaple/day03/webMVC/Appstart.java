package examaple.day03.webMVC;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication  //스프링부트 어노테이션
//1.내장서버(톰캣) 실행
//2.동등한 패키지 혹은 하위 패키지 내
// @controller @RestController들을 스캔
public class Appstart {

    public static void main(String[] args) {
        //스프링 시작
        SpringApplication.run(Appstart.class);
    }

}