package examaple.day11.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value="/day11")
public class RestController3 {
    // HTTP 이용한 매개변수 보내는 방법
        //1. 경로상의 변수 http://localhost/day11/black/value
        //2. 쿼리스트링 변수 http://localhost/day11/black/req=value
    // 1. get : get localhost:80/day11/black
    @GetMapping("/red")
    public String  getred(String reqMSG) throws IOException {
        System.out.println("RestController2.getWhite");
        //요청
        System.out.println("reqMSG = " + reqMSG);
        //응답
        return "안녕[클라이언트]";
    }
    // 2. post
    @PostMapping("/red")
    public Map<String,String> postred(HttpServletRequest req , HttpServletResponse resp) throws IOException{
        System.out.println("RestController2.postWhite");
        String reqMSG = req.getParameter("reqMSG");
        System.out.println("reqMSG = " + reqMSG);
        //응답
        //string[] a = new string[2];
        //a[0] = "안녕";
        //a[1] = "자바";
//        ArrayList<String> a = new ArrayList<String >();
//        a.add("안녕");
//        a.add("자바");
        Map<String ,String > a = new HashMap<>();
        a.put("안녕","자바");
        return a;
    }
    // 3. put
    @PutMapping("/red")
    public int putred(HttpServletRequest req) throws IOException{
        System.out.println("RestController2.putWhite");
        String reqMSG = req.getParameter("reqMSG");
        System.out.println("reqMSG = " + reqMSG);
        //응답
        return 10;
    }
    // 4. delete
    @DeleteMapping("/red")
    public boolean deletered(HttpServletRequest req) throws IOException{
        System.out.println("RestController2.deleteWhite");
        String reqMSG = req.getParameter("reqMSG");
        System.out.println("reqMSG = " + reqMSG);
        //응답
        return true;
    }
}
