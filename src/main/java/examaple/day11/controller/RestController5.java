package examaple.day11.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController()
@RequestMapping("/day11")
public class RestController5 {
//    @PostMapping("/ajax5")
//    private String ajax5(int id , @RequestParam String content){
//        System.out.println("id = " + id + ", content = " + content);
//        return "응답";
//    }
//    @PostMapping("/ajax5")
//    private String ajax5(@RequestParam Map<String ,String > map) {
//        System.out.println(map);
//        return "응답";
//    }
    @PostMapping("/ajax5")
    private String ajax5(AjaxDTO map) {
        System.out.println(map);
        return "응답";
    }
//
    @PostMapping("/ajax6")
    private String ajax6(@RequestBody  AjaxDTO map) {
        System.out.println(map);
        return "응답";
    }
//    @PostMapping("/ajax6")
//    private String ajax6(@RequestBody int id ,  String content) {
//        System.out.println("id = " + id);
//        System.out.println("content = " + content);
//        return "응답";
//    } 안됨 항상 묶음처리로 보내야함
}
