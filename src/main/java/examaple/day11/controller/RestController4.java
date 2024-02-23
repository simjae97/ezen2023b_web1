package examaple.day11.controller;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/day11") //해당 클래스 내 매핑함수의 공통 url
public class RestController4 {

    @GetMapping("/ajax1")
    public String ajax1(){
        System.out.println("RestController4.ajax1");
        return "응답";
    }

    @GetMapping("/ajax2/{id}/{content}")
    public String ajax2(@PathVariable int id, @PathVariable("content") String content2){
        System.out.println("RestController4.ajax2");
        System.out.println("id = " + id + ", content = " + content2);
        return "아작스2"+content2+id;
    }
    //RequestParam
//    @GetMapping("/ajax3")
//    public String ajax3(int id, @RequestParam("content") String content2){
//        System.out.println("RestController4.ajax3");
//        System.out.println("id = " + id + ", content = " + content2);
//        return content2+id;
//    }

    //getParameter
//    @GetMapping("/ajax3")
//    public String ajax3(HttpServletRequest req){
//        System.out.println("RestController4.ajax3");
//        int id = Integer.parseInt(req.getParameter("id"));
//        String content2 = req.getParameter("content");
//        System.out.println("id = " + id + ", content = " + content2);
//        return "아작스3"+content2+id;
////    }
//    @GetMapping("/ajax3")
//    public String ajax3(@RequestParam Map<String ,String > map){
//        System.out.println("map = " + map);
//        return ""+map;
//    }

    @GetMapping("/ajax3")
    public String ajax3(AjaxDTO map){
        System.out.println("map = " + map);
        return ""+map;
    }
    //http 본문 전송
//    @GetMapping("/ajax4")
//    public String ajax4(int id , String content){
//        System.out.println("idcontent = " + id + content);
//        return ""+id+content;
//    }
//    @GetMapping("/ajax4") //param안하면 오류
//    public String ajax4(@RequestParam Map<String ,String > map){
//        System.out.println("map = " + map);
//        return ""+map;
//    }
    @GetMapping("/ajax4")
    public String ajax4(AjaxDTO map){
        System.out.println("map = " + map);
        return ""+map;
    }
}
