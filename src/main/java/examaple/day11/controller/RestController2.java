package examaple.day11.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class RestController2 {
    // HTTP 이용한 매개변수 보내는 방법
        //1. 경로상의 변수 http://localhost/day11/black/value
        //2. 쿼리스트링 변수 http://localhost/day11/black/req=value
    // 1. get : get localhost:80/day11/black
    @RequestMapping(value="/day11/white" ,method = RequestMethod.GET)
    @ResponseBody //string은 text/plain
    public String  getWhite(HttpServletRequest req) throws IOException {
        System.out.println("RestController2.getWhite");
        //요청
        String reqMSG = req.getParameter("reqMSG");
        System.out.println("reqMSG = " + reqMSG);
        //응답
        return "안녕[클라이언트]";
    }
    // 2. post
    @RequestMapping(value = "/day11/white", method = RequestMethod.POST)
    @ResponseBody //나머지는 json형식
    public Map<String,String> postWhite(HttpServletRequest req , HttpServletResponse resp) throws IOException{
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
    @RequestMapping(value = "/day11/white", method = RequestMethod.PUT)
    @ResponseBody
    public int putWhite(HttpServletRequest req) throws IOException{
        System.out.println("RestController2.putWhite");
        String reqMSG = req.getParameter("reqMSG");
        System.out.println("reqMSG = " + reqMSG);
        //응답
        return 10;
    }
    // 4. delete
    @RequestMapping(value = "/day11/white" ,method = RequestMethod.DELETE)
    @ResponseBody
    public boolean deleteWhite(HttpServletRequest req) throws IOException{
        System.out.println("RestController2.deleteWhite");
        String reqMSG = req.getParameter("reqMSG");
        System.out.println("reqMSG = " + reqMSG);
        //응답
        return true;
    }
}
