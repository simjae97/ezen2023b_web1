package ezenweb.controller;

import ezenweb.model.dao.MemberDAO;
import ezenweb.model.dto.LoginDTO;
import ezenweb.model.dto.MemberDTO;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigInteger;

@Controller
public class MemberController {

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
        boolean result = true; // dao처리
        return result;
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
