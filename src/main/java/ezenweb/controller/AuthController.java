package ezenweb.controller;

import ezenweb.service.EmailService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    EmailService emailService;
    //세션 객체 호출
    @Autowired
    private HttpServletRequest request;
    //1.이메일 인증 요청
    @GetMapping("/email/req")
    public boolean getEmailReq(@RequestParam String email){
        System.out.println("AuthController.getEmailReq");
        System.out.println("email = " + email);
        //1.난수 이용한 6자리 인증코드 발급
        Random random = new Random();
            // 1. 난수 객체 생성
        String ecode =""; //정수 난수 생성 0도 되게 문자열로
        for (int i = 0; i < 6; i++) {
            ecode += random.nextInt(10); //0~9 : 10미만
        }

        System.out.println("ecode = " + ecode); //이메일 전송 안했을때 콘솔 보고 테스트 진행을 위해
        //2.http 세션에 특정시간만큼 인증코드 보관
            //1.세션에 속성 추가해서 발급된 인증코드 대입하기
        request.getSession().setAttribute("ecode",ecode);
            //2.세션에 생명주기
        request.getSession().setMaxInactiveInterval(100); //초단위
        //3.발급된 인증 코드를 유저의 이메일로 전송
        emailService.send(email,"EZEN WEB 인증코드" , ecode);
        return true;
    }
    //2.이메일 인증 확인
    @GetMapping("/email/check")
    public boolean getEmailcheck(String ecodeinput){
        try {
            System.out.println("AuthController.getEmailcheck");
            System.out.println("ecodeinput = " + ecodeinput);

            //1.http 세션에 보관했던 인증코드를 꺼내서
            String ecode = (String) request.getSession().getAttribute("ecode");

            //2. 입력받은 인증코드와 생성된 인증코드와의 일치여부
            // 1. 발급된 인증코드와 입력받은 인증코드와 같으면
            if(ecode.equals(ecodeinput)){
                return true;
            }
        }
        catch (NullPointerException e){
            System.out.println("E = " + e);
        }

        return false;
    }

}
