package examaple.day10;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan //서블릿을 찾겠다
public class SpringBootStart {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootStart.class);

    }
}

