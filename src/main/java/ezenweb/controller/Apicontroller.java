package ezenweb.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class Apicontroller {
    @GetMapping("")
    public String getAPi(){
        return "ezenweb/api/api";
    }
}
