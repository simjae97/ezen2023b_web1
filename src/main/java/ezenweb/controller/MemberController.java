package ezenweb.controller;

import ezenweb.model.dao.MemberDAO;
import ezenweb.model.dto.LoginDTO;
import ezenweb.model.dto.MemberDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigInteger;
import java.util.Objects;

@Controller
public class MemberController {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    MemberDAO memberDAO;
    //1단계: V<---->C 사이의 http 통신방식 설계
    //2단계: Controller mapping 함수 선언하고 통신 체크(API Tester)
    //3단계: Controller request 매개변수 이용
    //1============회원가입 처리 요청==========
    @PostMapping("/member/signup")
    @ResponseBody
    public boolean  doPostsignup(MemberDTO memberDTO){
        /*
        {id:"아이디",pw:"비밀번호",name:"이름",email:"이메일",phone:"전화번호",img:"프로필사진"}
         */
        System.out.println("MemberController.signup");
        System.out.println("memberDTO = " + memberDTO);
        boolean result = memberDAO.doPostsignup(memberDTO); // dao처리
        return result;
    }

    //2==========로그인 처리 요헝 ==========
    @PostMapping("/member/login")
    @ResponseBody
    public boolean doPostlogin(LoginDTO loginDTO){
        System.out.println("MemberController.login");
        System.out.println("loginDTO = " + loginDTO);
        int result = memberDAO.dologin(loginDTO); // dao처리
        // 로그인 성공시 (세션 )
            // 세션 저장소: 톰캣서버에 브라우저 마다의 메모리 할당
            // 1.http 요청 객체 호출 . HttpServletRequest
            // 2.Http 세션 객체 호출 .getSession()
            // 3.Http 세션 데이터 저장 .setAtrribute("세션명","값") ------- 오브젝트로 업캐스팅 당함
        if ( result != 0 ){
            request.getSession().setAttribute("loginDto", loginDTO.getId());
            return true;
        }
        return false;
    }// f end
    //2-2 =============로그인 여부 확인 요청 ==============
    @GetMapping("/member/login/check")
    @ResponseBody
    public String dogetloginchek(){
      //로그인 여부 확인 = 세션이 있다 없다 확인
        // 세션 저장소: 톰캣서버에 브라우저 마다의 메모리 할당
        // 1.http 요청 객체 호출 . HttpServletRequest
        // 2.Http 세션 객체 호출 .getSession()
        // 3.Http 세션 데이터 호출 .getAtrribute("세션명") ------ 원래 쓰던 타입으로 다운캐스팅 필요
        Object a = request.getSession().getAttribute("loginDto");
        String loginDto = null;
        if(a != null) {
            loginDto = (String) request.getSession().getAttribute("loginDto");
        }
        //만약 로그인 했으면 ( 세션에 데이터가 있으면)
        return loginDto;

    }
    @GetMapping("/member/logout")
    @ResponseBody
    public boolean dogetlogout(){
        //1. 로그인 세션 초기화
            //1.모든 세션 초기화
        request.getSession().invalidate(); // 모든 세션 초기화
//            //2.특정 세션 초기화
//        request.getSession().setAttribute("loginDto",null);
        return true;
        //로그아웃성공시 대부분 인덱스 로 돌아가야함 -> 인덱스 반환 혹은 responsebody해서 js에서 로케이션
    }
    //3=======회원가입 페이지 요청=================
    @GetMapping("/member/signup")
    public String viewSignup(){
        System.out.println("MemberController.viewSignup");
        return "/ezenweb/signup";
    }


    @GetMapping("/member/login")
    public String viewLogin(){
        System.out.println("MemberController.Login");
        return "/ezenweb/login";
    }

    @GetMapping("/user/{no}")
    public String viewnowlogin(@PathVariable int no, Model model){
        System.out.println("MemberController.viewSignup");
        model.addAttribute("no",no);
        return "/ezenweb/nowlogin";//추후 마이페이지 구현
    }

    @GetMapping("/user/{no}/edit")
    public String viewedit(@PathVariable int no, Model model){
        model.addAttribute("no",no);
        return "/ezenweb/edit";//추후 수정페이지 구현
    }

    @PostMapping("/user/edit")
    public String doEdit(MemberDTO memberDTO){
        /*
        {name:"이름",email:"이메일",phone:"전화번호",img:"프로필사진"}
         */
        System.out.println("MemberController.signup");
        System.out.println("memberDTO = " + memberDTO);
        boolean result = true; // dao처리
        if(result){
            return "redirect:/user/"+memberDTO.getNo();
        }
        return "";
    }

    @GetMapping("/user/{no}/del")
    public String doDelete(@PathVariable int no){
        System.out.println(no+"삭제");
        boolean result = true; // dao처리
        //Dao결과물 보냄
        if (result){
            return "redirect:/member/login";
        }
        return "";
    }

}
