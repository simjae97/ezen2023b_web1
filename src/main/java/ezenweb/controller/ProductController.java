package ezenweb.controller;


import ezenweb.model.dto.ProductDTO;
import ezenweb.service.MemberService;
import ezenweb.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;
    @Autowired
    private HttpServletRequest request;

    @Autowired
    MemberService memberService;
    // 1. 등록 서비스/기능처리 요청
    @PostMapping("/register.do")
    @ResponseBody
    public boolean postProductRegister(ProductDTO productDTO){
        System.out.println("ProductController.poostProductRegister");

//        Object object = request.getSession().getAttribute("loginDto");
//        if(object == null) return false;
//        productDTO.setMno( (memberService.doGetLoginInfo( (String)object) ).getNo() );
        productDTO.setMno(1);
        return productService.postProductRegister(productDTO);

    }
    // 2. 제품 출력(지도) 요청
    @GetMapping("/list.do")
    @ResponseBody
    public List<ProductDTO> getProductList(){
        System.out.println("ProductController.getProductList");

        return productService.getProductList();
    }
    //3.해당 제품의 찜하기 등록 // 로그인했고 찜하기 버튼 클릭시 매개변수:pno , 리턴 : boolean
    @GetMapping("/plike.do")
    @ResponseBody
    public boolean getPlikeWrite(int pno){
        Object object = request.getSession().getAttribute("loginDto");
        if(object == null) return false;
        int mno =  (memberService.doGetLoginInfo( (String)object) ).getNo();
        return productService.getPlikeWrite(pno,mno);
    }
    //4.해당 제품의 찜하기 상태 출력 // 로그인 했고 찜하기 버튼 출력시 ,매개변수:pno ,리턴 boolean
    @PostMapping("/plike.do")
    @ResponseBody
    public boolean getPlikeView(int pno){
        Object object = request.getSession().getAttribute("loginDto");
        if(object == null) return false;
        int mno =  (memberService.doGetLoginInfo( (String)object) ).getNo();
        return productService.getPlikeView(pno,mno);
    }
    //5.해당 제품의 찜하기 취소/삭제 // 로그인했고 찜하기 버튼 클릭시,매개변수:pno ,리턴 boolean
    @DeleteMapping("/plike.do")
    @ResponseBody
    public boolean getPlikeDelete(int pno){
        Object object = request.getSession().getAttribute("loginDto");
        if(object == null) return false;
        int mno =  (memberService.doGetLoginInfo( (String)object) ).getNo();
        return productService.getPlikeDelete(pno,mno);
    }
    //1.등록 페이지/화면/뷰 요청
    @GetMapping("register")
    public String productRegister(){
        return "ezenweb/product/register";
    }
    //2.제품 지도 페이지/화면/뷰 요청
    @GetMapping("/list")
    public String productList(){
        return "ezenweb/product/list";
    }
}
